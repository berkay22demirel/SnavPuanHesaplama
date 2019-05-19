package com.berkay22demirel.sinavpuanhesaplama.Database;

import com.berkay22demirel.sinavpuanhesaplama.Enum.ExamsEnum;
import com.berkay22demirel.sinavpuanhesaplama.Exception.DatabaseException;
import com.berkay22demirel.sinavpuanhesaplama.Interface.BaseDAO;
import com.berkay22demirel.sinavpuanhesaplama.Model.Exam;
import com.berkay22demirel.sinavpuanhesaplama.Model.KPSS;
import com.berkay22demirel.sinavpuanhesaplama.Util.ValidatorUtil;

import java.util.ArrayList;
import java.util.List;

public class KpssDAO implements BaseDAO {
    private DatabaseManager databaseManager;
    private DatabaseUtils databaseUtils;

    KpssDAO(DatabaseManager databaseManager, DatabaseUtils databaseUtils) {
        this.databaseManager = databaseManager;
        this.databaseUtils = databaseUtils;
    }

    @Override
    public Long put(Object data) {
        KPSS kpss = (KPSS) data;
        long examID = databaseUtils.saveExam(kpss.getName(), ExamsEnum.KPSS.getId(), null);
        return examID;
    }

    @Override
    public Object get(Long examID) {
        KPSS kpss = new KPSS();
        Exam exam = databaseUtils.getExam(examID);
        if (exam.getId() != null) {
            kpss.setId(examID);
            kpss.setName(exam.getName());
            kpss.setExamType(exam.getExamType());
            return kpss;
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
        List<Object> kpssList = new ArrayList<>();
        List<Exam> examKpssList = databaseUtils.getAll(ExamsEnum.KPSS.getId(), null);
        if (ValidatorUtil.isValidList(examKpssList)) {
            for (Exam exam : examKpssList) {
                Object kpss = get(exam.getId());
                if (kpss.equals(databaseManager.ERROR)) {
                    throw new DatabaseException(DatabaseException.UNEXPECTED_ERROR_TEXT);
                }
                kpssList.add(kpss);
            }
        }
        return kpssList;
    }
}
