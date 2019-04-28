package com.berkay22demirel.sinavpuanhesaplama;

import android.icu.text.RelativeDateTimeFormatter;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class AlesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ales);

        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.action_settings:
                Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();

        }
        return super.onOptionsItemSelected(item);
    }


}
