package com.eltendawy.newsapp.Base;

import android.content.Context;
import android.support.v4.app.Fragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Mohamed Nabil Mohamed (Nobel) on 9/13/2018.
 * byte code SA
 * m.nabil.fci2015@gmail.com
 */
public class BaseFragment extends Fragment {

    protected BaseActivity activity;

    public BaseFragment() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (BaseActivity) context;


    }
}
