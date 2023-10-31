package com.mockito.testdoubles.mock;

public class BookService {

	private final BookRepository bookRepository;

	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	public void addBook(Book book) {
		if(book.getPrice() > 1000)
			return;
		bookRepository.save(book);
	}

}
