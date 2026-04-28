package aln.finance.system.specification;

import aln.finance.system.model.Transaction;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TransactionSpecification {

    public static Specification<Transaction> hasUserId(Long userId) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("user").get("id"), userId);
    }

    public static Specification<Transaction> hasCategory(Long categoryId) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("category"), categoryId);
    }

    public static Specification<Transaction> dateBetween(LocalDate start, LocalDate end) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.between(root.get("date"), start, end);
    }

    public static Specification<Transaction> amountBetween(BigDecimal minAmount, BigDecimal maxAmount) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.between(root.get("amount"), minAmount, maxAmount));
    }
}
