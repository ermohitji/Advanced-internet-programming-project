package com.exampleSQLIntergation.demoSql.controller;

import com.exampleSQLIntergation.demoSql.model.BModel;
import com.exampleSQLIntergation.demoSql.service.BService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
public class BController{

    @Autowired
    BService bService;

    private static Logger logger = LoggerFactory.getLogger(BController.class);

    public BController(BService bService) {
        this.bService = bService;
    }

    @PutMapping("/book/insert")
    public void insertBook(@RequestParam("name") String name,
                           @RequestParam("author") String author,
                           @RequestParam("price") int price) throws SQLException {
        //using lombok
        BModel book = BModel.builder()
                .name(name)
                .author(author)
                .price(price).build();
        bService.save(book); //call will go to service
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////
    @PutMapping("book/update")
    public void update(@RequestBody BModel book) throws SQLException {
        bService.bookUpdate(book);
    }

    @DeleteMapping("book/delete")
    public void delete(@RequestParam("id") int id) throws SQLException {
        bService.deleteBook(id);
    }

    @GetMapping("book/getting")
    public BModel getting(@RequestParam("id") int id) throws SQLException {
        return bService.gettingBook(id);
    }
}

////////////////////////////////////////////////////////
// JPA - java persistance api
// has the contracts such as get, save,flush etc whose implementation is provided by hibernate
