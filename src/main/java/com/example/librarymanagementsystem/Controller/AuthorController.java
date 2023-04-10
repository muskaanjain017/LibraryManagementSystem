package com.example.librarymanagementsystem.Controller;

import com.example.librarymanagementsystem.DTO.BookResponseDTO;
import com.example.librarymanagementsystem.Entity.Author;
import com.example.librarymanagementsystem.Service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    AuthorService authorService;

    @PostMapping("/add")
    public String add_Author(@RequestBody Author author){
        return authorService.add_author(author);
    }
    @GetMapping("/get")
    public List<Author> get_Author(){
        return authorService.get_author();
    }

    @GetMapping("/getById/{id}")
    public Author getByAuthor(@PathVariable("id") int id){
        return authorService.getById_author(id);
    }
    @GetMapping("/getListOfBooks")
    public List<BookResponseDTO> getListOfBooks(@RequestParam("id")int id){
        return authorService.getListOfBooks(id);
    }
}
