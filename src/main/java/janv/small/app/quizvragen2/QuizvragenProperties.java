/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package janv.small.app.quizvragen2;

import java.util.List;

/**
 *
 * @author janv
 */
public interface QuizvragenProperties {
    
    List<Integer> getSuccessIndicatorIncrementChoiceList();
    
    int getSuccessIndicatorIncrementDefaultValue();
    
    int getSuccessIndicatorMinValue();
    
    int getSuccessIndicatorMaxValue();
    
}
