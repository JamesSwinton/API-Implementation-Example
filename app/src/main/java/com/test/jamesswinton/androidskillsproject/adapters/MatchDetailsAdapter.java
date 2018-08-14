package com.test.jamesswinton.androidskillsproject.adapters;

import android.content.res.Resources;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.test.jamesswinton.androidskillsproject.R;
import com.test.jamesswinton.androidskillsproject.objects.Match;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

public class MatchesAdapter extends RecyclerView.Adapter<MatchesAdapter.ViewHolder> {

    // Data
    private Resources res;
    private List<Match> matches;
    private MatchInteractionListener matchInteractionListener;

    public MatchesAdapter(List<Match> matches, MatchInteractionListener matchInteractionListener) {
        this.matches = matches;
        this.matchInteractionListener = matchInteractionListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // Match Layout
        private CardView matchLayout;

        // Home Team Elements
        private RelativeLayout homeTeamLayout;
        private ImageView homeTeamLogo;
        private TextView homeTeamScore;
        private TextView homeTeamScoreHalfTime;

        // Away Team Elements
        private RelativeLayout awayTeamLayout;
        private ImageView awayTeamLogo;
        private TextView awayTeamScore;
        private TextView awayTeamScoreHalfTime;

        // Meta Data Elements
        private TextView referee;
        private TextView kickOffTime;

        private ViewHolder(View v) {
            super(v);
            res = v.getContext().getResources();

            matchLayout = v.findViewById(R.id.match_layout);
            homeTeamLayout = v.findViewById(R.id.home_team_layout);
            homeTeamLogo = v.findViewById(R.id.home_team_logo);
            homeTeamScore = v.findViewById(R.id.home_team_score);
            homeTeamScoreHalfTime = v.findViewById(R.id.home_team_score_half_time);
            awayTeamLayout = v.findViewById(R.id.away_team_layout);
            awayTeamLogo = v.findViewById(R.id.away_team_logo);
            awayTeamScore = v.findViewById(R.id.away_team_score);
            awayTeamScoreHalfTime = v.findViewById(R.id.away_team_score_half_time);
            referee = v.findViewById(R.id.referee);
            kickOffTime = v.findViewById(R.id.kick_off_time);
        }
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MatchesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_data_v2, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, final int position) {
        Match match = matches.get(position);
        vh.matchLayout.setOnClickListener(view ->
                matchInteractionListener.onMatchSelected(match));
        vh.matchLayout.setOnLongClickListener(view -> {
            vh.matchLayout.setAlpha(.5f);
            return false;
        });

        vh.matchLayout.setAlpha(1f);

        // Set Up Home Team Layout
        initHomeTeam(vh, match.getHomeTeam().getName());
        initAwayTeam(vh, match.getAwayTeam().getName());

        // Init Scores
        vh.homeTeamScore.setText(String.valueOf(match.getScore().getFullTime().getHomeTeam()));
        vh.homeTeamScoreHalfTime.setText(String.valueOf(match.getScore().getHalfTime().getHomeTeam()));
        vh.awayTeamScore.setText(String.valueOf(match.getScore().getFullTime().getAwayTeam()));
        vh.awayTeamScoreHalfTime.setText(String.valueOf(match.getScore().getHalfTime().getAwayTeam()));

        // Init Meta Data
        vh.referee.setText(match.getReferees().get(0).getName());
        vh.kickOffTime.setText(getDate(match.getUtcDate()));

        Log.i("MATCH ID", String.valueOf(match.getId()));
    }

