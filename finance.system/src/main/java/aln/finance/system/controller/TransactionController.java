package aln.finance.system.controller;

import aln.finance.system.dto.GetTransactionResponseDTO;
import aln.finance.system.dto.TransactionDTO;
import aln.finance.system.model.Transaction;
import aln.finance.system.service.AuthService;
import aln.finance.system.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    @Autowired
    TransactionService transactionService;
    @Autowired
    AuthService authService;

    @PostMapping("/")
    public Transaction createTransaction(@Valid @RequestBody TransactionDTO transaction) {
        Long userId = authService.getLoggedUserId();
        Long categoryId = transaction.getCategoryId();
        return transactionService.createTransaction(transaction,userId,categoryId);
    }

     @GetMapping("/list")
    public Page<GetTransactionResponseDTO> listTransaction(Pageable page) {
        Long userId = authService.getLoggedUserId();
        return transactionService.getTransaction(userId,page);
    }


}
