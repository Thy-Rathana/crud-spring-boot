package com.example.crudDemo.Modules.Student;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class StudentController {

    @Autowired
    private StudentService service;

    @GetMapping("/student")
    public Page<Student> index(HttpServletRequest request){
        return service.index(request);
    }

    @PostMapping("/student")
    public Student store(@RequestBody Student student){
        return service.store(student);
    }

    @GetMapping("/student/{id}")
    public Student show(@PathVariable int id){
        return service.show(id);
    }

    @GetMapping("/student/{id}/{name}")
    public List<Student> show(@PathVariable int id, @PathVariable String name){
        return service.findByName(name);
    }

    @PutMapping("/student/{id}")
    public Student update(@PathVariable int id, @RequestBody Student student){
        return service.update(id, student);
    }

    @DeleteMapping("/student/{id}")
    public String destroy(@PathVariable int id){
        return service.destroy(id);
    }

//    @GetMapping("/")
//    public RedirectView redirectToIndex() {
//        return new RedirectView("/index.html");
//    }
//
//// return static json
//    @GetMapping("/test")
//    public Student test(){
//        return new Student(1, "test", 20, "test");
//    }
}
