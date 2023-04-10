package com.example.librarymanagementsystem.Controller;

import com.example.librarymanagementsystem.DTO.BookRequestDTO;
import com.example.librarymanagementsystem.Entity.Book;
import com.example.librarymanagementsystem.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    BookService bookService;
    @PostMapping("/add")
    public String add_book(@RequestBody BookRequestDTO bookRequestDTO){
        try{
            return bookService.add_book(bookRequestDTO);
        }
        catch(Exception e){
            return "Book not added successfully because author not found";
        }
    }

}
