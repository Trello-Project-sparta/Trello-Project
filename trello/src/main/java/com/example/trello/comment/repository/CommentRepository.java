package com.example.trello.comment.repository;

import com.example.trello.comment.entity.Comment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
//  List<Comment> findByCardId(Long cardId);
}
