package com.sportrader.board.dto;

import java.time.LocalDateTime;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.reactivestreams.Publisher;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Game {

    Long id;
    String homeTeam;
    String awayTeam;
    Integer homeTeamScore;
    Integer awayTeamScore;
    LocalDateTime startTime;
    LocalDateTime endTime;

    public Game(Long id, String homeTeam, String awayTeam, int homeTeamScore, int awayTeamScore, LocalDateTime startTime) {
        this.id = id;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeTeamScore = homeTeamScore;
        this.awayTeamScore = awayTeamScore;
        this.startTime = startTime;
    }

    public Game(String homeTeam, String awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
    }

    public Game() {

    }


    // Constructors, getters and setters

}
