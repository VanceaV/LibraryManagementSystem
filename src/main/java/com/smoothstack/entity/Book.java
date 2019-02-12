package com.smoothstack.entity;

public class Book extends Entity {

	private String title;
	private Author author;
	private Publisher publisher;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	public String forPrint() {
		return "" + getId() + ") " + title + " by " + author.getAuthorName();
	}

	@Override
	public String toString() {
		return "[" + getId() + "] Title " + title + " AuthorId " + author.getId() + " Publisher Id "
				+ publisher.getId();
	}
}