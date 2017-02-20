package org.jboard.prototipo.Adaptadores;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by USUARIO on 31/01/2017.
 */

public class PageAdapter extends FragmentPagerAdapter {

    //para usar el viewpager

    private ArrayList<Fragment> arrLstFragment;

    public PageAdapter(FragmentManager fm, ArrayList<Fragment> arrLstFragment) {
        super(fm);
        this.arrLstFragment = arrLstFragment;
    }

    @Override
    public Fragment getItem(int position){
        return arrLstFragment.get(position);
    }

    @Override
    public int getCount() {
        return arrLstFragment.size();
    }
}
