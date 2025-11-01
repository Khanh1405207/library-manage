package com.example.librarymanage.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BorrowRecordId implements Serializable {

    private Integer borrower;
    private Integer book;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BorrowRecordId)) return false;
        BorrowRecordId that = (BorrowRecordId) o;
        return Objects.equals(borrower, that.borrower) && Objects.equals(book, that.book);
    }

    @Override
    public int hashCode() {
        return Objects.hash(borrower, book);
    }
}
