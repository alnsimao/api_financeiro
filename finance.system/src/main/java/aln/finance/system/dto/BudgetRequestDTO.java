package aln.finance.system.dto;

import aln.finance.system.model.Budget;

import java.math.BigDecimal;

public record BudgetRequestDTO(
        Long categoryId,
        BigDecimal limitAmount,
        Budget.BudgetPeriod period
) {
}
