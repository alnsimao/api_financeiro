package aln.finance.system.controller;

import aln.finance.system.dto.CategorySummaryDTO;
import aln.finance.system.dto.DashboardSummaryDTO;
import aln.finance.system.dto.MonthlyTrendDTO;
import aln.finance.system.service.AuthService;
import aln.finance.system.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {
    @Autowired
    DashboardService dashboardService;
    @Autowired
    AuthService authService;

    @GetMapping("/summary")
    public DashboardSummaryDTO getDashboardSummary(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
                                                   @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        System.out.println("LOG: Entrei no método do Controller!");
        Long userId = authService.getLoggedUserId();
        return dashboardService.getDashboardSummary(userId, start, end);
    }
    @GetMapping("/by-category")
    public List<CategorySummaryDTO> getByCategory(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
                                                  @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        System.out.println("LOG: TESTE DO METODO");
        Long userId = authService.getLoggedUserId();
        return dashboardService.getTotalByCategory(userId, start, end);
    }
    @GetMapping("/monthly-trend")
    public List<MonthlyTrendDTO> getMonthlyTrend(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
                                                 @RequestParam @DateTimeFormat(iso= DateTimeFormat.ISO.DATE) LocalDate end) {
        Long userId = authService.getLoggedUserId();
        return dashboardService.getMonthlyTrend(userId, start, end);
    }
}
