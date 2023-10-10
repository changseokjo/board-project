package com.changseok.boardback.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.changseok.boardback.entity.FavoriteEntity;
import com.changseok.boardback.entity.primaryKey.FavoritePK;

@Repository
public interface FavoriteRepository extends JpaRepository<FavoriteEntity, FavoritePK>{
        
    boolean existsByUserEmailAndBoardNumber(String userEmail, Integer boardNumber);
    
    @Transactional
    void deleteByBoardNumber(Integer boardNumber);
}
