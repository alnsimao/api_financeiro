package aln.finance.system.repository;

import aln.finance.system.dto.BudgetResponseDTO;
import aln.finance.system.model.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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

    @Query("SELECT new aln.finance.system.dto.BudgetResponseDTO(" +
            "b.id, c.id, c.name, b.limitAmount, b.period, " +
            "CAST(0.0 AS bigdecimal), " +
            "COALESCE((SELECT CAST(SUM(t.amount) AS bigdecimal) FROM Transaction t " +
            "WHERE t.user.id = :userId " +
            "AND t.category.id = c.id " +
            "AND t.date BETWEEN :startDate AND :endDate), CAST(0.0 AS bigdecimal))) " +
            "FROM Budget b " +
            "JOIN b.category c " +
            "WHERE b.user.id = :userId AND b.period = :period")
    List<BudgetResponseDTO> findBudgetsWithProgressByPeriod(
            @Param("userId") Long userId,
            @Param("period") Budget.BudgetPeriod period,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);
}




