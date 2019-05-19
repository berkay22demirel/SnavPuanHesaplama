package com.berkay22demirel.sinavpuanhesaplama.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

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
import com.berkay22demirel.sinavpuanhesaplama.R;
import com.berkay22demirel.sinavpuanhesaplama.Util.CommonUtil;

import java.util.Comparator;
import java.util.List;

public class SavedExamsAdapter extends ArrayAdapter<Object> {
    LayoutInflater layoutInflater;
    Context context;
    List<Object> savedExamList;

    public SavedExamsAdapter(Context context, int resource, List<Object> savedExamList) {
        super(context, resource, savedExamList);
        this.context = context;
        this.savedExamList = savedExamList;
    }

    public void setSavedExamList(List<Object> savedExamList) {
        this.savedExamList = savedExamList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.list_item_saved_exams, parent, false);
        TextView textViewExamType = view.findViewById(R.id.textViewListItemSavedExamsExamType);
        TextView textViewName = view.findViewById(R.id.textViewListItemSavedExamsExamName);
        TextView textViewDescription = view.findViewById(R.id.textViewListItemSavedExamsExamDescription);
        Exam exam = (Exam) savedExamList.get(position);
        textViewExamType.setText(ExamsEnum.getName(exam.getExamType()));
        textViewName.setText(exam.getName());
        textViewDescription.setText(getExamDescription(exam));
        return view;
    }

    public String getExamDescription(Exam exam) {
        int examType = exam.getExamType();
        if (ExamsEnum.ALES.getId() == examType) {
            ALES ales = (ALES) exam;
            return ales.getNumericalResult() + CommonUtil.DIVISION_STRING + ales.getVerbalResult() + CommonUtil.DIVISION_STRING + ales.getEqualWeightResult();
        } else if (ExamsEnum.KPSS.getId() == examType) {
            KPSS kpss = (KPSS) exam;
            return CommonUtil.EMPTY_STRING;
        } else if (ExamsEnum.YKS.getId() == examType) {
            YKS yks = (YKS) exam;
            return yks.getResultSimpleTYT() + CommonUtil.DIVISION_STRING + yks.getResultSimpleNumerical() + CommonUtil.DIVISION_STRING + yks.getResultSimpleVerbal() + " ...";
        } else if (ExamsEnum.YDS.getId() == examType) {
            YDS yds = (YDS) exam;
            return yds.getResult() + "";
        } else if (ExamsEnum.DGS.getId() == examType) {
            DGS dgs = (DGS) exam;
            return dgs.getNumericalResult() + CommonUtil.DIVISION_STRING + dgs.getVerbalResult() + CommonUtil.DIVISION_STRING + dgs.getEqualWeightResult();
        } else if (ExamsEnum.EKPSS.getId() == examType) {
            EKPSS ekpss = (EKPSS) exam;
            return ekpss.getResult() + "";
        } else if (ExamsEnum.DUS.getId() == examType) {
            DUS dus = (DUS) exam;
            return dus.getResult() + "";
        } else if (ExamsEnum.TUS.getId() == examType) {
            TUS tus = (TUS) exam;
            return tus.getGraduateMedicineTPoint() + " - " + tus.getGraduateMedicineKPoint() + CommonUtil.DIVISION_STRING + tus.getGraduateMedicineAPoint() + CommonUtil.DIVISION_STRING;
        } else if (ExamsEnum.EUS.getId() == examType) {
            EUS eus = (EUS) exam;
            return eus.getResult() + "";
        }
        return CommonUtil.EMPTY_STRING;
    }
}
