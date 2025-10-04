package br.com.scandianx.fastdev.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import br.com.scandianx.fastdev.dto.UpdateUsuarioDTO;
import br.com.scandianx.fastdev.entity.Usuario;
import br.com.scandianx.fastdev.entity.Visualizador;
import br.com.scandianx.fastdev.repository.impl.UsuarioRepositoryImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/usuarios")
@Tag(name = "Usuários", description = "Operações de atualização e remoção de usuários")
public class UsuarioController {

    @Autowired
    private UsuarioRepositoryImpl usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @DeleteMapping("/{id}")
    @Operation(summary = "Remover usuário por ID")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Removido"),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        if(!usuarioRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        usuarioRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Atualização parcial de usuário")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    public ResponseEntity<Usuario> atualizarParcial(@PathVariable Long id, @RequestBody @Valid UpdateUsuarioDTO dto){
        Optional<Usuario> opt = usuarioRepository.findById(id);
        if(opt.isEmpty()) return ResponseEntity.notFound().build();
        Usuario u = opt.get();
        if(dto.getNome() != null) u.setNome(dto.getNome());
        if(dto.getTelefone() != null) u.setTelefone(dto.getTelefone());
        if(dto.getRole() != null) u.setRole(dto.getRole());
        if(dto.getPassword() != null && !dto.getPassword().isBlank()){
            u.setPassword(passwordEncoder.encode(dto.getPassword()));
        }
        if(dto.getNomeCompleto() != null && u instanceof Visualizador v){
            v.setNomeCompleto(dto.getNomeCompleto());
        }
        Usuario saved = usuarioRepository.save(u);
        return ResponseEntity.ok(saved);
    }
}
