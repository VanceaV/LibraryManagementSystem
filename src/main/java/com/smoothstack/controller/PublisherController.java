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

import com.smoothstack.entity.Publisher;
import com.smoothstack.repository.PublisherRepository;

@RestController
@RequestMapping("/lms/administrator")
public class PublisherController {

	@Autowired
	private PublisherRepository publisherRepository;

	@GetMapping("/publishers")
	public ResponseEntity<List<Publisher>> getAllPublishers() {
		List<Publisher> list = publisherRepository.getAll();
		return new ResponseEntity<List<Publisher>>(list, HttpStatus.OK);
	}

	@PostMapping("/publishers/publisher")
	public ResponseEntity<Publisher> addPublisher(@RequestBody Publisher publisher) {
		publisherRepository.create(publisher);
		return new ResponseEntity<Publisher>(HttpStatus.CREATED);
	}

	@PutMapping("/publishers/publisher")
	public ResponseEntity<Publisher> updatePublisher(@RequestBody Publisher publisher) {
		publisherRepository.update(publisher);
		return new ResponseEntity<Publisher>(HttpStatus.OK);
	}

	@DeleteMapping("/publishers/publisher{publisherId}")
	public ResponseEntity<Publisher> deletePublisher(@PathVariable long publisherId) {
		publisherRepository.delete(publisherId);
		return new ResponseEntity<Publisher>(HttpStatus.ACCEPTED);
	}
	
}

