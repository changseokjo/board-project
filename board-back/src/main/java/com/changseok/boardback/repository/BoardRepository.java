package com.changseok.boardback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.changseok.boardback.entity.BoardEntity;
import java.util.List;


@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Integer> {

    boolean existsByBoardNumber(Integer boardNumber);

    BoardEntity findByBoardNumber(Integer boardNumber);

}
