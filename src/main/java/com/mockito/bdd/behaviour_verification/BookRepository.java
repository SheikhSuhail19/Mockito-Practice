package com.mockito.bdd.behaviour_verification;

public interface BookRepository {

	void save(Book book);

	Book findBookById(String bookId);
}
