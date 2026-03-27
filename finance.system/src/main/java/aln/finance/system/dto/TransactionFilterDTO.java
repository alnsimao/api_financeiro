package aln.finance.system.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TransactionFilterDTO(LocalDate initialDate, LocalDate finalDate, Long categoryId,BigDecimal minAmount, BigDecimal maxAmount) {
}
