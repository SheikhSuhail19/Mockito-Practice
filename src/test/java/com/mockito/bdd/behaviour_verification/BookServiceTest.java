package com.mockito.bdd.behaviour_verification;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

	@InjectMocks
	private BookService bookService;

	@Mock
	private BookRepository bookRepository;

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
	public void test_Given_ABook_When_UpdatePriceIsCalledWithNewPrice_Then_BookPriceIsUpdated() {

		// Given - Arrange
		Book book = new Book("1234", "Mockito for Beginners", 700, LocalDate.now());
		given(bookRepository.findBookById("1234")).willReturn(book);

		// When - Act
		bookService.updatePrice("1234", 1700);

		// Then - Assert/Verify
		then(bookRepository).should().save(book);

	}

}
