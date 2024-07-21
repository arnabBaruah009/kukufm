package com.kuku.kuku.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kuku.kuku.entity.Book;
import com.kuku.kuku.service.BookService;

@RestController
@RequestMapping("/api/book")
public class BookController {
	@Autowired
	private BookService bookService;
	
	@GetMapping("/findAll")
	public List<Book> findAllBooks(){
		return bookService.findAllBooks();
	}
	
	@GetMapping("/find")
	public Book findBook(@RequestParam("id") Integer id) {
		return bookService.findBookById(id);
	}
	
	@GetMapping("/add")
	public Book addBook() {
		return bookService.addBooks();
	}
}
