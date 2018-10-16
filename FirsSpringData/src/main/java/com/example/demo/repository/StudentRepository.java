package com.example.demo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.entity.Students;

public interface StudentRepository extends MongoRepository<Students, String> {

	public List<Students> findByNameLikeIgnoreCase(String name);

}
