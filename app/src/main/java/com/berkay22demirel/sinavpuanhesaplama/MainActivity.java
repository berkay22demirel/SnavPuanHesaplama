package com.berkay22demirel.sinavpuanhesaplama;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.berkay22demirel.sinavpuanhesaplama.Enum.ExamsEnum;
import com.berkay22demirel.sinavpuanhesaplama.Model.ExamType;
import com.berkay22demirel.sinavpuanhesaplama.Util.ValidatorUtil;

import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getResources().getString(R.string.app_name));

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        addClickListenerToCardViews();

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
        getMenuInflater().inflate(R.menu.main_menu, menu);
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

        if (id == R.id.nav_saved_exam) {
            Intent intent = new Intent(MainActivity.this, SavedExamsActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_exam_calendar) {
            Intent intent = new Intent(MainActivity.this, ExamCalendarActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_yks) {

        } else if (id == R.id.nav_dgs) {

        } else if (id == R.id.nav_ales) {

        } else if (id == R.id.nav_yds) {

        } else if (id == R.id.nav_kpss) {

        } else if (id == R.id.nav_ekpss) {

        } else if (id == R.id.nav_tus) {

        } else if (id == R.id.nav_dus) {

        } else if (id == R.id.nav_eus) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void addClickListenerToCardViews() {
        List<ExamType> examTypeList = ExamsEnum.getExamsList();
        if (ValidatorUtil.isValidList(examTypeList)) {
            for (ExamType examType : examTypeList) {
                String cardViewId = "cardView" + examType.getTitle();
                CardView cardView = (CardView) findViewById(getResources().getIdentifier(cardViewId, "id", getPackageName()));
                addClickListenerToCardView(cardView, examType.getActivityReferance());
            }
        }
    }

    private void addClickListenerToCardView(CardView cardView, final Class activityReference) {
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, activityReference);
                startActivity(intent);
            }
        });
    }
}
