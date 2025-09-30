package br.com.scandianx.fastdev.controller3;
import br.com.scandianx.fastdev.dto.AuthenticationDTO;
import br.com.scandianx.fastdev.dto.LoginResponseDTO;
import br.com.scandianx.fastdev.dto.RegisterDTO;
import br.com.scandianx.fastdev.service.interfaces.AuthenticationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthWebController {

    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping("/")
    public String showAuthPage(Model model) {
        model.addAttribute("registerDTO", new RegisterDTO());
        model.addAttribute("loginDTO", new AuthenticationDTO());
        return "auth/auth";
    }

    @PostMapping("/login")
    public String processLogin(@ModelAttribute("loginDTO") AuthenticationDTO dto, Model model) {
        ResponseEntity<LoginResponseDTO> response = authenticationService.login(dto);

        if (response.getStatusCode().is2xxSuccessful()) {
            return "redirect:/web"; // sucesso
        }

        model.addAttribute("errorMessage", response.getBody()); // erro
        model.addAttribute("registerDTO", new RegisterDTO());
        return "auth/auth";
    }

    @PostMapping("/register")
    public String processRegister(@ModelAttribute("registerDTO") RegisterDTO dto, Model model) {
        ResponseEntity<String> response = authenticationService.register(dto);

        if (response.getStatusCode().is2xxSuccessful()) {
            model.addAttribute("successMessage", response.getBody());
        } else {
            model.addAttribute("errorMessage", response.getBody());
        }

        model.addAttribute("loginDTO", new AuthenticationDTO());
        return "auth/auth";
    }
}
