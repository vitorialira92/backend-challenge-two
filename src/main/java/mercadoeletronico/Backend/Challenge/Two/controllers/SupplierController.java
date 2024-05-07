package mercadoeletronico.Backend.Challenge.Two.controllers;

import io.swagger.v3.oas.annotations.Parameter;
import mercadoeletronico.Backend.Challenge.Two.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller()
@RequestMapping("/supplier")
public class SupplierController {
    @Autowired
    private SupplierService service;

    @GetMapping()
    public String getAllSuppliers(Model model){
        System.out.println("all");

        model.addAttribute("suppliers", service.getAll());

        return "all-suppliers";
    }

    @GetMapping("/{id}")
    public String getSupplierById(@Parameter(description = "Supplier Id", example = "4848644589")
                                      @PathVariable Long id, Model model){
        System.out.println("by id");
        model.addAttribute("supplier", service.getById(id));
        return "supplier";
    }
}
