package com.example.trello.card;


import com.example.trello.columnList.entity.ColumnList;
import com.example.trello.userBoard.entity.UserBoard;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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
  @JoinColumn(name = "userboard_id")
  private UserBoard userBoard;

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "card_workers", // 연결 테이블 이름
      joinColumns = @JoinColumn(name = "card_id"), // 현재 엔티티(Card) 측의 조인 컬럼
      inverseJoinColumns = @JoinColumn(name = "userboard_id") // 반대 측 엔티티(UserBoard)의 조인 컬럼
  )
  private List<UserBoard> workers = new ArrayList<>();

  @ManyToOne
  @JoinColumn(name = "column_id", nullable = false)
  private ColumnList column;

//    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Comment> comments = new ArrayList<>();

  public Card(CardRequestDto cardRequestDto, UserBoard userBoard, ColumnList column) {
    this.userBoard = userBoard;
    this.cardname = cardRequestDto.getCardname();
    this.description = cardRequestDto.getDescription();
    this.color = cardRequestDto.getColor();
    this.column = column;
    this.deadline = cardRequestDto.getDeadline();
  }

  public void updateCard(CardUpdateDto cardUpdateDto) {
    this.cardname = cardUpdateDto.getCardname();
    this.description = cardUpdateDto.getDescription();
    this.color = cardUpdateDto.getColor();
  }

  public void updateDeadline(LocalDateTime deadline) {
    this.deadline = deadline;
  }

  public void updateColumn(ColumnList column) {
    this.column = column;
  }


}
