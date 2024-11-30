package com.frosty.riders.review.service.controllers;

import com.frosty.riders.review.service.entity.Product;
import com.frosty.riders.review.service.entity.Review;
import com.frosty.riders.review.service.model.request.FindReviewRequest;
import com.frosty.riders.review.service.model.request.ReviewRequest;
import com.frosty.riders.review.service.service.ProductService;
import com.frosty.riders.review.service.service.ReviewService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class FrontendController {

    private final ReviewService reviewService;
    private final ProductService productService;

    @GetMapping("/")
    public String addReviewForm(final Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        model.addAttribute("review", new ReviewRequest());
        return "add-review-form";
    }

    @PostMapping("/add-review")
    public String addReview(@ModelAttribute @Valid ReviewRequest review, BindingResult result, Model model) {
        reviewService.createReview(review);
        return "redirect:/?success=true";
    }

    @GetMapping("/find-review-form")
    public String findReviewForm(final Model model, HttpServletRequest request) {
        model.addAttribute("review", new FindReviewRequest());
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
        if (inputFlashMap != null) {
            Review review = (Review) inputFlashMap.get("review");
            model.addAttribute("reviewDetails", review);
        }
        return "find-review-form";
    }

    @PostMapping("/find-review")
    public RedirectView findReview(@ModelAttribute @Valid FindReviewRequest review, BindingResult result, Model model, RedirectAttributes redirectAttrs) {
        Review reviewById = reviewService.findById(review.getReviewId());
        redirectAttrs.addFlashAttribute("review", reviewById);
        return new RedirectView("/find-review-form", true);
    }

}
