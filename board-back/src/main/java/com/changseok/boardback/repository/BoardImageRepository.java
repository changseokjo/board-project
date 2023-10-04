package com.changseok.boardback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.changseok.boardback.entity.BoardImageEntity;
import java.util.List;


@Repository
public interface BoardImageRepository extends JpaRepository<BoardImageEntity, Integer> {
    
    List<BoardImageEntity> findByBoardNumber(Integer boardNumber);

}
