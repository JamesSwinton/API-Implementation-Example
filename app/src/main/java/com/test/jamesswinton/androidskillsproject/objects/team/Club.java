
package com.test.jamesswinton.androidskillsproject.objects.TeamObjects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Club {

    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("shortName")
    @Expose
    private String shortName;
    @SerializedName("tla")
    @Expose
    private String tla;
    @SerializedName("clubColors")
    @Expose
    private String clubColors;
    @SerializedName("squad")
    @Expose
    private List<Squad> squad = null;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getTla() {
        return tla;
    }

    public void setTla(String tla) {
        this.tla = tla;
    }

    public String getClubColors() {
        return clubColors;
    }

    public void setClubColors(String clubColors) {
        this.clubColors = clubColors;
    }

    public List<Squad> getSquad() {
        return squad;
    }

    public void setSquad(List<Squad> squad) {
        this.squad = squad;
    }
}
