package com.changseok.boardback.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.changseok.boardback.entity.SearchLogEntity;
import com.changseok.boardback.repository.resultSet.SearchWordResultSet;

@Repository
public interface SearchLogRepository extends JpaRepository<SearchLogEntity, Integer>{
    
    @Query(
        value = 
        "SELECT search_word, COUNT(*) AS count " +
        "FROM search_log " +
        "WHERE relation IS FALSE " +
        "GROUP BY search_word " +
        "ORDER BY count DESC " +
        "LIMIT 15 ",
        nativeQuery = true
    )
    List<SearchWordResultSet> getPopularWordList();
}
