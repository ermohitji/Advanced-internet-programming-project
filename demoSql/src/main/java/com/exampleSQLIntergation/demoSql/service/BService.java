package com.exampleSQLIntergation.demoSql.service;

import com.exampleSQLIntergation.demoSql.model.BModel;
import com.exampleSQLIntergation.demoSql.repo.BRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class BService {
    @Autowired
    BRepo bRepo;
    public void save(BModel book) throws SQLException {
        //we want to insert book
        //write logic u suppose u want to manipulate data after that send the object to dao to inserted to db
        bRepo.insert(book);
    }

    public void bookUpdate(BModel book) throws SQLException {
        bRepo.update(book);
    }

    public void deleteBook(int BookId) throws SQLException {
        bRepo.delete(BookId);
    }

    public BModel gettingBook(int BookId) throws SQLException {
        return bRepo.getting(BookId);
    }

}
