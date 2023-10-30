package com.mockito.testdoubles.fake;

import java.util.Collection;

public class BookService {

	private final BookRepository bookRepository;

	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
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
}
