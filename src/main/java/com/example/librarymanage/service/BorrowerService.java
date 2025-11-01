package com.example.librarymanage.service;

import com.example.librarymanage.model.Borrower;
import com.example.librarymanage.repository.BorrowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BorrowerService {

    @Autowired
    BorrowerRepository borrowerRepository;

    public List<Borrower> getAll(){
        return borrowerRepository.findAll();
    }

    public Borrower getOne(Integer id){
        return borrowerRepository.findById(id).get();
    }

    public void addBorrower(Borrower borrower){
        borrowerRepository.save(borrower);
    }

    public void updateBorrower(Borrower borrower){
        borrowerRepository.save(borrower);
    }

    public void deleteBorrower(Integer id){
        borrowerRepository.deleteById(id);
    }
}
