package com.titan.transactions;

import com.titan.product.ProductEntity;
import com.titan.transactions.request.TransactionCardRequest;
import com.titan.transactions.request.TransactionCashRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    private static final Logger log = LoggerFactory.getLogger(TransactionController.class);

    @GetMapping
    public ResponseEntity<List<TransactionEntity>> getTransactions() {
        return ResponseEntity.ok(transactionService.getTransactions());
    }

    @GetMapping("/{date}")
    public ResponseEntity<List<TransactionEntity>> getTransactionForDate(
            @PathVariable(name = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate date
    ) {
        return ResponseEntity.ok(transactionService.getTransactionsForDate(date));
    }


    @PostMapping("/cash")
    public ResponseEntity<TransactionEntity> cashTransaction(
            @RequestBody TransactionCashRequest request
    ) {
        return ResponseEntity.ok(transactionService.setCashTransaction(request));
    }

    @PostMapping("/{id}")
    public ResponseEntity<TransactionEntity> updateTransaction(
            @PathVariable(name = "id") Long id,
            @RequestBody List<ProductEntity> products
    ) {
        return ResponseEntity.ok(transactionService.addProductsToTransaction(id, products));
    }

    @PostMapping("/card")
    public ResponseEntity<TransactionEntity> creditTransaction(
            @RequestBody TransactionCardRequest request
    ) {
        return ResponseEntity.ok(transactionService.setCardTransaction(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TransactionEntity> cancelTransaction(
            @PathVariable(name = "id") Long id
    ) {
        return ResponseEntity.ok(transactionService.cancelTransaction(id));
    }
}
