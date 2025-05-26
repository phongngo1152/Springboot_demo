package com.example.demo.repository.query;

import com.example.demo.entity.Product;
import com.example.demo.entity.ProductRequestModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRequestRepository extends JpaRepository<ProductRequestModel, Long> {
}
