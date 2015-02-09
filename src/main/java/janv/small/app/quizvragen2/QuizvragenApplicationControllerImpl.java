/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package janv.small.app.quizvragen2;

import janv.small.app.quizvragen2.domain.CategoryUtil;
import janv.small.app.quizvragen2.domain.Question;
import janv.small.app.quizvragen2.domain.QuestionPresenter;
import janv.small.app.quizvragen2.domain.QuestionPresenterImpl;
import janv.small.app.quizvragen2.domain.SelectionPresenter;
import janv.small.app.quizvragen2.domain.SelectionPresenterImpl;
import janv.small.app.quizvragen2.domain.StartupPresenter;
import janv.small.app.quizvragen2.domain.StartupPresenterImpl;
import janv.small.app.quizvragen2.persistence.AbstractExcelPersistenceImpl;
import janv.small.app.quizvragen2.persistence.ExcelPersistenceRowAsIdImpl;
import janv.small.app.quizvragen2.persistence.QuizvragenPersistenceService;
import java.io.File;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;


/**
 *
 * @author janv
 */
public class QuizvragenApplicationControllerImpl 
        implements QuizvragenApplicationController {
    
    private StageContainer stageContainer; 
    
    private QuizvragenPersistenceService persistence;
    
    private StartupPresenter startupPresenter;
    private QuestionPresenter questionPresenter;
    private SelectionPresenter selectionPresenter;
    
    private LinkedHashMap<Integer, Question> selectectedQuestionsLinkedHashMap = new LinkedHashMap<>();
    private Set<String> categories = new HashSet<String>();
    private Set<String> types = new HashSet<String>();
    private Set<Integer> statusses = new HashSet<Integer>();

    private Map<Integer, Question> questions;
            
    public QuizvragenApplicationControllerImpl() {
    }

    @Override
    public void setStageContainer(StageContainer stageContainer) {
        this.stageContainer = stageContainer;
        
        /* create presenters */
        startupPresenter = new StartupPresenterImpl(this, 
                (StartupPresenter.View) stageContainer.createScene("/fxml/StartupView.fxml"));
        selectionPresenter = new SelectionPresenterImpl(this, 
                (SelectionPresenter.View) stageContainer.createScene("/fxml/SelectionView.fxml"));
        questionPresenter = new QuestionPresenterImpl(this, 
                (QuestionPresenter.View) stageContainer.createScene("/fxml/QuestionView.fxml"));
        
        /* show start screen */
        String fileName = Quizvragen.getFileName();
        if (fileName != null) {
            createPersistence();
            showSelectionView();
        } else {
            showStartupView();
        }
    }
    
    public void createPersistence() {
        try {
            String fileName = Quizvragen.getInstance().getFileName();
            File file = new File(fileName);
            AbstractExcelPersistenceImpl.ExcelVersion version = AbstractExcelPersistenceImpl.ExcelVersion.XLS;
            if (fileName.endsWith(".xlsx")) {
                version = AbstractExcelPersistenceImpl.ExcelVersion.XLSX;
            }
            persistence = new ExcelPersistenceRowAsIdImpl(file, version);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void createSelection(Set<String> selectedCategories,
            Set<String> selectedTypes, Set<Integer> selectedStatus) {
        // create a selection: get the ids in a specific order
        Set<Integer> selectedQuestionIds = new HashSet<Integer>();
        boolean hasCategoriesSelected = hasSelected(selectedCategories);
        boolean hasTypesSelected = hasSelected(selectedTypes);
        boolean hasStatussesSelected = hasSelected(selectedStatus);
        if (!hasCategoriesSelected && !hasTypesSelected && !hasStatussesSelected) {
            // select all
            selectedQuestionIds.addAll(questions.keySet());
        } else {
            for (Question question : questions.values()) {
               if ((!hasCategoriesSelected || selectedCategories.contains(question.getCategory())) &&
                    (!hasTypesSelected || selectedTypes.contains(question.getType())) &&
                    (!hasStatussesSelected || selectedStatus.contains(question.getStatus()))) {
                   selectedQuestionIds.add(question.getId());
               } 
            }
        }
        //Collections.sort(selectedQuestionIds);
        
        // create a linkedhashmap and pass to questionsview
        selectectedQuestionsLinkedHashMap.clear();
        for (Integer id : selectedQuestionIds) {
            Question question = questions.get(id);
            selectectedQuestionsLinkedHashMap.put(id, question);
            System.out.println(question);
        }
        questionPresenter.setQuestions(selectectedQuestionsLinkedHashMap);
        
        // show the questions view
        stageContainer.showScene("Questions", questionPresenter.getView());
    }
    
    private boolean hasSelected(Set<?> selectedSet) {
        return selectedSet != null && selectedSet.size() > 0;
    }

    @Override
    public void showSelectionView() {
        questions = persistence.getVragen();
        categories.clear();
        types.clear();
        statusses.clear();
        for (Question question : questions.values()) {
            categories.add(question.getCategory());
            types.add(question.getType());
            statusses.add(question.getStatus());
        }
        selectionPresenter.setCategoryChoice(categories);
        selectionPresenter.setTypeChoice(types);
        selectionPresenter.setStatusChoice(statusses);
        stageContainer.showScene("Selection", selectionPresenter.getView());
    }

    @Override
    public void updateStatus(Integer currentQuestionId, boolean improve) {
        if (currentQuestionId != null && questions.containsKey(currentQuestionId)) {
           Question question = questions.get(currentQuestionId);
           persistence.saveCategory(question.getRowId(), CategoryUtil.getNewCategory(question.getStatus(), improve));
        }
    }    

    @Override
    public void showStartupView() {
        stageContainer.showScene("Startup", startupPresenter.getView());
    }

    @Override
    public void onSelectFile() {
        File file = stageContainer.onSelectFile();
        if (file != null && file.isFile()) {
            Quizvragen.setFileName(file.getAbsolutePath());
            createPersistence();
            showSelectionView();
        }
    }
}
