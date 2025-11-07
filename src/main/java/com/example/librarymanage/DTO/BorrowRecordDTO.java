package com.example.librarymanage.DTO;

import com.example.librarymanage.model.BorrowRecord;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BorrowRecordDTO {

    public String username;
    public Integer bookId;
    public String bookTittle;
    public LocalDateTime startDate;
    public LocalDateTime endDate;

    public BorrowRecordDTO() {
    }

    public BorrowRecordDTO(BorrowRecord borrowRecord) {
        this.username = borrowRecord.getAccount().getUsername();
        this.bookId = borrowRecord.getBook().getId();
        this.bookTittle = borrowRecord.getBook().getTitle();
        this.startDate = borrowRecord.getStartDate();
        this.endDate = borrowRecord.getEndDate();
    }
}
