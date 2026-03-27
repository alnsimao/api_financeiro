package aln.finance.system.repository;

import aln.finance.system.model.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface TransactionRepository extends JpaRepository<Transaction, Long>, JpaSpecificationExecutor<Transaction> {

    Page<Transaction> findByUser_Id(Long userId, Pageable pageable);
    Page<Transaction> findByUser_IdAndDateBetween(Long userId, LocalDate initialDate,LocalDate finalDate, Pageable pageable);
    Page<Transaction> findByUser_IdAndCategory_Id(Long userId, Long categoryId, Pageable pageable);
    Page<Transaction> findByUser_IdAndAmountGreaterThan(Long userId, BigDecimal amount, Pageable pageable);
    Page<Transaction> findByUser_IdAndAmountLessThan(Long userId, BigDecimal amount, Pageable pageable);
    Page<Transaction> findByUser_IdAndAmountBetween(Long userId, BigDecimal initialAmount,BigDecimal finalAmount, Pageable pageable);
}
