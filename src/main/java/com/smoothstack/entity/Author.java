package com.smoothstack.entity;

public class Author extends Entity {

	private String authorName;

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	@Override
	public String toString() {
		return "Author [authorName=" + authorName + ", getId()=" + getId().intValue() + "]";
	}

	
	
	
	
}
