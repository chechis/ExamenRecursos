package jcalv.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import jcalv.myapplication.adapter.BaseViewPageAdapter;

/**
 * Created by jcalv on 2/09/2017.
 */

public class GoogleFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.app_bar_with_tabs, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ViewPager viewPager= (ViewPager) view.findViewById(R.id.view_pager);
        viewPager.setAdapter(new GoogleAdapter(getActivity().getSupportFragmentManager()));

        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() instanceof MainActivity){
            MainActivity activity=(MainActivity) getActivity();
            activity.updateView(getString(R.string.google_logo));
            activity.navigationView.setCheckedItem(R.id.nav_google);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        MainActivity activity= (MainActivity) getActivity();
        activity.navigationView.setCheckedItem(R.id.nav_google);
    }

    private class GoogleAdapter extends BaseViewPageAdapter {

        public GoogleAdapter(FragmentManager manager) {
            super(manager, new String[]{"POP", "ROCK", "REGGAE"},  new String[]{
                    "La música pop (del inglés pop music, contracción de popular music) es un " +
                            "género de música popular que tuvo su origen a finales de los años " +
                            "1950 como una derivación del rock and roll, en combinación con " +
                            "otros géneros musicales que estaban en moda en aquel momento.",
                    "El rock es un término amplio que agrupa a una variedad de géneros musicales. " +
                            "Su forma originaria, conocida como rock and roll, surgió mayormente " +
                            "de la combinación de dos géneros anteriores como eran el rhythm and " +
                            "blues y el country.",
                    "El reggae es un género musical que se desarrolló por primera vez en Jamaica " +
                            "hacia mediados de los años 1960."
            });
        }
    }
}
