package aln.finance.system.service;


import aln.finance.system.dto.TransactionDTO;
import aln.finance.system.model.Category;
import aln.finance.system.model.Transaction;
import aln.finance.system.model.User;
import aln.finance.system.repository.CategoryRepository;
import aln.finance.system.repository.TransactionRepository;
import aln.finance.system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sun.net.ftp.FtpDirEntry;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;


    public Transaction createTransaction(TransactionDTO transaction, Long userId, Long categoryId) {
        if(userRepository.findById(userId).isEmpty()) {
            throw new RuntimeException("User not found");
        } User user = userRepository.findById(userId).get();
       if (categoryRepository.findById(categoryId).isEmpty()) {
           throw new RuntimeException("Category not found");
       } Category category = categoryRepository.findById(categoryId).get();
       if(!category.getUser().getId().equals(userId)) {
          throw new RuntimeException("Category don't match with user id") ;
       }
    Transaction newTransaction = new Transaction();
       newTransaction.setCategory(category);
       newTransaction.setUser(user);
       newTransaction.setAmount(transaction.getAmount());
       newTransaction.setDate(transaction.getDate());
        return transactionRepository.save(newTransaction);

    }
}
