package aln.finance.system.service;


import aln.finance.system.dto.GetTransactionResponseDTO;
import aln.finance.system.dto.TransactionDTO;
import aln.finance.system.dto.TransactionFilterDTO;
import aln.finance.system.model.Category;
import aln.finance.system.model.Transaction;
import aln.finance.system.model.User;
import aln.finance.system.repository.CategoryRepository;
import aln.finance.system.repository.TransactionRepository;
import aln.finance.system.repository.UserRepository;
import aln.finance.system.specification.TransactionSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;


    public Transaction createTransaction(TransactionDTO transaction, Long userId, Long categoryId) {
        if (userRepository.findById(userId).isEmpty()) {
            throw new RuntimeException("User not found");
        }
        User user = userRepository.findById(userId).get();
        if (categoryRepository.findById(categoryId).isEmpty()) {
            throw new RuntimeException("Category not found");
        }
        Category category = categoryRepository.findById(categoryId).get();
        if (!category.getUser().getId().equals(userId)) {
            throw new RuntimeException("Category don't match with user id");
        }
        Transaction newTransaction = new Transaction();
        newTransaction.setCategory(category);
        newTransaction.setUser(user);
        newTransaction.setAmount(transaction.getAmount());
        newTransaction.setDate(transaction.getDate());
        newTransaction.setDescription(transaction.getDescription());
        return transactionRepository.save(newTransaction);
    }

    public Page<GetTransactionResponseDTO> getTransaction(Long userId, Pageable page) {
        Page<Transaction> transactionList = transactionRepository.findByUser_Id(userId, page);
        return transactionList.map(transaction -> new GetTransactionResponseDTO
                (
                        transaction.getId()
                        , transaction.getAmount()
                        , transaction.getDescription(), transaction.getCategory().getId()
                        , transaction.getCategory().getName(),
                        transaction.getCreatedAt(),
                        transaction.getDate()));
    }

    public Page<GetTransactionResponseDTO> getTransactionsWithFilters(Long userId, TransactionFilterDTO filter, Pageable page) {
        Specification<Transaction> specification = TransactionSpecification.hasUserId(userId);
        if (filter.categoryId() != null) {
            specification = specification.and(TransactionSpecification.hasCategory(filter.categoryId())
            );
        }
        if (filter.initialDate() != null && filter.finalDate() != null) {
            specification = specification.and(TransactionSpecification.dateBetween(filter.initialDate(), filter.finalDate())
            );
        }
        if (filter.minAmount() != null && filter.maxAmount() != null) {
            specification = specification.and(TransactionSpecification.amountBetween(filter.minAmount(), filter.maxAmount()));
        }
        Page<Transaction> transactions = transactionRepository.findAll(specification, page);

        return transactions.map(transaction -> new GetTransactionResponseDTO(
                transaction.getId(),
                transaction.getAmount(),
                transaction.getDescription(),
                transaction.getCategory().getId(),
                transaction.getCategory().getName(),
                transaction.getCreatedAt(),
                transaction.getDate()
        ));
    }

    public GetTransactionResponseDTO editTransaction(Long transactionId, TransactionDTO transaction, Long userId, Long categoryId) {

        Transaction existingTransaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));

        if(!existingTransaction.getUser().getId().equals(userId)) {
            throw new RuntimeException("User don't match with user id");
        }
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        if(!category.getUser().getId().equals(userId)) {
            throw new RuntimeException("Category don't match with user id");
        }
        existingTransaction.setCategory(category);
        existingTransaction.setDescription(transaction.getDescription());
        existingTransaction.setAmount(transaction.getAmount());
        existingTransaction.setDate(transaction.getDate());

        Transaction savedTransaction = transactionRepository.save(existingTransaction);

        return new GetTransactionResponseDTO(savedTransaction);

    }

    public void deleteTransaction(Long transactionId,Long userId) {
        Transaction existingTransaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));
        if(!existingTransaction.getUser().getId().equals(userId)) {
            throw new RuntimeException("User don't match with user id");
        }
        transactionRepository.delete(existingTransaction);
    }


}
