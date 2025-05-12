package br.com.scandianx.fastdev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.scandianx.fastdev.model.Favorito;
import br.com.scandianx.fastdev.service.interfaces.FavoritosService;
import br.com.scandianx.fastdev.view.FavoritoDTO;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/favoritos")
public class FavoritoController {

    @Autowired
    private FavoritosService favoritosService;

    @PostMapping("/{videoId}")
    public ResponseEntity<String> adicionar(@PathVariable Long videoId, HttpServletRequest request) {
        FavoritoDTO favorito = favoritosService.adicionarFavorito(request, videoId);
        if (favorito == null) {
            return ResponseEntity.badRequest().body("Ocorreu um erro ao favoritar o vídeo");
        }
        return ResponseEntity.ok("O vídeo foi favoritado com sucesso!");
    }

    @GetMapping
    public ResponseEntity<List<FavoritoDTO>> listar(HttpServletRequest request) {
        List<FavoritoDTO> favoritos = favoritosService.listarPorUsuario(request);
        return ResponseEntity.ok(favoritos);
    }

    @DeleteMapping("/{videoId}")
    public ResponseEntity<String> remover(@PathVariable Long videoId, HttpServletRequest request) {
        favoritosService.removerFavorito(request, videoId);
        return ResponseEntity.ok("O vídeo foi desfavoritado com sucesso!");
    }
}
