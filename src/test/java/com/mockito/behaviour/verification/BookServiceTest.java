package com.mockito.behaviour.verification;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

	@InjectMocks
	private BookService bookService;

	@Mock
	private BookRepository bookRepository;

	@Test
	public void testAddBook() {
		Book book = new Book(null, "Mockito in Reaction", 500, LocalDate.now());
		bookService.addBook(book);
//		verify(bookRepository).save(book);
	}

	@Test
	public void testSaveBookWithBookRequestWithGreaterPrice() {
		BookRequest bookRequest = new BookRequest("Mockito for Beginners", 500, LocalDate.now());
		Book book = new Book(null, "Mockito for Beginners", 500, LocalDate.now());
		bookService.addBook(bookRequest);
		verify(bookRepository, times(0)).save(book);
	}

	@Test
	public void testSaveBookWithBookRequestWithGreaterPrice1() {
		BookRequest bookRequest = new BookRequest("Mockito for Beginners", 700, LocalDate.now());
		Book book = new Book(null, "Mockito for Beginners", 700, LocalDate.now());
		bookService.addBook(bookRequest);
		verify(bookRepository).save(book);
		verify(bookRepository, times(1)).save(book);
	}

	@Test
	public void testSaveBookWithBookRequestWithGreaterPrice2() {
		BookRequest bookRequest = new BookRequest("Mockito for Beginners", 500, LocalDate.now());
		Book book = new Book(null, "Mockito for Beginners", 500, LocalDate.now());
		bookService.addBook(bookRequest);
		verify(bookRepository, never()).save(book);
	}

	@Test
	public void testUpdatePrice() {
//		bookService.updatePrice("1234", 800);
		bookService.updatePrice(null, 800);
		verifyNoInteractions(bookRepository);

	}

	@Test
	public void testUpdatePrice1() {
		Book book = new Book("1234", "Mockito for Beginners", 700, LocalDate.now());
		when(bookRepository.findBookById("1234")).thenReturn(book);
		bookService.updatePrice("1234", 700);
		verify(bookRepository).findBookById("1234");
		verify(bookRepository).save(book);
		verifyNoMoreInteractions(bookRepository);

	}

	@Test
	public void testUpdatePrice2() {
		Book book = new Book("1234", "Mockito for Beginners", 700, LocalDate.now());
		when(bookRepository.findBookById("1234")).thenReturn(book);
		bookService.updatePrice("1234", 700);

		InOrder inOrder = Mockito.inOrder(bookRepository);
		inOrder.verify(bookRepository).findBookById("1234");
		inOrder.verify(bookRepository).save(book);
		verifyNoMoreInteractions(bookRepository);

	}

	@Test
	public void testSaveBookWithBookRequestWithGreaterPrice3() {
		BookRequest bookRequest = new BookRequest("Mockito for Beginners", 600, LocalDate.now());
		Book book = new Book(null, "Mockito for Beginners", 600, LocalDate.now());
		bookService.addBook(bookRequest);
		bookService.addBook(bookRequest);
		bookService.addBook(bookRequest);
		bookService.addBook(bookRequest);
		bookService.addBook(bookRequest);
//		verify(bookRepository, times(2)).save(book);
		verify(bookRepository, atLeastOnce()).save(book);
		verify(bookRepository, atLeast(3)).save(book);
		verify(bookRepository, atMost(6)).save(book);
	}

}
