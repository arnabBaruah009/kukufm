package com.kuku.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kuku.entity.Book;
import com.kuku.repository.BookRepository;

@Service
public class BookService {
	@Autowired
	private BookRepository bookRepository;
	
	public List<Book> findAllBooks(String writer,String genre,float ratings){
		//System.out.println(writer+", "+genre+", "+ratings);
		if(writer.equals("null") && genre.equals("null") && ratings==0) {
			
			return bookRepository.findAll();
		}
		else if(genre.equals("null")) {
			if(writer.equals("null")) {
				return bookRepository.findAllBooksByRating(ratings);
			}
			else if(ratings==0) {
				return bookRepository.findAllBooksByWriter(writer);
			}
			else {
				return bookRepository.findBooksByRatingAndAuthor(writer, ratings);
			}
		}
		else if(writer.equals("null")) {
			if(ratings==0) {
				return bookRepository.findAllBooksByGenre(genre);
			}
			else {
				return bookRepository.findBooksByRatingAndGener(ratings, genre);
			}
		}
		else if(ratings==0) {
			return bookRepository.findBooksByGenerAndAuthor(writer, genre);
		}
		return bookRepository.findAllBooksByRatingAuthorGenre(writer, ratings, genre);
	}
	public Book findBookById(Integer id) {
		Optional<Book> result= bookRepository.findById(id);
		if(result.isPresent()) {
			return result.get();
		}
		return null;
	}
	public Book addBooks() {
		Book book = new Book();
		String filePath = "C://Users//User//Downloads//historyOfTime.jpg";
		try {
			// Create a File object representing the photo file
			File file = new File(filePath);

			// Check if the file exists
			if (!file.exists()) {
				System.out.println("File not found: " + filePath);
				return null;
			}

			// Create a FileInputStream to read the file
			FileInputStream fis = new FileInputStream(file);

			// Create a byte array to hold the bytes read from the file
			byte[] photoBytes = new byte[(int) file.length()];

			// Read bytes from the file into the byte array
			fis.read(photoBytes);

			// Close the FileInputStream
			fis.close();
			book.setImage(photoBytes);
			// Now you have the photoBytes array containing the photo data
			// You can process this byte array as needed (e.g., save to another file,
			// display in UI, etc.)

			System.out.println("Photo read successfully from file: " + filePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		book.setTitle("A Brief History of Time");
		book.setDescription("Stephen Hawking's \"A Brief History of Time\" takes listeners on a journey through the universe's most profound questions, from the origins of the cosmos to the nature of black holes and the possibility of time travel. With clarity and insight, Hawking explores complex theories of physics, including relativity and quantum mechanics, offering a glimpse into the scientific quest for a unified theory of everything. This audiobook remains a timeless exploration of the mysteries of the universe and our quest to understand its underlying principles.");
	    book.setGenre("Science");
	    book.setPrice(267.13f);
	    book.setWriter("Stephen Hawking");
	    book.setIsbn("JJ764GKJ31");
	    book.setPublished(new Date());
	    book.setRating(0);
	    return bookRepository.save(book);
	}
}

