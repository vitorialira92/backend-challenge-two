package mercadoeletronico.Backend.Challenge.Two.controllers;

import io.swagger.v3.oas.annotations.Parameter;
import mercadoeletronico.Backend.Challenge.Two.domain.supplier.Supplier;
import mercadoeletronico.Backend.Challenge.Two.dtos.*;
import mercadoeletronico.Backend.Challenge.Two.exceptions.DuplicateCreationAttemptException;
import mercadoeletronico.Backend.Challenge.Two.exceptions.ResourceNotFoundException;
import mercadoeletronico.Backend.Challenge.Two.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

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
                                      @PathVariable String id){
        try {
            SupplierDTO supplier = service.getById(id);
            return ResponseEntity.ok(supplier);
        } catch (ResourceNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        }
    }
    @PostMapping("/{userId}")
    public ResponseEntity<String> createSupplier(@Parameter(description = "Supplier Id", example = "4848644589")
                                     @PathVariable String userId, @RequestBody SupplierCreationDTO supplierDTO){
        try{
            Supplier supplier = service.createSupplier(supplierDTO, userId);
            return ResponseEntity.ok(supplier.getId());
        }catch(DuplicateCreationAttemptException ex){
            throw new ResponseStatusException(HttpStatus.CONFLICT, ex.getMessage(), ex);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updateSupplier(@Parameter(description = "Supplier Id", example = "4848644589")
                                 @PathVariable String id,
                                 @RequestBody SupplierUpdateDTO updateDTO){
        try{
            Supplier supplier = service.updateSupplier(id, updateDTO);
            return ResponseEntity.ok(supplier.getId());
        }catch(ResourceNotFoundException ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        }
    }
    @DeleteMapping("/{id}")
    public String deleteSupplier(@PathVariable String id) {
        try{
            service.deleteSupplier(id);
            return "all-suppliers";
        }catch(ResourceNotFoundException ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        }
    }
}
