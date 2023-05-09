package com.sportrader.board.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ScoreRequest {
    Long matchId;
    int homeTeamScore;
    int awayTeamScore;

    public ScoreRequest(Long matchId, int homeTeamScore, int awayTeamScore) {
    this.matchId=matchId;
    this.homeTeamScore=homeTeamScore;
    this.awayTeamScore=awayTeamScore;
    }
}
