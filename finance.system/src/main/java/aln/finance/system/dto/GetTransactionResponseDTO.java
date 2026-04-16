package aln.finance.system.dto;

import aln.finance.system.model.Transaction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record GetTransactionResponseDTO(Long id, BigDecimal amount, String description, Long categoryId,
                                        String categoryName, LocalDateTime createdAt, LocalDate date) {
    public GetTransactionResponseDTO(Transaction transaction) {
        this(transaction.getId(),
                transaction.getAmount(),
                transaction.getDescription(),
                transaction.getCategory().getId(),
                transaction.getCategory().getName(),
                transaction.getCreatedAt(),
                transaction.getDate()
        );

    }
}

