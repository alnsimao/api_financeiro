package aln.finance.system.repository;

import aln.finance.system.dto.CategorySummaryDTO;
import aln.finance.system.dto.MonthlyTrendDTO;
import aln.finance.system.model.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


public interface TransactionRepository extends JpaRepository<Transaction, Long>, JpaSpecificationExecutor<Transaction> {
    Page<Transaction> findByUser_Id(Long userId, Pageable pageable);

    @Query("SELECT COALESCE(SUM(t.amount), 0) FROM Transaction t " +
            "WHERE t.user.id = :userId " +
            "AND t.category.type = 'INCOME' " +
            "AND t.date BETWEEN :start AND :end")
    BigDecimal sumIncome(Long userId, LocalDate start, LocalDate end);
    @Query("SELECT COALESCE(SUM(t.amount), 0) FROM Transaction t " +
            "WHERE t.user.id = :userId " +
            "AND t.category.type = 'EXPENSE' " +
            "AND t.date BETWEEN :start AND :end")
    BigDecimal sumExpense(Long userId, LocalDate start, LocalDate end);

    @Query("SELECT new aln.finance.system.dto.CategorySummaryDTO(t.category.name, SUM(t.amount))" +
    "FROM Transaction t" +
    " WHERE t.user.id = :userId AND t.date BETWEEN :start AND :end"+
    " GROUP BY t.category.name")
    List<CategorySummaryDTO> sumByCategory(Long userId, LocalDate start, LocalDate end);

    @Query("SELECT new aln.finance.system.dto.MonthlyTrendDTO(" +
            "FUNCTION('MONTH', t.date), " +
            "FUNCTION('YEAR', t.date), " +
            "SUM(t.amount), " +
            "t.category.type) " +
            "FROM Transaction t " +
            "WHERE t.user.id = :userId AND t.date BETWEEN :start AND :end " +
            "GROUP BY FUNCTION('YEAR', t.date), FUNCTION('MONTH', t.date), t.category.type " +
            "ORDER BY FUNCTION('YEAR', t.date) DESC, FUNCTION('MONTH', t.date) DESC")
    List<MonthlyTrendDTO> getMonthlyTrend(Long userId, LocalDate start, LocalDate end);
}
