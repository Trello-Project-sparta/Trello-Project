package com.example.trello.card;

import com.example.trello.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {

}
