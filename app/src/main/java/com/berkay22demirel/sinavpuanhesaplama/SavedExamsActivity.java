package com.berkay22demirel.sinavpuanhesaplama;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.berkay22demirel.sinavpuanhesaplama.Adapter.SavedExamsAdapter;
import com.berkay22demirel.sinavpuanhesaplama.Database.DatabaseManager;
import com.berkay22demirel.sinavpuanhesaplama.Enum.ExamsEnum;
import com.berkay22demirel.sinavpuanhesaplama.Model.ALES;
import com.berkay22demirel.sinavpuanhesaplama.Model.DGS;
import com.berkay22demirel.sinavpuanhesaplama.Model.DUS;
import com.berkay22demirel.sinavpuanhesaplama.Model.EKPSS;
import com.berkay22demirel.sinavpuanhesaplama.Model.EUS;
import com.berkay22demirel.sinavpuanhesaplama.Model.Exam;
import com.berkay22demirel.sinavpuanhesaplama.Model.KPSS;
import com.berkay22demirel.sinavpuanhesaplama.Model.TUS;
import com.berkay22demirel.sinavpuanhesaplama.Model.YDS;
import com.berkay22demirel.sinavpuanhesaplama.Model.YKS;
import com.berkay22demirel.sinavpuanhesaplama.Service.YdsService;
import com.berkay22demirel.sinavpuanhesaplama.Util.AdUtil;
import com.google.android.gms.ads.AdView;

import java.util.List;

public class SavedExamsActivity extends AppCompatActivity {