    private String getDate(String date)
    {
        try
        {
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

    private void initHomeTeam(ViewHolder vh, String teamName) {
        switch(teamName) {
            case "AFC Bournemouth":
                vh.homeTeamLayout.setBackgroundColor(res.getColor(R.color.bournemouth));
                vh.homeTeamLogo.setImageDrawable(res.getDrawable(R.drawable.bournemouth));
                //vh.homeTeamShortName.setText(res.getString(R.string.bournemouth));
                break;
            case "Arsenal FC":
                vh.homeTeamLayout.setBackgroundColor(res.getColor(R.color.arsenal));
                vh.homeTeamLogo.setImageDrawable(res.getDrawable(R.drawable.arsenal));
                //vh.homeTeamShortName.setText(res.getString(R.string.arsenal));
                break;
            case "Brighton & Hove Albion FC":
                vh.homeTeamLayout.setBackgroundColor(res.getColor(R.color.brighton));
                vh.homeTeamLogo.setImageDrawable(res.getDrawable(R.drawable.brighton));
                //vh.homeTeamShortName.setText(res.getString(R.string.brighton));
                break;
            case "Burnley FC":
                vh.homeTeamLayout.setBackgroundColor(res.getColor(R.color.burnley));
                vh.homeTeamLogo.setImageDrawable(res.getDrawable(R.drawable.burnley));
                //vh.homeTeamShortName.setText(res.getString(R.string.burnley));
                break;
            case "Chelsea FC":
                vh.homeTeamLayout.setBackgroundColor(res.getColor(R.color.chelsea));
                vh.homeTeamLogo.setImageDrawable(res.getDrawable(R.drawable.chelsea));
                //vh.homeTeamShortName.setText(res.getString(R.string.chelsea));
                break;
            case "Crystal Palace FC":
                vh.homeTeamLayout.setBackgroundColor(res.getColor(R.color.crystal_palace));
                vh.homeTeamLogo.setImageDrawable(res.getDrawable(R.drawable.crystal_palace));
                //vh.homeTeamShortName.setText(res.getString(R.string.crystal_palace));
                break;
            case "Everton FC":
                vh.homeTeamLayout.setBackgroundColor(res.getColor(R.color.everton));
                vh.homeTeamLogo.setImageDrawable(res.getDrawable(R.drawable.everton));
                //vh.homeTeamShortName.setText(res.getString(R.string.everton));
                break;
            case "Huddersfield Town AFC":
                vh.homeTeamLayout.setBackgroundColor(res.getColor(R.color.huddersfield));
                vh.homeTeamLogo.setImageDrawable(res.getDrawable(R.drawable.huddersfield));
                //vh.homeTeamShortName.setText(res.getString(R.string.huddersfield));
                break;
            case "Leicester City FC":
                vh.homeTeamLayout.setBackgroundColor(res.getColor(R.color.leicester));
                vh.homeTeamLogo.setImageDrawable(res.getDrawable(R.drawable.leicester));
                //vh.homeTeamShortName.setText(res.getString(R.string.leicester));
                break;
            case "Liverpool FC":
                vh.homeTeamLayout.setBackgroundColor(res.getColor(R.color.liverpool));
                vh.homeTeamLogo.setImageDrawable(res.getDrawable(R.drawable.liverpool));
                //vh.homeTeamShortName.setText(res.getString(R.string.liverpool));
                break;
            case "Manchester City FC":
                vh.homeTeamLayout.setBackgroundColor(res.getColor(R.color.manchester_city));
                vh.homeTeamLogo.setImageDrawable(res.getDrawable(R.drawable.manchester_city));
                //vh.homeTeamShortName.setText(res.getString(R.string.manchester_city));
                break;
            case "Manchester United FC":
                vh.homeTeamLayout.setBackgroundColor(res.getColor(R.color.manchester_united));
                vh.homeTeamLogo.setImageDrawable(res.getDrawable(R.drawable.manchester_united));
                //vh.homeTeamShortName.setText(res.getString(R.string.manchester_united));
                break;
            case "Newcastle United FC":
                vh.homeTeamLayout.setBackgroundColor(res.getColor(R.color.newcastle));
                vh.homeTeamLogo.setImageDrawable(res.getDrawable(R.drawable.newcastle_united));
                //vh.homeTeamShortName.setText(res.getString(R.string.newcastle));
                break;
            case "Southampton FC":
                vh.homeTeamLayout.setBackgroundColor(res.getColor(R.color.southampton));
                vh.homeTeamLogo.setImageDrawable(res.getDrawable(R.drawable.southampton));
                //vh.homeTeamShortName.setText(res.getString(R.string.southampton));
                break;
            case "Stoke City FC":
                vh.homeTeamLayout.setBackgroundColor(res.getColor(R.color.stoke));
                vh.homeTeamLogo.setImageDrawable(res.getDrawable(R.drawable.stoke));
                //vh.homeTeamShortName.setText(res.getString(R.string.stoke));
                break;
            case "Swansea City AFC":
                vh.homeTeamLayout.setBackgroundColor(res.getColor(R.color.swansea));
                vh.homeTeamLogo.setImageDrawable(res.getDrawable(R.drawable.swansea));
                //vh.homeTeamShortName.setText(res.getString(R.string.swansea));
                break;
            case "Tottenham Hotspur FC":
                vh.homeTeamLayout.setBackgroundColor(res.getColor(R.color.tottenham));
                vh.homeTeamLogo.setImageDrawable(res.getDrawable(R.drawable.tottenham));
                //vh.homeTeamShortName.setText(res.getString(R.string.tottenham));
                break;
            case "West Bromwich Albion FC":
                vh.homeTeamLayout.setBackgroundColor(res.getColor(R.color.west_brom));
                vh.homeTeamLogo.setImageDrawable(res.getDrawable(R.drawable.west_brom));
                //vh.homeTeamShortName.setText(res.getString(R.string.west_brom));
                break;
            case "West Ham United FC":
                vh.homeTeamLayout.setBackgroundColor(res.getColor(R.color.west_ham));
                vh.homeTeamLogo.setImageDrawable(res.getDrawable(R.drawable.west_ham));
                //vh.homeTeamShortName.setText(res.getString(R.string.west_ham));
                break;
            case "Watford FC":
                vh.homeTeamLayout.setBackgroundColor(res.getColor(R.color.watford));
                vh.homeTeamLogo.setImageDrawable(res.getDrawable(R.drawable.watford));
                //vh.homeTeamShortName.setText(res.getString(R.string.watford));
                break;
        }
    }

    private void initAwayTeam(ViewHolder vh, String teamName) {
        switch(teamName) {
            case "AFC Bournemouth":
                vh.awayTeamLayout.setBackgroundColor(res.getColor(R.color.bournemouth));
                vh.awayTeamLogo.setImageDrawable(res.getDrawable(R.drawable.bournemouth));
                //vh.awayTeamShortName.setText(res.getString(R.string.bournemouth));
                break;
            case "Arsenal FC":
                vh.awayTeamLayout.setBackgroundColor(res.getColor(R.color.arsenal));
                vh.awayTeamLogo.setImageDrawable(res.getDrawable(R.drawable.arsenal));
                //vh.awayTeamShortName.setText(res.getString(R.string.arsenal));
                break;
            case "Brighton & Hove Albion FC":
                vh.awayTeamLayout.setBackgroundColor(res.getColor(R.color.brighton));
                vh.awayTeamLogo.setImageDrawable(res.getDrawable(R.drawable.brighton));
                //vh.awayTeamShortName.setText(res.getString(R.string.brighton));
                break;
            case "Burnley FC":
                vh.awayTeamLayout.setBackgroundColor(res.getColor(R.color.burnley));
                vh.awayTeamLogo.setImageDrawable(res.getDrawable(R.drawable.burnley));
                //vh.awayTeamShortName.setText(res.getString(R.string.burnley));
                break;
            case "Chelsea FC":
                vh.awayTeamLayout.setBackgroundColor(res.getColor(R.color.chelsea));
                vh.awayTeamLogo.setImageDrawable(res.getDrawable(R.drawable.chelsea));
                //vh.awayTeamShortName.setText(res.getString(R.string.chelsea));
                break;
            case "Crystal Palace FC":
                vh.awayTeamLayout.setBackgroundColor(res.getColor(R.color.crystal_palace));
                vh.awayTeamLogo.setImageDrawable(res.getDrawable(R.drawable.crystal_palace));
                //vh.awayTeamShortName.setText(res.getString(R.string.crystal_palace));
                break;
            case "Everton FC":
                vh.awayTeamLayout.setBackgroundColor(res.getColor(R.color.everton));
                vh.awayTeamLogo.setImageDrawable(res.getDrawable(R.drawable.everton));
                //vh.awayTeamShortName.setText(res.getString(R.string.everton));
                break;
            case "Huddersfield Town AFC":
                vh.awayTeamLayout.setBackgroundColor(res.getColor(R.color.huddersfield));
                vh.awayTeamLogo.setImageDrawable(res.getDrawable(R.drawable.huddersfield));
                //vh.awayTeamShortName.setText(res.getString(R.string.huddersfield));
                break;
            case "Leicester City FC":
                vh.awayTeamLayout.setBackgroundColor(res.getColor(R.color.leicester));
                vh.awayTeamLogo.setImageDrawable(res.getDrawable(R.drawable.leicester));
                //vh.awayTeamShortName.setText(res.getString(R.string.leicester));
                break;
            case "Liverpool FC":
                vh.awayTeamLayout.setBackgroundColor(res.getColor(R.color.liverpool));
                vh.awayTeamLogo.setImageDrawable(res.getDrawable(R.drawable.liverpool));
                //vh.awayTeamShortName.setText(res.getString(R.string.liverpool));
                break;
            case "Manchester City FC":
                vh.awayTeamLayout.setBackgroundColor(res.getColor(R.color.manchester_city));
                vh.awayTeamLogo.setImageDrawable(res.getDrawable(R.drawable.manchester_city));
                //vh.awayTeamShortName.setText(res.getString(R.string.manchester_city));
                break;
            case "Manchester United FC":
                vh.awayTeamLayout.setBackgroundColor(res.getColor(R.color.manchester_united));
                vh.awayTeamLogo.setImageDrawable(res.getDrawable(R.drawable.manchester_united));
                //vh.awayTeamShortName.setText(res.getString(R.string.manchester_united));
                break;
            case "Newcastle United FC":
                vh.awayTeamLayout.setBackgroundColor(res.getColor(R.color.newcastle));
                vh.awayTeamLogo.setImageDrawable(res.getDrawable(R.drawable.newcastle_united));
                //vh.awayTeamShortName.setText(res.getString(R.string.newcastle));
                break;
            case "Southampton FC":
                vh.awayTeamLayout.setBackgroundColor(res.getColor(R.color.southampton));
                vh.awayTeamLogo.setImageDrawable(res.getDrawable(R.drawable.southampton));
                //vh.awayTeamShortName.setText(res.getString(R.string.southampton));
                break;
            case "Stoke City FC":
                vh.awayTeamLayout.setBackgroundColor(res.getColor(R.color.stoke));
                vh.awayTeamLogo.setImageDrawable(res.getDrawable(R.drawable.stoke));
                //vh.awayTeamShortName.setText(res.getString(R.string.stoke));
                break;
            case "Swansea City AFC":
                vh.awayTeamLayout.setBackgroundColor(res.getColor(R.color.swansea));
                vh.awayTeamLogo.setImageDrawable(res.getDrawable(R.drawable.swansea));
                //vh.awayTeamShortName.setText(res.getString(R.string.swansea));
                break;
            case "Tottenham Hotspur FC":
                vh.awayTeamLayout.setBackgroundColor(res.getColor(R.color.tottenham));
                vh.awayTeamLogo.setImageDrawable(res.getDrawable(R.drawable.tottenham));
                //vh.awayTeamShortName.setText(res.getString(R.string.tottenham));
                break;
            case "West Bromwich Albion FC":
                vh.awayTeamLayout.setBackgroundColor(res.getColor(R.color.west_brom));
                vh.awayTeamLogo.setImageDrawable(res.getDrawable(R.drawable.west_brom));
                //vh.awayTeamShortName.setText(res.getString(R.string.west_brom));
                break;
            case "West Ham United FC":
                vh.awayTeamLayout.setBackgroundColor(res.getColor(R.color.west_ham));
                vh.awayTeamLogo.setImageDrawable(res.getDrawable(R.drawable.west_ham));
                //vh.awayTeamShortName.setText(res.getString(R.string.west_ham));
                break;
            case "Watford FC":
                vh.awayTeamLayout.setBackgroundColor(res.getColor(R.color.watford));
                vh.awayTeamLogo.setImageDrawable(res.getDrawable(R.drawable.watford));
                //vh.awayTeamShortName.setText(res.getString(R.string.watford));
                break;
        }
    }

    @Override
    public int getItemCount() {
        return matches.size();
    }

    // Call Back to View Match Details Fragment
    public interface MatchInteractionListener {
        void onMatchSelected(Match match);
    }
}
