package com.test.jamesswinton.androidskillsproject.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.Toast;

import com.test.jamesswinton.androidskillsproject.R;
import com.test.jamesswinton.androidskillsproject.adapters.MatchDetailsAdapter;
import com.test.jamesswinton.androidskillsproject.fragments.MatchDetailsFragment;
import com.test.jamesswinton.androidskillsproject.objects.Match;
import com.test.jamesswinton.androidskillsproject.objects.PremierLeagueGames;
import com.test.jamesswinton.androidskillsproject.sync.SyncHandler;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static String HOME_TEAM_ID = "home-team-id";
    private static String AWAY_TEAM_ID = "away-team-id";
    private static String HOME_TEAM = "home-team";
    private static String AWAY_TEAM = "away-team";
    private static String HOME_TEAM_SCORE = "home-team-score";
    private static String AWAY_TEAM_SCORE = "away-team-score";
    private static String REFEREE = "ref";
    private static String KICK_OFF_TIME = "kick-off-time";

    private List<Match> matches;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getMatches(handleAPICallback());
    }

    private Callback<PremierLeagueGames> handleAPICallback() {
        return new Callback<PremierLeagueGames>() {
            @Override
            public void onResponse(Call<PremierLeagueGames> call, Response<PremierLeagueGames> response) {
                displayData(response.body());
            }

            @Override
            public void onFailure(Call<PremierLeagueGames> call, Throwable t) {
                handleFailure();
            }
        };
    }

    private void getMatches(Callback<PremierLeagueGames> getMatchesCallback) {
        SyncHandler.getMatches(getMatchesCallback);
    }

    private void displayData(PremierLeagueGames premierLeagueGames) {
        // Get Matches
        matches = premierLeagueGames.getMatches();

        // Init RecyclerView
        RecyclerView dataRecyclerView = findViewById(R.id.dataRecyclerView);
        dataRecyclerView.setHasFixedSize(true);
        dataRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Init Adapter
        MatchDetailsAdapter matchesAdapter = new MatchDetailsAdapter(matches, new MatchDetailsAdapter.MatchInteractionListener() {
            @Override
            public void onMatchSelected(Match match) {
                MatchDetailsFragment matchDetailsFragment = new MatchDetailsFragment();
                matchDetailsFragment.setArguments(setFragmentArguments(match));

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.matchDetailsHolder, matchDetailsFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }

            @Override
            public void bigSixMatchSelected() {
                Toast.makeText(MainActivity.this,
                        "Please subscribe to view Big Six games", Toast.LENGTH_LONG).show();
            }
        });
        dataRecyclerView.setAdapter(matchesAdapter);

        // Init Swipe / Move Handler
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(handleDataChanges(matchesAdapter));
        itemTouchHelper.attachToRecyclerView(dataRecyclerView);
    }

    private ItemTouchHelper.SimpleCallback handleDataChanges(MatchDetailsAdapter matchesAdapter) {
        return new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.ACTION_STATE_DRAG | ItemTouchHelper.RIGHT | ItemTouchHelper.UP | ItemTouchHelper.DOWN) {

            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                int swipeFlags = ItemTouchHelper.END;
                return makeMovementFlags(dragFlags, swipeFlags);
            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                Collections.swap(matches, viewHolder.getAdapterPosition(), target.getAdapterPosition());
                matchesAdapter.notifyItemMoved(viewHolder.getAdapterPosition(), target.getAdapterPosition());
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                matches.remove(viewHolder.getAdapterPosition());
                matchesAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            }

            @Override
            public boolean isItemViewSwipeEnabled() {
                return true;
            }

            @Override
            public boolean isLongPressDragEnabled() {
                return true;
            }
        };
    }

    private void handleFailure() {
        // TODO: Implement Http Failure
    }

    private Bundle setFragmentArguments(Match match) {
        Bundle fragmentArguments = new Bundle();
        fragmentArguments.putLong(HOME_TEAM_ID, match.getHomeTeam().getId());
        fragmentArguments.putLong(AWAY_TEAM_ID, match.getAwayTeam().getId());
        fragmentArguments.putString(HOME_TEAM, match.getHomeTeam().getName());
        fragmentArguments.putString(AWAY_TEAM, match.getAwayTeam().getName());
        fragmentArguments.putLong(HOME_TEAM_SCORE, match.getScore().getFullTime().getHomeTeam());
        fragmentArguments.putLong(AWAY_TEAM_SCORE, match.getScore().getFullTime().getAwayTeam());
        fragmentArguments.putString(REFEREE, match.getReferees().get(0).getName());
        fragmentArguments.putString(KICK_OFF_TIME, getDate(match.getUtcDate()));
        return fragmentArguments;
    }

    private String getDate(String date)
    {
        try {
            // Set Format Rules
            SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            SimpleDateFormat outputDateFormat = new SimpleDateFormat("E, d MMM - h:mma");
            // Set TimeZones
            inputDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            outputDateFormat.setTimeZone(TimeZone.getDefault());
            // Return Date
            date = outputDateFormat.format(inputDateFormat.parse(date));
        } catch (Exception e) {
            date = "Error parsing date";
        } return date;
    }
}
