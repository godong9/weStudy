package com.example.godong.westudy;

import android.app.ActionBar;
import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;


public class MainActivity extends FragmentActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /** Fragment managing the behaviors, interactions and presentation of the navigation drawer. **/
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /** Used to store the last screen title. For use in restoreActionBar(). **/
    private CharSequence mTitle;

    /** Tag 선언 **/
    private static final String TAB_TIMELINE_TAG = "timeline";
    private static final String TAB_CAL_TAG = "calendar";
    private static final String TAB_PLAN_TAG = "planner";

    /** fragment들 선언 **/
    private TimelineFragment fragTimeline;
    private ProfileFragment fragProfile;
    private StudyFragment fragStudy;
    private PlanFragment fragPlan;
    private CalendarFragment fragCal;

    private FragmentTabHost mTabHost;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        /** drawer Setup **/
        mNavigationDrawerFragment.setUp(R.id.navigation_drawer, (DrawerLayout)findViewById(R.id.drawer_layout));

        /** fragment 초기화 **/
//        fragStudy = StudyFragment.newInstance();
//        fragTimeline = TimelineFragment.newInstance();
//        getSupportFragmentManager()
//                .beginTransaction()
//                .replace(R.id.fl_container, fragTimeline)
//                .commit();

        /** View 초기화 **/
        initView();


    }


    /** Tab View 초기화 **/
    private void initView() {
        mTabHost = (FragmentTabHost)findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

        mTabHost.addTab(mTabHost.newTabSpec(TAB_TIMELINE_TAG).setIndicator("타임라인"), TimelineFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec(TAB_CAL_TAG).setIndicator("캘린더"), CalendarFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec(TAB_PLAN_TAG).setIndicator("플래너"), PlanFragment.class, null);
    }

    /**
     * Navigation Drawer item 선택 되었을 때 작업
     * @param position
     */
    @Override
    public void onNavigationDrawerItemSelected(int position){
        /** fragement로 main content update **/
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager
                .beginTransaction()
                .replace(R.id.realtabcontent, PlaceholderFragment.newInstance(position+1))
                .commit();
    }

    /**
     * Select 됐을 때
     * @param number
     */
    public void onSectionAttached(int number){
        switch(number){
            case 1:
                mTitle = getString(R.string.title_timeline);
                fragTimeline = TimelineFragment.newInstance();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.realtabcontent, fragTimeline)
                        .commit();
                break;
            case 2:
                mTitle = getString(R.string.title_profile);
                fragProfile = ProfileFragment.newInstance();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.realtabcontent, fragProfile)
                        .commit();
                break;
            case 3:
                mTitle = getString(R.string.title_study);
                fragStudy = StudyFragment.newInstance();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.realtabcontent, fragStudy)
                        .commit();
                break;
        }
    }

    /**
     * ActionBar restore
     * Fragment 전환 전 ActionBar Title 변경
     */
    public void restoreActionBar(){
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        if(!mNavigationDrawerFragment.isDrawerOpen()){
            /** Drawer가 열리지 않았을 때(안 보일 때) menu item이 보일 수 있다. **/
            /** Only show items in the action bar relevant to this screen
                if the drawer is not showing. Otherwise, let the drawer
                decide what to show in the action bar. **/
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    public static class PlaceholderFragment extends Fragment {

        /** The fragment argument representing the section number for this fragment. **/
        private static final String ARG_SECTION_NUMBER = "section_number";


        /** Returns a new instance of this fragment for the given section number. **/
        public static PlaceholderFragment newInstance(int sectionNumber){
            PlaceholderFragment fragement = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragement.setArguments(args);
            return fragement;
        }

        public PlaceholderFragment() {}

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
            View rootView = inflater.inflate(R.layout.fragment_timeline, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity){
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }


}
