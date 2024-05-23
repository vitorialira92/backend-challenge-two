package mercadoeletronico.Backend.Challenge.Two.controllers;

import mercadoeletronico.Backend.Challenge.Two.exceptions.ResourceNotFoundException;
import mercadoeletronico.Backend.Challenge.Two.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PagesController {
    @Autowired
    private SupplierService supplierService;


    @GetMapping("/login")
    public String showLoginForm(Model model) {
        return "login";
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
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
    public String editSupplier(@PathVariable String id, Model model) throws ResourceNotFoundException {
        model.addAttribute("supplierId", id);
        return "edit-supplier";
    }

    @GetMapping("/view-supplier/{id}")
    public String viewSupplier(@PathVariable String id, Model model) throws ResourceNotFoundException {
        model.addAttribute("supplierId", id);
        return "view-supplier";
    }

    @GetMapping("/all-suppliers")
    public String viewAllSuppliers() {
        return "all-suppliers";
    }
}
