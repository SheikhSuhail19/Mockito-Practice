package com.mockito.argument_matchers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

	@InjectMocks
	private BookService bookService;

	@Mock
	private BookRepository bookRepository;


	@Test
	public void testUpdatePrice() {
		Book book1 = new Book("1234", "Mockito for Beginners", 600, LocalDate.now());
		Book book2 = new Book("1234", "Mockito for Beginners", 700, LocalDate.now());

		when(bookRepository.findBookById(any())).thenReturn(book1);

		bookService.updatePrice("1234", 700);

		verify(bookRepository).save(book2);

	}

	@Test
	public void testInvalidUseOfArgumentMatchers() {
		Book book = new Book("1234", "Mockito for Beginners", 600, LocalDate.now());
		when(bookRepository.findBookByTitleAndPublishedDate(eq("Mockito for Beginners"), any())).thenReturn(book);
		Book actualBook = bookService.getBookByTitleAndPublishedDate("Mockito for Beginners", LocalDate.now());
		assertEquals("Mockito for Beginners", actualBook.getTitle());


		// Argument Matchers should be provided for all arguments
		// Argument Matchers can't be used outside stubbing/verification
	}


	@Test
	public void testSpecificTypeOfArgumentMatchers() {
		Book book = new Book("1234", "Mockito In Action", 600, LocalDate.now());
		when(bookRepository.findBookByTitleAndPriceAndIsDigital(anyString(), anyInt(), anyBoolean())).thenReturn(book);
		Book actualBook = bookService.getBookByTitleAndPriceAndIsDigital("Mockito In Action", 600, true);
		assertEquals("Mockito In Action", actualBook.getTitle());
	}

	@Test
	public void testCollectionTypeArgumentMatchers() {
		List<Book> books = new ArrayList<>();
		Book book = new Book("1234", "Mockito In Action", 600, LocalDate.now());
		books.add(book);
		books.add(book);
		books.add(book);
		bookService.addBooks(books);
		verify(bookRepository).saveAll(anyList()); // anyList anySet, anyMap, OR anyCollection
	}

	@Test
	public void testStringTypeArgumentMatchers() {
		Book book = new Book("1234", "Mockito In Action", 600, LocalDate.now());
//		when(bookRepository.findBookByTitleAndPriceAndIsDigital(startsWith("Mockito"), anyInt(), anyBoolean())).thenReturn(book);
		when(bookRepository.findBookByTitleAndPriceAndIsDigital(contains("Action"), anyInt(), anyBoolean())).thenReturn(book);
		// Other methods -> endsWith(), matches()

//		Book actualBook = bookService.getBookByTitleAndPriceAndIsDigital("Mockito In Action", 600, true);
		Book actualBook = bookService.getBookByTitleAndPriceAndIsDigital("Junit 5 In Action", 600, true);
		assertEquals("Mockito In Action", actualBook.getTitle());
	}

}
