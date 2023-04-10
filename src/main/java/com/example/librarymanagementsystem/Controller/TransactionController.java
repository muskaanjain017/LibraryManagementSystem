package com.example.librarymanagementsystem.Controller;

import com.example.librarymanagementsystem.DTO.IssuedBookRequestDTO;
import com.example.librarymanagementsystem.DTO.IssuedBookResponseDTO;
import com.example.librarymanagementsystem.Service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/transaction")
public class TransactionController {
    @Autowired
    TransactionService transactionService;
    @PostMapping("/issueBook")
    public ResponseEntity issueBook(IssuedBookRequestDTO isissuedbookrequestDTO){
        IssuedBookResponseDTO issuedBookResponseDTO;
        try {
            issuedBookResponseDTO= transactionService.issueBook(isissuedbookrequestDTO);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(issuedBookResponseDTO, HttpStatus.ACCEPTED);
    }
}
