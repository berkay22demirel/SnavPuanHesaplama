package com.berkay22demirel.sinavpuanhesaplama.Database;

import com.berkay22demirel.sinavpuanhesaplama.Enum.ExamsEnum;
import com.berkay22demirel.sinavpuanhesaplama.Enum.LessonsEnum;
import com.berkay22demirel.sinavpuanhesaplama.Enum.ResultsEnum;
import com.berkay22demirel.sinavpuanhesaplama.Exception.DatabaseException;
import com.berkay22demirel.sinavpuanhesaplama.Interface.BaseDAO;
import com.berkay22demirel.sinavpuanhesaplama.Model.DUS;
import com.berkay22demirel.sinavpuanhesaplama.Model.Exam;
import com.berkay22demirel.sinavpuanhesaplama.Model.Question;
import com.berkay22demirel.sinavpuanhesaplama.Util.ValidatorUtil;

import java.util.ArrayList;
import java.util.List;

public class DusDAO implements BaseDAO {
    private DatabaseManager databaseManager;
    private DatabaseUtils databaseUtils;

    DusDAO(DatabaseManager databaseManager, DatabaseUtils databaseUtils) {
        this.databaseManager = databaseManager;
        this.databaseUtils = databaseUtils;
    }

    @Override
    public Long put(Object data) {
        DUS dus = (DUS) data;
        long examID = databaseUtils.saveExam(dus.getName(), ExamsEnum.DUS.getId(), null);
        databaseUtils.saveQuestion(examID, LessonsEnum.BASIC_SCIENCES.getId(), dus.getBasicSciencesTrue(), dus.getBasicSciencesFalse(), dus.getBasicSciencesNet());
        databaseUtils.saveQuestion(examID, LessonsEnum.CLINICAL_SCIENCES.getId(), dus.getClinicalSciencesTrue(), dus.getClinicalSciencesFalse(), dus.getClinicalSciencesNet());
        databaseUtils.saveResult(examID, ResultsEnum.DUS.getId(), dus.getResult());
        return examID;
    }

    @Override
    public Object get(Long examID) {
        DUS dus = new DUS();
        Exam exam = databaseUtils.getExam(examID);
        if (exam.getId() != null) {
            dus.setId(examID);
            dus.setName(exam.getName());
            dus.setExamType(exam.getExamType());
            Question basicSciencesQuestion = databaseUtils.getQuestion(examID, LessonsEnum.BASIC_SCIENCES.getId());
            dus.setBasicSciencesTrue(basicSciencesQuestion.getQuestionTrue());
            dus.setBasicSciencesFalse(basicSciencesQuestion.getQuestionFalse());
            dus.setBasicSciencesNet(basicSciencesQuestion.getQuestionNet());
            Question clinicalSciencesQuestion = databaseUtils.getQuestion(examID, LessonsEnum.CLINICAL_SCIENCES.getId());
            dus.setClinicalSciencesTrue(clinicalSciencesQuestion.getQuestionTrue());
            dus.setClinicalSciencesFalse(clinicalSciencesQuestion.getQuestionFalse());
            dus.setClinicalSciencesNet(clinicalSciencesQuestion.getQuestionNet());
            dus.setResult(databaseUtils.getResult(examID, ResultsEnum.DUS.getId()));
            return dus;
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
        List<Object> dusList = new ArrayList<>();
        List<Exam> examDusList = databaseUtils.getAll(ExamsEnum.DUS.getId(), null);
        if (ValidatorUtil.isValidList(examDusList)) {
            for (Exam exam : examDusList) {
                Object dus = get(exam.getId());
                if (dus.equals(databaseManager.ERROR)) {
                    throw new DatabaseException(DatabaseException.UNEXPECTED_ERROR_TEXT);
                }
                dusList.add(dus);
            }
        }
        return dusList;
    }
}
