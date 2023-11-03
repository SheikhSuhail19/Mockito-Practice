package com.mockito.bdd.stubbing;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StubTest {
	@InjectMocks
	private BookService bookService;

	@Mock
	private BookRepository bookRepository;

	@Test
	public void demoStubWithMockito() {

		Book book1 = new Book("1234", "Mockito for Beginners", 200, LocalDate.now());
		Book book2 = new Book("1235", "Junit for Beginners", 100, LocalDate.now());
		Book book3 = new Book("1236", "Java for Beginners", 2000, LocalDate.now());

		List<Book> newBooks = new ArrayList<>();

		newBooks.add(book1);
		newBooks.add(book2);
		newBooks.add(book3);

		when(bookRepository.findNewBooks(7)).thenReturn(newBooks);

		List<Book> newBooksWithAppliedDiscount = bookService.getNewBooksWithAppliedDiscount(10, 7);

		assertEquals(3, newBooksWithAppliedDiscount.size());
		assertEquals(180, newBooksWithAppliedDiscount.get(0).getPrice());
		assertEquals(90, newBooksWithAppliedDiscount.get(1).getPrice());
		assertEquals(1800, newBooksWithAppliedDiscount.get(2).getPrice());
	}

	@Test
	@DisplayName("Given - New books, When - Get new books with applied discount method is called, Then - New books with applied discount is returned")
	public void test_Given_NewBooks_When_GetNewBooksWithAppliedDiscountIsCalled_Then_NewBooksWithAppliedDiscountIsReturned() {

		// Arrange - Given

		Book book1 = new Book("1234", "Mockito In Action", 500, LocalDate.now());
		Book book2 = new Book("1235", "JUnit 5 In Action", 400, LocalDate.now());

		List<Book> newBooks = new ArrayList<>();
		newBooks.add(book1);
		newBooks.add(book2);

		given(bookRepository.findNewBooks(7)).willReturn(newBooks);

		// Act - When

		List<Book> newBooksWithAppliedDiscount = bookService.getNewBooksWithAppliedDiscount(10, 7);

		// Assert - Then

//		assertEquals(2, newBooksWithAppliedDiscount.size());
//		assertEquals(450, newBooksWithAppliedDiscount.get(0).getPrice());
//		assertEquals(360, newBooksWithAppliedDiscount.get(1).getPrice());

		// AssertJ - BDDAssertions

		then(newBooksWithAppliedDiscount).isNotNull();
		then(newBooksWithAppliedDiscount.size()).isEqualTo(2);
		then(newBooksWithAppliedDiscount.get(0).getPrice()).isEqualTo(450);


	}

}
