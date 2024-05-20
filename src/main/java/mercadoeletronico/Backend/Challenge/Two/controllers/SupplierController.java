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
    public ResponseEntity<String> createSupplier(@Parameter(description = "Supplier Id", example = "4848644589")
                                     @PathVariable String userId, @RequestBody SupplierCreationDTO supplierDTO){
        try{
            Supplier supplier = service.createSupplier(supplierDTO, userId);
            return ResponseEntity.ok(supplier.getId());
        }catch(DuplicateCreationAttemptException ex){
            return ResponseEntity.badRequest().build();
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updateSupplier(@Parameter(description = "Supplier Id", example = "4848644589")
                                 @PathVariable String id,
                                 @RequestBody SupplierUpdateDTO updateDTO){
        try{
            Optional<Supplier> supplier = service.updateSupplier(id, updateDTO);
            return ResponseEntity.ok(supplier.get().getId());
        }catch(ResourceNotFoundException ex){
            return ResponseEntity.badRequest().build();
        }
    }
    @DeleteMapping("/{id}")
    public String deleteSupplier(@PathVariable String id, Model model) throws ResourceNotFoundException {
        service.deleteSupplier(id);
        return "all-suppliers";
    }
}
