package com.eltendawy.newsapp.Adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import java.util.ArrayList;
//import com.android.internal.
/**
 * Created by Islam on 10-Oct-17.
 */

public class SimpleFragmentPagerAdapter  extends FragmentPagerAdapter {
    private Context mContext;
    private ArrayList<Fragment>fragments;
    private ArrayList<String>titles;
    public SimpleFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    public  void add(Fragment fragment,String title)
    {
        if(fragments==null||titles==null)
        {
            fragments=new ArrayList<>();
            titles=new ArrayList<>();
        }
        fragments.add(fragment);
        titles.add(title);

    }
    // This determines the fragment for each tab
    @Override
    public Fragment getItem(int position) {

        return fragments.get(position);
//        if (position == 2){//how to send data
//            {
//                Review r=new Review();
//                Bundle args = new Bundle();
//                args.putString("0", movieid);
//                r.setArguments(args);
//                return r;
//            }
//        }
    }

    // This determines the number of tabs
    @Override
    public int getCount() {
        return fragments.size();
    }

    // This determines the title for each tab
    @Override
    public String getPageTitle(int position) {
        return  titles.get(position);
        }
    }



