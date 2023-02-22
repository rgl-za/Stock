package com.example.stock.service;

import com.example.stock.domain.Stock;
import com.example.stock.repository.StockRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PessimisticLockStockService {

    private StockRepository stockRepository;

    public PessimisticLockStockService(StockRepository stockRepository){
        this.stockRepository = stockRepository;
    }

    /*
    * Lock을 걸고 데이터를 가져온 후에 재고를 감소시키고 값을 저장시키는 로직*/
    @Transactional
    public void decrease(Long id, Long quantity){
        Stock stock = stockRepository.findByIdWithPessimistLock(id); // lock을 걸고 데이터를 가져옴

        stock.decrease(quantity);

        stockRepository.saveAndFlush(stock);
    }
}
