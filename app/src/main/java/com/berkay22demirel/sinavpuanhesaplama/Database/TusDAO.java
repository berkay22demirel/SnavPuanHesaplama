package com.berkay22demirel.sinavpuanhesaplama.Database;

import com.berkay22demirel.sinavpuanhesaplama.Enum.ExamsEnum;
import com.berkay22demirel.sinavpuanhesaplama.Enum.LessonsEnum;
import com.berkay22demirel.sinavpuanhesaplama.Enum.ResultsEnum;
import com.berkay22demirel.sinavpuanhesaplama.Exception.DatabaseException;
import com.berkay22demirel.sinavpuanhesaplama.Interface.BaseDAO;
import com.berkay22demirel.sinavpuanhesaplama.Model.Exam;
import com.berkay22demirel.sinavpuanhesaplama.Model.Question;
import com.berkay22demirel.sinavpuanhesaplama.Model.TUS;
import com.berkay22demirel.sinavpuanhesaplama.Util.ValidatorUtil;

import java.util.ArrayList;
import java.util.List;

public class TusDAO implements BaseDAO {
    private DatabaseManager databaseManager;
    private DatabaseUtils databaseUtils;

    TusDAO(DatabaseManager databaseManager, DatabaseUtils databaseUtils) {
        this.databaseManager = databaseManager;
        this.databaseUtils = databaseUtils;
    }

    @Override
    public Long put(Object data) {
        TUS tus = (TUS) data;
        long examID = databaseUtils.saveExam(tus.getName(), ExamsEnum.TUS.getId(), null);
        databaseUtils.saveQuestion(examID, LessonsEnum.BASIC_MEDICINE_SCIENCES.getId(), tus.getBasicMedicineSciencesTrue(), tus.getBasicMedicineSciencesFalse(), tus.getBasicMedicineSciencesNet());
        databaseUtils.saveQuestion(examID, LessonsEnum.CLINICAL_MEDICINE_SCIENCES.getId(), tus.getClinicalMedicineSciencesTrue(), tus.getClinicalMedicineSciencesFalse(), tus.getClinicalMedicineSciencesNet());
        databaseUtils.saveResult(examID, ResultsEnum.GRADUATE_MEDICINE_K_POINT.getId(), tus.getGraduateMedicineKPoint());
        databaseUtils.saveResult(examID, ResultsEnum.GRADUATE_MEDICINE_T_POINT.getId(), tus.getGraduateMedicineTPoint());
        databaseUtils.saveResult(examID, ResultsEnum.GRADUATE_MEDICINE_A_POINT.getId(), tus.getGraduateMedicineAPoint());
        databaseUtils.saveResult(examID, ResultsEnum.NOT_GRADUATE_MEDICINE_T_POINT.getId(), tus.getNotGraduateMedicineTPoint());
        return examID;
    }

    @Override
    public Object get(Long examID) {
        TUS tus = new TUS();
        Exam exam = databaseUtils.getExam(examID);
        if (exam.getId() != null) {
            tus.setId(examID);
            tus.setName(exam.getName());
            tus.setExamType(exam.getExamType());
            Question basicMedicineSciencesQuestion = databaseUtils.getQuestion(examID, LessonsEnum.BASIC_MEDICINE_SCIENCES.getId());
            tus.setBasicMedicineSciencesTrue(basicMedicineSciencesQuestion.getQuestionTrue());
            tus.setBasicMedicineSciencesFalse(basicMedicineSciencesQuestion.getQuestionFalse());
            tus.setBasicMedicineSciencesNet(basicMedicineSciencesQuestion.getQuestionNet());
            Question clinicalMedicineSciencesQuestion = databaseUtils.getQuestion(examID, LessonsEnum.CLINICAL_MEDICINE_SCIENCES.getId());
            tus.setClinicalMedicineSciencesTrue(clinicalMedicineSciencesQuestion.getQuestionTrue());
            tus.setClinicalMedicineSciencesFalse(clinicalMedicineSciencesQuestion.getQuestionFalse());
            tus.setClinicalMedicineSciencesNet(clinicalMedicineSciencesQuestion.getQuestionNet());
            tus.setGraduateMedicineKPoint(databaseUtils.getResult(examID, ResultsEnum.GRADUATE_MEDICINE_K_POINT.getId()));
            tus.setGraduateMedicineTPoint(databaseUtils.getResult(examID, ResultsEnum.GRADUATE_MEDICINE_T_POINT.getId()));
            tus.setGraduateMedicineAPoint(databaseUtils.getResult(examID, ResultsEnum.GRADUATE_MEDICINE_A_POINT.getId()));
            tus.setNotGraduateMedicineTPoint(databaseUtils.getResult(examID, ResultsEnum.NOT_GRADUATE_MEDICINE_T_POINT.getId()));
            return tus;
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
        List<Object> tusList = new ArrayList<>();
        List<Exam> examTusList = databaseUtils.getAll(ExamsEnum.TUS.getId(), null);
        if (ValidatorUtil.isValidList(examTusList)) {
            for (Exam exam : examTusList) {
                Object tus = get(exam.getId());
                if (tus.equals(databaseManager.ERROR)) {
                    throw new DatabaseException(DatabaseException.UNEXPECTED_ERROR_TEXT);
                }
                tusList.add(tus);
            }
        }
        return tusList;
    }
}
