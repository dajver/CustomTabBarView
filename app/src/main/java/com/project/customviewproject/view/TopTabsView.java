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

    //Константы для определения какие у нас есть кнопки на вьюхе
    public static final int LAST_WEEK = 0;
    public static final int LAST_MONTH = 1;
    public static final int ALL_TIME = 2;

    //биндим вьюхи с помощью butter knife
    @BindView(R.id.lastWeek)
    Button lastWeek;
    @BindView(R.id.lastMonth)
    Button lastMonth;
    @BindView(R.id.allTime)
    Button allTime;

    //биндим цвета с помощью той же либы
    @BindColor(R.color.white)
    int whiteColor;
    @BindColor(R.color.colorPrimary)
    int blueColor;

    //Это наш лисенер для нажатия на кнопки, мы его создадим позже
    private OnTopTabsViewClickListener onTopTabsViewClickListener;
    //текущий таб
    private int currentTab;

    // конструкторы для сетапа вьюх на нем.
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

    // этот метод для для установки параметров которые мы задали в файле attrs.xml
    private void setParams(Context context, AttributeSet attrs) {
        //находим наш параметер в файле attrs
        TypedArray arr = context.obtainStyledAttributes(attrs, R.styleable.TopTabsComponent);
        //берем его что бы дальше передать по умолчанию
        int tabId = arr.getInt(R.styleable.TopTabsComponent_tabId, LAST_WEEK);
        //передаем по умолчанию что бы была выбранна какая-то кнопка по умолчанию
        setActiveTab(tabId);
        arr.recycle();
    }

    // что то типа onCreate для вьюх, прописываем что используем как и зачем
    private void init(Context context) {
        //сетапим XML которую будем использовать для кастомного таба
        inflate(context, R.layout.view_top_tabs, this);
        //ставим ориентацию, не сильно важный метод, но маст хев в некоторых случаях
        setOrientation(VERTICAL);
        //это у нас для того что бы использовать @BindView и @BindColor
        ButterKnife.bind(this);
        setUp();
    }

    // задали листенеры для onClick
    private void setUp() {
        lastWeek.setOnClickListener(this);
        lastMonth.setOnClickListener(this);
        allTime.setOnClickListener(this);
    }

    //метод который выделает кликнутую вьюху или дефолтную
    public void setActiveTab(int tabId) {
        currentTab = tabId;
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

    public int getCurrentTab() {
        return currentTab;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lastWeek:
                //по клику делаем вьюху активной
                setActiveTab(LAST_WEEK);
                //отправляем колбек в активити что бы поменять фрагмент на экране
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

    //сеттер для колбека
    public void setOnTopTabsViewClickListener(OnTopTabsViewClickListener onTopSubTabsViewClickListener) {
        this.onTopTabsViewClickListener = onTopSubTabsViewClickListener;
    }

    //сам интерфейс
    public interface OnTopTabsViewClickListener {
        //таб айди для определния по какой вьюхе кликнули
        int onTopTabViewClick(int tabId);
    }
}