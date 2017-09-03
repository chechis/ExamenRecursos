package jcalv.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by jcalv on 2/09/2017.
 */

public class BaseFragment extends Fragment{

    private String name;
    private String description;
    private static final String ARG_NAME = "name";
    private static final String ARG_DESCRIPTION = "description";


    public static BaseFragment getInstance(String name, String description){
        BaseFragment fragment = new BaseFragment();

        Bundle args = new Bundle();
        args.putString(ARG_NAME, name);
        args.putString(ARG_DESCRIPTION, description);

        fragment.setArguments(args);
        return fragment;

    }

    public String getName (){return name;}
    public void setName (String name){this.name=name;}
    public String getDescription() {return description;}

    public void setDescription(String description){
        this.description = description;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args= getArguments();
        if (args !=null){
            name=args.getString(ARG_NAME);
            description=args.getString(ARG_DESCRIPTION);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragmento, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ((TextView) view.findViewById(R.id.text_name)).setText(this.name);
        ((TextView) view.findViewById(R.id.text_description)).setText(this.description);
    }
}
