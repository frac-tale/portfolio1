package com.example.Sample.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Sample.entity.Database;
import com.example.Sample.repository.DatabaseRepository;

@Service
@Transactional
public class DatabaseServiceImpl implements DatabaseService {
	
	private final DatabaseRepository repository;
	public DatabaseServiceImpl(DatabaseRepository repository) {
		this.repository = repository;
	}

	@Override
	public Iterable<Database> selectAll() {
		return repository.findAll();
	}
	
	@Override
	public Optional<Database> selectOneById(Integer id) {
		return repository.findById(id);
	}

	@Override
	public void insertDatabase(Database database) {
		repository.save(database);

	}

	@Override
	public void updateDatabase(Database database) {
		repository.save(database);

	}

	@Override
	public void deleteDatabaseById(Integer id) {
		repository.deleteById(id);

	}

}
