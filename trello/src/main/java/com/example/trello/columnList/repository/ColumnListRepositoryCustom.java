package com.example.trello.columnList.repository;

import com.example.trello.columnList.entity.ColumnList;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface ColumnListRepositoryCustom {

    List<ColumnList> searchAllMoreThanSearchAndLessThanSequence(Integer search, Integer sequence);

    List<ColumnList> searchAllMoreThanSearch(Integer sequence);
}
