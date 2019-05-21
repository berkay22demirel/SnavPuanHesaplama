package com.berkay22demirel.sinavpuanhesaplama;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.berkay22demirel.sinavpuanhesaplama.Adapter.SavedExamsAdapter;
import com.berkay22demirel.sinavpuanhesaplama.Database.DatabaseManager;

import java.util.List;

public class SavedExamsActivity extends AppCompatActivity {

    DatabaseManager databaseManager;
    ListView listViewSavedExams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_exams);
        getSupportActionBar().setTitle("Kayıtlı Sınavlarım");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        databaseManager = new DatabaseManager(this);
        setViewReferences();
        provideViews();
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
                Intent intentSettings = new Intent(SavedExamsActivity.this, SettingsActivity.class);
                startActivity(intentSettings);
                break;
            case R.id.action_info:
                Intent intentInfo = new Intent(SavedExamsActivity.this, InfoActivity.class);
                startActivity(intentInfo);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setViewReferences() {
        listViewSavedExams = findViewById(R.id.listViewSavedExams);
    }

    private void provideViews() {
        List<Object> examList = databaseManager.getAllExam();
        SavedExamsAdapter adapter = new SavedExamsAdapter(SavedExamsActivity.this, R.layout.list_item_saved_exams, examList);
        listViewSavedExams.setAdapter(adapter);
    }
}
