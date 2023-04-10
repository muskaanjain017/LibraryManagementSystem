package com.example.librarymanagementsystem.DTO;

import com.example.librarymanagementsystem.Enum.Department;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentRequestDTO {
    private String name;
    private int age;
    @Enumerated(EnumType.STRING)
    private Department department;
    private String email;
}
