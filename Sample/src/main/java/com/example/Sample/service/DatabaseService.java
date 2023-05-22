package com.example.Sample.service;

import java.util.Optional;

import com.example.Sample.entity.Database;

public interface DatabaseService {
	Iterable<Database> selectAll();
	Optional<Database> selectOneById(Integer id);
	
	void insertDatabase(Database database);
	void updateDatabase(Database database);
	void deleteDatabaseById(Integer id);
}
