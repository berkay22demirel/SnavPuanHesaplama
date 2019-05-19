package com.berkay22demirel.sinavpuanhesaplama.Database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.berkay22demirel.sinavpuanhesaplama.Enum.ExamsEnum;
import com.berkay22demirel.sinavpuanhesaplama.Interface.BaseDAO;
import com.berkay22demirel.sinavpuanhesaplama.Model.Exam;
import com.berkay22demirel.sinavpuanhesaplama.Model.Question;

import java.util.ArrayList;
import java.util.List;

public class DatabaseUtils {

    private DatabaseHelper databaseHelper;
    private DatabaseManager databaseManager;

    DatabaseUtils(DatabaseHelper databaseHelper, DatabaseManager databaseManager) {
        this.databaseHelper = databaseHelper;
        this.databaseManager = databaseManager;
    }

    public Long saveExam(String name, Integer examType, Integer examSubType) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(databaseHelper.EXAMS_COLUMN_NAME, name);
        contentValues.put(databaseHelper.EXAMS_COLUMN_EXAM_TYPE, examType);
        contentValues.put(databaseHelper.EXAMS_COLUMN_EXAM_SUB_TYPE, examSubType);
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        return database.insert(DatabaseHelper.EXAMS_TABLE_NAME, null, contentValues);
    }

    public Long saveQuestion(Long examID, Integer lessonID, Integer questionsTrue, Integer questionsFalse, Double questionsNet) {
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(databaseHelper.QUESTIONS_COLUMN_EXAM_ID, examID);
            contentValues.put(databaseHelper.QUESTIONS_COLUMN_LESSON_ID, lessonID);
            contentValues.put(databaseHelper.QUESTIONS_COLUMN_TRUE, questionsTrue);
            contentValues.put(databaseHelper.QUESTIONS_COLUMN_FALSE, questionsFalse);
            contentValues.put(databaseHelper.QUESTIONS_COLUMN_NET, questionsNet);
            SQLiteDatabase database = databaseHelper.getWritableDatabase();
            return database.insert(DatabaseHelper.QUESTIONS_TABLE_NAME, null, contentValues);
        } catch (Exception e) {
            return databaseManager.ERROR;
        }
    }

    public Long saveResult(Long examID, Integer resultType, Double result) {
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(databaseHelper.RESULTS_COLUMN_EXAM_ID, examID);
            contentValues.put(databaseHelper.RESULTS_COLUMN_RESULT_TYPE, resultType);
            contentValues.put(databaseHelper.RESULTS_COLUMN_RESULT, result);
            SQLiteDatabase database = databaseHelper.getWritableDatabase();
            return database.insert(DatabaseHelper.RESULTS_TABLE_NAME, null, contentValues);
        } catch (Exception e) {
            return databaseManager.ERROR;
        }
    }

    public Exam getExam(Long examID) {
        Exam exam = new Exam();
        String where = databaseHelper.EXAMS_COLUMN_ID + "=?";
        String[] whereArgs = new String[]{examID.toString()};
        SQLiteDatabase database = databaseHelper.getReadableDatabase();
        Cursor cursor = database.query(databaseHelper.EXAMS_TABLE_NAME, databaseHelper.EXAM_FULL_PROJECTION, where, whereArgs, null, null, null);
        if (cursor != null && cursor.moveToNext()) {
            exam.setId(examID);
            exam.setName(cursor.getString(cursor.getColumnIndex(DatabaseHelper.EXAMS_COLUMN_NAME)));
            exam.setExamType(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.EXAMS_COLUMN_EXAM_TYPE)));
            exam.setExamSubType(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.EXAMS_COLUMN_EXAM_SUB_TYPE)));
            return exam;
        }
        return exam;
    }

    public Question getQuestion(Long examID, Integer lessonID) {
        Question question = new Question();
        String where = databaseHelper.QUESTIONS_COLUMN_EXAM_ID + "=? AND " + databaseHelper.QUESTIONS_COLUMN_LESSON_ID + "=?";
        String[] whereArgs = new String[]{examID.toString(), lessonID.toString()};
        SQLiteDatabase database = databaseHelper.getReadableDatabase();
        Cursor cursor = database.query(databaseHelper.QUESTIONS_TABLE_NAME, databaseHelper.QUESTIONS_FULL_PROJECTION, where, whereArgs, null, null, null);
        if (cursor != null && cursor.moveToNext()) {
            question.setQuestionTrue(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.QUESTIONS_COLUMN_TRUE)));
            question.setQuestionFalse(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.QUESTIONS_COLUMN_FALSE)));
            question.setQuestionNet(cursor.getDouble(cursor.getColumnIndex(DatabaseHelper.QUESTIONS_COLUMN_NET)));
        }
        return question;
    }

    public Double getResult(Long examID, Integer resultType) {
        String where = databaseHelper.RESULTS_COLUMN_EXAM_ID + "=? AND " + databaseHelper.RESULTS_COLUMN_RESULT_TYPE + "=?";
        String[] whereArgs = new String[]{examID.toString(), resultType.toString()};
        SQLiteDatabase database = databaseHelper.getReadableDatabase();
        Cursor cursor = database.query(databaseHelper.RESULTS_TABLE_NAME, databaseHelper.RESULTS_FULL_PROJECTION, where, whereArgs, null, null, null);
        if (cursor != null && cursor.moveToNext()) {
            return cursor.getDouble(cursor.getColumnIndex(DatabaseHelper.RESULTS_COLUMN_RESULT));
        }
        return null;
    }

    public int deleteExam(Long examID) {
        String where = databaseHelper.EXAMS_COLUMN_ID + "=?";
        String[] whereArgs = new String[]{examID.toString()};
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        return database.delete(databaseHelper.EXAMS_TABLE_NAME, where, whereArgs);
    }

    public int deleteQuestions(Long examID) {
        try {
            String where = databaseHelper.QUESTIONS_COLUMN_EXAM_ID + "=?";
            String[] whereArgs = new String[]{examID.toString()};
            SQLiteDatabase database = databaseHelper.getWritableDatabase();
            return database.delete(databaseHelper.QUESTIONS_TABLE_NAME, where, whereArgs);
        } catch (Exception e) {
            return (int) DatabaseManager.ERROR;
        }
    }

    public int deleteResults(Long examID) {
        try {
            String where = databaseHelper.RESULTS_COLUMN_EXAM_ID + "=?";
            String[] whereArgs = new String[]{examID.toString()};
            SQLiteDatabase database = databaseHelper.getWritableDatabase();
            return database.delete(databaseHelper.RESULTS_TABLE_NAME, where, whereArgs);
        } catch (Exception e) {
            return (int) DatabaseManager.ERROR;
        }

    }

    public List<Exam> getAll(Integer examType, Integer examSubType) {
        List<Exam> examList = new ArrayList<>();
        String where = databaseHelper.EXAMS_COLUMN_EXAM_TYPE + "=?";
        String[] whereArgs = new String[]{examType.toString()};
        String orderBy = databaseHelper.EXAMS_COLUMN_ID + " DESC";
        if (examSubType != null) {
            where = where + "AND " + databaseHelper.EXAMS_COLUMN_EXAM_SUB_TYPE + "=?";
            whereArgs = new String[]{examType.toString(), examSubType.toString()};
        }
        SQLiteDatabase database = databaseHelper.getReadableDatabase();
        Cursor cursor = database.query(databaseHelper.EXAMS_TABLE_NAME, databaseHelper.EXAM_FULL_PROJECTION, where, whereArgs, null, null, orderBy);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                Exam exam = new Exam();
                exam.setId(cursor.getLong(cursor.getColumnIndex(DatabaseHelper.EXAMS_COLUMN_ID)));
                exam.setName(cursor.getString(cursor.getColumnIndex(DatabaseHelper.EXAMS_COLUMN_NAME)));
                exam.setExamSubType(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.EXAMS_COLUMN_EXAM_SUB_TYPE)));
                examList.add(exam);
            }
        }
        return examList;
    }

    public List<Object> getAllExam() {
        List<Object> examList = new ArrayList<>();
        String orderBy = databaseHelper.EXAMS_COLUMN_ID + " DESC";
        SQLiteDatabase database = databaseHelper.getReadableDatabase();
        Cursor cursor = database.query(databaseHelper.EXAMS_TABLE_NAME, databaseHelper.EXAM_FULL_PROJECTION, null, null, null, null, orderBy);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                Integer examTpye = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.EXAMS_COLUMN_EXAM_TYPE));
                Long examId = cursor.getLong(cursor.getColumnIndex(DatabaseHelper.EXAMS_COLUMN_ID));
                BaseDAO baseDAO = getDAO(examTpye);
                if (baseDAO != null) {
                    Object exam = baseDAO.get(examId);
                    examList.add(exam);
                }
            }
        }
        return examList;
    }

    private BaseDAO getDAO(int examType) {
        if (examType == ExamsEnum.ALES.getId()) {
            return new AlesDAO(databaseManager, this);
        } else if (examType == ExamsEnum.DGS.getId()) {
            return new DgsDAO(databaseManager, this);
        } else if (examType == ExamsEnum.DUS.getId()) {
            return new DusDAO(databaseManager, this);
        } else if (examType == ExamsEnum.EKPSS.getId()) {
            return new EkpssDAO(databaseManager, this);
        } else if (examType == ExamsEnum.EUS.getId()) {
            return new EusDAO(databaseManager, this);
        } else if (examType == ExamsEnum.KPSS.getId()) {
            return new KpssDAO(databaseManager, this);
        } else if (examType == ExamsEnum.TUS.getId()) {
            return new TusDAO(databaseManager, this);
        } else if (examType == ExamsEnum.YDS.getId()) {
            return new YdsDAO(databaseManager, this);
        } else if (examType == ExamsEnum.YKS.getId()) {
            return new YksDAO(databaseManager, this);
        }
        return null;
    }
}
