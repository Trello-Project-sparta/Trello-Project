package com.example.trello.card;



import com.example.trello.columnList.entity.ColumnList;
import com.example.trello.user.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "card")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cardId;

    @Column(nullable = false)
    private String cardname;

    private String description;

    private String color;

    private LocalDateTime deadline;

    @ManyToOne(optional = false)
    @JoinColumn(name= "creator_id")
    private User creator;;

    @ManyToOne
    @JoinColumn(name = "worker_id")
    private User worker;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn (name = "column_id", nullable = false)
    private ColumnList column;

//    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Comment> comments = new ArrayList<>();

    public Card (CardRequestDto cardRequestDto, User creator, ColumnList column){
        this.creator = creator;
        this.cardname = cardRequestDto.getCardname();
        this.description = cardRequestDto.getDescription();
        this.color = cardRequestDto.getColor();
        this.column = column;
        this.deadline = cardRequestDto.getDeadline();
    }

    public void updateCard(CardUpdateDto cardUpdateDto){
        this.cardname = cardUpdateDto.getCardname();
        this.description = cardUpdateDto.getDescription();
        this.color = cardUpdateDto.getColor();
    }

    public void updateDeadline(LocalDateTime deadline){
        this.deadline = deadline;
    }

    public void updateColumn(ColumnList column){
        this.column = column;
    }

    public void updateWorker(User worker){ this.worker = worker; }




}
