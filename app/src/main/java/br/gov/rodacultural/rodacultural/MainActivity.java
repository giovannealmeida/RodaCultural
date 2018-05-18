package br.gov.rodacultural.rodacultural;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import br.gov.rodacultural.rodacultural.adapters.FeedAdapter;
import br.gov.rodacultural.rodacultural.models.FeedItem;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView recyclerView;

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
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://assistant.google.com/services/invoke/uid/00000076b40a8c05"));
                startActivity(browserIntent);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        recyclerView = (RecyclerView) findViewById(R.id.feed);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<FeedItem> list = new ArrayList<>();
        list.add(new FeedItem(1,"Jailso Andrade","Professor de capoeira",getString(R.string.lorem_ipsum),"https://www.jennybeaumont.com/wp-content/uploads/2015/03/placeholder-800x423.gif","https://projects.scpr.org/static-files/_v4/images/default_avatar.png", true));
        list.add(new FeedItem(2,"Instituto Cultural Steve Biko","ONG",getString(R.string.lorem_ipsum),"https://static.wixstatic.com/media/d7dbcc_397215f6704d4e77a5f6aff465b72305~mv2.jpeg/v1/fill/w_300,h_225,al_c,q_80,usm_0.66_1.00_0.01/d7dbcc_397215f6704d4e77a5f6aff465b72305~mv2.webp","https://static.wixstatic.com/media/d7dbcc_edeb2e9faea94e5fafc17d248a0191c9.png/v1/fill/w_128,h_129,al_c,usm_0.66_1.00_0.01/d7dbcc_edeb2e9faea94e5fafc17d248a0191c9.png",true));
        list.add(new FeedItem(3,"Museu do descobrimento","Museu",getString(R.string.lorem_ipsum),"https://www.jennybeaumont.com/wp-content/uploads/2015/03/placeholder-800x423.gif","https://projects.scpr.org/static-files/_v4/images/default_avatar.png",false));
        list.add(new FeedItem(4,"Rio dos Macacos","Comunidade quilombola",getString(R.string.lorem_ipsum),"https://www.jennybeaumont.com/wp-content/uploads/2015/03/placeholder-800x423.gif","https://projects.scpr.org/static-files/_v4/images/default_avatar.png",false));
        list.add(new FeedItem(5,"CECUP - Centro de Educação e Cultura Popular","ONG",getString(R.string.lorem_ipsum),"http://1.bp.blogspot.com/_CYiU1C4-83s/S9c7pSRmBOI/AAAAAAAAAq4/D-L0OSh8uTE/S260/cecup200.jpg","https://projects.scpr.org/static-files/_v4/images/default_avatar.png",false));
        recyclerView.setAdapter(new FeedAdapter(list));

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
