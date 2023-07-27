package com.example.crudDemo.Modules.Student;

import com.example.crudDemo.Modules.Crud.CrudService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentService extends CrudService {
    @Autowired
    private StudentRepo repo;

    public Student store(Student student){
        return repo.save(student);
    }

    public Page<Student> index(HttpServletRequest request){
        Map<String, String> params = new HashMap<>();

        for (Map.Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
            params.put(entry.getKey(), String.join(",", entry.getValue()));
        }

        getFilterFields(params);
        getFilterOptions(params);

//      pagination
        Pageable pageable = PageRequest.of(
                Integer.parseInt(params.getOrDefault("page", "0")),
                Integer.parseInt(params.getOrDefault("size", "10"))

        );

        return repo.findAll(pageable);
    }

    public List<Student> findByName(String name){
//        return repo.findByName(name);
//        return repo.findByNameContaining(name);
        return repo.findByNameContainingIgnoreCase(name);
    }


    public List<Student> findByAge(int age){
        return repo.findByAge(age);
    }

    public Student show(int id){
        return repo.findById(id).orElse(null);
    }

    public Student update(int id, Student student){
        Student oldStudent = repo.findById(id).orElse(null);

        oldStudent.setName(student.getName());
        oldStudent.setAge(student.getAge());
        oldStudent.setAddress(student.getAddress());
        return repo.save(oldStudent);
    }

    public String destroy(int id){
        repo.deleteById(id);
        return "Student with id " + id + " was deleted";
    }


}

