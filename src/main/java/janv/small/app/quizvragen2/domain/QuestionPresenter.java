/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package janv.small.app.quizvragen2.domain;

import java.util.LinkedHashMap;

/**
 *
 * @author Jan Venstermans
 */
public interface QuestionPresenter {

    public QuizvragenViewPresenter getView();
    
    public interface View extends QuizvragenViewPresenter {

        void setHandler(Handler vragenView);
        
        void setAnswerVisible(boolean visible);
        
        void setQuestionAnswer(String question, String answer);
        
        void setShownIncrementValue(Integer incrementValue);
        
        void setShownDefaultIncrementValue(int defaultIncrementValue);

        public void setAllQuestionsAsked(boolean b);

        public void setCountLabel(String string);
        
    }
    
    public interface Handler {

        public void onOk(int increment);

        public void onNotOk(int decrement);

        public void onNewSelection();

    }

    void setQuestions(LinkedHashMap<Integer, Question> question);
}
