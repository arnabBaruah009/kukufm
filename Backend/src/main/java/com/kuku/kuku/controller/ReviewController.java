package com.kuku.kuku.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kuku.kuku.entity.Review;
import com.kuku.kuku.service.ReviewService;

@RestController
@RequestMapping("/api")
public class ReviewController {
	@Autowired
	private ReviewService reviewService;
	@PostMapping("/writeReview")
	public Review writeReview(@RequestBody Map<String,String> req,@RequestParam("bookId") Integer bookId,@RequestParam("customerId") Integer customerId) {
		return reviewService.writeReview(req, bookId, customerId);
	}
}
