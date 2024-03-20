package com.example.trello.comment.repository;

import com.example.trello.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
  List<Comment> findAllByCard_CardId(Long cardId);


}
