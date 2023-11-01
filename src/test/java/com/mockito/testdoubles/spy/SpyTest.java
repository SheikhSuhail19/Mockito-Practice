package com.mockito.testdoubles.spy;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SpyTest {

	@Test
	public void demoSpy() {
		BookRepositorySpy bookRepositorySpy = new BookRepositorySpy();
		BookService bookService = new BookService(bookRepositorySpy);

		Book book1 = new Book("1234", "Mockito for Beginners", 200, LocalDate.now());
		Book book2 = new Book("1235", "Junit for Beginners", 100, LocalDate.now());
		Book book3 = new Book("1236", "Java for Beginners", 2000, LocalDate.now());

		bookService.addBook(book1);
		bookService.addBook(book2);
		bookService.addBook(book3);

		assertEquals(3, bookRepositorySpy.timesCalled());
		assertTrue(bookRepositorySpy.calledWith(book3));

	}

	@Test
	public void demoSpyWithMockito() {
		BookRepository bookRepositorySpy = Mockito.spy(BookRepository.class);
		BookService bookService = new BookService(bookRepositorySpy);

		Book book1 = new Book("1234", "Mockito for Beginners", 200, LocalDate.now());
		Book book2 = new Book("1235", "Junit for Beginners", 100, LocalDate.now());
		Book book3 = new Book("1236", "Java for Beginners", 2000, LocalDate.now());

		bookService.addBook(book1);
		bookService.addBook(book2);
		bookService.addBook(book3);

		Mockito.verify(bookRepositorySpy, Mockito.times(1)).save(book1);
		Mockito.verify(bookRepositorySpy, Mockito.times(1)).save(book2);

	}

}
