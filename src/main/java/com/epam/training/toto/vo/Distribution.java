package com.epam.training.toto.vo;

import lombok.Data;

import java.text.DecimalFormat;

@Data
public class Distribution {

    private int teamOneScore = 0;
    private int teamTwoScore = 0;
    private int drawScore = 0;

    public void teamOneWon() {
        teamOneScore++;
    }

    public void teamTwoWon() {
        teamTwoScore++;
    }

    public void draw() {
        drawScore++;
    }

    public String getTeamOneScore(DecimalFormat formatter) {
        double percent = (double) teamOneScore / (teamOneScore + teamTwoScore + drawScore);
        return formatter.format(percent);

    }

    public String getTeamTwoScore(DecimalFormat formatter) {
        double percent = (double) teamTwoScore / (teamOneScore + teamTwoScore + drawScore);
        return formatter.format(percent);

    }

    public String getDrawScore(DecimalFormat formatter) {
        double percent = (double) drawScore / (teamOneScore + teamTwoScore + drawScore);
        return formatter.format(percent);

    }


}
