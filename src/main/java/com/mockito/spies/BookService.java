package com.mockito.spies;


public class BookService {

	public Book findBook(String bookId) {
		// Code to bring book from DB
		// return null;
		// OR "Method not implemented" Exception
		throw new RuntimeException("Method not implemented");
	}

	public int getAppliedDiscount(Book book, int discountRate) {
		int price = book.getPrice();
		int newPrice = price - (price * discountRate / 100);
		return newPrice;
	}
}
