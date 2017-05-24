package com.project.customviewproject.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.project.customviewproject.R;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gleb on 5/24/17.
 */

public class TopTabsView extends LinearLayout implements View.OnClickListener {

    public static final int LAST_WEEK = 0;
    public static final int LAST_MONTH = 1;
    public static final int ALL_TIME = 2;

    @BindView(R.id.lastWeek)
    Button lastWeek;
    @BindView(R.id.lastMonth)
    Button lastMonth;
    @BindView(R.id.allTime)
    Button allTime;

    @BindColor(R.color.white)
    int whiteColor;
    @BindColor(R.color.colorPrimary)
    int blueColor;

    private OnTopTabsViewClickListener onTopTabsViewClickListener;

    public TopTabsView(Context context) {
        super(context);
        init(context);
    }

    public TopTabsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
        setParams(context, attrs);
    }

    public TopTabsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
        setParams(context, attrs);
    }

    private void setParams(Context context, AttributeSet attrs) {
        TypedArray arr = context.obtainStyledAttributes(attrs, R.styleable.TopTabsComponent);
        int tabId = arr.getInt(R.styleable.TopTabsComponent_tabId, 0);
        setActiveTab(tabId);
        arr.recycle();
    }

    private void init(Context context) {
        inflate(context, R.layout.view_top_tabs, this);
        setOrientation(VERTICAL);
        ButterKnife.bind(this);
        setUp();
    }

    private void setUp() {
        lastWeek.setOnClickListener(this);
        lastMonth.setOnClickListener(this);
        allTime.setOnClickListener(this);
    }

    public void setActiveTab(int tabId) {
        switch (tabId) {
            case LAST_WEEK:
                lastWeek.setBackgroundResource(R.drawable.selected_rounded_button);
                lastWeek.setTextColor(whiteColor);
                lastMonth.setBackgroundResource(R.drawable.unselected_tranding_rounded_button);
                lastMonth.setTextColor(blueColor);
                allTime.setBackgroundResource(R.drawable.unselected_tranding_rounded_button);
                allTime.setTextColor(blueColor);
                break;
            case LAST_MONTH:
                lastWeek.setBackgroundResource(R.drawable.unselected_tranding_rounded_button);
                lastWeek.setTextColor(blueColor);
                lastMonth.setBackgroundResource(R.drawable.selected_rounded_button);
                lastMonth.setTextColor(whiteColor);
                allTime.setBackgroundResource(R.drawable.unselected_tranding_rounded_button);
                allTime.setTextColor(blueColor);
                break;
            case ALL_TIME:
                lastWeek.setBackgroundResource(R.drawable.unselected_tranding_rounded_button);
                lastWeek.setTextColor(blueColor);
                lastMonth.setBackgroundResource(R.drawable.unselected_tranding_rounded_button);
                lastMonth.setTextColor(blueColor);
                allTime.setBackgroundResource(R.drawable.selected_rounded_button);
                allTime.setTextColor(whiteColor);
                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lastWeek:
                setActiveTab(LAST_WEEK);
                onTopTabsViewClickListener.onTopTabViewClick(LAST_WEEK);
                break;
            case R.id.lastMonth:
                setActiveTab(LAST_MONTH);
                onTopTabsViewClickListener.onTopTabViewClick(LAST_MONTH);
                break;
            case R.id.allTime:
                setActiveTab(ALL_TIME);
                onTopTabsViewClickListener.onTopTabViewClick(ALL_TIME);
                break;
        }
    }

    public void setOnTopTabsViewClickListener(OnTopTabsViewClickListener onTopSubTabsViewClickListener) {
        this.onTopTabsViewClickListener = onTopSubTabsViewClickListener;
    }

    public interface OnTopTabsViewClickListener {
        void onTopTabViewClick(int tabId);
    }
}