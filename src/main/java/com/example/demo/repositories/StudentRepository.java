package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entities.Student;

public interface StudentRepository extends CrudRepository<Student, Long> {
    List<Student> findAll();
}                          
