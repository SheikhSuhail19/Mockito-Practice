package com.mockito.testdoubles.mock;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

public class MockTest {

	@Test
	public void demoMock() {
		BookRepositoryMock bookRepositoryMock = new BookRepositoryMock();
		BookService bookService = new BookService(bookRepositoryMock);

		Book book1 = new Book("1234", "Mockito for Beginners", 1200, LocalDate.now());
		Book book2 = new Book("1235", "Junit for Beginners", 200, LocalDate.now());
		Book book3 = new Book("1236", "Java for Beginners", 2000, LocalDate.now());

		bookService.addBook(book1);
		bookService.addBook(book2);
		bookService.addBook(book3);

		bookRepositoryMock.verify(book2, 1);

	}

	@Test
	public void demoMockWithMockito() {
		BookRepository bookRepositoryMock = Mockito.mock(BookRepository.class);
		BookService bookService = new BookService(bookRepositoryMock);

		Book book1 = new Book("1234", "Mockito for Beginners", 1200, LocalDate.now());
		Book book2 = new Book("1235", "Junit for Beginners", 200, LocalDate.now());
		Book book3 = new Book("1236", "Java for Beginners", 2000, LocalDate.now());

		bookService.addBook(book1);
		bookService.addBook(book2);
		bookService.addBook(book3);

//		Mockito.verify(bookRepositoryMock).save(book1);
		Mockito.verify(bookRepositoryMock, Mockito.times(0)).save(book1);

		Mockito.verify(bookRepositoryMock).save(book2);
		Mockito.verify(bookRepositoryMock, Mockito.times(1)).save(book2);

	}

}
