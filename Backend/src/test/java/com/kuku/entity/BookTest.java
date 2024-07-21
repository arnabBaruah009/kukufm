package com.kuku.entity;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.kuku.kuku.entity.Book;
import com.kuku.kuku.repository.BookRepository;

public class BookTest {

	@Autowired
	private BookRepository bookRepository;
	
	@Test
	public void addBooks() {

		Book book = new Book();
		String filePath = "C://Users//User//Downloads//NightCircus.jpg";
		try {
			// Create a File object representing the photo file
			File file = new File(filePath);

			// Check if the file exists
			if (!file.exists()) {
				System.out.println("File not found: " + filePath);
				return;
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
		book.setTitle("The Night Circus");
		book.setDescription("Immerse yourself in the enchanting world of \"The Night Circus,\" where a mysterious competition between two young magicians unfolds amidst a magical circus that appears without warning and mesmerizes its audience. Erin Morgenstern weaves a tale of love, rivalry, and wonder, blending intricate storytelling with vivid descriptions that transport listeners to a realm where dreams and reality intertwine. As the fates of the protagonists, Celia and Marco, become increasingly entwined, listeners are drawn deeper into a narrative that explores the power of creativity, the allure of illusion, and the complexities of human connection.");
	    book.setGenre("Entertainment");
	    book.setPrice(200.45f);
	    book.setWriter("Erin Morgenstern");
	    book.setIsbn("GH18EW411");
	    book.setPublished(new Date());
	    book.setRating(0);
	    bookRepository.save(book);
	}

}
