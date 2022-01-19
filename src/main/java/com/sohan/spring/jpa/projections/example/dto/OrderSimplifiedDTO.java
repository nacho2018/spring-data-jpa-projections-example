package com.sohan.spring.jpa.projections.example.dto;

/**
 * Simple DTO for Order, simplified search
 */
public class OrderSimplifiedDTO {
    private Long orderNumber;

    public Long getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Long orderNumber) {
        this.orderNumber = orderNumber;
    }
}
