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

import com.smoothstack.entity.LibraryBranch;
import com.smoothstack.repository.LibraryBranchRepository;

@RestController
@RequestMapping("/lms/administrator")
public class LibraryBranchController {

	@Autowired
	private LibraryBranchRepository libraryBranchRepository;

	@GetMapping("/libraryBranches")
	public ResponseEntity<List<LibraryBranch>> getAllLibraryBranches() {
		List<LibraryBranch> list = libraryBranchRepository.getAll();
		return new ResponseEntity<List<LibraryBranch>>(list, HttpStatus.OK);
	}

	@PostMapping("/libraryBranches/libraryBranch")
	public ResponseEntity<LibraryBranch> addLibraryBranch(@RequestBody LibraryBranch libraryBranch) {
		libraryBranchRepository.create(libraryBranch);
		return new ResponseEntity<LibraryBranch>(HttpStatus.CREATED);
	}

	@PutMapping("/libraryBranches/libraryBranch")
	public ResponseEntity<LibraryBranch> updateLibraryBranch(@RequestBody LibraryBranch libraryBranch) {
		libraryBranchRepository.update(libraryBranch);
		return new ResponseEntity<LibraryBranch>(HttpStatus.OK);
	}

	@DeleteMapping("/libraryBranches/libraryBranch{libraryBranchId}")
	public ResponseEntity<LibraryBranch> deleteLibraryBranch(@PathVariable long libraryBranchId) {
		libraryBranchRepository.delete(libraryBranchId);
		return new ResponseEntity<LibraryBranch>(HttpStatus.ACCEPTED);
		
	}
	

}
