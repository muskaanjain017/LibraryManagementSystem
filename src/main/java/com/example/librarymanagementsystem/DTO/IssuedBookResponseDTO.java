package com.example.librarymanagementsystem.DTO;

import com.example.librarymanagementsystem.Enum.TransactionStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class IssuedBookResponseDTO {
    private String transactionId;
    private String transactionNo;
    private String bookName;
    @Enumerated(EnumType.STRING)
    private TransactionStatus transactionStatus;
}
