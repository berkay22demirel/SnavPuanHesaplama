package com.berkay22demirel.sinavpuanhesaplama.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.berkay22demirel.sinavpuanhesaplama.Enum.ExamsEnum;
import com.berkay22demirel.sinavpuanhesaplama.Model.ExamDate;
import com.berkay22demirel.sinavpuanhesaplama.R;
import com.berkay22demirel.sinavpuanhesaplama.Util.DateTimeUtil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ExamCalendarAdapter extends ArrayAdapter<ExamDate> {

    LayoutInflater layoutInflater;
    Context context;
    List<ExamDate> examDateList;
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public ExamCalendarAdapter(Context context, int resource, List<ExamDate> examDateList) {
        super(context, resource, examDateList);
        this.context = context;
        this.examDateList = examDateList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.list_item_exam_calendar, parent, false);
        TextView textViewExamType = view.findViewById(R.id.textViewListItemExamCalendarExamType);
        TextView textViewDate = view.findViewById(R.id.textViewListItemExamCalendarDate);
        TextView textViewRemainingTime = view.findViewById(R.id.textViewListItemExamCalendarRemainingTime);
        ExamDate examDate = examDateList.get(position);
        textViewExamType.setText(ExamsEnum.getName(examDate.getExamType()));
        textViewDate.setText(dateFormat.format(examDate.getExamDate()));
        textViewRemainingTime.setText(DateTimeUtil.getRemainingTime(examDate.getExamDate().getTime() - new Date().getTime()));
        return view;
    }
}
