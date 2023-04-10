package com.example.librarymanagementsystem.Service;

import com.example.librarymanagementsystem.DTO.StudentRequestDTO;
import com.example.librarymanagementsystem.DTO.StudentResponseDTO;
import com.example.librarymanagementsystem.Entity.LibraryCard;
import com.example.librarymanagementsystem.Entity.Student;
import com.example.librarymanagementsystem.Enum.Status;
import com.example.librarymanagementsystem.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.librarymanagementsystem.Enum.Status.ACTIVATED;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;
    public StudentResponseDTO add_student(StudentRequestDTO studentRequestDTO) {
        Student student = new Student();
        student.setName(studentRequestDTO.getName());
        student.setAge(studentRequestDTO.getAge());
        student.setDepartment(studentRequestDTO.getDepartment());
        student.setEmail(studentRequestDTO.getEmail());

        LibraryCard libraryCard = new LibraryCard();
        libraryCard.setValidTill("20-07-2024");
        libraryCard.setStatus(ACTIVATED);
        libraryCard.setStudent(student);
        student.setLibraryCard(libraryCard);
        studentRepository.save(student);

        StudentResponseDTO studentResponseDTO = new StudentResponseDTO();
        studentResponseDTO.setId(student.getId());
        studentResponseDTO.setName(student.getName());
        return studentResponseDTO;
    }

    public List<Student> get_student() {
        return studentRepository.findAll();
    }

    public StudentResponseDTO getById(int id) {
        StudentResponseDTO studentResponseDTO = new StudentResponseDTO();
        Student student = studentRepository.findById(id).get();
        studentResponseDTO.setId(student.getId());
        studentResponseDTO.setName(student.getName());
        return studentResponseDTO;
    }
}
