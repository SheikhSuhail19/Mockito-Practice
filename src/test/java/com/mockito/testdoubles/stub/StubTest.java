package com.mockito.testdoubles.stub;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StubTest {

	@Test
	public void demoStub() {
		BookRepository bookRepository = new BookRepositoryStub();
		BookService bookService = new BookService(bookRepository);

		List<Book> newBooksWithAppliedDiscount = bookService.getNewBooksWithAppliedDiscount(10, 7);

		assertEquals(3, newBooksWithAppliedDiscount.size());
		assertEquals(180, newBooksWithAppliedDiscount.get(0).getPrice());
		assertEquals(90, newBooksWithAppliedDiscount.get(1).getPrice());
		assertEquals(1800, newBooksWithAppliedDiscount.get(2).getPrice());

	}

	@Test
	public void demoStubWithMockito() {
		BookRepository bookRepository = Mockito.mock(BookRepository.class);
		BookService bookService = new BookService(bookRepository);

		List<Book> newBooks = new ArrayList<>();
		Book book1 = new Book("1234", "Mockito for Beginners", 200, LocalDate.now());
		Book book2 = new Book("1235", "Junit for Beginners", 100, LocalDate.now());
		Book book3 = new Book("1236", "Java for Beginners", 2000, LocalDate.now());

		newBooks.add(book1);
		newBooks.add(book2);
		newBooks.add(book3);
		
		Mockito.when(bookRepository.findNewBooks(7)).thenReturn(newBooks);

		List<Book> newBooksWithAppliedDiscount = bookService.getNewBooksWithAppliedDiscount(10, 7);

		assertEquals(3, newBooksWithAppliedDiscount.size());
		assertEquals(180, newBooksWithAppliedDiscount.get(0).getPrice());
		assertEquals(90, newBooksWithAppliedDiscount.get(1).getPrice());
		assertEquals(1800, newBooksWithAppliedDiscount.get(2).getPrice());
	}

}
