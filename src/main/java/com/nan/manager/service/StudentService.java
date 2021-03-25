package com.nan.manager.service;


import com.nan.manager.model.Student;
import com.nan.manager.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository repository){
        studentRepository = repository;
    }

    @Cacheable(value = "manager/students", key = "#ID")
    @Transactional(readOnly = true)
    public Iterable<Student> findStudentById(Integer ID){
        return studentRepository.findAllById(Collections.singleton(ID));
    }

    @Cacheable(value = "manager/students", key = "#name")
    @Transactional(readOnly = true)
    public Iterable<Student> findStudentByName(String name) {
        return studentRepository.findStudentByName(name);
    }

    @Cacheable(value = "manager/students")
    @Transactional(readOnly = true)
    public Iterable<Student> findAllStudents(){
        return studentRepository.findAll();
    }


    @Transactional
    @CacheEvict(value = "manager/students", allEntries = true)
    public void saveStudent(Student student) {
        studentRepository.save(student);
    }

    @Transactional
    @CacheEvict(value = "manager/students", allEntries = true)
    public boolean deleteStudent(Integer id) {
        if(studentRepository.existsById(id)){
            studentRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Transactional
    @CacheEvict(value = "manager/students", allEntries = true)
    public void updateStudent(Student student) {
        if(studentRepository.existsById(student.getId())){
            studentRepository.deleteById(student.getId());
            studentRepository.save(student);
        }
    }
}
