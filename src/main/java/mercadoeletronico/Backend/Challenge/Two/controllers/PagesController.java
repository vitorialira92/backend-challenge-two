package mercadoeletronico.Backend.Challenge.Two.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PagesController {

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegisterForm() {
        return "register";
    }

    @GetMapping("/home-page")
    public String homePage() {
        return "home-page";
    }

    @GetMapping("/create-supplier")
    public String createSupplier() {
        return "create-supplier";
    }

    @GetMapping("/edit-supplier/{id}")
    public String editSupplier(@PathVariable String id, Model model) {
        model.addAttribute("supplierId", id);
        return "edit-supplier";
    }

    @GetMapping("/view-supplier/{id}")
    public String viewSupplier(@PathVariable String id, Model model) {
        model.addAttribute("supplierId", id);
        return "view-supplier";
    }

    @GetMapping("/all-suppliers")
    public String viewAllSuppliers() {
        return "all-suppliers";
    }
}
