package com.sportrader.board.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "matches")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Match {
    @Id
    @GeneratedValue
    Long id;

    @Column(name = "home_team")
    String homeTeam;

    @Column(name = "away_team")
    String awayTeam;

    @Column(name = "home_team_score")
    int homeTeamScore;

    @Column(name = "away_team_score")
    int awayTeamScore;

    @Column(name = "start_time")
    LocalDateTime startTime;

    @Column(name = "last_update_time")
    LocalDateTime lastUpdateTime;

    // Constructors, getters, and setters
    public Match(){
    }
    public Match(String homeTeam, String awayTeam){
        this.homeTeam=homeTeam;
        this.awayTeam=awayTeam;
    }

    public Match(Long id,String homeTeam, String awayTeam, int homeTeamScore, int awayTeamScore, LocalDateTime startTime) {
        this.id=id;
        this.homeTeam=homeTeam;
        this.awayTeam=awayTeam;
        this.homeTeamScore=homeTeamScore;
        this.awayTeamScore=awayTeamScore;
        this.startTime=startTime;
    }


    @PrePersist
    protected void prePersist() {
        lastUpdateTime = LocalDateTime.now();
        if (startTime == null) {
            startTime = LocalDateTime.now();
        }
    }
}

