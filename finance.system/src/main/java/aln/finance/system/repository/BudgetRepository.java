package aln.finance.system.repository;

import aln.finance.system.model.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface BudgetRepository extends JpaRepository<Budget, Long> {
    Optional<Budget> findByUserIdAndCategoryIdAndPeriod(Long userId,Long categoryId, Budget.BudgetPeriod period);
    List<Budget> findByUserId(Long userId);

    @Query("SELECT SUM(t.amount) FROM Transaction t "+
    "WHERE t.user.id = :userId "+
    "AND t.category.id = :categoryId "+
    "AND t.date BETWEEN :startDate AND :endDate")
    BigDecimal sumByCategoryAndDateRange(Long userId, Long categoryId, LocalDate startDate, LocalDate endDate);

}
