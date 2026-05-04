package aln.finance.system.repository;

import aln.finance.system.model.Budget;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BudgetRepository extends JpaRepository<Budget, Long> {
    Optional<Budget> findByUserIdAndCategoryIdAndPeriod(Long userId,Long categoryId, Budget.BudgetPeriod period);

    List<Budget> findByUserId(Long userId);

}
