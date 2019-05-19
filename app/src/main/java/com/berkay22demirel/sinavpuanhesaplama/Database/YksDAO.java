package com.berkay22demirel.sinavpuanhesaplama.Database;

import com.berkay22demirel.sinavpuanhesaplama.Enum.ExamsEnum;
import com.berkay22demirel.sinavpuanhesaplama.Enum.LessonsEnum;
import com.berkay22demirel.sinavpuanhesaplama.Enum.ResultsEnum;
import com.berkay22demirel.sinavpuanhesaplama.Exception.DatabaseException;
import com.berkay22demirel.sinavpuanhesaplama.Interface.BaseDAO;
import com.berkay22demirel.sinavpuanhesaplama.Model.Exam;
import com.berkay22demirel.sinavpuanhesaplama.Model.Question;
import com.berkay22demirel.sinavpuanhesaplama.Model.YKS;
import com.berkay22demirel.sinavpuanhesaplama.Util.ValidatorUtil;

import java.util.ArrayList;
import java.util.List;

public class YksDAO implements BaseDAO {

    private DatabaseManager databaseManager;
    private DatabaseUtils databaseUtils;

    YksDAO(DatabaseManager databaseManager, DatabaseUtils databaseUtils) {
        this.databaseManager = databaseManager;
        this.databaseUtils = databaseUtils;
    }

    @Override
    public Long put(Object data) {
        YKS yks = (YKS) data;
        long examID = databaseUtils.saveExam(yks.getName(), ExamsEnum.YKS.getId(), null);
        databaseUtils.saveQuestion(examID, LessonsEnum.TURKISH.getId(), yks.getTurkishTrue(), yks.getTurkishFalse(), yks.getTurkishNet());
        databaseUtils.saveQuestion(examID, LessonsEnum.SOCIAL.getId(), yks.getSocialTrue(), yks.getSocialFalse(), yks.getSocialNet());
        databaseUtils.saveQuestion(examID, LessonsEnum.MATHS.getId(), yks.getMathsTrue(), yks.getMathsFalse(), yks.getMathsNet());
        databaseUtils.saveQuestion(examID, LessonsEnum.SCIENCE.getId(), yks.getScienceTrue(), yks.getScienceFalse(), yks.getScienceNet());
        databaseUtils.saveQuestion(examID, LessonsEnum.MATHS2.getId(), yks.getMaths2True(), yks.getMaths2False(), yks.getMaths2Net());
        databaseUtils.saveQuestion(examID, LessonsEnum.PHYSICS.getId(), yks.getPhysicsTrue(), yks.getPhysicsFalse(), yks.getPhysicsNet());
        databaseUtils.saveQuestion(examID, LessonsEnum.CHEMISTRY.getId(), yks.getChemistryTrue(), yks.getChemistryFalse(), yks.getChemistryNet());
        databaseUtils.saveQuestion(examID, LessonsEnum.BIOLOGY.getId(), yks.getBiologyTrue(), yks.getBiologyFalse(), yks.getBiologyNet());
        databaseUtils.saveQuestion(examID, LessonsEnum.LITERATURE.getId(), yks.getLiteratureTrue(), yks.getLiteratureFalse(), yks.getLiteratureNet());
        databaseUtils.saveQuestion(examID, LessonsEnum.HISTORY.getId(), yks.getHistoryTrue(), yks.getHistoryFalse(), yks.getHistoryNet());
        databaseUtils.saveQuestion(examID, LessonsEnum.GEOGRAPHICS.getId(), yks.getGeographicsTrue(), yks.getGeographicsFalse(), yks.getGeographicsNet());
        databaseUtils.saveQuestion(examID, LessonsEnum.HISTORY2.getId(), yks.getHistory2True(), yks.getHistory2False(), yks.getHistory2Net());
        databaseUtils.saveQuestion(examID, LessonsEnum.GEOGRAPHICS2.getId(), yks.getGeographics2True(), yks.getGeographics2False(), yks.getGeographics2Net());
        databaseUtils.saveQuestion(examID, LessonsEnum.PHILOSOPHY.getId(), yks.getPhilosophyTrue(), yks.getPhilosophyFalse(), yks.getPhilosophyNet());
        databaseUtils.saveQuestion(examID, LessonsEnum.RELIGION.getId(), yks.getReligionTrue(), yks.getReligionFalse(), yks.getReligionNet());
        databaseUtils.saveQuestion(examID, LessonsEnum.LANGUAGE.getId(), yks.getLanguageTrue(), yks.getLanguageFalse(), yks.getLanguageNet());
        databaseUtils.saveQuestion(examID, LessonsEnum.DIPLOMA_GRADE.getId(), yks.getDiplomaGrade(), null, null);
        databaseUtils.saveResult(examID, ResultsEnum.SIMPLE_TYT.getId(), yks.getResultSimpleTYT());
        databaseUtils.saveResult(examID, ResultsEnum.SIMPLE_NUMERICAL.getId(), yks.getResultSimpleNumerical());
        databaseUtils.saveResult(examID, ResultsEnum.SIMPLE_VERBAL.getId(), yks.getResultSimpleVerbal());
        databaseUtils.saveResult(examID, ResultsEnum.SIMPLE_EQUAL_WEIGHT.getId(), yks.getResultSimpleEqualWeight());
        databaseUtils.saveResult(examID, ResultsEnum.SIMPLE_LANGUAGE.getId(), yks.getResultSimpleLanguage());
        databaseUtils.saveResult(examID, ResultsEnum.CALCULATED_TYT.getId(), yks.getResultCalculatedTYT());
        databaseUtils.saveResult(examID, ResultsEnum.CALCULATED_NUMERICAL.getId(), yks.getResultCalculatedNumerical());
        databaseUtils.saveResult(examID, ResultsEnum.CALCULATED_VERBAL.getId(), yks.getResultCalculatedVerbal());
        databaseUtils.saveResult(examID, ResultsEnum.CALCULATED_EQUAL_WEIGHT.getId(), yks.getResultCalculatedEqualWeight());
        databaseUtils.saveResult(examID, ResultsEnum.CALCULATED_LANGUAGE.getId(), yks.getResultCalculatedLanguage());
        return examID;
    }

