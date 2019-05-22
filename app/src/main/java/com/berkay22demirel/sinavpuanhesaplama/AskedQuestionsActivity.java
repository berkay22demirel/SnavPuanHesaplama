package com.berkay22demirel.sinavpuanhesaplama;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.berkay22demirel.sinavpuanhesaplama.Util.AdUtil;
import com.google.android.gms.ads.AdView;

public class AskedQuestionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asked_questions);
        getSupportActionBar().setTitle("Çıkmış Sorular");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        AdView adView = findViewById(R.id.adViewAskedQuestions);
        AdUtil.showAd(adView);
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
                Intent intentSettings = new Intent(AskedQuestionsActivity.this, SettingsActivity.class);
                startActivity(intentSettings);
                break;
            case R.id.action_info:
                Intent intentInfo = new Intent(AskedQuestionsActivity.this, InfoActivity.class);
                startActivity(intentInfo);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showAlertDialog() {
        new AlertDialog.Builder(AskedQuestionsActivity.this)
                .setTitle("UYARI!")
                .setMessage("*Bu sayfa yapım aşamasındadır!")
                .setPositiveButton(getResources().getText(R.string.button_ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).setIcon(getResources().getDrawable(R.drawable.alert_dialog_icon))
                .show();
    }
}
