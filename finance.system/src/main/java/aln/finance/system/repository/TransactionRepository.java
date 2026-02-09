package aln.finance.system.repository;

import aln.finance.system.model.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Page<Transaction> findByUser_Id(Long userId, Pageable pageable);
    Page<Transaction> findByUser_IdAndDateBetween(Long userId, Date date, Pageable pageable);
    Page<Transaction> findByUser_IdAndCategory_Id(Long userId, Long categoryId, Pageable pageable);
    Page<Transaction> findByUser_IdAndAmountGreaterThan(Long userId, Long amount, Pageable pageable);
}
