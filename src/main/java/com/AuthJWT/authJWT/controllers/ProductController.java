package com.AuthJWT.authJWT.controllers;

import com.AuthJWT.authJWT.DTOS.ProductCreateDTO;
import com.AuthJWT.authJWT.entities.Product;
import com.AuthJWT.authJWT.services.ProductServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/api/products")
@Tag(name = "products",description = "the products api")
public class ProductController {

    @Autowired
    ProductServiceImpl service;

    @Operation(summary = "Get All Products",tags = { "products" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get all Products",
                    content = @Content(mediaType = "application/json",schema = @Schema(implementation = Product.class))),
            @ApiResponse(responseCode = "404", description = "not found",
                    content = @Content(mediaType = "application/json")),
    })
    @GetMapping("/all") //Documentacion
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok().body(service.findAll());
    }

    @Operation(summary = "Get by name",tags = { "products" }, description = "First character in uppercase")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get product by name",
                    content = @Content(mediaType = "application/json",schema = @Schema(implementation = Product.class))),
            @ApiResponse(responseCode = "404", description = "not found",
                    content = @Content(mediaType = "application/json")),
    })
    @GetMapping("/") //Documentacion
    public ResponseEntity<?> getByName(@RequestParam("name") String name){

        Optional<Product> productDb = service.findByName(name);

        if(productDb.isPresent()){
            return ResponseEntity.ok().body(productDb.orElseThrow());
        }

        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Get by id",tags = { "products" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get product by id",
                    content = @Content(mediaType = "application/json",schema = @Schema(implementation = Product.class))),
            @ApiResponse(responseCode = "404", description = "not found",
                    content = @Content(mediaType = "application/json")),
    })
    @GetMapping("/{id}") //Documentacion
    public ResponseEntity<?> getById(@PathVariable Long id){

        Optional<Product> productDb = service.findById(id);

        if(productDb.isPresent()){
            return ResponseEntity.ok().body(productDb.orElseThrow());
        }

        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Create product", tags = { "products" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "create product",
                    content = @Content(mediaType = "application/json",schema = @Schema(implementation = ProductCreateDTO.class))),
            @ApiResponse(responseCode = "400", description = "body incorrect",
                    content = @Content(mediaType = "application/json",schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "403",description = "Restricted access",
                    content = @Content(mediaType = "application/json",schema = @Schema(implementation = String.class)))
    })
    @PostMapping //Documentacion
    @SecurityRequirement(name = "Bearer Authentication")//Documentacion
    public ResponseEntity<?> create(@Valid @RequestBody ProductCreateDTO productDTO, BindingResult result){
        if(result.hasFieldErrors()){
            return validation(result);
        }

        Product newProduct = new Product();
        newProduct.setName(productDTO.getName());
        newProduct.setPrice(productDTO.getPrice());
        newProduct.setStock(productDTO.getStock());
        newProduct.setType(productDTO.getType());

        return ResponseEntity.status(201).body(service.save(newProduct));
    }

    @Operation(summary = "Delete product", tags = { "products" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Delete product",
                    content = @Content(mediaType = "application/json",schema = @Schema(implementation = Product.class))),
            @ApiResponse(responseCode = "404", description = "not found",
                    content = @Content(mediaType = "application/json",schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "403",description = "Restricted access",
                    content = @Content(mediaType = "application/json",schema = @Schema(implementation = String.class)))
    })
    @DeleteMapping("/{id}") //Documentacion
    @SecurityRequirement(name = "Bearer Authentication") //Documentacion
    private ResponseEntity<?> delete(@PathVariable Long id){

        Optional<Product> productDelete = service.deleteById(id);

        if (productDelete.isPresent()){
            return ResponseEntity.ok().body(productDelete);
        }

        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Update product", tags = { "products" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Update product",
                    content = @Content(mediaType = "application/json",schema = @Schema(implementation = Product.class))),
            @ApiResponse(responseCode = "404", description = "not found",
                    content = @Content(mediaType = "application/json",schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "403",description = "Restricted access",
                    content = @Content(mediaType = "application/json",schema = @Schema(implementation = String.class)))
    })
    @SecurityRequirement(name = "Bearer Authentication") //Documentacion
    @PutMapping("/{id}")
    private ResponseEntity<?> update(@Valid @RequestBody ProductCreateDTO productDTO,BindingResult result,@PathVariable Long id){

        if(result.hasFieldErrors()){
            return validation(result);
        }

        Product newProduct = new Product();
        newProduct.setName(productDTO.getName());
        newProduct.setPrice(productDTO.getPrice());
        newProduct.setStock(productDTO.getStock());
        newProduct.setType(productDTO.getType());

        Optional<Product> response = service.update(newProduct,id);

        if(response.isPresent()){
            return ResponseEntity.status(201).body(response.orElseThrow());
        }

        return ResponseEntity.notFound().build();
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String,String> errors = new HashMap<>();

        result.getFieldErrors().forEach(err ->{
            errors.put(err.getField(),"El campo "+ err.getField() + " " + err.getDefaultMessage());
        });

        return ResponseEntity.badRequest().body(errors);
    }
}
