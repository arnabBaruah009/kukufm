package com.kuku.kuku.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kuku.kuku.entity.Book;
import com.kuku.kuku.repository.BookRepository;

@Service
public class BookService {
	@Autowired
	private BookRepository bookRepository;
	
	public List<Book> findAllBooks(){
		return bookRepository.findAll();
	}
	public Book findBookById(Integer id) {
		Optional <Book> result= bookRepository.findById(id);
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
