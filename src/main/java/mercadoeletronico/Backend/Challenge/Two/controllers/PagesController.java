package mercadoeletronico.Backend.Challenge.Two.controllers;

import mercadoeletronico.Backend.Challenge.Two.dtos.SupplierCreationDTO;
import mercadoeletronico.Backend.Challenge.Two.dtos.SupplierDTO;
import mercadoeletronico.Backend.Challenge.Two.exceptions.ResourceNotFoundException;
import mercadoeletronico.Backend.Challenge.Two.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class PagesController {
    @Autowired
    private SupplierService supplierService;

    @GetMapping("/home-page")
    public String homePage() {
        return "home-page";
    }

    @GetMapping("/create-supplier")
    public String createSupplier(Model model) {
        SupplierCreationDTO creationDTO = new SupplierCreationDTO();
        model.addAttribute("creationDTO", creationDTO);
        return "create-supplier";
    }
    @GetMapping("/view-supplier/{id}")
    public String viewSupplier(@PathVariable String id, Model model) throws ResourceNotFoundException {
        Optional<SupplierDTO> supplier = supplierService.getById(id);
        if(supplier.isEmpty()) {
            return "home-page";
        }
        model.addAttribute("supplierId", id);
        return "view-supplier";
    }

    @GetMapping("/all-suppliers")
    public String viewAllSuppliers() {
        return "all-suppliers";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }
}
