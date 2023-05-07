package com.sportrader.board.dto;

import java.time.LocalDateTime;

public class MatchSummary {
    int id;
    private String homeTeam;
    private String awayTeam;
    private int homeTeamScore;
    private int awayTeamScore;
    private int totalScore;
    private LocalDateTime startTime;
    private LocalDateTime lastUpdateTime;

    public MatchSummary(int id, String homeTeam, String awayTeam, int homeTeamScore, int awayTeamScore, int totalScore, LocalDateTime startTime, LocalDateTime lastUpdateTime) {
        this.id = id;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeTeamScore = homeTeamScore;
        this.awayTeamScore = awayTeamScore;
        this.totalScore = totalScore;
        this.startTime = startTime;
        this.lastUpdateTime = lastUpdateTime;
    }

    public MatchSummary(String homeTeam, String awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
    }

    public int getId() {
        return id;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public int getHomeTeamScore() {
        return homeTeamScore;
    }

    public int getAwayTeamScore() {
        return awayTeamScore;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getLastUpdateTime() {
        return lastUpdateTime;
    }
}
