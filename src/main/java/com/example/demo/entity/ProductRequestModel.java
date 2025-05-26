package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "product")
public class ProductRequestModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;
    @Column(name = "product_name")
    private String productName;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserRequestModel userRequestModel;


    public ProductRequestModel() {
    }

    public ProductRequestModel(Long id, String productName, UserRequestModel userRequestModel) {
        this.id = id;
        this.productName = productName;
        this.userRequestModel = userRequestModel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public UserRequestModel getUserRequestModel() {
        return userRequestModel;
    }

    public void setUserRequestModel(UserRequestModel userRequestModel) {
        this.userRequestModel = userRequestModel;
    }
}
