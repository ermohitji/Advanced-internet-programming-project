package com.exampleSQLIntergation.demoSql.model;

import lombok.*;

import javax.persistence.Entity;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BModel {
    int id;
    String name;
    String author;
    float price;
    String publisher;
    long editionNo;
}
