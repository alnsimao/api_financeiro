package aln.finance.system.dto;

import aln.finance.system.model.Budget;

import java.math.BigDecimal;

public record BudgetResponseDTO(
        Long id,
        Long categoryId,
        String categoryName,
        BigDecimal limitAmount,
        Budget.BudgetPeriod period,
        BigDecimal usedPercentage
) { }
