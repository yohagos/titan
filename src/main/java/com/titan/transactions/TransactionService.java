package com.titan.transactions;

import com.titan.exceptions.TransactionsNotFoundException;
import com.titan.product.ProductEntity;
import com.titan.transactions.request.TransactionCardRequest;
import com.titan.transactions.request.TransactionCashRequest;
import com.titan.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    private static final Logger log = LoggerFactory.getLogger(TransactionService.class);

    public List<TransactionEntity> getTransactions() {
        return transactionRepository.findAll();
    }

    public TransactionEntity setCashTransaction(TransactionCashRequest request) {
        var user = userRepository.findById(request.getUserId()).orElseThrow();
        return transactionRepository.save(
                new TransactionEntity(
                        request.getPrice(),
                        request.getWithTip(),
                        request.getTip(),
                        true,
                        user,
                        LocalDateTime.now()
                )
        );
    }

    public TransactionEntity setCardTransaction(TransactionCardRequest request) {
        var user = userRepository.findById(request.getUserId()).orElseThrow();
        return transactionRepository.save(
                new TransactionEntity(
                        request.getPrice(),
                        request.getWithTip(),
                        request.getTip(),
                        request.getWithCard(),
                        request.getCardNumber(),
                        request.getPaid(),
                        user,
                        LocalDateTime.now()
                )
        );
    }

    public List<TransactionEntity> getTransactionsForDate(LocalDate date) {
        return transactionRepository.findByDate(date);
    }

    public TransactionEntity addProductsToTransaction(Long id, List<ProductEntity> products) {
        var transaction = transactionRepository.findById(id).orElseThrow();

        Optional.of(products)
                .filter(prod -> !prod.isEmpty())
                .ifPresent(transaction::setProducts);
        return transactionRepository.save(transaction);
    }

    public TransactionEntity cancelTransaction(Long id) {
        var transaction = transactionRepository.findById(id).orElseThrow();
        transactionRepository.deleteById(transaction.getId());
        return transaction;
    }
}
