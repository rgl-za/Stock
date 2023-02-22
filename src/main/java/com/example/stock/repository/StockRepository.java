package com.example.stock.repository;

import com.example.stock.domain.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.LockModeType;

public interface StockRepository extends JpaRepository<Stock, Long> {

    // Pessimist Lock
    @Lock(value = LockModeType.PESSIMISTIC_WRITE) //스프링 jpa는 Lock이라는 어노테이션을 이용하여 Pessimist Lock 쉽게 구현할 수 있음
    @Query("select s from Stock s where s.id =:id")
    Stock findByIdWithPessimistLock(@Param("id") Long id);

    // Optimistic Lock
    @Lock(value = LockModeType.OPTIMISTIC)
    @Query("select s from Stock s where s.id =: id")
    Stock findByIdWithOptimisticLock(@Param("id") Long id);

}
