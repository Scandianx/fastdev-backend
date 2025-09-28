package br.com.scandianx.fastdev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.scandianx.fastdev.dto.FavoritoDTO;
import br.com.scandianx.fastdev.service.interfaces.FavoritosService;
import jakarta.servlet.http.HttpServletRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.ProblemDetail;

@RestController
@RequestMapping("/api/v1/favoritos")
@Tag(name = "Favoritos", description = "CRUD de favoritos do usuário")
public class FavoritoController {

    @Autowired
    private FavoritosService favoritosService;

    @PostMapping("/{videoId}")
    @Operation(summary = "Adicionar favorito")
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
                schema = @Schema(implementation = ProblemDetail.class)))
    })
    public ResponseEntity<FavoritoDTO> adicionar(@PathVariable Long videoId, HttpServletRequest request) {
        FavoritoDTO favorito = favoritosService.adicionarFavorito(request, videoId);
        java.net.URI location = java.net.URI.create("/api/v1/favoritos/" + videoId);
        return ResponseEntity.created(location).body(favorito);
    }

    @GetMapping
    @Operation(summary = "Listar favoritos do usuário")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "OK")
    })
    public ResponseEntity<List<FavoritoDTO>> listar(HttpServletRequest request) {
        List<FavoritoDTO> favoritos = favoritosService.listarPorUsuario(request);
        return ResponseEntity.ok(favoritos);
    }

    @DeleteMapping("/{videoId}")
    @Operation(summary = "Remover favorito")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Removido"),
        @ApiResponse(responseCode = "401", description = "Não autenticado",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = ProblemDetail.class))),
        @ApiResponse(responseCode = "404", description = "Favorito não encontrado",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = ProblemDetail.class)))
    })
    public ResponseEntity<Void> remover(@PathVariable Long videoId, HttpServletRequest request) {
        favoritosService.removerFavorito(request, videoId);
        return ResponseEntity.noContent().build();
    }
}
