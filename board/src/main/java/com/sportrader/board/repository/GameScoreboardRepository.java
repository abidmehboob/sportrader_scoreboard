package com.sportrader.board.repository;

import com.sportrader.board.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameScoreboardRepository extends JpaRepository<Match, Long> {
}