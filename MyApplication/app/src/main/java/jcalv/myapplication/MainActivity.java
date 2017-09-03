package jcalv.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.customtabs.CustomTabsIntent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout drawer;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, new MainFragment())
                .commit();


        navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(this);
        }

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

    }
    public void updateView (String title){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null)
            toolbar.setTitle(title);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle= new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }



    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(navigationView))
            drawer.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        Fragment fragment = null;

        TextView textView = (TextView) findViewById(R.id.text_title);


        switch (id) {

            case R.id.nav_home:
                fragment = new MainFragment();
                break;
            case R.id.nav_face:
                fragment = new FaceFragment();
                break;
            case R.id.nav_insta:
                fragment = new InstaFragment();
                break;
            case R.id.nav_google:
                fragment = new GoogleFragment();
                break;
            case R.id.nav_pajaro:
                fragment = new PajaroFragment();
                break;
        }
        if (fragment!=null)
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, fragment)
                    .addToBackStack(null).commit();

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        SubMenu menu1= menu.addSubMenu("Redes Sociales");
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://instagram.com"));
        Intent intent1 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com"));
        Intent intent2 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://plus.google.com/?hl=es"));
        Intent intent3 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/?lang=es"));

        menu1.add(0,1,1, "Facebook").setIcon(R.drawable.face_logo).setIntent(intent1);
        menu1.add(0,2,2, "Instagram").setIcon(R.drawable.insta_logo).setIntent(intent);
        menu1.add(0,3,3, "Google Plus").setIcon(R.drawable.google_logo).setIntent(intent2);
        menu1.add(0,4,4, "Twitter").setIcon(R.drawable.pajarito_logo).setIntent(intent3);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id==R.id.accion1){
            seleccionarRed();
        } else if (id==R.id.accion2){
            navegar("https://www.youtube.com/channel/UCnHTPVmbx6JWKv8o6taUeXg", ContextCompat.getColor(this, R.color.colorAccent));
        }
        return super.onOptionsItemSelected(item);
    }

    public void navegar (String url, int color){

        CustomTabsIntent.Builder builder= new CustomTabsIntent.Builder();
        if (color != -1) builder.setToolbarColor(color);
        builder.setStartAnimations(this, android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        builder.setExitAnimations(this, android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(this, Uri.parse(url));
    }

    public void seleccionarRed (){

        final String [] redes = {"Facebook","Twitter","Instagram","Google Plus","Whatsapp","Messenger","SMS"};
        final boolean [] checked = {false,false,false,false,false,false,false};
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Selecciona donde quieres compartir esta aplicacion");
        builder.setMultiChoiceItems(redes, checked, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which, boolean b) {

            }
        });
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                String result = "";
                int cont = 1;
                for (int i =0; i <checked.length; i++) {
                    if (checked[i])
                        if ((cont != ((AlertDialog) dialogInterface).getListView().getCheckedItemCount())){
                            result += redes[i] + ", ";
                            cont++;
                        }else result +=redes [i];
                }
                builder.setTitle("Redes sociales seleccionadas");
                final String mensaje = "¿Desea compartir en redes sociales seleccionadas?";
                final String finalResult = result;
                builder.setMessage(mensaje).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        dialogInterface.cancel();
                        Toast.makeText(getApplicationContext(),"Se compartió en las siguientes redes sociales:\n"+finalResult, Toast.LENGTH_LONG).show();

                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        dialogInterface.cancel();
                    }
                }).create().show();
            }
        });
        builder.create().show();

    }

}