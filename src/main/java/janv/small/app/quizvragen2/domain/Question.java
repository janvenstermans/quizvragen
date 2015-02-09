/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package janv.small.app.quizvragen2.domain;

/**
 *
 * @author Jan Venstermans
 */
public class Question {

    private Integer id;
    
    private Integer rowId;
    
    private String question;
    
    private String response;
    
    private String category;
    
    private String type;
    
    private Integer status;

    public Question(Integer id, String vraag, String antwoord) {
        this(id, vraag, antwoord, null, null, null, null);
    }

    public Question(Integer id, String vraagText, String antwoordText, 
            String category, String type, Integer rowId, Integer status) {
        this.id = id;
        this.question = vraagText;
        this.response = antwoordText;
        this.category = category;
        this.type = type;
        this.rowId = rowId;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRowId() {
        return rowId;
    }

    public void setRowId(Integer rowId) {
        this.rowId = rowId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    
    @Override
    public String toString() {
        return "Question{" + "id=" + id + ", question=" + question + 
                ", response=" + response + ", category=" + category 
                + ", type=" + type + ", rowId=" + rowId 
                + ", status=" + status + "}";
    }
    
    
}
