package com.test.jamesswinton.androidskillsproject.sync;

import com.test.jamesswinton.androidskillsproject.objects.PremierLeagueGames;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface ApiInterface {

    @Headers({"X-Auth-Token: 367bdc66bcf44630baffbd4a6b8a2650"})
    @GET("competitions/{league}/matches")
    Call<PremierLeagueGames> getMatches(
            @Path("league") int league
    );
}
