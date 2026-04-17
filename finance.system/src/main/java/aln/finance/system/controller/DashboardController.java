package aln.finance.system.controller;

import aln.finance.system.dto.DashboardSummaryDTO;
import aln.finance.system.service.AuthService;
import aln.finance.system.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/dasboard")
public class DasboardController {
    @Autowired
    DashboardService dashboardService;
    @Autowired
    AuthService authService;

    @GetMapping("/summary")
    public DashboardSummaryDTO getDashboardSummary(LocalDate start, LocalDate end) {
        Long userId = authService.getLoggedUserId();
        return dashboardService.getDashboardSummary(userId, start, end);

    }
}
