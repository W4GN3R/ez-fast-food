package br.com.fiap.ez.fastfood.adapters.in.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.ez.fastfood.application.dto.ProductDTO;
import br.com.fiap.ez.fastfood.application.ports.in.ProductService;
import br.com.fiap.ez.fastfood.config.exception.BusinessException;
import br.com.fiap.ez.fastfood.domain.model.Category;
import br.com.fiap.ez.fastfood.domain.model.Product;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/products")
@Tag(name = "Product Operations", description = "Operations related to products")
public class ProductController {

	@Autowired
	private final ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@Operation(summary = "Create a new product")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Produto criado"),
			@ApiResponse(responseCode = "400", description = "Invalid input data") })
	@PostMapping(path = "/create-new", produces = "application/json")
	public ResponseEntity<Product> createProduct(@RequestBody ProductDTO productDTO) {
		Product product = new Product();
		product.setName(productDTO.getName());
		product.setDescription(productDTO.getDescription());
		product.setPrice(productDTO.getPrice());

		Category category = new Category();
		category.setName(productDTO.getCategoryName());
		product.setCategory(category);

		Product createdProduct = productService.createProduct(product);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
	}

	@Operation(summary = "Modify Product by name")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Produto alterado"),
			@ApiResponse(responseCode = "400", description = "Invalid input data") })
	@PutMapping("update-by-name/{name}")
	public ResponseEntity<Product> updateProductByName(@PathVariable String name, @RequestBody ProductDTO productDTO) {
		Product updatedProduct = productService.updateProduct(name, productDTO);
		return ResponseEntity.ok(updatedProduct);
	}

	@Operation(summary = "Remove Product by name")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Produto removido"),
			@ApiResponse(responseCode = "400", description = "Invalid input data") })
	@DeleteMapping("delete-by-name/{name}")
	public ResponseEntity<Void> deleteProductByName(@PathVariable String name) {
		productService.deleteProduct(name);
		return ResponseEntity.noContent().build();
	}

	@Operation(summary = "Find Products by Category Name")
	@GetMapping("find-by-category/{categoryName}")
	public ResponseEntity<?> getProductsByCategoryName(@PathVariable String categoryName) {
		List<Product> products = productService.findProductsByCategoryName(categoryName);
		if (products == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Nenhum produto encontrado na Categoria: " + categoryName);
		}
		return ResponseEntity.ok(products);
	}

	@Operation(summary = "List all products")
	@GetMapping(path = "/list-all", produces = "application/json")
	public ResponseEntity<?> listProducts() {
		try {
			List<Product> products = productService.listProducts();
			return new ResponseEntity<>(products, HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@Hidden
	@Operation(summary = "Find Product by ID")
	@GetMapping("/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable Long id) {
		Product product = productService.findById(id);
		return ResponseEntity.ok(product);
	}

}
