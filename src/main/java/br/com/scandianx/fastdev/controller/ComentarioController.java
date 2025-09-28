package br.com.scandianx.fastdev.controller;

import br.com.scandianx.fastdev.dto.ComentarioDTO;
import br.com.scandianx.fastdev.dto.ComentarioRequestDTO;
import br.com.scandianx.fastdev.service.interfaces.ComentarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.ProblemDetail;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;




import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Comentários", description = "CRUD de comentários de vídeos")
public class ComentarioController {

    @Autowired
    private ComentarioService comentarioService;

    @PostMapping("/videos/{videoId}/comentarios")
    @Operation(summary = "Criar comentário em um vídeo")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Criado"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "401", description = "Não autenticado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "404", description = "Vídeo não encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "422", description = "Regra de negócio violada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class)))
    })
    public ResponseEntity<ComentarioDTO> criar(@PathVariable Long videoId,
                                               @RequestBody @Valid ComentarioRequestDTO dto,
                                               HttpServletRequest request) {
        ComentarioDTO created = comentarioService.adicionarComentario(request, videoId, dto.texto());
        URI location = URI.create(String.format("/api/v1/comentarios/%d", created.id()));
        return ResponseEntity.created(location).body(created);
    }

    @GetMapping("/videos/{videoId}/comentarios")
    @Operation(summary = "Listar comentários de um vídeo")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Vídeo não encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class)))
    })
    public ResponseEntity<List<ComentarioDTO>> listarPorVideo(@PathVariable Long videoId) {
        List<ComentarioDTO> list = comentarioService.listarComentariosPorVideo(videoId);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/comentarios/{comentarioId}")
    @Operation(summary = "Obter um comentário por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Comentário não encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class)))
    })
    public ResponseEntity<ComentarioDTO> obter(@PathVariable Long comentarioId) {
        ComentarioDTO dto = comentarioService.obterComentario(comentarioId);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/comentarios/{comentarioId}")
    @Operation(summary = "Atualizar um comentário por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "401", description = "Não autenticado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "403", description = "Sem permissão",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "404", description = "Comentário não encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class)))
    })
    public ResponseEntity<ComentarioDTO> atualizar(@PathVariable Long comentarioId,
                                                   @RequestBody @Valid ComentarioRequestDTO dto,
                                                   HttpServletRequest request) {
        ComentarioDTO updated = comentarioService.atualizarComentario(request, comentarioId, dto.texto());
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/comentarios/{comentarioId}")
    @Operation(summary = "Remover um comentário por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Removido"),
            @ApiResponse(responseCode = "401", description = "Não autenticado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "403", description = "Sem permissão",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = org.springframework.http.ProblemDetail.class))),
            @ApiResponse(responseCode = "404", description = "Comentário não encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class)))
    })
    public ResponseEntity<Void> deletar(@PathVariable Long comentarioId,
                                        HttpServletRequest request) {
        comentarioService.removerComentario(request, comentarioId);
        return ResponseEntity.noContent().build();
    }
}
