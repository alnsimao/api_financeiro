package aln.finance.system.dto;

import java.math.BigDecimal;

public record CategorySummaryDTO(
        String categoryName,
        BigDecimal totalAmount
){}
