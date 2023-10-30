package com.mockito.testdoubles.stub;

import org.junit.jupiter.api.Test;

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

}
