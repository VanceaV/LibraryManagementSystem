package com.smoothstack.entity;

public class BookCopies extends Entity {

	private Book book;
	private LibraryBranch libraryBranch;
	private int noOfCopies;

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public LibraryBranch getLibraryBranch() {
		return libraryBranch;
	}

	public void setLibraryBranch(LibraryBranch libraryBranch) {
		this.libraryBranch = libraryBranch;
	}

	public int getNoOfCopies() {
		return noOfCopies;
	}

	public void setNoOfCopies(int noOfCopies) {
		this.noOfCopies = noOfCopies;
	}

	public void decrementNumberOfCopies() {
		if (noOfCopies > 0)
			noOfCopies--;
	}

	public void incrementNumberOfCopies() {
		noOfCopies++;
	}

	@Override
	public String toString() {
		return "BookID=[" + book.getId() + "] LibraryBranch=[" + libraryBranch.getId() + "] Number of Copies = "
				+ noOfCopies;
	}

}
