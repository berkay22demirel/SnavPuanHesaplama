package com.berkay22demirel.sinavpuanhesaplama.Database;

import com.berkay22demirel.sinavpuanhesaplama.Enum.ExamsEnum;
import com.berkay22demirel.sinavpuanhesaplama.Enum.LessonsEnum;
import com.berkay22demirel.sinavpuanhesaplama.Enum.ResultsEnum;
import com.berkay22demirel.sinavpuanhesaplama.Exception.DatabaseException;
import com.berkay22demirel.sinavpuanhesaplama.Interface.BaseDAO;
import com.berkay22demirel.sinavpuanhesaplama.Model.EUS;
import com.berkay22demirel.sinavpuanhesaplama.Model.Exam;
import com.berkay22demirel.sinavpuanhesaplama.Model.Question;
import com.berkay22demirel.sinavpuanhesaplama.Util.ValidatorUtil;

import java.util.ArrayList;
import java.util.List;

public class EusDAO implements BaseDAO {
    private DatabaseManager databaseManager;
    private DatabaseUtils databaseUtils;

    EusDAO(DatabaseManager databaseManager, DatabaseUtils databaseUtils) {
        this.databaseManager = databaseManager;
        this.databaseUtils = databaseUtils;
    }

    @Override
    public Long put(Object data) {
        EUS eus = (EUS) data;
        long examID = databaseUtils.saveExam(eus.getName(), ExamsEnum.EUS.getId(), null);
        databaseUtils.saveQuestion(examID, LessonsEnum.EUS.getId(), eus.getEusTrue(), eus.getEusFalse(), eus.getEusNet());
        databaseUtils.saveResult(examID, ResultsEnum.EUS.getId(), eus.getResult());
        return examID;
    }

    @Override
    public Object get(Long examID) {
        EUS eus = new EUS();
        Exam exam = databaseUtils.getExam(examID);
        if (exam.getId() != null) {
            eus.setId(examID);
            eus.setName(exam.getName());
            eus.setExamType(exam.getExamType());
            Question question = databaseUtils.getQuestion(examID, LessonsEnum.EUS.getId());
            eus.setEusTrue(question.getQuestionTrue());
            eus.setEusFalse(question.getQuestionFalse());
            eus.setEusNet(question.getQuestionNet());
            eus.setResult(databaseUtils.getResult(examID, ResultsEnum.EUS.getId()));
            return eus;
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
        List<Object> eusList = new ArrayList<>();
        List<Exam> examEusList = databaseUtils.getAll(ExamsEnum.EUS.getId(), null);
        if (ValidatorUtil.isValidList(examEusList)) {
            for (Exam exam : examEusList) {
                Object eus = get(exam.getId());
                if (eus.equals(databaseManager.ERROR)) {
                    throw new DatabaseException(DatabaseException.UNEXPECTED_ERROR_TEXT);
                }
                eusList.add(eus);
            }
        }
        return eusList;
    }
}
