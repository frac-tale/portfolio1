package com.example.Sample.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.Sample.entity.Database;

public interface DatabaseRepository extends CrudRepository<Database, Integer> {

}
