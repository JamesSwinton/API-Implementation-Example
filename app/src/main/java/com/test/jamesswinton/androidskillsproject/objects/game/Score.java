
package com.test.jamesswinton.androidskillsproject.objects.GameObjects;

public class Score {

    private String winner;
    private Result halfTime, fullTime;

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public Result getResult() {
        return fullTime;
    }

    public void setResult(Result fullTime) {
        this.fullTime = fullTime;
    }

    public Result getHalfTime() {
        return halfTime;
    }

    public void setHalfTime(Result halfTime) {
        this.halfTime = halfTime;
    }
}
