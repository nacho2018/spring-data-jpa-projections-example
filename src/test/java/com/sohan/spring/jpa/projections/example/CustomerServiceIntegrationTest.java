package com.sohan.spring.jpa.projections.example;

import com.sohan.spring.jpa.projections.example.dao.CustomerRepository;
import com.sohan.spring.jpa.projections.example.dto.CustomerDetailsDTO;
import com.sohan.spring.jpa.projections.example.dto.CustomerSimplifiedDTO;
import com.sohan.spring.jpa.projections.example.dto.OrderDTO;
import com.sohan.spring.jpa.projections.example.dto.OrderSimplifiedDTO;
import com.sohan.spring.jpa.projections.example.service.CustomerService;
import com.sohan.spring.jpa.projections.example.service.impl.CustomerServiceImpl;
import static  org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.*;


import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CustomerServiceIntegrationTest {

    private CustomerDetailsDTO customerDetailsDTO;
    private CustomerSimplifiedDTO customerSimplifiedDTO;
    private List<CustomerDetailsDTO> customerDetailsDTOS;
    private List<CustomerSimplifiedDTO> customerSimplifiedDTOS;


    @TestConfiguration
    static class CustomerServiceIntegrationTestConfiguration{

        @Bean
        public CustomerService customerService(){
            return new CustomerServiceImpl();
        }
    }

    @Autowired
    private CustomerService customerService;

    @MockBean
    private CustomerRepository customerRepository;

    @BeforeEach
    public void init(){
        customerDetailsDTO = new CustomerDetailsDTO() {
            @Override
            public Integer getCustomerId() {
                return 1;
            }

            @Override
            public String getCustomerName() {
                return "Mariam Williams";
            }

            @Override
            public String getCity() {
                return "New Manchester";
            }

            @Override
            public String getCountry() {
                return "U.S.A.";
            }

            @Override
            public OrderDTO getOrder() {
                OrderDTO orderDTO = new OrderDTO();
                orderDTO.setOrderNumber(1L);
                orderDTO.setTotalAmount("$ 123.77");

                return orderDTO;
            }
        };

        List<CustomerDetailsDTO> customerDetailsDTOs =
                new ArrayList<>();
        customerDetailsDTOs.add(customerDetailsDTO);

        customerSimplifiedDTO = new CustomerSimplifiedDTO() {
            @Override
            public String getFullName() {
                return "Mario Recio";
            }

            @Override
            public String getCity() {
                return "London city";
            }

            @Override
            public OrderSimplifiedDTO getOrder() {
                OrderSimplifiedDTO orderDTO = new OrderSimplifiedDTO();
                orderDTO.setOrderNumber(1L);
                return orderDTO;
            }
        };

        customerSimplifiedDTOS = new ArrayList<>();
       customerSimplifiedDTOS.add(customerSimplifiedDTO);
    }

   @Test
    public void getCustomersAndOrderDataTest(){
        when(customerRepository.getCustomerDetails())
               .thenReturn(customerDetailsDTOS);

        List<CustomerDetailsDTO> customers = customerService.getCustomersAndOrderData();

      assertEquals(1, customers.size());
   }

   @Test
    public void searchCustomerByFirstNameTest(){
        when(customerRepository.searchCustomerByFirstName(anyString()))
                .thenReturn(customerDetailsDTOS);

       List<CustomerDetailsDTO> customers = customerRepository.searchCustomerByFirstName("firstname");
       assertEquals(1, customers.size());
   }

   @Test
    public void getCustomersAndOrderDataSimpleVersionTest(){
        when(customerRepository.getCustomerSimplifiedData())
                .thenReturn(customerSimplifiedDTOS);

        List<CustomerSimplifiedDTO> customers = customerService.getCustomersAndOrderDataSimpleVersion();
       assertEquals(1, customers.size());
   }




}
