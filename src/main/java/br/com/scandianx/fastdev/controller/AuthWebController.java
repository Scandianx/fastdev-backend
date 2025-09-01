package br.com.scandianx.fastdev.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthWebController {
    @GetMapping("/auth-web")
    public String authPage() {
        return "auth/auth";
    }
}
