/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package janv.small.app.quizvragen2.domain;

import janv.small.app.quizvragen2.Quizvragen;
import janv.small.app.quizvragen2.QuizvragenApplicationController;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 *
 * @author Jan Venstermans
 */
public class QuestionPresenterImpl implements QuestionPresenter, 
        QuestionPresenter.Handler {
    
    private final QuizvragenApplicationController applicationController;
    
    private View view;
    
    private LinkedHashMap<Integer, Question> questionsLinkedHashMap;
    private List<Integer> idsList;
    
    private int currentQuestionIndex;
    private int defaultIncrementValue;

    public QuestionPresenterImpl(QuizvragenApplicationController applicationController,
            View view) {
        this.applicationController = applicationController;
        this.view = view;
        view.setHandler(this);
        // increment values
        defaultIncrementValue = Quizvragen.getInstance().
                getQuizvragenProperties().getSuccessIndicatorIncrementDefaultValue();
        if (defaultIncrementValue <= 0) {
            defaultIncrementValue = 1;
        }
        view.setShownDefaultIncrementValue(defaultIncrementValue);
    }

    @Override
    public void setQuestions(LinkedHashMap<Integer, Question> questionsInput) {
        this.questionsLinkedHashMap = questionsInput;
        idsList = new ArrayList(questionsLinkedHashMap.keySet());
        view.setAllQuestionsAsked(false);
        currentQuestionIndex = -1;
        loadNextQuestion();
    }

    private void loadNextQuestion() {
        currentQuestionIndex++;
        Integer questionId = getCurrentQuestionId();
        if (questionId != null) {
            view.setAnswerVisible(false);
            Question question = questionsLinkedHashMap.get(questionId);
            view.setQuestionAnswer(question.getQuestion(), question.getResponse());
            int questionPosition = currentQuestionIndex + 1;
            view.setCountLabel(questionPosition + "/" + questionsLinkedHashMap.size());
        } else {
            view.setAllQuestionsAsked(true);
        }
    }

    @Override
    public QuizvragenViewPresenter getView() {
        return view;
    }

    @Override
    public void onOk(int increment) {
        applicationController.updateStatus(getCurrentQuestion().getRowId(), true, increment);
        loadNextQuestion();
    }

    @Override
    public void onNotOk(int decrement) {
        applicationController.updateStatus(getCurrentQuestion().getRowId(), false, decrement);
        loadNextQuestion();
    }

    @Override
    public void onNewSelection() {
        applicationController.saveBuffer();
        applicationController.showSelectionView();
    }
    
    private Integer getCurrentQuestionId() {
        if (currentQuestionIndex < questionsLinkedHashMap.size()) {
            return idsList.get(currentQuestionIndex);
        } 
        return null;
    }
    
    private Question getCurrentQuestion() {
        if (currentQuestionIndex < questionsLinkedHashMap.size()) {
            Integer questionId = idsList.get(currentQuestionIndex);
            return questionsLinkedHashMap.get(questionId);
        }
        return null;
    }

}
