package com.javapractice.thymeleaf.repository;

import com.javapractice.thymeleaf.entity.Product;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface IProductRepository extends JpaRepository<Product, Integer> {
  List<Product> findByProductNameContainingIgnoreCase(String keyword);

  @Query("UPDATE Product p SET p.availability = :availability WHERE p.productId = :productId")
  @Modifying
  public void updateAvailabilityStatus(Integer productId, boolean availability);
}
