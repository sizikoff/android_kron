package com.mercury.kron.ui.activity.showInstruction;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class PageAddapterInstr extends FragmentPagerAdapter {
    public PageAddapterInstr(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return(PageFragment.newInstance(position));
    }

    @Override
    public int getCount() {
        return 6;
    }
}
