package aln.finance.system.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record GetTransactionResponseDTO(Long id, BigDecimal amount, String description, Long categoryId,
                                        String categoryName, LocalDateTime createdAt, LocalDate date) {
}
