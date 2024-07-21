package com.kuku.kuku.service;

import java.util.Date;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kuku.kuku.entity.Book;
import com.kuku.kuku.entity.Customer;
import com.kuku.kuku.entity.Review;
import com.kuku.kuku.repository.BookRepository;
import com.kuku.kuku.repository.CustomerRepository;
import com.kuku.kuku.repository.ReviewRepository;

@Service
public class ReviewService {
	@Autowired
	private BookRepository bookRepo;
	@Autowired
	private CustomerRepository customerRepo;
	@Autowired
	private ReviewRepository reviewRepo;
	
	public Review writeReview(Map<String, String> req,Integer bookId,Integer customerId) {
		Review review=new Review();
		review.setComment(req.get("comment"));
		review.setHeadline(req.get("headline"));
		review.setReviewTime(new Date());
		Optional<Book> result=bookRepo.findById(bookId);
		if(result.isPresent()) {
			review.setBook(result.get());
		}
		Optional<Customer> res=customerRepo.findById(customerId);
		if(res.isPresent()) {
			review.setCustomer(res.get());
		}
		return reviewRepo.save(review);
	}
}
