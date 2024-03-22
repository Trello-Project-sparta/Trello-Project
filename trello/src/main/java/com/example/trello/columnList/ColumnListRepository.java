package com.example.trello.columnList;

import com.example.trello.columnList.entity.ColumnList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColumnListRepository extends JpaRepository<ColumnList, Long> {

}
