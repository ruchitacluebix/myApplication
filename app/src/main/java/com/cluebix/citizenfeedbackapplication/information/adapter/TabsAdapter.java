package com.cluebix.citizenfeedbackapplication.information.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.cluebix.citizenfeedbackapplication.information.fragment.DepartmentFragment;
import com.cluebix.citizenfeedbackapplication.information.fragment.ZonalFragment;

public class TabsAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    public TabsAdapter(FragmentManager fm, int NoofTabs){
        super(fm);
        this.mNumOfTabs = NoofTabs;
    }
    @Override
    public int getCount() {
        return mNumOfTabs;
    }

    @Override
    public Fragment getItem(int position){
        switch (position){
            case 0:
                ZonalFragment zone = new ZonalFragment();
                return zone;
            case 1:
                DepartmentFragment departmentFragment = new DepartmentFragment();
                return departmentFragment;

            default:

                return null;
        }
    }
}
