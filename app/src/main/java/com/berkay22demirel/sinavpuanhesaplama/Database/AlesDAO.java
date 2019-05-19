package com.berkay22demirel.sinavpuanhesaplama.Database;

import com.berkay22demirel.sinavpuanhesaplama.Enum.ExamsEnum;
import com.berkay22demirel.sinavpuanhesaplama.Enum.LessonsEnum;
import com.berkay22demirel.sinavpuanhesaplama.Enum.ResultsEnum;
import com.berkay22demirel.sinavpuanhesaplama.Exception.DatabaseException;
import com.berkay22demirel.sinavpuanhesaplama.Interface.BaseDAO;
import com.berkay22demirel.sinavpuanhesaplama.Model.ALES;
import com.berkay22demirel.sinavpuanhesaplama.Model.Exam;
import com.berkay22demirel.sinavpuanhesaplama.Model.Question;
import com.berkay22demirel.sinavpuanhesaplama.Util.ValidatorUtil;

import java.util.ArrayList;
import java.util.List;

public class AlesDAO implements BaseDAO {

    private DatabaseManager databaseManager;
    private DatabaseUtils databaseUtils;

    AlesDAO(DatabaseManager databaseManager, DatabaseUtils databaseUtils) {
        this.databaseManager = databaseManager;
        this.databaseUtils = databaseUtils;
    }

    @Override
    public Long put(Object data) {
        ALES ales = (ALES) data;
        long examID = databaseUtils.saveExam(ales.getName(), ExamsEnum.ALES.getId(), null);
        databaseUtils.saveQuestion(examID, LessonsEnum.MATHS.getId(), ales.getMathsTrue(), ales.getMathsFalse(), ales.getMathsNet());
        databaseUtils.saveQuestion(examID, LessonsEnum.TURKISH.getId(), ales.getTurkishTrue(), ales.getTurkishFalse(), ales.getTurkishNet());
        databaseUtils.saveResult(examID, ResultsEnum.NUMERICAL.getId(), ales.getNumericalResult());
        databaseUtils.saveResult(examID, ResultsEnum.VERBAL.getId(), ales.getVerbalResult());
        databaseUtils.saveResult(examID, ResultsEnum.EQUAL_WEIGHT.getId(), ales.getEqualWeightResult());
        return examID;
    }

    @Override
    public Object get(Long examID) {
        ALES ales = new ALES();
        Exam exam = databaseUtils.getExam(examID);
        if (exam.getId() != null) {
            ales.setId(examID);
            ales.setName(exam.getName());
            ales.setExamType(exam.getExamType());
            Question mathsQuestion = databaseUtils.getQuestion(examID, LessonsEnum.MATHS.getId());
            ales.setMathsTrue(mathsQuestion.getQuestionTrue());
            ales.setMathsFalse(mathsQuestion.getQuestionFalse());
            ales.setMathsNet(mathsQuestion.getQuestionNet());
            Question turkishQuestion = databaseUtils.getQuestion(examID, LessonsEnum.TURKISH.getId());
            ales.setTurkishTrue(turkishQuestion.getQuestionTrue());
            ales.setTurkishFalse(turkishQuestion.getQuestionFalse());
            ales.setTurkishNet(turkishQuestion.getQuestionNet());
            ales.setNumericalResult(databaseUtils.getResult(examID, ResultsEnum.NUMERICAL.getId()));
            ales.setVerbalResult(databaseUtils.getResult(examID, ResultsEnum.VERBAL.getId()));
            ales.setEqualWeightResult(databaseUtils.getResult(examID, ResultsEnum.EQUAL_WEIGHT.getId()));
            return ales;
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
        List<Object> alesList = new ArrayList<>();
        List<Exam> examAlesList = databaseUtils.getAll(ExamsEnum.ALES.getId(), null);
        if (ValidatorUtil.isValidList(examAlesList)) {
            for (Exam exam : examAlesList) {
                Object ales = get(exam.getId());
                if (ales.equals(databaseManager.ERROR)) {
                    throw new DatabaseException(DatabaseException.UNEXPECTED_ERROR_TEXT);
                }
                alesList.add(ales);
            }
        }
        return alesList;
    }
}
