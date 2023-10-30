package com.mockito.testdoubles.fake;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FakeTest {

	@Test
	public void testFake() {
		BookRepository bookRepository = new FakeBookRepository();
		BookService bookService = new BookService(bookRepository);

		bookService.addBook(new Book("1234", "Mockito for Beginners", 200, LocalDate.now()));
		bookService.addBook(new Book("1236", "JUnit5 for Beginners", 170, LocalDate.now()));

		assertEquals(2, bookService.findNumberOfBooks());

	}

}
