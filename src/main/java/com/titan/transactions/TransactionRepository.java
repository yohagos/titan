package com.titan.transactions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

    Optional<List<TransactionEntity>> findByCardNumber(String cardNumber);

    @Query("SELECT t FROM TransactionEntity t WHERE DATE(t.date) = :filterDate")
    List<TransactionEntity> findByDate(@Param("filterDate") LocalDate date);
}
