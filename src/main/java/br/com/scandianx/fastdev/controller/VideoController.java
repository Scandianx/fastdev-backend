package br.com.scandianx.fastdev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import br.com.scandianx.fastdev.dto.VideoRequestDTO;
import br.com.scandianx.fastdev.dto.VideoDTO;
import br.com.scandianx.fastdev.components.Converter;
import br.com.scandianx.fastdev.entity.Usuario;
import br.com.scandianx.fastdev.entity.VideoAbstrato;
import br.com.scandianx.fastdev.repository.impl.VideoRepositoryImpl;
import br.com.scandianx.fastdev.service.interfaces.AuthorizationService;
import br.com.scandianx.fastdev.service.interfaces.VideoService;
import jakarta.servlet.http.HttpServletRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import jakarta.persistence.EntityNotFoundException;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/videos")
@Tag(name = "Vídeos", description = "Operações com vídeos (criar e listar permitidos)")
@Validated
public class VideoController {

    @Autowired
    private VideoService videoService;
    @Autowired
    private Converter converter;

    @PostMapping
    @Operation(summary = "Criar vídeo")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Criado"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "401", description = "Não autenticado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "422", description = "Regra de negócio violada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão pra criar um vídeo", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class)))
    })
    public ResponseEntity<VideoAbstrato> criarVideo(@RequestBody @jakarta.validation.Valid VideoRequestDTO dto) {
        VideoAbstrato video = videoService.criarVideo(dto);
        java.net.URI location = java.net.URI.create("/api/v1/videos/" + (video.getId() != null ? video.getId() : ""));
        return ResponseEntity.created(location).body(video);
    }

    @GetMapping
    @Operation(summary = "Listar vídeos permitidos ao usuário")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK")
    })
    public List<VideoAbstrato> listarPermitidos(HttpServletRequest request) {
        return videoService.listarPermitidos(request);
    }

    @GetMapping("/search")
    @Operation(summary = "Buscar vídeos por título (apenas permitidos)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Parâmetro inválido", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class)))
    })
    public ResponseEntity<List<VideoAbstrato>> buscarPorTitulo(
            @RequestParam("titulo") @NotBlank(message = "titulo obrigatório") @Size(min = 2, max = 120, message = "titulo deve ter entre 2 e 120 caracteres") String titulo,
            HttpServletRequest request) {
        List<VideoAbstrato> lista = videoService.listarPermitidosPorTitulo(titulo, request);
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/top")
    @Operation(summary = "Listar vídeos mais favoritados (apenas permitidos)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Parâmetro inválido", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class)))
    })
    public ResponseEntity<List<VideoAbstrato>> listarMaisFavoritados(
            @RequestParam(name = "limit", defaultValue = "10") @Min(value = 1, message = "limit mínimo é 1") @Max(value = 100, message = "limit máximo é 100") int limit,
            HttpServletRequest request) {
        List<VideoAbstrato> lista = videoService.listarMaisFavoritados(limit, request);
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obter vídeo por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Vídeo não encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class)))
    })
    public ResponseEntity<VideoDTO> obterPorId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(converter.toDTO(videoService.obterPorId(id)));
        } catch (jakarta.persistence.EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar vídeo por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "404", description = "Vídeo não encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class)))
    })
    public ResponseEntity<VideoAbstrato> atualizar(@PathVariable Long id,
            @RequestBody @jakarta.validation.Valid VideoRequestDTO dto) {
        try {
            VideoAbstrato atualizado = videoService.atualizarVideo(id, dto);
            return ResponseEntity.ok(atualizado);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remover vídeo por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Removido"),
            @ApiResponse(responseCode = "404", description = "Vídeo não encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = org.springframework.http.ProblemDetail.class)))
    })
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            videoService.deletarVideo(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
