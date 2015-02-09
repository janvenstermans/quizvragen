/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package janv.small.app.quizvragen2.persistence;

import janv.small.app.quizvragen2.domain.Question;
import java.util.Map;

/**
 * Interface for getting vragen from source.
 * 
 * @author Jan Venstermans
 */
public interface QuizvragenPersistenceService {
    
    Map<Integer, Question> getVragen();
    
    void saveCategory(int rowId, int category);
}
