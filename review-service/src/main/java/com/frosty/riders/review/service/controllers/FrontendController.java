package com.frosty.riders.review.service.controllers;

import com.frosty.riders.review.service.entity.Product;
import com.frosty.riders.review.service.model.request.ReviewRequest;
import com.frosty.riders.review.service.service.ProductService;
import com.frosty.riders.review.service.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class FrontendController {

    private final ReviewService reviewService;
    private final ProductService productService;

    @GetMapping("/add-review-form")
    public String test(final Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        model.addAttribute("review", new ReviewRequest());
        return "add-review-form";
    }

    @PostMapping("/add-review")
    public String addReview(@ModelAttribute @Valid ReviewRequest review, BindingResult result, Model model) {
        reviewService.createReview(review);
        return "review-added";
    }

    @GetMapping("/review-added")
    public String reviewAddded(final Model model) {
        return "review-added";
    }

}
