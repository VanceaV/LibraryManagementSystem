package com.smoothstack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.smoothstack.dao.LibraryBranchDao;
import com.smoothstack.entity.LibraryBranch;
import com.smoothstack.util.DbUtil;

import java.sql.Connection;
import java.util.List;

@RestController
public class LibraryBranchController {

	@Autowired(required = true)
	private LibraryBranchDao libraryBranchDao;
	Connection conn = DbUtil.getConnection();

	@RequestMapping(path = "/lms/libraryBranch/{libraryBranchId}", method = RequestMethod.GET)
	public LibraryBranch getLibraryBranchById(@PathVariable(name = "libraryBranchId") int id) {
		return libraryBranchDao.getById(conn, id);
	}

	@RequestMapping(path = "/lms/libraryBranchs", method = RequestMethod.GET)
	public List<LibraryBranch> getAllLibraryBranchs() {
		return libraryBranchDao.getAll(conn);
	}

	@RequestMapping(path = "/lms/libraryBranch", method = RequestMethod.POST)
	public void addLibraryBranch(@RequestBody LibraryBranch libraryBranch) {
		libraryBranchDao.add(conn, libraryBranch);
	}

	@RequestMapping(path = "/lms/libraryBranch", method = RequestMethod.PUT)
	public void updateLibraryBranch(@RequestBody LibraryBranch libraryBranch) {
		libraryBranchDao.update(conn, libraryBranch);
	}

	@RequestMapping(path = "/lms/libraryBranch", method = RequestMethod.DELETE)
	public void deleteLibraryBranch(@RequestBody int libraryBranchId) {
		libraryBranchDao.delete(conn, libraryBranchId);
	}
}
