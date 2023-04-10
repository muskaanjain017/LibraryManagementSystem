package com.example.librarymanagementsystem.Service;

import com.example.librarymanagementsystem.DTO.BookResponseDTO;
import com.example.librarymanagementsystem.Entity.Author;
import com.example.librarymanagementsystem.Entity.Book;
import com.example.librarymanagementsystem.Repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorService {
    @Autowired
    AuthorRepository authorRepository;
    public String add_author(Author author) {
        authorRepository.save(author);
        return "Author details added successfully";
    }

    public List<Author> get_author() {
        return authorRepository.findAll();
    }

    public Author getById_author(int id) {
        return authorRepository.findById(id).get();
    }

    public List<BookResponseDTO> getListOfBooks(int id) {
        List<BookResponseDTO> listOfBooks = new ArrayList<>();
        Author author = authorRepository.findById(id).get();
        List<Book> list= author.getListOfBooks();
        for(int i=0; i<list.size(); i++){
            BookResponseDTO bookResponseDTO = new BookResponseDTO();
            bookResponseDTO.setTitle(list.get(i).getTitle());
            bookResponseDTO.setIssued(list.get(i).isIssued());
            listOfBooks.add(bookResponseDTO);
        }
        return listOfBooks;
    }
}
