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
public enum Category {

    VARIA("Varia"),
    TV("Tv"),
    ACTUA("Actua"),
    SPORT("Sport");
    
    private String value;

    private Category(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
