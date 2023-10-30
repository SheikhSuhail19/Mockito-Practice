package com.mockito.testdoubles.dummy;

import java.util.Collection;

public class BookService {

	private final BookRepository bookRepository;
	private final EmailService emailService;

	public BookService(BookRepository bookRepository, EmailService emailService) {
		this.bookRepository = bookRepository;
		this.emailService = emailService;
	}

	public void addBook(Book book) {
		bookRepository.save(book);
	}

	public Collection<Book> findAll() {
		return bookRepository.findAll();
	}

	public int findNumberOfBooks() {
		return bookRepository.findAll().size();
	}

	// Other methods which use the EmailService

}
