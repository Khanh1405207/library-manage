package com.example.librarymanage.repository;

import com.example.librarymanage.model.Account;
import com.example.librarymanage.model.Books;
import com.example.librarymanage.model.BorrowRecord;
import com.example.librarymanage.model.BorrowRecordId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, BorrowRecordId> {

    public List<BorrowRecord> findBorrowRecordsByAccount_Id(Integer id);

    public BorrowRecord findBorrowRecordByAccount_IdAndBook_Id(Integer accountId, Integer bookId);

    public Boolean existsByAccount_IdAndBook_IdAndEndDateIsNull(Integer accountId, Integer bookId);
}
