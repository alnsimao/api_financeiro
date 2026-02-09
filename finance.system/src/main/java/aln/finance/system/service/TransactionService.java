package aln.finance.system.service;


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


    public Transaction createTransaction(Transaction transaction, Long userId, Long categoryId) {
        if(userRepository.findById(userId).isEmpty()) {
            throw new RuntimeException("User not found");
        } User user = userRepository.findById(userId).get();
       if (categoryRepository.findById(categoryId).isEmpty()) {
           throw new RuntimeException("Category not found");
       } Category category = categoryRepository.findById(categoryId).get();
       if(!category.getUser().getId().equals(userId)) {
          throw new RuntimeException("Category don't match with user id") ;
       } transaction.setCategory(category);
        transaction.setUser(user);
        return transactionRepository.save(transaction);

    }
}
