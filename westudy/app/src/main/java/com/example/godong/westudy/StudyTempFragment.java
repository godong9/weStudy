package com.example.godong.westudy;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost.OnTabChangeListener;

/**
 * Created by baggajin on 14. 7. 14..
 */
public class StudyTempFragment extends Fragment implements OnTabChangeListener{

    private static final String TAG = "StudyTabs";
    private static final String TAB_TIMELINE = "타임라인";
    private static final String TAB_CALENDAR = "캘린더";
    private static final String TAB_PLANNER = "플래너";

    private View mRoot;
    private FragmentTabHost mTabHost;
    //    private FragmentTabHost mTabhost;
    private int mCurrentTab;

    private TimelineFragment fragTimeline;
    private CalendarFragment fragCal;
    private PlanFragment fragPlan;


    /** ================================> variable declaration part end   **/

    public StudyTempFragment(){

    }

    public static StudyFragment newInstance(){

        StudyFragment fragment = new StudyFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        mTabHost.setOnTabChangedListener(this);
        mTabHost.setCurrentTab(mCurrentTab);
        // manually start loading stuff in the first tab
//        updateTab(TAB_TIMELINE, R.id.tab_timeline);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        mTabHost = new FragmentTabHost(getActivity());
        mTabHost.setup(getActivity(), getChildFragmentManager(), android.R.id.tabcontent);

        mTabHost.addTab(mTabHost.newTabSpec(TAB_TIMELINE).setIndicator(TAB_TIMELINE),
                TimelineFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec(TAB_CALENDAR).setIndicator(TAB_CALENDAR),
                CalendarFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec(TAB_PLANNER).setIndicator(TAB_PLANNER),
                PlanFragment.class, null);

        return mTabHost;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mTabHost = null;
    }


    @Override
    public void onTabChanged(String tabId) {
        Log.d(TAG, "onTabChanged(): tabId=" + tabId);
        if(TAB_TIMELINE.equals(tabId)){
//            updateTab(tabId, R.id.tab_timeline);
            mCurrentTab = 0;
            return;
        }
        if(TAB_CALENDAR.equals(tabId)){
//            updateTab(tabId, R.id.tab_calender);
            mCurrentTab = 1;
            return;
        }
        if(TAB_PLANNER.equals(tabId)){
//            updateTab(tabId, R.id.tab_plan);
            mCurrentTab = 2;
            return;
        }
    }
//
//    private TabSpec newTab(String tag, int labelID, int tabContentID){
//        Log.d(TAG, "buildTab(): tag=" + tag);
//
////        View indicator = LayoutInflater
////                                    .from(getActivity())
////                                    .inflate(R.layout.tabs_study,(ViewGroup)mRoot.findViewById(android.R.id.tabs), false);
//
//        TabSpec tabSpec = mTabHost.newTabSpec(tag);
////        tabSpec.setIndicator(indicator);
//        tabSpec.setContent(tabContentID);
//        return tabSpec;
//    }

//    private void updateTab(String tabId, int placeholder){
//
//        if(TAB_TIMELINE.equals(tabId)){
//            fragTimeline = TimelineFragment.newInstance();
//            getFragmentManager()
//                    .beginTransaction()
//                    .replace(placeholder, fragTimeline)
//                    .commit();
//        }else if(TAB_CALENDAR.equals(tabId)){
//            fragCal = CalendarFragment.newInstance();
//            getFragmentManager()
//                    .beginTransaction()
//                    .replace(placeholder, fragCal)
//                    .commit();
//        }else if(TAB_PLANNER.equals(tabId)){
//            fragPlan = PlanFragment.newInstance();
//            getFragmentManager()
//                    .beginTransaction()
//                    .replace(placeholder, fragPlan)
//                    .commit();
//        }
//    }
//

}
