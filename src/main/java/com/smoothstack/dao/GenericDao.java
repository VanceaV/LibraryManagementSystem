package com.smoothstack.dao;

import java.sql.Connection;
import java.util.List;

import com.smoothstack.entity.Entity;

public interface GenericDao<E extends Entity> {

	default List<E> getAll(Connection conn) {
		return null;
	}

	default E getById(Connection conn, int id) {
		return null;
	}

	default void add(Connection conn, E e) {
	}

	default void update(Connection conn, E e) {

	}

	default void delete(Connection conn, int id) {

	}
}
