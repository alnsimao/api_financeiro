package aln.finance.system.controller;

import aln.finance.system.dto.DashboardSummaryDTO;
import aln.finance.system.service.AuthService;
import aln.finance.system.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

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
}
