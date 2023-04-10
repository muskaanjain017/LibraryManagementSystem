package com.example.librarymanagementsystem.Controller;

import com.example.librarymanagementsystem.DTO.StudentRequestDTO;
import com.example.librarymanagementsystem.DTO.StudentResponseDTO;
import com.example.librarymanagementsystem.Entity.Student;
import com.example.librarymanagementsystem.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentService studentService;
    @PostMapping("/add_student")
    public StudentResponseDTO add_student(@RequestBody StudentRequestDTO studentRequestDTO){

        return studentService.add_student(studentRequestDTO);
    }
    @GetMapping("/get_student")
    public List<Student> get_student(){
        return studentService.get_student();
    }
    @GetMapping("/getById")
    public StudentResponseDTO getById(@RequestParam("q") int id){
        return studentService.getById(id);
    }
}
