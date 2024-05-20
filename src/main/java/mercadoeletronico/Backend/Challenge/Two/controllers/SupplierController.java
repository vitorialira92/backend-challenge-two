package mercadoeletronico.Backend.Challenge.Two.controllers;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import mercadoeletronico.Backend.Challenge.Two.domain.supplier.Supplier;
import mercadoeletronico.Backend.Challenge.Two.dtos.*;
import mercadoeletronico.Backend.Challenge.Two.exceptions.DuplicateCreationAttemptException;
import mercadoeletronico.Backend.Challenge.Two.exceptions.ResourceNotFoundException;
import mercadoeletronico.Backend.Challenge.Two.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller()
@RequestMapping("/supplier")
public class SupplierController {
    @Autowired
    private SupplierService service;

    @GetMapping("/all/{userId}")
    @ResponseBody
    public ResponseEntity<List<SupplierSimpleDTO>> getUsersAllSuppliers(@Parameter(description = "Supplier Id", example = "4848644589")
                                                                            @PathVariable String userId) {
        List<SupplierSimpleDTO> suppliersList = service.getAll(userId);
        return ResponseEntity.ok(suppliersList);
    }
    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<SupplierDTO> getSupplierById(@Parameter(description = "Supplier Id", example = "4848644589")
                                      @PathVariable String id, Model model){
        try{
            Optional<SupplierDTO> supplier = service.getById(id);
            return supplier.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        }catch(ResourceNotFoundException ex){
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/{userId}")
    public String createSupplier(@Parameter(description = "Supplier Id", example = "4848644589")
                                     @PathVariable String userId, @ModelAttribute("creationDTO") @Valid SupplierCreationDTO supplierDTO){
        try{
            Supplier supplier = service.createSupplier(supplierDTO, userId);
            return "redirect:/view-supplier/" + supplier.getId();
        }catch(DuplicateCreationAttemptException ex){
            return "error-message";
        }
    }
    @PutMapping("/{id}")
    public String updateSupplier(@Parameter(description = "Supplier Id", example = "4848644589")
                                 @PathVariable String id,
                                 @ModelAttribute("updateDTO") @Valid SupplierUpdateDTO updateDTO,
                                 Model model){
        try{
            Optional<Supplier> supplier = service.updateSupplier(id, updateDTO);
            model.addAttribute("supplier", supplier);
            return "all-suppliers";
        }catch(ResourceNotFoundException ex){
            return "error-message";
        }
    }
    @DeleteMapping("/{id}")
    public String deleteSupplier(@PathVariable String id, Model model){
        try{
            service.deleteSupplier(id);
            return "all-suppliers";
        }catch(ResourceNotFoundException ex){
            return "error-message";
        }
    }
}
