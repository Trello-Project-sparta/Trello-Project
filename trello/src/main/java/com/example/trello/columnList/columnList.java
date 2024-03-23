package com.example.trello.columnList;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor

@Entity
@Table(name = "columnlist")
public class columnList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long column_id;


    @Column(nullable = false)
    private String title;
}
