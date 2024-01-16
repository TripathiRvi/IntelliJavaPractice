package com.javapractice.thymeleaf.entity;

import javax.persistence.*;

@Entity
@Table(name = "products")
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer productId;

  @Column(length = 128, nullable = false)
  private String productName;

  @Column(length = 256)
  private String productDesc;

  @Column(nullable = false)
  private int stockSize;

  @Column
  private boolean availability;

  public Product() {

  }

  public Product(String productName, String productDesc, int stockSize, boolean availability) {
    this.productName = productName;
    this.productDesc = productDesc;
    this.stockSize = stockSize;
    this.availability = availability;
  }

  public Integer getProductId() {
    return productId;
  }

  public void setProductId(Integer productId) {
    this.productId = productId;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public String getProductDesc() {
    return productDesc;
  }

  public void setProductDesc(String productDesc) {
    this.productDesc = productDesc;
  }

  public int getStockSize() {
    return stockSize;
  }

  public void setStockSize(int stockSize) {
    this.stockSize = stockSize;
  }

  public boolean isAvailability() {
    return availability;
  }

  public void setAvailability(boolean availability) {
    this.availability = availability;
  }

  @Override
  public String toString() {
    return "Product{" +
            "productId=" + productId +
            ", productName='" + productName + '\'' +
            ", productDesc='" + productDesc + '\'' +
            ", stockSize=" + stockSize +
            ", availability=" + availability +
            '}';
  }
}
