package com.berkay22demirel.sinavpuanhesaplama.Database;

import com.berkay22demirel.sinavpuanhesaplama.Enum.ExamsEnum;
import com.berkay22demirel.sinavpuanhesaplama.Enum.LessonsEnum;
import com.berkay22demirel.sinavpuanhesaplama.Enum.ResultsEnum;
import com.berkay22demirel.sinavpuanhesaplama.Exception.DatabaseException;
import com.berkay22demirel.sinavpuanhesaplama.Interface.BaseDAO;
import com.berkay22demirel.sinavpuanhesaplama.Model.DGS;
import com.berkay22demirel.sinavpuanhesaplama.Model.Exam;
import com.berkay22demirel.sinavpuanhesaplama.Model.Question;
import com.berkay22demirel.sinavpuanhesaplama.Util.ValidatorUtil;

import java.util.ArrayList;
import java.util.List;

public class DgsDAO implements BaseDAO {

    private DatabaseManager databaseManager;
    private DatabaseUtils databaseUtils;

    DgsDAO(DatabaseManager databaseManager, DatabaseUtils databaseUtils) {
        this.databaseManager = databaseManager;
        this.databaseUtils = databaseUtils;
    }

    @Override
    public Long put(Object data) {
        DGS dgs = (DGS) data;
        long examID = databaseUtils.saveExam(dgs.getName(), ExamsEnum.DGS.getId(), null);
        databaseUtils.saveQuestion(examID, LessonsEnum.NUMERICAL.getId(), dgs.getNumericalTrue(), dgs.getNumericalFalse(), dgs.getNumericalNet());
        databaseUtils.saveQuestion(examID, LessonsEnum.VERBAL.getId(), dgs.getVerbalTrue(), dgs.getVerbalFalse(), dgs.getVerbalNet());
        databaseUtils.saveResult(examID, ResultsEnum.NUMERICAL.getId(), dgs.getNumericalResult());
        databaseUtils.saveResult(examID, ResultsEnum.VERBAL.getId(), dgs.getVerbalResult());
        databaseUtils.saveResult(examID, ResultsEnum.EQUAL_WEIGHT.getId(), dgs.getEqualWeightResult());
        return examID;
    }

    @Override
    public Object get(Long examID) {
        DGS dgs = new DGS();
        Exam exam = databaseUtils.getExam(examID);
        if (exam.getId() != null) {
            dgs.setId(examID);
            dgs.setName(exam.getName());
            dgs.setExamType(exam.getExamType());
            Question numericalQuestion = databaseUtils.getQuestion(examID, LessonsEnum.NUMERICAL.getId());
            dgs.setNumericalTrue(numericalQuestion.getQuestionTrue());
            dgs.setNumericalFalse(numericalQuestion.getQuestionFalse());
            dgs.setNumericalNet(numericalQuestion.getQuestionNet());
            Question verbalQuestion = databaseUtils.getQuestion(examID, LessonsEnum.VERBAL.getId());
            dgs.setVerbalTrue(verbalQuestion.getQuestionTrue());
            dgs.setVerbalFalse(verbalQuestion.getQuestionFalse());
            dgs.setVerbalNet(verbalQuestion.getQuestionNet());
            Question associateDegreeSuccessGradeQuestion = databaseUtils.getQuestion(examID, LessonsEnum.ASSOCUATE_DEGREE_SUCCESS_GRADE.getId());
            dgs.setAssociateDegreeSuccessGrade(associateDegreeSuccessGradeQuestion.getQuestionTrue());
            dgs.setNumericalResult(databaseUtils.getResult(examID, ResultsEnum.NUMERICAL.getId()));
            dgs.setVerbalResult(databaseUtils.getResult(examID, ResultsEnum.VERBAL.getId()));
            dgs.setEqualWeightResult(databaseUtils.getResult(examID, ResultsEnum.EQUAL_WEIGHT.getId()));
            return dgs;
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
        List<Object> dgsList = new ArrayList<>();
        List<Exam> examDgsList = databaseUtils.getAll(ExamsEnum.DGS.getId(), null);
        if (ValidatorUtil.isValidList(examDgsList)) {
            for (Exam exam : examDgsList) {
                Object dgs = get(exam.getId());
                if (dgs.equals(databaseManager.ERROR)) {
                    throw new DatabaseException(DatabaseException.UNEXPECTED_ERROR_TEXT);
                }
                dgsList.add(dgs);
            }
        }
        return dgsList;
    }
}
