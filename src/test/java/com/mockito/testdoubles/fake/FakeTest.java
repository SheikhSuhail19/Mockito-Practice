package com.mockito.testdoubles.fake;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

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

	@Test
	public void testFakeWithMockito() {
		BookRepository bookRepository = Mockito.mock(BookRepository.class);
		BookService bookService = new BookService(bookRepository);

		Book book1 = new Book("1234", "Mockito for Beginners", 200, LocalDate.now());
		Book book2 = new Book("1236", "JUnit5 for Beginners", 170, LocalDate.now());

		Collection<Book> books = new ArrayList<>();
		books.add(book1);
		books.add(book2);

		Mockito.when(bookRepository.findAll()).thenReturn(books);

		assertEquals(2, bookService.findNumberOfBooks());

	}

}
