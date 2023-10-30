package com.mockito.testdoubles.dummy;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DummyTest {

	@Test
	public void demoDummy() {
		BookRepository bookRepository = new FakeBookRepository();
		EmailService emailService = new DummyEmailService();
		BookService bookService = new BookService(bookRepository, emailService);

		bookService.addBook(new Book("1234", "Mockito for Beginners", 200, LocalDate.now()));
		bookService.addBook(new Book("1236", "JUnit5 for Beginners", 170, LocalDate.now()));

		assertEquals(2, bookService.findNumberOfBooks());

	}

}
