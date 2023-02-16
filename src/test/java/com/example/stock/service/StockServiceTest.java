package com.example.stock.service;

import com.example.stock.domain.Stock;
import com.example.stock.repository.StockRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class StockServiceTest {

    @Autowired
    private StockService stockService;

    @Autowired
    private StockRepository stockRepository;

    @BeforeEach
    public void before(){
        Stock stock = new Stock(1L, 100L);

        stockRepository.saveAndFlush(stock);
    }

    @AfterEach
    public void after(){
        stockRepository.deleteAll(); // 정상적으로 종료되면 데이터 삭제
    }

    @Test
    public void stock_decrease(){
        stockService.decrease(1L, 1L); // 1개 감소

        // 로직대로라면 100 - 1 = 99개가 남아있어야 함
        Stock stock = stockRepository.findById(1L).orElseThrow();
        assertEquals(99, stock.getQuantity());
    }
}