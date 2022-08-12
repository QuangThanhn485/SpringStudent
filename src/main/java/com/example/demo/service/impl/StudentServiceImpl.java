package com.example.demo.service.impl;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Student;
import com.example.demo.service.*;
import com.example.demo.repositories.StudentRepository;

@Service
public class StudentServiceImpl  implements IStudentService {
    @Autowired
    StudentRepository studentRepository;

    @Override
    public Student create(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student update(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void delete(Long id) {
        Optional<Student> studentOpt = studentRepository.findById(id);
        Student student =  studentOpt.get();
        if(student != null){
            studentRepository.delete(student);
        }
    }

    @Override
    public Optional<Student> findById(Long id) {
        return studentRepository.findById(id);
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }
    
}
