package ch.bbcag.wynncraftstatistics.Activities;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import ch.bbcag.wynncraftstatistics.HelperClass;
import ch.bbcag.wynncraftstatistics.R;
import ch.bbcag.wynncraftstatistics.WynncraftClass;

public class WynClassFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_wyn_class, container, false);
        Intent thisIntent = getActivity().getIntent();
        String classname = thisIntent.getStringExtra("classname");
        WynncraftClass curClass = HelperClass.genereateWynnClass(classname);

        ImageView icon = (ImageView) rootView.findViewById(R.id.classIcon);
        icon.setImageResource(curClass.getIcon());

        TextView title = (TextView) rootView.findViewById(R.id.class_title);
        title.setText(curClass.getClassTitle());

        TextView description = (TextView) rootView.findViewById(R.id.description);
        description.setText(curClass.getDescription());

        ProgressBar health = (ProgressBar) rootView.findViewById(R.id.health_bar);
        health.setProgress(curClass.getHealth());
        ProgressBarAnimation healthAnim = new ProgressBarAnimation(health, 0, health.getProgress());
        healthAnim.setDuration(1000);
        health.startAnimation(healthAnim);

        ProgressBar strength = (ProgressBar) rootView.findViewById(R.id.strength_bar);
        strength.setProgress(curClass.getStrength());
        ProgressBarAnimation strengthAnim = new ProgressBarAnimation(strength, 0, strength.getProgress());
        strengthAnim.setDuration(1000);
        strength.startAnimation(strengthAnim);

        ProgressBar speed = (ProgressBar) rootView.findViewById(R.id.speed_bar);
        speed.setProgress(curClass.getSpeed());
        ProgressBarAnimation speedAnim = new ProgressBarAnimation(speed, 0, speed.getProgress());
        speedAnim.setDuration(1000);
        speed.startAnimation(speedAnim);

        return rootView;

    }


}
