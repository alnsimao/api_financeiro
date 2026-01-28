package aln.finance.system.controller;

import aln.finance.system.model.Category;
import aln.finance.system.service.AuthService;
import aln.finance.system.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    AuthService authService;

    @PostMapping("/")
    public ResponseEntity<Category> createCategory(@Valid @RequestBody Category category){
        Long userId = authService.getLoggedUserId();
        Category newCategory = categoryService.createCategory(category, userId);

        return ResponseEntity.status(HttpStatus.CREATED).body(newCategory);
    }
}
