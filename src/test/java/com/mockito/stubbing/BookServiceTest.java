package com.mockito.stubbing;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

	@InjectMocks
	private BookService bookService;

	@Mock
	private BookRepository bookRepository;

	@Test
	public void testCalculateTotalCostOfBooks() {
		List<String> bookIds = new ArrayList<>();
		bookIds.add("1234");
		bookIds.add("1235");
		bookIds.add("1236");

		Book book1 = new Book("1234", "Mockito for Beginners", 200, LocalDate.now());
		Book book2 = new Book("1235", "Junit for Beginners", 100, LocalDate.now());
		Book book3 = new Book("1236", "Java for Beginners", 2000, LocalDate.now());

//		when(bookRepository.findBookById("1234")).thenReturn(book1);
//		when(bookRepository.findBookById("1235")).thenReturn(book2);
//		when(bookRepository.findBookById("1236")).thenReturn(book3);

		doReturn(book1).when(bookRepository).findBookById("1234");
		doReturn(book2).when(bookRepository).findBookById("1235");
		doReturn(book3).when(bookRepository).findBookById("1236");

		int actualCost = bookService.calculateTotalCost(bookIds);

		assertEquals(2300, actualCost);


//		when(bookRepository.findBookById("1234")).thenReturn(book1, book1);
//		OR we can use
//		when(bookRepository.findBookById("1234"))
//				.thenReturn(book1)
//				.thenReturn(book1);
//
//		int actualCost = bookService.calculateTotalCost(bookIds);
//
//		assertEquals(400, actualCost);

	}

	/**
	 * Stubbing void method (doNothing)
	 */
	@Test
	public void testSaveBook() {
//		Book book1 = new Book("1234", "Mockito for Beginners", 200, LocalDate.now());
		Book book1 = new Book(null, "Mockito for Beginners", 200, LocalDate.now());
		doNothing().when(bookRepository).save(book1);
		bookService.addBook(book1);
	}

	@Test
	public void testSaveBookWithBookRequest() {
		BookRequest bookRequest = new BookRequest("Mockito for Beginners", 200, LocalDate.now());
		Book book = new Book(null, "Mockito for Beginners", 200, LocalDate.now());
		doNothing().when(bookRepository).save(book);
		bookService.addBook(bookRequest);
	}

}
