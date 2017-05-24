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

    // инициализируем таб бар
    @BindView(R.id.topBarView)
    TopTabsView topTabsView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        // инициализируем лисенер для клика на табы
        topTabsView.setOnTopTabsViewClickListener(this);
        // задаем текущий таб по умолчанию что бы показывало тот фрагмент который у нас по дефолту установлен в XML
        onTopTabViewClick(topTabsView.getCurrentTab());
    }

    //дальше по клику врубаем какой хотим фрагмент
    @Override
    public int onTopTabViewClick(int tabId) {
        Fragment fragment = null;
        switch (tabId) {
            case LAST_WEEK:
                fragment = new LastWeekFragment();
                break;
            case LAST_MONTH:
                fragment = new LastMonthFragment();
                break;
            case ALL_TIME:
                fragment = new AllTimeFragment();
                break;
        }
        setupFragment(fragment);
        return LAST_WEEK;
    }

    //метод который реализует открытие фрагмента
    private void setupFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, fragment)
                .commit();
    }
}
