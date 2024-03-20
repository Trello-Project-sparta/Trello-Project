package com.example.trello.board.entity;

import com.example.trello.common.util.Timestamped;
import com.example.trello.team.entity.Team;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor

@Entity
@Table(name = "board")
public class Board extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;

    @Column(nullable = false)
    private String title;

    @Column
    private String background_color;

    @Column
    private String description;
    
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Team> teams = new HashSet<>();


    public Board(String title, String background_color, String description) {
        this.title = title;
        this.background_color = background_color;
        this.description = description;
    }


    public void update(String title, String background_color, String description) {
        this.title = title;
        this.background_color = background_color;
        this.description = description;
    }


}
