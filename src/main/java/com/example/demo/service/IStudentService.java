package com.example.demo.service;
import java.util.List;
import java.util.Optional;


import com.example.demo.entities.Student;

public interface IStudentService {
    List<Student> findAll();
    Optional<Student> findById(Long id);
    Student create(Student student);
    Student update(Student student);
    void delete(Long id);

}
