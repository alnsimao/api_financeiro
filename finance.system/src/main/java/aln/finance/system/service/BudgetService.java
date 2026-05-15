package aln.finance.system.service;

import aln.finance.system.dto.BudgetRequestDTO;
import aln.finance.system.dto.BudgetResponseDTO;
import aln.finance.system.model.Budget;
import aln.finance.system.model.Category;
import aln.finance.system.model.User;
import aln.finance.system.repository.BudgetRepository;
import aln.finance.system.repository.CategoryRepository;
import aln.finance.system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
@RequiredArgsConstructor
public class BudgetService {
    @Autowired
    private BudgetRepository budgetRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private UserRepository userRepository;

    public BudgetResponseDTO createBudget(Long userId, Long categoryId, BudgetRequestDTO budget) {
        if (userRepository.findById(userId).isEmpty()) {
            throw new RuntimeException("User not found");
        }
        User user = userRepository.findById(userId).get();

        if (categoryRepository.findById(categoryId).isEmpty()) {
            throw new RuntimeException("Category not found");
        }
        Category category = categoryRepository.findById(categoryId).get();
        if (!category.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Category IDs don't match");
        }
        budgetRepository.findByUserIdAndCategoryIdAndPeriod(userId, budget.categoryId(), budget.period())
                .ifPresent(b -> {
                    throw new RuntimeException("Budget already exists for this category and period");
                });
        Budget budgetEntity = new Budget();
        budgetEntity.setUser(user);
        budgetEntity.setCategory(category);
        budgetEntity.setLimitAmount(budget.limitAmount());
        budgetEntity.setPeriod(budget.period());

        Budget savedBudget = budgetRepository.save(budgetEntity);

        return new BudgetResponseDTO(
                savedBudget.getId(),
                category.getId(),
                category.getName(),
                savedBudget.getLimitAmount(),
                savedBudget.getPeriod(),
                BigDecimal.ZERO
        );
    }

    public BudgetResponseDTO updateBudget(Long userId, Long budgetId, BudgetRequestDTO budget) {

        Budget existingBudget = budgetRepository.findById(budgetId).orElseThrow(() -> new RuntimeException("Budget not found"));

        if (userRepository.findById(userId).isEmpty()) {
            throw new RuntimeException("User not found");
        }
        if (!existingBudget.getUser().getId().equals(userId)) {
            throw new RuntimeException("You don't have permission to update this budget");
        }

        Category category = categoryRepository.findById(budget.categoryId()).orElseThrow(
                () -> new RuntimeException("Category not found")
        );

        if (!existingBudget.getCategory().getId().equals(budget.categoryId())) {
            throw new RuntimeException("Category IDs don't match");
        }


        existingBudget.setCategory(category);
        existingBudget.setLimitAmount(budget.limitAmount());
        existingBudget.setPeriod(budget.period());

        Budget updatedBudget = budgetRepository.save(existingBudget);

        return new BudgetResponseDTO(updatedBudget.getId(),
                category.getId(),
                category.getName(),
                updatedBudget.getLimitAmount(),
                updatedBudget.getPeriod(),
                BigDecimal.ZERO);

    }

    public  void deleteBudget(Long userId, Long budgetId) {
        Budget existingBudget = budgetRepository.findById(budgetId).orElseThrow(() -> new RuntimeException("Budget not found"));
        if (userRepository.findById(userId).isEmpty()) {
            throw new RuntimeException("User not found");
        }
        if (!existingBudget.getUser().getId().equals(userId)) {
            throw new RuntimeException("You don't have permission to delete this budget");
        }
        budgetRepository.deleteById(budgetId);


    }



}
