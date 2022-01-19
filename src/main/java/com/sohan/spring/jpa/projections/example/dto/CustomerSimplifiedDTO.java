package com.sohan.spring.jpa.projections.example.dto;

import org.springframework.beans.factory.annotation.Value;

/**
 * DTO for customers and their order in a simple way
 */
public interface CustomerSimplifiedDTO {

    @Value("#{target.firstname + ' ' + target.lastname}")
    String getFullName();

    String getCity();

    @Value("#{@mapperUtility.buildSimplifiedOrderDTO(target.orderNumber)}")
    OrderSimplifiedDTO getOrder();

}
