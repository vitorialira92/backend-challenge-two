package mercadoeletronico.Backend.Challenge.Two.controllers;

import mercadoeletronico.Backend.Challenge.Two.services.AuthorizationService;
import mercadoeletronico.Backend.Challenge.Two.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller()
@RequestMapping("/auth")

public class AuthController {

    @Autowired
    private AuthorizationService authorizationService;
    @Autowired
    private TokenService tokenService;
    @GetMapping("/login")
    public String getAllSuppliers(Model model){
        System.out.println("login entrou");
        return "login";
    }

}
