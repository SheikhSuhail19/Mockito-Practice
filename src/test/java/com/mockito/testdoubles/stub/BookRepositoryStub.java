package com.mockito.testdoubles.stub;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BookRepositoryStub implements BookRepository {
	@Override
	public List<Book> findNewBooks(int days) {
		List<Book> newBooks = new ArrayList<>();
		Book book1 = new Book("1234", "Mockito for Beginners", 200, LocalDate.now());
		Book book2 = new Book("1235", "Junit for Beginners", 100, LocalDate.now());
		Book book3 = new Book("1236", "Java for Beginners", 2000, LocalDate.now());

		newBooks.add(book1);
		newBooks.add(book2);
		newBooks.add(book3);

		return newBooks;
	}

}
