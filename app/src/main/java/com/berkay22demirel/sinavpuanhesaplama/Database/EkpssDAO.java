package com.berkay22demirel.sinavpuanhesaplama.Database;

import com.berkay22demirel.sinavpuanhesaplama.Enum.ExamsEnum;
import com.berkay22demirel.sinavpuanhesaplama.Enum.LessonsEnum;
import com.berkay22demirel.sinavpuanhesaplama.Enum.ResultsEnum;
import com.berkay22demirel.sinavpuanhesaplama.Exception.DatabaseException;
import com.berkay22demirel.sinavpuanhesaplama.Interface.BaseDAO;
import com.berkay22demirel.sinavpuanhesaplama.Model.EKPSS;
import com.berkay22demirel.sinavpuanhesaplama.Model.Exam;
import com.berkay22demirel.sinavpuanhesaplama.Model.Question;
import com.berkay22demirel.sinavpuanhesaplama.Util.ValidatorUtil;

import java.util.ArrayList;
import java.util.List;

public class EkpssDAO implements BaseDAO {
    private DatabaseManager databaseManager;
    private DatabaseUtils databaseUtils;

    EkpssDAO(DatabaseManager databaseManager, DatabaseUtils databaseUtils) {
        this.databaseManager = databaseManager;
        this.databaseUtils = databaseUtils;
    }

    @Override
    public Long put(Object data) {
        EKPSS ekpss = (EKPSS) data;
        long examID = databaseUtils.saveExam(ekpss.getName(), ExamsEnum.EKPSS.getId(), ekpss.getExamSubType());
        databaseUtils.saveQuestion(examID, LessonsEnum.GENERAL_ABILITY.getId(), ekpss.getGeneralAbilityTrue(), ekpss.getGeneralAbilityFalse(), ekpss.getGeneralAbilityNet());
        databaseUtils.saveQuestion(examID, LessonsEnum.GENERAL_KNOWLEDGE.getId(), ekpss.getGeneralKnowledgeTrue(), ekpss.getGeneralKnowledgeFalse(), ekpss.getGeneralKnowledgeNet());
        databaseUtils.saveResult(examID, ResultsEnum.EKPSS.getId(), ekpss.getResult());
        return examID;
    }

    @Override
    public Object get(Long examID) {
        EKPSS ekpss = new EKPSS();
        Exam exam = databaseUtils.getExam(examID);
        if (exam.getId() != null) {
            ekpss.setId(examID);
            ekpss.setName(exam.getName());
            ekpss.setExamType(exam.getExamType());
            ekpss.setExamSubType(exam.getExamSubType());
            Question generalAbilityQuestion = databaseUtils.getQuestion(examID, LessonsEnum.GENERAL_ABILITY.getId());
            ekpss.setGeneralAbilityTrue(generalAbilityQuestion.getQuestionTrue());
            ekpss.setGeneralAbilityFalse(generalAbilityQuestion.getQuestionFalse());
            ekpss.setGeneralAbilityNet(generalAbilityQuestion.getQuestionNet());
            Question generalKnowledgeQuestion = databaseUtils.getQuestion(examID, LessonsEnum.GENERAL_KNOWLEDGE.getId());
            ekpss.setGeneralKnowledgeTrue(generalKnowledgeQuestion.getQuestionTrue());
            ekpss.setGeneralKnowledgeFalse(generalKnowledgeQuestion.getQuestionFalse());
            ekpss.setGeneralKnowledgeNet(generalKnowledgeQuestion.getQuestionNet());
            ekpss.setResult(databaseUtils.getResult(examID, ResultsEnum.EKPSS.getId()));
            return ekpss;
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
        List<Object> ekpssList = new ArrayList<>();
        List<Exam> examEkpssList = databaseUtils.getAll(ExamsEnum.EKPSS.getId(), null);
        if (ValidatorUtil.isValidList(examEkpssList)) {
            for (Exam exam : examEkpssList) {
                Object ekpss = get(exam.getId());
                if (ekpss.equals(databaseManager.ERROR)) {
                    throw new DatabaseException(DatabaseException.UNEXPECTED_ERROR_TEXT);
                }
                ekpssList.add(ekpss);
            }
        }
        return ekpssList;
    }
}
