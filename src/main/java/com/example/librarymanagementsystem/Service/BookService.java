package com.example.librarymanagementsystem.Service;

import com.example.librarymanagementsystem.DTO.BookRequestDTO;
import com.example.librarymanagementsystem.Entity.Author;
import com.example.librarymanagementsystem.Entity.Book;
import com.example.librarymanagementsystem.Repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {
    @Autowired
    AuthorRepository authorRepository;

    public String add_book(BookRequestDTO bookRequestDTO){
        Book book = new Book();
        book.setTitle(bookRequestDTO.getTitle());
        book.setPrice(bookRequestDTO.getPrice());
        book.setGenre(bookRequestDTO.getGenre());
        book.setIssued(false);
        Author author;
        try {
            author = authorRepository.findById(bookRequestDTO.getAuthor_id()).get();
        }
        catch(Exception e){
            return "Author not found";
        }
        book.setAuthor(author);
        author.getListOfBooks().add(book);
        authorRepository.save(author);
        return "Book added successfully";
    }
}
