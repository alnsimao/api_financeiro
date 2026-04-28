package aln.finance.system.dto;

import java.math.BigDecimal;
import aln.finance.system.model.Category.CategoryType;

public record MonthlyTrendDTO(
        Integer month,
        Integer year,
        BigDecimal totalAmount,
        CategoryType type
) {
}
