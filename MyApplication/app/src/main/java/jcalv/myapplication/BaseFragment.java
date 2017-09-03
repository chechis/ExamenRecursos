package jcalv.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by jcalv on 2/09/2017.
 */

public class BaseFragment extends Fragment {

    private String name;
    private String description;
    private static final String ARG_NAME="name";
    private static final String ARG_DESCRIPTION="description";


    public void BaseFragment getInstance(String name, String description){
        BaseFragment fragment = new BaseFragment();

        Bundle args= new Bundle();
        args.putString(ARG_NAME, name);
        args.putString(ARG_DESCRIPTION, description);

        fragment.setArguments(args);
        return fragment;
    }


}
