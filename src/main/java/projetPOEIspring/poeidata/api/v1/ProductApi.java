package projetPOEIspring.poeidata.api.v1;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import projetPOEIspring.poeidata.api.dto.ProductDto;
import projetPOEIspring.poeidata.exceptions.UnknownResourceException;
import projetPOEIspring.poeidata.mappers.ProductMapper;
import projetPOEIspring.poeidata.models.Product;
import projetPOEIspring.poeidata.services.ProductService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/products")
public class ProductApi {

    private final ProductService productService;

    private final ProductMapper productMapper;

    public ProductApi(ProductService productService, ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(
            summary = "Return the list of all products ordered by name ascending.")
    public ResponseEntity<List<ProductDto>> getAll() {
        return ResponseEntity.ok(
                this.productService.getAll().stream()
                        .map(this.productMapper::mapProductToDto)
                        .toList()
        );
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Trying to retrieve a product from the given ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Return the product found the given ID"),
            @ApiResponse(responseCode = "404", description = "No customer found the given ID")
    })
    public ResponseEntity<ProductDto> getById(@PathVariable final Integer id) {
        try {
            return ResponseEntity.ok(
                    this.productMapper
                            .mapProductToDto(this.productService.getById(id)));
        } catch (UnknownResourceException ure) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ure.getMessage());
        }
    }

    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE}
    )
    @Operation(summary = "Create a product")
    @ApiResponse(responseCode = "201", description = "Created")
    public ResponseEntity<ProductDto> createProduct(@RequestBody final ProductDto productDto) {
        try {
            ProductDto productDtoResponse =
                    this.productMapper.mapProductToDto(
                            this.productService.createProduct(
                                    this.productMapper.mapProductToModel(productDto)
                            ));

            return ResponseEntity
                    .created(URI.create("/v1/products/" + productDtoResponse.getId()))
                    .body(productDtoResponse);
        } catch (UnknownResourceException ure) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ure.getMessage());
        }
    }

    @DeleteMapping(path = "/{id}")
    @Operation(summary = "Delete a product for the given ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "404", description = "No product found the given ID"),
            @ApiResponse(responseCode = "403", description = "Cannot delete the product for the given ID")
    })
    public ResponseEntity<Void> deleteProduct(@PathVariable final Integer id) {
        try {
            this.productService.deleteProduct(id);
            return ResponseEntity.noContent().build();
        } catch (UnknownResourceException ure) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ure.getMessage());
        }
    }

    @PutMapping(path = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    @Operation(summary = "Update a product")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No content")
    })
    public ResponseEntity<Void> updateProduct(@PathVariable final Integer id, @RequestBody ProductDto productDto) {
        try {
            productDto.setId(id);
            this.productService.updateProduct(productMapper.mapProductToModel(productDto));

            return ResponseEntity.noContent().build();
        } catch (UnknownResourceException ure) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ure.getMessage());
        }
    }

}
