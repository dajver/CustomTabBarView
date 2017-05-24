package com.project.customviewproject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.project.customviewproject.ui.AllTimeFragment;
import com.project.customviewproject.ui.LastMonthFragment;
import com.project.customviewproject.ui.LastWeekFragment;
import com.project.customviewproject.view.TopTabsView;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.project.customviewproject.view.TopTabsView.ALL_TIME;
import static com.project.customviewproject.view.TopTabsView.LAST_MONTH;
import static com.project.customviewproject.view.TopTabsView.LAST_WEEK;

public class MainActivity extends AppCompatActivity implements TopTabsView.OnTopTabsViewClickListener {

    @BindView(R.id.topBarView)
    TopTabsView topTabsView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        topTabsView.setOnTopTabsViewClickListener(this);
        setupFragment(new LastWeekFragment());
    }

    @Override
    public void onTopTabViewClick(int tabId) {
        switch (tabId) {
            case LAST_WEEK:
                setupFragment(new LastWeekFragment());
                break;
            case LAST_MONTH:
                setupFragment(new LastMonthFragment());
                break;
            case ALL_TIME:
                setupFragment(new AllTimeFragment());
                break;
        }
    }

    private void setupFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, fragment)
                .commit();
    }
}