    @Override
    public Object get(Long examID) {
        YKS yks = new YKS();
        Exam exam = databaseUtils.getExam(examID);
        if (exam.getId() != null) {
            yks.setId(examID);
            yks.setName(exam.getName());
            yks.setExamType(exam.getExamType());
            Question diplomaGrade = databaseUtils.getQuestion(examID, LessonsEnum.DIPLOMA_GRADE.getId());
            yks.setDiplomaGrade(diplomaGrade.getQuestionTrue());
            Question turkishQuestion = databaseUtils.getQuestion(examID, LessonsEnum.TURKISH.getId());
            yks.setTurkishTrue(turkishQuestion.getQuestionTrue());
            yks.setTurkishFalse(turkishQuestion.getQuestionFalse());
            yks.setTurkishNet(turkishQuestion.getQuestionNet());
            Question socialQuestion = databaseUtils.getQuestion(examID, LessonsEnum.SOCIAL.getId());
            yks.setSocialTrue(socialQuestion.getQuestionTrue());
            yks.setSocialFalse(socialQuestion.getQuestionFalse());
            yks.setSocialNet(socialQuestion.getQuestionNet());
            Question mathsQuestion = databaseUtils.getQuestion(examID, LessonsEnum.MATHS.getId());
            yks.setMathsTrue(mathsQuestion.getQuestionTrue());
            yks.setMathsFalse(mathsQuestion.getQuestionFalse());
            yks.setMathsNet(mathsQuestion.getQuestionNet());
            Question scienceQuestion = databaseUtils.getQuestion(examID, LessonsEnum.SCIENCE.getId());
            yks.setScienceTrue(scienceQuestion.getQuestionTrue());
            yks.setScienceFalse(scienceQuestion.getQuestionFalse());
            yks.setScienceNet(scienceQuestion.getQuestionNet());
            Question maths2Question = databaseUtils.getQuestion(examID, LessonsEnum.MATHS2.getId());
            yks.setMaths2True(maths2Question.getQuestionTrue());
            yks.setMaths2False(maths2Question.getQuestionFalse());
            yks.setMaths2Net(maths2Question.getQuestionNet());
            Question physicsQuestion = databaseUtils.getQuestion(examID, LessonsEnum.PHYSICS.getId());
            yks.setPhysicsTrue(physicsQuestion.getQuestionTrue());
            yks.setPhysicsFalse(physicsQuestion.getQuestionFalse());
            yks.setPhysicsNet(physicsQuestion.getQuestionNet());
            Question chemistryQuestion = databaseUtils.getQuestion(examID, LessonsEnum.CHEMISTRY.getId());
            yks.setChemistryTrue(chemistryQuestion.getQuestionTrue());
            yks.setChemistryFalse(chemistryQuestion.getQuestionFalse());
            yks.setChemistryNet(chemistryQuestion.getQuestionNet());
            Question biologyQuestion = databaseUtils.getQuestion(examID, LessonsEnum.BIOLOGY.getId());
            yks.setBiologyTrue(biologyQuestion.getQuestionTrue());
            yks.setBiologyFalse(biologyQuestion.getQuestionFalse());
            yks.setBiologyNet(biologyQuestion.getQuestionNet());
            Question literatureQuestion = databaseUtils.getQuestion(examID, LessonsEnum.LITERATURE.getId());
            yks.setLiteratureTrue(literatureQuestion.getQuestionTrue());
            yks.setLiteratureFalse(literatureQuestion.getQuestionFalse());
            yks.setLiteratureNet(literatureQuestion.getQuestionNet());
            Question historyQuestion = databaseUtils.getQuestion(examID, LessonsEnum.HISTORY.getId());
            yks.setHistoryTrue(historyQuestion.getQuestionTrue());
            yks.setHistoryFalse(historyQuestion.getQuestionFalse());
            yks.setHistoryNet(historyQuestion.getQuestionNet());
            Question geographicsQuestion = databaseUtils.getQuestion(examID, LessonsEnum.GEOGRAPHICS.getId());
            yks.setGeographicsTrue(geographicsQuestion.getQuestionTrue());
            yks.setGeographicsFalse(geographicsQuestion.getQuestionFalse());
            yks.setGeographicsNet(geographicsQuestion.getQuestionNet());
            Question history2Question = databaseUtils.getQuestion(examID, LessonsEnum.HISTORY2.getId());
            yks.setHistory2True(history2Question.getQuestionTrue());
            yks.setHistory2False(history2Question.getQuestionFalse());
            yks.setHistory2Net(history2Question.getQuestionNet());
            Question geographics2Question = databaseUtils.getQuestion(examID, LessonsEnum.GEOGRAPHICS2.getId());
            yks.setGeographics2True(geographics2Question.getQuestionTrue());
            yks.setGeographics2False(geographics2Question.getQuestionFalse());
            yks.setGeographics2Net(geographics2Question.getQuestionNet());
            Question philosophyQuestion = databaseUtils.getQuestion(examID, LessonsEnum.PHILOSOPHY.getId());
            yks.setPhilosophyTrue(philosophyQuestion.getQuestionTrue());
            yks.setPhilosophyFalse(philosophyQuestion.getQuestionFalse());
            yks.setPhilosophyNet(philosophyQuestion.getQuestionNet());
            Question religionQuestion = databaseUtils.getQuestion(examID, LessonsEnum.RELIGION.getId());
            yks.setReligionTrue(religionQuestion.getQuestionTrue());
            yks.setReligionFalse(religionQuestion.getQuestionFalse());
            yks.setReligionNet(religionQuestion.getQuestionNet());
            Question languageQuestion = databaseUtils.getQuestion(examID, LessonsEnum.LANGUAGE.getId());
            yks.setLanguageTrue(languageQuestion.getQuestionTrue());
            yks.setLanguageFalse(languageQuestion.getQuestionFalse());
            yks.setLanguageNet(languageQuestion.getQuestionNet());
            yks.setResultSimpleTYT(databaseUtils.getResult(examID, ResultsEnum.SIMPLE_TYT.getId()));
            yks.setResultSimpleNumerical(databaseUtils.getResult(examID, ResultsEnum.SIMPLE_NUMERICAL.getId()));
            yks.setResultSimpleVerbal(databaseUtils.getResult(examID, ResultsEnum.SIMPLE_VERBAL.getId()));
            yks.setResultSimpleEqualWeight(databaseUtils.getResult(examID, ResultsEnum.SIMPLE_EQUAL_WEIGHT.getId()));
            yks.setResultSimpleLanguage(databaseUtils.getResult(examID, ResultsEnum.SIMPLE_LANGUAGE.getId()));
            yks.setResultCalculatedTYT(databaseUtils.getResult(examID, ResultsEnum.CALCULATED_TYT.getId()));
            yks.setResultCalculatedNumerical(databaseUtils.getResult(examID, ResultsEnum.CALCULATED_NUMERICAL.getId()));
            yks.setResultCalculatedVerbal(databaseUtils.getResult(examID, ResultsEnum.CALCULATED_VERBAL.getId()));
            yks.setResultCalculatedEqualWeight(databaseUtils.getResult(examID, ResultsEnum.CALCULATED_EQUAL_WEIGHT.getId()));
            yks.setResultCalculatedLanguage(databaseUtils.getResult(examID, ResultsEnum.CALCULATED_LANGUAGE.getId()));
            return yks;
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
        List<Object> yksList = new ArrayList<>();
        List<Exam> examYksList = databaseUtils.getAll(ExamsEnum.YKS.getId(), null);
        if (ValidatorUtil.isValidList(examYksList)) {
            for (Exam exam : examYksList) {
                Object yks = get(exam.getId());
                if (yks.equals(databaseManager.ERROR)) {
                    throw new DatabaseException(DatabaseException.UNEXPECTED_ERROR_TEXT);
                }
                yksList.add(yks);
            }
        }
        return yksList;
    }
}
