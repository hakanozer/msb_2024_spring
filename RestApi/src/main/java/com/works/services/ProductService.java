package com.works.services;

import com.works.entities.Customer;
import com.works.entities.Product;
import com.works.repositories.ProductRepository;
import com.works.utils.Util;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class ProductService {

    final ProductRepository productRepository;
    final HttpServletRequest req;
    final CacheManager cacheManager;

    public Customer customer() {
        Customer customer = (Customer) req.getSession().getAttribute("customer");
        return customer;
    }

    public ResponseEntity save(Product product) {
        product.setCid(customer().getCid());
        productRepository.save(product);
        cacheManager.getCache("product").clear();
        return Util.success(product);
    }

    @Cacheable("product")
    public ResponseEntity list() {
        List<Product> ls = productRepository.findAll();
        return Util.success(ls);
    }

    @Scheduled(fixedDelay = 60, timeUnit = TimeUnit.SECONDS)
    public void call() {
        System.out.println("this line call");
    }


}
