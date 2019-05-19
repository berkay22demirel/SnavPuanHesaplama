package com.berkay22demirel.sinavpuanhesaplama.Database;

import com.berkay22demirel.sinavpuanhesaplama.Enum.ExamsEnum;
import com.berkay22demirel.sinavpuanhesaplama.Enum.LessonsEnum;
import com.berkay22demirel.sinavpuanhesaplama.Enum.ResultsEnum;
import com.berkay22demirel.sinavpuanhesaplama.Exception.DatabaseException;
import com.berkay22demirel.sinavpuanhesaplama.Interface.BaseDAO;
import com.berkay22demirel.sinavpuanhesaplama.Model.Exam;
import com.berkay22demirel.sinavpuanhesaplama.Model.Question;
import com.berkay22demirel.sinavpuanhesaplama.Model.YDS;
import com.berkay22demirel.sinavpuanhesaplama.Util.ValidatorUtil;

import java.util.ArrayList;
import java.util.List;

public class YdsDAO implements BaseDAO {
    private DatabaseManager databaseManager;
    private DatabaseUtils databaseUtils;

    YdsDAO(DatabaseManager databaseManager, DatabaseUtils databaseUtils) {
        this.databaseManager = databaseManager;
        this.databaseUtils = databaseUtils;
    }

    @Override
    public Long put(Object data) {
        YDS yds = (YDS) data;
        long examID = databaseUtils.saveExam(yds.getName(), ExamsEnum.YDS.getId(), null);
        databaseUtils.saveQuestion(examID, LessonsEnum.LANGUAGE.getId(), yds.getLanguageTrue(), yds.getLanguageFalse(), yds.getLanguageNet());
        databaseUtils.saveResult(examID, ResultsEnum.YDS.getId(), yds.getResult());
        return examID;
    }

    @Override
    public Object get(Long examID) {
        YDS yds = new YDS();
        Exam exam = databaseUtils.getExam(examID);
        if (exam.getId() != null) {
            yds.setId(examID);
            yds.setName(exam.getName());
            yds.setExamType(exam.getExamType());
            Question question = databaseUtils.getQuestion(examID, LessonsEnum.LANGUAGE.getId());
            yds.setLanguageTrue(question.getQuestionTrue());
            yds.setLanguageFalse(question.getQuestionFalse());
            yds.setLanguageNet(question.getQuestionNet());
            yds.setResult(databaseUtils.getResult(examID, ResultsEnum.YDS.getId()));
            return yds;
        }
        return DatabaseManager.ERROR;
    }

    @Override
    public Long delete(Long examID) {
        if (examID != null) {
            int deletedExamNumber = databaseUtils.deleteExam(examID);
            if (deletedExamNumber > 0) {
                databaseUtils.deleteQuestions(examID);
                databaseUtils.deleteResults(examID);
                return databaseManager.SUCCESSFUL;
            }
        }
        return databaseManager.ERROR;
    }

    @Override
    public List<Object> getAll() throws DatabaseException {
        List<Object> ydsList = new ArrayList<>();
        List<Exam> examYdsList = databaseUtils.getAll(ExamsEnum.YDS.getId(), null);
        if (ValidatorUtil.isValidList(examYdsList)) {
            for (Exam exam : examYdsList) {
                Object yds = get(exam.getId());
                if (yds.equals(databaseManager.ERROR)) {
                    throw new DatabaseException(DatabaseException.UNEXPECTED_ERROR_TEXT);
                }
                ydsList.add(yds);
            }
        }
        return ydsList;
    }
}
