package br.com.fiap.ez.fastfood.adapters.in.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.fiap.ez.fastfood.application.ports.in.CategoryService;
import br.com.fiap.ez.fastfood.domain.model.Category;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

	@Operation(summary = "Create a new Category")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Categoria criada"),
	@ApiResponse(responseCode = "400", description = "Invalid input data") })
	@PostMapping(path = "/create-new", produces = "application/json")
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        Category createdCategory = categoryService.createCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCategory);
    }

	@Operation(summary = "List all categories")
	@GetMapping(path = "/list-all", produces = "application/json")
    public ResponseEntity<List<Category>> listCategories() {
        List<Category> categories = categoryService.listCategories();
        return ResponseEntity.ok(categories);
    }

	@Operation(summary = "Find Category by ID")
	@GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        Category category = categoryService.findById(id);
        return ResponseEntity.ok(category);
    }

	@Operation(summary = "Remove Category by ID")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Categoria removida"),
	@ApiResponse(responseCode = "400", description = "Invalid input data") })
	@DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
