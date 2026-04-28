package aln.finance.system.service;

import aln.finance.system.dto.CategorySummaryDTO;
import aln.finance.system.dto.DashboardSummaryDTO;
import aln.finance.system.dto.MonthlyTrendDTO;
import aln.finance.system.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class DashboardService {
    @Autowired
    private TransactionRepository transactionRepository;

    public DashboardSummaryDTO getDashboardSummary(Long userId, LocalDate start, LocalDate end) {
        BigDecimal totalIncome = transactionRepository.sumIncome(userId, start, end);
        BigDecimal totalExpense = transactionRepository.sumExpense(userId, start, end);
        if(start.isAfter(end)) {
            throw  new IllegalArgumentException("Start date is after end date");
        }
        BigDecimal balance = totalIncome.subtract(totalExpense);

        return new DashboardSummaryDTO(totalIncome, totalExpense, balance);
    }
    public List<CategorySummaryDTO> getTotalByCategory(Long userId, LocalDate start, LocalDate end) {
        return transactionRepository.sumByCategory(userId, start, end);
    }
    public List<MonthlyTrendDTO> getMonthlyTrend(Long userId, LocalDate start, LocalDate end) {
        return transactionRepository.getMonthlyTrend(userId, start, end);
    }
}
