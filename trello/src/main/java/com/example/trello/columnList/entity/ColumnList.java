package com.example.trello.columnList.entity;


import com.example.trello.board.entity.Board;
import com.example.trello.card.Card;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor

@Entity
@Table(name = "columnList")
public class ColumnList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long columnId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Integer sequence;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

//    @OneToMany(mappedBy = "column", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Card> cards = new ArrayList<>();


    public ColumnList(String title, Integer sequence, Board board) {
        this.title = title;
        this.sequence = sequence;
        this.board = board;
    }

    public void updateTitle(String title) {
        this.title = title;
    }

    public void move(Integer sequence) {
        this.sequence = sequence;
    }

}
