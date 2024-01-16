package com.javapractice.thymeleaf.service;

import com.javapractice.thymeleaf.entity.Product;
import com.javapractice.thymeleaf.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    IProductRepository iProductRepository;

    public String getProducts(Model model, String keyword) {
        try {
            List<Product> products = new ArrayList<Product>();

            if (keyword == null) {
                iProductRepository.findAll().forEach(products::add);
            } else {
                iProductRepository.findByProductNameContainingIgnoreCase(keyword).forEach(products::add);
                model.addAttribute("keyword", keyword);
            }

            model.addAttribute("products", products);
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
        }

        return "products";
    }

    public String addProducts(Model model) {
        Product product = new Product();
        product.setAvailability(true);

        model.addAttribute("product", product);
        model.addAttribute("pageTitle", "Create new Product Details");

        return "product_form";
    }

    public String productSave(Product product, RedirectAttributes redirectAttributes) {
        try {
            iProductRepository.save(product);

            redirectAttributes.addFlashAttribute("message", "The Product Details has been saved successfully!");
        } catch (Exception e) {
            redirectAttributes.addAttribute("message", e.getMessage());
        }

        return "redirect:/products";
    }

    public String productEdit(Integer productId, Model model, RedirectAttributes redirectAttributes) {
        try {
            Product product = iProductRepository.findById(productId).get();

            model.addAttribute("product", product);
            model.addAttribute("pageTitle", "Edit Product Details (ProductName: " + product.getProductName() + ")");

            return "product_form";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());

            return "redirect:/products";
        }
    }

    public String productDelete(Integer productId, RedirectAttributes redirectAttributes) {
        try {
            iProductRepository.deleteById(productId);

            redirectAttributes.addFlashAttribute("message", "The Product with productId=" + productId + " has been deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }

        return "redirect:/products";
    }

    public String updateProduct(Integer productId, boolean availability, RedirectAttributes redirectAttributes) {
        try {
            iProductRepository.updateAvailabilityStatus(productId, availability);

            String status = availability ? "enabled" : "disabled";
            String message = "The availability of Product Id=" + productId + " has been " + status;

            redirectAttributes.addFlashAttribute("message", message);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }

        return "redirect:/products";
    }
}
