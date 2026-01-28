package aln.finance.system.service;

import aln.finance.system.model.Category;
import aln.finance.system.model.User;
import aln.finance.system.repository.CategoryRepository;
import aln.finance.system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public Category createCategory(Category category, Long userId) {
        if (categoryRepository.existsByUserIdAndNameIgnoreCase(userId, category.getName())) {
            throw new RuntimeException("Category already exists");
        }
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        category.setUser(user);
        return categoryRepository.save(category);
        }

        public Category updateCategory(Category category, Long userId) {
        Category existingCategory = categoryRepository.findByIdAndUserId
                (category.getId(), userId).orElseThrow
                (() -> new RuntimeException("Category not found"));
        boolean nameExists = categoryRepository.existsByUserIdAndNameIgnoreCase(userId, category.getName());
        if(nameExists && !existingCategory.getName().equals(category.getName())) {
            throw new RuntimeException("Category already exists");
        }
        existingCategory.setName(category.getName());
        return categoryRepository.save(existingCategory);
        }

        public List<Category> getAllCategories(Long userId) {
            return categoryRepository.findByUserId(userId);
        }
        public void deleteCategory(Long userId, Long categoryId) {
        Category existCategory = categoryRepository.findByIdAndUserId(categoryId, userId).orElseThrow(() -> new RuntimeException("Category not found"));
         categoryRepository.delete(existCategory);
        }


    }
