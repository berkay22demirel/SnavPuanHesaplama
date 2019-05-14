package com.berkay22demirel.sinavpuanhesaplama.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "SINAV_PUAN_HESAPLA";
    public static final int DATABASE_VERSION = 1;
    public static final String EXAMS_TABLE_NAME = "EXAM";
    public static final String EXAMS_COLUMN_ID = "_ID";
    public static final String EXAMS_COLUMN_NAME = "NAME";
    public static final String EXAMS_COLUMN_EXAM_TYPE = "EXAM_TYPE";
    public static final String EXAMS_COLUMN_EXAM_SUB_TYPE = "EXAM_SUB_TYPE";
    public static final String QUESTIONS_TABLE_NAME = "QUESTIONS";
    public static final String QUESTIONS_COLUMN_ID = "_ID";
    public static final String QUESTIONS_COLUMN_EXAM_ID = "EXAM_ID";
    public static final String QUESTIONS_COLUMN_LESSON_ID = "LESSON_ID";
    public static final String QUESTIONS_COLUMN_TRUE = "TRUE";
    public static final String QUESTIONS_COLUMN_FALSE = "FALSE";
    public static final String QUESTIONS_COLUMN_NET = "NET";
    public static final String RESULTS_TABLE_NAME = "RESULTS";
    public static final String RESULTS_COLUMN_ID = "_ID";
    public static final String RESULTS_COLUMN_EXAM_ID = "EXAM";
    public static final String RESULTS_COLUMN_RESULT_TYPE = "RESULT_TYPE";
    public static final String RESULTS_COLUMN_RESULT = "RESULT";
    private static final String EXAMS_TABLE_CREATE = "CREATE TABLE " + EXAMS_TABLE_NAME + " (" +
            EXAMS_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            EXAMS_COLUMN_NAME + " TEXT NOT NULL, " +
            EXAMS_COLUMN_EXAM_TYPE + " INTEGER NOT NULL, " +
            EXAMS_COLUMN_EXAM_SUB_TYPE + " INTEGER NOT NULL); ";
    private static final String QUESTIONS_TABLE_CREATE = "CREATE TABLE " + QUESTIONS_TABLE_NAME + " (" +
            QUESTIONS_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            QUESTIONS_COLUMN_EXAM_ID + " INTEGER NOT NULL, " +
            QUESTIONS_COLUMN_LESSON_ID + " INTEGER NOT NULL, " +
            QUESTIONS_COLUMN_TRUE + " INTEGER, " +
            QUESTIONS_COLUMN_FALSE + " INTEGER, " +
            QUESTIONS_COLUMN_NET + " INTEGER); ";
    private static final String RESULTS_TABLE_CREATE = "CREATE TABLE " + RESULTS_TABLE_NAME + " (" +
            RESULTS_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            RESULTS_COLUMN_EXAM_ID + " INTEGER NOT NULL, " +
            RESULTS_COLUMN_RESULT_TYPE + " INTEGER NOT NULL, " +
            RESULTS_COLUMN_RESULT + " INTEGER NOT NULL); ";
    private static final String EXAMS_TABLE_DROP = "DROP TABLE IF EXISTS " + EXAMS_TABLE_NAME + ";";
    private static final String QUESTIONS_TABLE_DROP = "DROP TABLE IF EXISTS " + QUESTIONS_TABLE_NAME + ";";
    private static final String RESULTS_TABLE_DROP = "DROP TABLE IF EXISTS " + RESULTS_TABLE_NAME + ";";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(EXAMS_TABLE_CREATE);
        db.execSQL(QUESTIONS_TABLE_CREATE);
        db.execSQL(RESULTS_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w("DatabaseHelper", "Veritabanı " + oldVersion + "dan " + newVersion + "a güncellendi.");
        db.execSQL(EXAMS_TABLE_DROP);
        db.execSQL(QUESTIONS_TABLE_DROP);
        db.execSQL(RESULTS_TABLE_DROP);
        onCreate(db);
    }
}
