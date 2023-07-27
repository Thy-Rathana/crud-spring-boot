package com.example.crudDemo.Modules.Student;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepo extends JpaRepository<Student, Integer> {


    public List<Student> findByName(String name);

    public List<Student> findByNameContaining(String name);

    public List<Student> findByNameContainingIgnoreCase(String name);
    public List<Student> findByAge(int age);

}

