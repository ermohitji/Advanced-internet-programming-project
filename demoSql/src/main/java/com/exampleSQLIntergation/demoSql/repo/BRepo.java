package com.exampleSQLIntergation.demoSql.repo;

import com.exampleSQLIntergation.demoSql.model.BModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class BRepo {
    private static Logger logger = LoggerFactory.getLogger(BRepo.class);
    private Connection connection;
    BRepo() throws SQLException {
        logger.info("inside BRepo constructor");
        createTable();
    }
    public void createTable() throws SQLException {
        String sql = "create table IF NOT EXISTS book_autoInc(id int primary key auto_increment, name varchar(30),price int, author varchar(30));";
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dummy_db","root","mysql");
        Statement statement = connection.createStatement();
        statement.execute(sql);
    }
    public void insert(BModel book) throws SQLException {
        logger.info("inside BRepo constructor insert method");
        String sql = "insert into book_autoInc (name,price,author) values(?,?,?);";  //static query generally compiled at compile time itself
        PreparedStatement stmt = connection.prepareStatement(sql);

        //new field name createddate
        stmt.setString(1,book.getName());
        stmt.setFloat(2,book.getPrice());
        stmt.setString(3, book.getAuthor());

        stmt.execute();//  -----> will return boolean only when some result set is there if not then return false

        // stmt.executeUpdate();----> it returns the no. of records updated

        //stmt.executeQuery();----> gives the result set mainly with query
    }
    public void update(BModel book) throws SQLException{
        logger.info("inside bRepo constructor update method");
        String sql = "update book_autoInc set price=?, name =?,author= ?"+"where id=?;";
        PreparedStatement stmt = connection.prepareStatement(sql);

        //new filled name createddate
        stmt.setFloat(1,book.getPrice());
        stmt.setString(2,book.getName());
        stmt.setString(3, book.getAuthor());
        stmt.setInt(4,book.getId());
        int noOfRecords = stmt.executeUpdate();
        logger.info("no. of updated records "+ noOfRecords);
    }

    public void delete(int BookId) throws SQLException {
        logger.info("inside bRepo constructor delete method");
        String sql = "delete from book_autoInc where id=?;";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1,BookId);
        int noOfRecords = stmt.executeUpdate();
        logger.info("No. of records deleted: "+noOfRecords);
    }

    public BModel getting(int BookId) throws SQLException {
        logger.info("inside bRepo constructor getting method");
        String sql = "select * from book_autoInc where id=?;";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1,BookId);
        ResultSet resultSet = stmt.executeQuery();
        logger.info("ID "+resultSet.getInt(BookId));
        logger.info("ID "+resultSet.getInt("name"));
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String author = resultSet.getString("author");
        BModel book = BModel.builder().id(id).name(name).author(author).build();
        return book;
    }


}
