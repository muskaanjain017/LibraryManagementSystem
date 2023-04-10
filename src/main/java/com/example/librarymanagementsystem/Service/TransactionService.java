package com.example.librarymanagementsystem.Service;

import com.example.librarymanagementsystem.DTO.IssuedBookResponseDTO;
import com.example.librarymanagementsystem.DTO.IssuedBookRequestDTO;
import com.example.librarymanagementsystem.Entity.LibraryCard;
import com.example.librarymanagementsystem.Entity.Book;
import com.example.librarymanagementsystem.Entity.Transaction;
import com.example.librarymanagementsystem.Enum.Status;
import com.example.librarymanagementsystem.Enum.TransactionStatus;
import com.example.librarymanagementsystem.Repository.BookRepository;
import com.example.librarymanagementsystem.Repository.CardRepository;
import com.example.librarymanagementsystem.Repository.TransactionRepository;
import com.example.librarymanagementsystem.DTO.IssuedBookRequestDTO;
import com.example.librarymanagementsystem.DTO.IssuedBookResponseDTO;
import com.example.librarymanagementsystem.Entity.Book;
import com.example.librarymanagementsystem.Entity.LibraryCard;
import com.example.librarymanagementsystem.Entity.Transaction;
import com.example.librarymanagementsystem.Enum.Status;
import com.example.librarymanagementsystem.Enum.TransactionStatus;
import com.example.librarymanagementsystem.Repository.BookRepository;
import com.example.librarymanagementsystem.Repository.CardRepository;
import com.example.librarymanagementsystem.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TransactionService {

    @Autowired
    CardRepository libraryCardRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    TransactionRepository transactionRepository;


    public IssuedBookResponseDTO issueBook(IssuedBookRequestDTO issueBookRequestDTO) throws Exception {
        Transaction transaction = new Transaction();
        transaction.setTransactionNo(String.valueOf(UUID.randomUUID()));
        transaction.setIssueOperation(true);

        LibraryCard card;
        try{
            card = libraryCardRepository.findById(issueBookRequestDTO.getLibraryCardId()).get();
        }
        catch(Exception e){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transaction.setMessage("Invalid Card ID");
            transactionRepository.save(transaction);
            throw new Exception("Invalid Card ID");
        }

        Book book;
        try{
            book = bookRepository.findById(issueBookRequestDTO.getBookId()).get();
        }
        catch(Exception e){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transaction.setMessage("Invalid Book ID");
            transactionRepository.save(transaction);
            throw new Exception("Invalid Book ID");
        }

        transaction.setBook(book);
        transaction.setLibraryCard(card);

        if(card.getStatus() != Status.ACTIVATED){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transaction.setMessage("Card Not Activated");
            transactionRepository.save(transaction);
            throw new Exception("Your card is not activated!");
        }
        if(book.isIssued()==true){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transaction.setMessage("Book already issued");
            transactionRepository.save(transaction);
            throw new Exception("Sorry! Book is already issued");
        }

        transaction.setTransactionStatus(TransactionStatus.SUCCESS);
        transaction.setMessage("Book issued successfully");
        book.setIssued(true);
        book.setLibraryCard(card);
        book.getListOfTransaction().add(transaction);
        card.getListOfTransactions().add(transaction);
        card.getListOfBooks().add(book);

        libraryCardRepository.save(card);

        IssuedBookResponseDTO issueBookResponseDTO = new IssuedBookResponseDTO();
        issueBookResponseDTO.setBookName(book.getTitle());
        issueBookResponseDTO.setTransactionNo(transaction.getTransactionNo());
        issueBookResponseDTO.setTransactionStatus(transaction.getTransactionStatus());

        return issueBookResponseDTO;


    }
}