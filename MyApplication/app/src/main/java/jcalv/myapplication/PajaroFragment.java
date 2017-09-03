package jcalv.myapplication;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
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

public class PajaroFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.app_bar_with_tabs, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ViewPager viewPager= (ViewPager) view.findViewById(R.id.view_pager);
        viewPager.setAdapter(new PajaroAdapter(getActivity().getSupportFragmentManager()));

        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        Drawable color=new ColorDrawable(getResources().getColor(R.color.pajaro));
        tabLayout.setBackground(color);

        if (tabLayout != null){
            tabLayout.setupWithViewPager(viewPager);

            for ( int i = 0; i < tabLayout.getTabCount(); i++){
                TabLayout.Tab tab= tabLayout.getTabAt(i);
                Drawable icon = null;
                switch (i){
                    case 0:
                        icon = getResources().getDrawable(R.drawable.google_campana);
                        break;
                    case 1:
                        icon = getResources().getDrawable(R.drawable.pajaro_mensaje);
                        break;
                    case 2:
                        icon = getResources().getDrawable(R.drawable.insta_buscar);
                        break;
                }
                if (tab !=null){
                    tab.setIcon(icon);
                }
            }

        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() instanceof MainActivity){
            MainActivity activity=(MainActivity) getActivity();
            activity.updateView(getString(R.string.pajaro_titulo));
            activity.navigationView.setCheckedItem(R.id.nav_pajaro);

            Drawable color=new ColorDrawable(getResources().getColor(R.color.pajaro));
            activity.getSupportActionBar().setBackgroundDrawable(color);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        MainActivity activity= (MainActivity) getActivity();
        activity.navigationView.setCheckedItem(R.id.nav_pajaro);
    }

    private class PajaroAdapter extends BaseViewPageAdapter {

        public PajaroAdapter(FragmentManager manager) {
            super(manager, new String[]{" ", " ", " "},  new String[]{
                    "Notificaciones",
                    "Mensajes",
                    "Buscar"
            });
        }
    }
}
