package com.example.tp2;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener  {
    ArrayList subjects = new ArrayList<>(Arrays.asList("LDDM", "Grafos", "AED 2"));
    LinkedList<String> anotherSubjects = new LinkedList<>(Arrays.asList("Banco de Dados", "Calculo I", "Cálculo II", "Cálculo III", "Algebra Linear", "Matemática Discreta", "Estatística", "AED 1", "AED 3", "LP", "PAA", "IA", "Compiladores", "PID", "Comp. Paralela", "Comp. Gráfica", "Otimização"));
    Menu menu = null;
    SubMenu pdfMenu = null;
    SubMenu linkMenu = null;
    SubMenu videoMenu = null;
    ArrayList<String> Links = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //startActivity(new Intent(MainActivity.this, PoP.class));





                    String subject = anotherSubjects.poll();
                    linkMenu.add(subject).setOnMenuItemClickListener(onMenuItemClick("LINK"));
                    pdfMenu.add(subject).setOnMenuItemClickListener(onMenuItemClick("PDF"));
                    videoMenu.add(subject).setOnMenuItemClickListener(onMenuItemClick("VIDEO"));
//                    menu.add(subject);
                    Snackbar.make(view, "Matéria adicionada com sucesso!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                }
            });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        inicializa();
//
//        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    public void inicializa(){
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        menu = navigationView.getMenu();
        pdfMenu = menu.addSubMenu("PDF");
        linkMenu = menu.addSubMenu("Link");
        videoMenu = menu.addSubMenu("Vídeo");

        for (int i = 0; i < subjects.size(); i++) {
            pdfMenu.add(String.valueOf(subjects.get(i))).setOnMenuItemClickListener(onMenuItemClick("PDF"));
            linkMenu.add(String.valueOf(subjects.get(i))).setOnMenuItemClickListener(onMenuItemClick("LINK"));
            videoMenu.add(String.valueOf(subjects.get(i))).setOnMenuItemClickListener(onMenuItemClick("VIDEO"));
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }else if(id == R.id.fab){
            menu.add(anotherSubjects.peekFirst());
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }
    public MenuItem.OnMenuItemClickListener onMenuItemClick(final String fileType) {
        return new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                Bundle args = new Bundle();

                switch (fileType) {
                    case "PDF":
//                        FirstFragment firstFragment = new FirstFragment();
//
//                        args.putString("subjectName", item.getTitle().toString());
//                        firstFragment.setArguments(args);
//                        ft.replace(R.id.frame, firstFragment);
                        break;
                    case "LINK":
                        SecondFragment secondFragment = new SecondFragment();

                        args.putString("subjectName", item.getTitle().toString());
                        secondFragment.setArguments(args);
                        ft.replace(R.id.frame, secondFragment);
                        break;
                    case "VIDEO":
                        ThirdFragment thirdFragment = new ThirdFragment();

                        args.putString("subjectName", item.getTitle().toString());
                        thirdFragment.setArguments(args);
                        ft.replace(R.id.frame, thirdFragment);
                        break;

                }

                ft.commit();
                return false;
            }
        };
    }

}
