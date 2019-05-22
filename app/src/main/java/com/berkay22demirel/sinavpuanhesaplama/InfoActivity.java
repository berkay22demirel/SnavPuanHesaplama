package com.berkay22demirel.sinavpuanhesaplama;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class InfoActivity extends AppCompatActivity {

    CardView cardViewURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        getSupportActionBar().setTitle("Bilgi");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setViewReferences();
        setViewListeners();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.action_settings:
                Intent intentSettings = new Intent(InfoActivity.this, SettingsActivity.class);
                startActivity(intentSettings);
                break;
            case R.id.action_info:
                Intent intentInfo = new Intent(InfoActivity.this, InfoActivity.class);
                startActivity(intentInfo);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setViewReferences() {
        cardViewURL = findViewById(R.id.cardViewInfoURL);
    }

    private void setViewListeners() {
        cardViewURL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://berkay22demirel.blogspot.com.tr"));
                startActivity(browserIntent);
            }
        });
    }
}
