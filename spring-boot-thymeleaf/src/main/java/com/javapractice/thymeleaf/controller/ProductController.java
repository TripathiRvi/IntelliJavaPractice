package com.javapractice.thymeleaf.controller;

import com.javapractice.thymeleaf.entity.Product;
import com.javapractice.thymeleaf.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ProductController {

  @Autowired
  ProductService productService;

  @GetMapping("/products")
  public String getAll(Model model, @Param("keyword") String keyword) {
    return productService.getProducts(model, keyword);
  }

  

  @GetMapping("/products/new")
  public String addProduct(Model model) {
    return productService.addProducts(model);
  }
  

  @PostMapping("/products/save")
  public String saveProduct(Product product, RedirectAttributes redirectAttributes) {
    return productService.productSave(product, redirectAttributes);
  }

  

  @GetMapping("/products/{productId}")
  public String editProduct(@PathVariable("productId") Integer productId, Model model, RedirectAttributes redirectAttributes) {
    return productService.productEdit(productId, model, redirectAttributes);
  }

  

  @GetMapping("/products/delete/{productId}")
  public String deleteProduct(@PathVariable("productId") Integer productId, Model model, RedirectAttributes redirectAttributes) {
    return productService.productDelete(productId, redirectAttributes);
  }

  

  @GetMapping("/products/{productId}/availability/{status}")
  public String updateProductAvailabilityStatus(@PathVariable("productId") Integer productId, @PathVariable("status") boolean availability,
      Model model, RedirectAttributes redirectAttributes) {
    return productService.updateProduct(productId, availability, redirectAttributes);
  }
  
}
