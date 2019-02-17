package com.smoothstack.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smoothstack.entity.Author;
import com.smoothstack.entity.Book;
import com.smoothstack.repository.AuthorRepository;

@RestController
@RequestMapping("/lms/administrator")
public class AuthorController {

	@Autowired
	private AuthorRepository authorRepository;

	@GetMapping("/authors")
	public ResponseEntity<List<Author>> getAllAuthors() {
		List<Author> list = authorRepository.getAll();
		return new ResponseEntity<List<Author>>(list, HttpStatus.OK);
	}

	@PostMapping("/authors/author")
	public ResponseEntity<Book> addAuthor(@RequestBody Author author) {
		authorRepository.create(author);
		return new ResponseEntity<Book>(HttpStatus.CREATED);
	}

	@PutMapping("/authors/author")
	public ResponseEntity<Book> updateAuthor(@RequestBody Author author) {
		authorRepository.update(author);
		return new ResponseEntity<Book>(HttpStatus.OK);
		
	}

	@DeleteMapping("/authors/author{authorId}")
	public ResponseEntity<Book> deleteAuthor(@PathVariable long authorId) {
		authorRepository.delete(authorId);
		return new ResponseEntity<Book>(HttpStatus.ACCEPTED);
	}

}
