package aln.finance.system.service;

import aln.finance.system.model.Category;
import aln.finance.system.model.User;
import aln.finance.system.repository.CategoryRepository;
import aln.finance.system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public Category create(Category category, Long userId) {
        if (categoryRepository.existsByUserIdAndNameIgnoreCase(userId, category.getName())) {
            throw new RuntimeException("Category already exists");
        }
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        category.setUser(user);
        return categoryRepository.save(category);
        }
    }
