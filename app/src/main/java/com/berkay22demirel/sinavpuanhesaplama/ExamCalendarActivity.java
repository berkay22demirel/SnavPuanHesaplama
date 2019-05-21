package com.berkay22demirel.sinavpuanhesaplama;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.berkay22demirel.sinavpuanhesaplama.Adapter.ExamCalendarAdapter;
import com.berkay22demirel.sinavpuanhesaplama.Model.ExamDate;
import com.berkay22demirel.sinavpuanhesaplama.Util.ExamDateUtil;

import java.util.List;

public class ExamCalendarActivity extends AppCompatActivity {

    ListView listViewExamCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_calendar);
        getSupportActionBar().setTitle("Sınav Takvimi");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
                Intent intentSettings = new Intent(ExamCalendarActivity.this, SettingsActivity.class);
                startActivity(intentSettings);
                break;
            case R.id.action_info:
                Intent intentInfo = new Intent(ExamCalendarActivity.this, InfoActivity.class);
                startActivity(intentInfo);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setViewReferences() {
        listViewExamCalendar = findViewById(R.id.listViewExamCalendar);
    }

    private void provideViews() {
        List<ExamDate> examDateList = ExamDateUtil.getAllExamDateList();
        ExamCalendarAdapter adapter = new ExamCalendarAdapter(ExamCalendarActivity.this, R.layout.list_item_exam_calendar, examDateList);
        listViewExamCalendar.setAdapter(adapter);
    }
}
