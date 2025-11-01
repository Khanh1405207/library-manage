package com.example.librarymanage.repository;

import com.example.librarymanage.model.Books;
import com.example.librarymanage.model.BorrowRecord;
import com.example.librarymanage.model.BorrowRecordId;
import com.example.librarymanage.model.Borrower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, BorrowRecordId> {

    public List<BorrowRecord> findBorrowRecordsByBorrower(Borrower borrower);

    public Optional<BorrowRecord> findBorrowRecordByBorrowerAndBook(Borrower borrower,Books books);

    public Boolean existsByBorrowerAndBookAndEndDateIsNull(Borrower borrower,Books books);
}
