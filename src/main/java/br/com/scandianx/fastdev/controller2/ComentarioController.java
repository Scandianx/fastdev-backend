package br.com.scandianx.fastdev.controller2;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.scandianx.fastdev.dto.ComentarioDTO;
import br.com.scandianx.fastdev.dto.ComentarioRequestDTO;
import br.com.scandianx.fastdev.service.interfaces.ComentarioService;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/comentarios")
public class ComentarioController {

    @Autowired
    private ComentarioService comentarioService;

    @PostMapping("/{videoId}")
    public ResponseEntity<ComentarioDTO> adicionar(@PathVariable Long videoId, @RequestBody ComentarioRequestDTO dto, HttpServletRequest request) {
        ComentarioDTO response = comentarioService.adicionarComentario(request, videoId, dto.texto());
        if (response == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{videoId}")
    public ResponseEntity<List<ComentarioDTO>> listar(@PathVariable Long videoId) {
        List<ComentarioDTO> comentarios = comentarioService.listarComentariosPorVideo(videoId);
        return ResponseEntity.ok(comentarios);
    }

    @DeleteMapping("/{comentarioId}")
    public ResponseEntity<String> remover(@PathVariable Long comentarioId, HttpServletRequest request) {
        comentarioService.removerComentario(request, comentarioId);
        return ResponseEntity.ok("Comentário excluido com sucesso");
    }
    @PutMapping("/{comentarioId}")
    public ResponseEntity<ComentarioDTO> atualizar(@PathVariable Long comentarioId,
                                                   @RequestBody ComentarioRequestDTO dto,
                                                   HttpServletRequest request) {
        ComentarioDTO updated = comentarioService.atualizarComentario(request, comentarioId, dto.texto());
        if (updated == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(updated);
    }
}

