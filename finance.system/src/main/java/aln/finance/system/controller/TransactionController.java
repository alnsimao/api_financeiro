package aln.finance.system.controller;

import aln.finance.system.dto.TransactionDTO;
import aln.finance.system.model.Transaction;
import aln.finance.system.service.AuthService;
import aln.finance.system.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
