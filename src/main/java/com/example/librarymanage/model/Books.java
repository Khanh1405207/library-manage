package com.example.librarymanage.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "books",uniqueConstraints = @UniqueConstraint(columnNames = "book_code"))
public class Books {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "book_code")
    @NotBlank
    private String bookCode;

    @Column(name = "title")
    @NotBlank
    private String title;

    @Column(name = "author")
    @NotBlank
    private String author;

    @Column(name = "category")
    @NotNull
    private String category;

    @Column(name = "remaining")
    @Positive
    private Integer remaining;
}
