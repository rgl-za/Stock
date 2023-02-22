package com.example.stock.domain;

import javax.persistence.*;

@Entity
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long productId;
    private Long quantity;

    // Optimistic Lock을 사용하기 위한 컬럼
    @Version
    private Long version;

    public Stock(){

    }

    public Stock(Long productId, Long quantity){
        this.productId = productId;
        this.quantity = quantity;
    }

    public Long getQuantity(){
        return quantity;
    }

    // 재고 감소 메소드
    public void decrease(Long quantity){
        if(this.quantity - quantity < 0){
            throw new RuntimeException("수량: 0개 미만 !!!");
        }
        this.quantity = this.quantity - quantity;
    }
}
