package com.stepproject.app.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.stepproject.app.R;
import com.stepproject.app.adapters.LogListAdapter;
import com.stepproject.app.managers.FriendListManager;
import com.stepproject.app.managers.LogManager;
import com.stepproject.app.models.Scores;
import com.stepproject.app.utils.SharedPreferenceValue;
import com.stepproject.app.utils.StaticAccess;

import java.util.List;

public class LogActivity extends AppCompatActivity implements View.OnClickListener, LogManager.ScoreLogFeedbackListener {
    private ImageButton ibtnBack;
    private RecyclerView rvLogList;
    private LogActivity activity;
    RecyclerView.Adapter adapter;
    private LogManager logManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        initUI();

    }
    private void initUI() {
        activity = this;
        logManager = new LogManager(activity, true, this);
        ibtnBack = findViewById(R.id.ibtnBack);
        rvLogList = findViewById(R.id.rvLogList);
        LinearLayoutManager manager = new LinearLayoutManager(activity);
        rvLogList.setLayoutManager(manager);
        rvLogList.setHasFixedSize(true);
        ibtnBack.setOnClickListener(this);
        logManager.getAllScoreLogByUserID(SharedPreferenceValue.getLoggedinUser(activity));

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ibtnBack:
                startDashboard();
                break;

        }
    }

    private void startDashboard() {
        startActivity(new Intent(activity, DashboardActivity.class));
        finish();
    }

    @Override
    public void logOfScores(List<Scores> scoresList) {
        List<Scores> currentMonthScoresList = logManager.sortScoreByCurrentMonth(scoresList);
        if (currentMonthScoresList.size() > 0) {
            adapter = new LogListAdapter(activity, currentMonthScoresList);
            rvLogList.setAdapter(adapter);
        }
    }

    @Override
    public void noScoreLogFound() {
        Toast.makeText(activity, "No score log found", Toast.LENGTH_SHORT).show();
    }
}
