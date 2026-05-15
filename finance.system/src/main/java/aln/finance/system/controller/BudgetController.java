package aln.finance.system.controller;

import aln.finance.system.dto.BudgetRequestDTO;
import aln.finance.system.dto.BudgetResponseDTO;
import aln.finance.system.service.AuthService;
import aln.finance.system.service.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping
    public ResponseEntity<List<BudgetResponseDTO>> getBudgets() {
        Long userId = authService.getLoggedUserId();
        List<BudgetResponseDTO> responseDTO = budgetService.listBudgetsWithProgress(userId);
        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BudgetResponseDTO> editBudget(@PathVariable Long id, @RequestBody BudgetRequestDTO budgetRequestDTO) {
        Long userId = authService.getLoggedUserId();
        BudgetResponseDTO responseDTO = budgetService.updateBudget(userId,id,budgetRequestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{budgetId}")
    public ResponseEntity<Void> deleteBudgets(@PathVariable Long budgetId) {
        Long userId = authService.getLoggedUserId();
        budgetService.deleteBudget(userId,budgetId);
        return ResponseEntity.noContent().build();

    }




}
