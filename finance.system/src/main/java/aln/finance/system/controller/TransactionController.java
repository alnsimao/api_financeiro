package aln.finance.system.controller;

import aln.finance.system.dto.GetTransactionResponseDTO;
import aln.finance.system.dto.TransactionDTO;
import aln.finance.system.dto.TransactionFilterDTO;
import aln.finance.system.model.Transaction;
import aln.finance.system.service.AuthService;
import aln.finance.system.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    @Autowired
    TransactionService transactionService;
    @Autowired
    AuthService authService;

    @PostMapping("/")
    public GetTransactionResponseDTO createTransaction(@Valid @RequestBody TransactionDTO transaction) {
        Long userId = authService.getLoggedUserId();
        Long categoryId = transaction.getCategoryId();
        return transactionService.createTransaction(transaction, userId, categoryId);
    }

    @GetMapping("/list")
    public Page<GetTransactionResponseDTO> listTransaction(Pageable page) {
        Long userId = authService.getLoggedUserId();
        Page<GetTransactionResponseDTO> resultado = transactionService.getTransaction(userId, page);
        System.out.println("Classe retornada: " + resultado.getContent().get(0).getClass().getName());
        return transactionService.getTransaction(userId, page);
    }

    @GetMapping("/")
    public Page<GetTransactionResponseDTO> getTransactions(
            @RequestParam(required = false) LocalDate initialDate
            , @RequestParam(required = false) LocalDate finalDate
            , @RequestParam(required = false) Long categoryId
            , @RequestParam(required = false) BigDecimal minAmount
            , @RequestParam(required = false) BigDecimal maxAmount,
            Pageable page) {
        Long userId = authService.getLoggedUserId();

        TransactionFilterDTO filter = new TransactionFilterDTO(initialDate,finalDate, categoryId, minAmount, maxAmount);
        return transactionService.getTransactionsWithFilters(userId, filter, page);
    }
        @PutMapping("/{id}")
        public GetTransactionResponseDTO updateTransaction(@PathVariable Long id, @Valid @RequestBody TransactionDTO transaction) {
            Long userId = authService.getLoggedUserId();
            return transactionService.editTransaction(id,transaction,userId,transaction.getCategoryId());

        }
    @DeleteMapping("/{id}")
    public void deleteTransaction(@PathVariable Long id) {
        Long userId = authService.getLoggedUserId();
        transactionService.deleteTransaction(id, userId);
    }


}
