package com.alizhezi.tablibrary;

import android.support.v4.app.Fragment;

import java.util.Arrays;
import java.util.List;

public class ItHqAddFragment {

    public static List<Fragment> addFragment(Fragment... fragments) {
        Fragment[] m = fragments;
        List<Fragment> fragmentList = Arrays.asList(m);
        return fragmentList;
    }
}
