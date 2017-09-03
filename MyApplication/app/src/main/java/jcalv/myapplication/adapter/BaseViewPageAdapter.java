package jcalv.myapplication.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import jcalv.myapplication.BaseFragment;

/**
 * Created by jcalv on 2/09/2017.
 */

public class BaseViewPageAdapter extends FragmentStatePagerAdapter {

    private String [] tabs;
    private String [] descriptions;


    public BaseViewPageAdapter(FragmentManager manager, String[] tabs, String[] descriptions) {
        super(manager);
        this.tabs=tabs;
        this.descriptions=descriptions;
    }

    @Override
    public Fragment getItem(int position) {
        return BaseFragment.getInstance(tabs[position], descriptions[position]);
    }

    @Override
    public int getCount() {
        return tabs.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs[position];
    }
}
