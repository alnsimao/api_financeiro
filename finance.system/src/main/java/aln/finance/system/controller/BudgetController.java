package aln.finance.system.controller;

import aln.finance.system.dto.BudgetRequestDTO;
import aln.finance.system.dto.BudgetResponseDTO;
import aln.finance.system.service.AuthService;
import aln.finance.system.service.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/budgets")
public class BudgetController {
    @Autowired
    private AuthService authService;
    @Autowired
    private BudgetService budgetService;


    @PostMapping
    public ResponseEntity<BudgetResponseDTO> getBudgets(@RequestBody BudgetRequestDTO budgetRequestDTO) {
        Long userId = authService.getLoggedUserId();
        Long categoryId = budgetRequestDTO.categoryId();

        BudgetResponseDTO responseDTO = budgetService.createBudget(userId,categoryId,budgetRequestDTO);
        return ResponseEntity.ok(responseDTO);
    }


}