    DatabaseManager databaseManager;
    ListView listViewSavedExams;
    AdView adView;
    List<Object> examList;
    SavedExamsAdapter savedExamsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_exams);
        getSupportActionBar().setTitle("Kayıtlı Sınavlarım");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        databaseManager = new DatabaseManager(this);
        setViewReferences();
        provideViews();
        setViewListeners();
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
        adView = findViewById(R.id.adViewSavedExams);
    }

    private void provideViews() {
        examList = databaseManager.getAllExam();
        savedExamsAdapter = new SavedExamsAdapter(SavedExamsActivity.this, R.layout.list_item_saved_exams, examList);
        listViewSavedExams.setAdapter(savedExamsAdapter);
    }

    private void setViewListeners() {
        listViewSavedExams.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showResultDialog((Exam) examList.get(position));
            }
        });
        listViewSavedExams.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final Exam exam = (Exam) examList.get(position);
                new AlertDialog.Builder(SavedExamsActivity.this)
                        .setTitle("Sınavı Sil")
                        .setMessage("Bu Sınavı Silmek İstediğinize Emin Misiniz?")
                        .setPositiveButton(getResources().getText(R.string.button_yes), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                int examType = exam.getExamType();
                                long deleteResult = 0;
                                if (ExamsEnum.ALES.getId() == examType) {
                                    deleteResult = databaseManager.delete(exam.getId(), ALES.class);
                                } else if (ExamsEnum.KPSS.getId() == examType) {
                                    deleteResult = databaseManager.delete(exam.getId(), KPSS.class);
                                } else if (ExamsEnum.YKS.getId() == examType) {
                                    deleteResult = databaseManager.delete(exam.getId(), YKS.class);
                                } else if (ExamsEnum.YDS.getId() == examType) {
                                    deleteResult = databaseManager.delete(exam.getId(), YDS.class);
                                } else if (ExamsEnum.DGS.getId() == examType) {
                                    deleteResult = databaseManager.delete(exam.getId(), DGS.class);
                                } else if (ExamsEnum.EKPSS.getId() == examType) {
                                    deleteResult = databaseManager.delete(exam.getId(), EKPSS.class);
                                } else if (ExamsEnum.DUS.getId() == examType) {
                                    deleteResult = databaseManager.delete(exam.getId(), DUS.class);
                                } else if (ExamsEnum.TUS.getId() == examType) {
                                    deleteResult = databaseManager.delete(exam.getId(), TUS.class);
                                } else if (ExamsEnum.EUS.getId() == examType) {
                                    deleteResult = databaseManager.delete(exam.getId(), EUS.class);
                                }
                                if (deleteResult > 0) {
                                    examList.remove(exam);
                                    savedExamsAdapter.remove(exam);
                                    savedExamsAdapter.notifyDataSetChanged();
                                }
                            }
                        }).setNegativeButton(getResources().getText(R.string.button_no), null)
                        .setIcon(getResources().getDrawable(R.drawable.alert_dialog_icon))
                        .show();
                return true;
            }
        });
    }

    private void showResultDialog(Exam exam) {
        Integer examType = exam.getExamType();
        final Dialog dialog = new Dialog(this);
        if (ExamsEnum.ALES.getId() == examType) {
            dialog.setContentView(R.layout.dialog_saved_result_ales);
            TextView textViewNumeric = dialog.findViewById(R.id.textViewALESSavedResultNumeric);
            TextView textViewVerbal = dialog.findViewById(R.id.textViewALESSavedResultVerbal);
            TextView textViewEqualWeight = dialog.findViewById(R.id.textViewALESSavedResultEqualWeight);
            Button buttonStatics = dialog.findViewById(R.id.buttonALESSavedResultDialogStatics);
            Button buttonClose = dialog.findViewById(R.id.buttonALESSavedResultDialogClose);
            ALES ales = (ALES) exam;
            textViewNumeric.setText(String.valueOf(ales.getNumericalResult()));
            textViewVerbal.setText(String.valueOf(ales.getVerbalResult()));
            textViewEqualWeight.setText(String.valueOf(ales.getEqualWeightResult()));
            setDialogViewListeners(dialog, buttonStatics, buttonClose);
        } else if (ExamsEnum.KPSS.getId() == examType) {

        } else if (ExamsEnum.YKS.getId() == examType) {
            dialog.setContentView(R.layout.dialog_saved_result_yks);
            TextView textViewYKSResultSimpleTYT = dialog.findViewById(R.id.textViewYKSSavedResultSimpleTYT);
            TextView textViewYKSResultSimpleNumerical = dialog.findViewById(R.id.textViewYKSSavedResultSimpleNumerical);
            TextView textViewYKSResultSimpleVerbal = dialog.findViewById(R.id.textViewYKSSavedResultSimpleVerbal);
            TextView textViewYKSResultSimpleEqualWeight = dialog.findViewById(R.id.textViewYKSSavedResultSimpleEqualWeight);
            TextView textViewYKSResultSimpleLanguage = dialog.findViewById(R.id.textViewYKSSavedResultSimpleLanguage);
            TextView textViewYKSResultCalculatedTYT = dialog.findViewById(R.id.textViewYKSSavedResultCalculatedTYT);
            TextView textViewYKSResultCalculatedNumerical = dialog.findViewById(R.id.textViewYKSSavedResultCalculatedNumerical);
            TextView textViewYKSResultCalculatedVerbal = dialog.findViewById(R.id.textViewYKSSavedResultCalculatedVerbal);
            TextView textViewYKSResultCalculatedEqualWeight = dialog.findViewById(R.id.textViewYKSSavedResultCalculatedEqualWeight);
            TextView textViewYKSResultCalculatedLanguage = dialog.findViewById(R.id.textViewYKSSavedResultCalculatedLanguage);
            Button buttonStatics = dialog.findViewById(R.id.buttonYKSSavedResultDialogStatics);
            Button buttonClose = dialog.findViewById(R.id.buttonYKSSavedResultDialogClose);
            YKS yks = (YKS) exam;
            textViewYKSResultSimpleTYT.setText(String.valueOf(yks.getResultSimpleTYT()));
            textViewYKSResultSimpleNumerical.setText(String.valueOf(yks.getResultSimpleNumerical()));
            textViewYKSResultSimpleVerbal.setText(String.valueOf(yks.getResultSimpleVerbal()));
            textViewYKSResultSimpleEqualWeight.setText(String.valueOf(yks.getResultSimpleEqualWeight()));
            textViewYKSResultSimpleLanguage.setText(String.valueOf(yks.getResultSimpleLanguage()));
            textViewYKSResultCalculatedTYT.setText(String.valueOf(yks.getResultCalculatedTYT()));
            textViewYKSResultCalculatedNumerical.setText(String.valueOf(yks.getResultCalculatedNumerical()));
            textViewYKSResultCalculatedVerbal.setText(String.valueOf(yks.getResultCalculatedVerbal()));
            textViewYKSResultCalculatedEqualWeight.setText(String.valueOf(yks.getResultCalculatedEqualWeight()));
            textViewYKSResultCalculatedLanguage.setText(String.valueOf(yks.getResultCalculatedLanguage()));
            setDialogViewListeners(dialog, buttonStatics, buttonClose);
        } else if (ExamsEnum.YDS.getId() == examType) {
            YdsService ydsService = new YdsService(this);
            dialog.setContentView(R.layout.dialog_saved_result_yds);
            TextView textViewResult = dialog.findViewById(R.id.textViewYDSSavedResult);
            TextView textViewLevel = dialog.findViewById(R.id.textViewYDSSavedResultLevel);
            Button buttonStatics = dialog.findViewById(R.id.buttonYDSSavedResultDialogStatics);
            Button buttonClose = dialog.findViewById(R.id.buttonYDSSavedResultDialogClose);
            YDS yds = (YDS) exam;
            textViewResult.setText(String.valueOf(yds.getResult()));
            textViewLevel.setText(ydsService.getLevel(yds.getResult()));
            setDialogViewListeners(dialog, buttonStatics, buttonClose);
        } else if (ExamsEnum.DGS.getId() == examType) {
            dialog.setContentView(R.layout.dialog_saved_result_dgs);
            TextView textViewResultNumeric = dialog.findViewById(R.id.textViewDGSSavedResultNumeric);
            TextView textViewResultVerbal = dialog.findViewById(R.id.textViewDGSSavedResultVerbal);
            TextView textViewResultEqualWeight = dialog.findViewById(R.id.textViewDGSSavedResultEqualWeight);
            Button buttonStatics = dialog.findViewById(R.id.buttonDGSSavedResultDialogStatics);
            Button buttonClose = dialog.findViewById(R.id.buttonDGSSavedResultDialogClose);
            DGS dgs = (DGS) exam;
            textViewResultNumeric.setText(String.valueOf(dgs.getNumericalResult()));
            textViewResultVerbal.setText(String.valueOf(dgs.getVerbalResult()));
            textViewResultEqualWeight.setText(String.valueOf(dgs.getEqualWeightResult()));
            setDialogViewListeners(dialog, buttonStatics, buttonClose);
        } else if (ExamsEnum.EKPSS.getId() == examType) {
            dialog.setContentView(R.layout.dialog_saved_result_ekpss);
            TextView textViewResult = dialog.findViewById(R.id.textViewEKPSSSavedResult);
            Button buttonStatics = dialog.findViewById(R.id.buttonEKPSSSavedResultDialogStatics);
            Button buttonClose = dialog.findViewById(R.id.buttonEKPSSSavedResultDialogClose);
            EKPSS ekpss = (EKPSS) exam;
            textViewResult.setText(String.valueOf(ekpss.getResult()));
            setDialogViewListeners(dialog, buttonStatics, buttonClose);
        } else if (ExamsEnum.DUS.getId() == examType) {
            dialog.setContentView(R.layout.dialog_saved_result_dus);
            TextView textViewResult = dialog.findViewById(R.id.textViewDUSSavedResult);
            Button buttonStatics = dialog.findViewById(R.id.buttonDUSSavedResultDialogStatics);
            Button buttonClose = dialog.findViewById(R.id.buttonDUSSavedResultDialogClose);
            DUS dus = (DUS) exam;
            textViewResult.setText(String.valueOf(dus.getResult()));
            setDialogViewListeners(dialog, buttonStatics, buttonClose);
        } else if (ExamsEnum.TUS.getId() == examType) {
            dialog.setContentView(R.layout.dialog_saved_result_tus);
            TextView textViewGraduateMedicineKPoint = dialog.findViewById(R.id.textViewTUSSavedResultGraduateMedicineKPoint);
            TextView textViewGraduateMedicineTPoint = dialog.findViewById(R.id.textViewTUSSavedResultGraduateMedicineTPoint);
            TextView textViewGraduateMedicineAPoint = dialog.findViewById(R.id.textViewTUSSavedResultGraduateMedicineAPoint);
            TextView textViewNotGraduateMedicineTPoint = dialog.findViewById(R.id.textViewTUSSavedResultNotGraduateMedicineTPoint);
            Button buttonStatics = dialog.findViewById(R.id.buttonTUSSavedResultDialogStatics);
            Button buttonClose = dialog.findViewById(R.id.buttonTUSSavedResultDialogClose);
            TUS tus = (TUS) exam;
            textViewGraduateMedicineKPoint.setText(String.valueOf(tus.getGraduateMedicineKPoint()));
            textViewGraduateMedicineTPoint.setText(String.valueOf(tus.getGraduateMedicineTPoint()));
            textViewGraduateMedicineAPoint.setText(String.valueOf(tus.getGraduateMedicineAPoint()));
            textViewNotGraduateMedicineTPoint.setText(String.valueOf(tus.getNotGraduateMedicineTPoint()));
            setDialogViewListeners(dialog, buttonStatics, buttonClose);
        } else if (ExamsEnum.EUS.getId() == examType) {
            dialog.setContentView(R.layout.dialog_saved_result_eus);
            TextView textViewResult = dialog.findViewById(R.id.textViewEUSSavedResult);
            Button buttonStatics = dialog.findViewById(R.id.buttonEUSSavedResultDialogStatics);
            Button buttonClose = dialog.findViewById(R.id.buttonEUSSavedResultDialogClose);
            EUS eus = (EUS) exam;
            textViewResult.setText(String.valueOf(eus.getResult()));
            setDialogViewListeners(dialog, buttonStatics, buttonClose);
        }
        dialog.show();
    }

    private void setDialogViewListeners(final Dialog dialog, Button buttonStatics, Button buttonClose) {
        buttonStatics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent intent = new Intent(SavedExamsActivity.this, StaticsActivity.class);
                startActivity(intent);
            }
        });
        buttonClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }
}
