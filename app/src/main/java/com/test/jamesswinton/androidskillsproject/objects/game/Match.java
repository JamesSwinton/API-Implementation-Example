
package com.test.jamesswinton.androidskillsproject.objects.GameObjects;

import java.util.List;

public class Match {

    private Long id, matchday;
    private String utcDate;
    private Team homeTeam, awayTeam;
    private Score score;
    private List<Referee> referees = null;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUtcDate() {
        return utcDate;
    }

    public void setUtcDate(String utcDate) {
        this.utcDate = utcDate;
    }

    public Long getMatchday() {
        return matchday;
    }

    public void setMatchday(Long matchday) {
        this.matchday = matchday;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    public List<Referee> getReferees() {
        return referees;
    }

    public void setReferees(List<Referee> referees) {
        this.referees = referees;
    }
}
