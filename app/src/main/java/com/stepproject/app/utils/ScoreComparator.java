package com.stepproject.app.utils;

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.stepproject.app.models.Scores;

import java.util.Comparator;

/**
 * Created by tajmulalam on 7/11/18.
 */

public class ScoreComparator implements Comparator<Scores> {
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int compare(Scores score1, Scores score2) {
        return Long.compare(Long.parseLong(score1.getSteps()), Long.parseLong(String.valueOf(score2.getSteps())));
    }
}