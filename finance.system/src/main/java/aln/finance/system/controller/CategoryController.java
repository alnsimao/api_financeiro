package aln.finance.system.controller;

import aln.finance.system.model.Category;
import aln.finance.system.service.AuthService;
import aln.finance.system.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    AuthService authService;

    @PostMapping("/")
    public Category createCategory(@Valid @RequestBody Category category){
        Long userId = authService.getLoggedUserId();
        return categoryService.createCategory(category, userId);
    }
    @GetMapping("/")
    public List<Category> getAllCategories(){
        long userId = authService.getLoggedUserId();
        return categoryService.getAllCategories(userId);
    }
    @PutMapping("/{id}")
    public Category updateCategory(@PathVariable Long id, @Valid @RequestBody Category category){
        Long userId = authService.getLoggedUserId();
        category.setId(id);
        return categoryService.updateCategory(category, userId);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id){
        Long userId = authService.getLoggedUserId();
        categoryService.deleteCategory(userId, id);
    }
}
