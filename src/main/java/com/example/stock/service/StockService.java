package com.example.stock.service;

import com.example.stock.domain.Stock;
import com.example.stock.repository.StockRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StockService {
    private StockRepository stockRepository;

    public StockService(StockRepository stockRepository){
        this.stockRepository = stockRepository;
    }

    // 재고 감소 메소드
    @Transactional
    public void decrease(Long id, Long quantity){
        /* 로직
        * stock repository를 활용하여 stock repository를 가져온 후에 (get stock)
        * 재고 감소
        * 갱신된 값을 저장
        * */
        Stock stock = stockRepository.findById(id).orElseThrow();
        stock.decrease(quantity);
        stockRepository.saveAndFlush(stock); // 재고 감소 후 값 갱신
    }
}
