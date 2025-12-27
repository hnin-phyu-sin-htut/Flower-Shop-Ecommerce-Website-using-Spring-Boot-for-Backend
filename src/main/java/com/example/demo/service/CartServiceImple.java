package com.example.demo.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.CustomerDao;
import com.example.demo.dao.OrderDao;
import com.example.demo.dao.ProductDao;
import com.example.demo.dao.ProductOrderDao;
import com.example.demo.dto.OrderDto;
import com.example.demo.dto.ProductDto;
import com.example.demo.dto.ProductOrderDto;
import com.example.demo.dto.UtilMethods;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Order;
import com.example.demo.entity.Product;
import com.example.demo.entity.ProductOrder;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CartServiceImple implements CartService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private CustomerDao customerDao;

    @Override
    public OrderDto createOrder(List<ProductOrderDto> orders, String username) {

        Customer customer = customerDao
                .findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Customer not found."));

        Order order = new Order();
        order.setOrderDate(LocalDate.now());
        order.setOrderNumber("ORD-" + (1000 + new Random().nextInt(9000)));
        order.setCustomer(customer);

        BigDecimal total = BigDecimal.ZERO;
        List<ProductOrder> productOrders = new ArrayList<>();

        for (ProductOrderDto dto : orders) {
            Product product = productDao
                    .findById(dto.getId())
                    .orElseThrow(() -> new RuntimeException("Product not found."));

            ProductOrder po = new ProductOrder();
            po.setProduct(product);
            po.setQuantity(dto.getQuantity());
            po.setOrder(order);
            
            BigDecimal subTotal = product.getPrice().multiply(BigDecimal.valueOf(dto.getQuantity()));
            po.setSubTotal(subTotal);

            total = total.add(subTotal);
            productOrders.add(po);
        }

        order.setTotalPrice(total);
        order.setProductOrders(productOrders);

        Order savedOrder = orderDao.save(order);

        customer.getOrders().add(savedOrder);

        return UtilMethods.toOrderDto(savedOrder);
    }

}
