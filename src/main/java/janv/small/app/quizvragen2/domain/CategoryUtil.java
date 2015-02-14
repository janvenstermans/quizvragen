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
public class CategoryUtil {
    
    private static final int CATEGORY_MIN_VALUE = 1;
    private static final int CATEGORY_MAX_VALUE = 100;

    public static int getCategoryInt(String categoryText) {
        try {
            return Integer.parseInt(categoryText);
        } catch (Exception e) {
            // do nothing
        }
        return 0;
    }

    private CategoryUtil() {
    }

    public static String toUpperCase(String input) {
        return input.toUpperCase();
    }
    
    public static int getNewCategory(int oldCategory, boolean improve, int increment) {
       if (oldCategory == 0) {
           return improve ? 3 : 7; 
       } 
       int temp = improve ? oldCategory - increment : oldCategory + increment;
       if (temp < CATEGORY_MIN_VALUE) {
          return CATEGORY_MIN_VALUE; 
       } else if (temp > CATEGORY_MAX_VALUE) {
           return CATEGORY_MAX_VALUE;
       }
       return temp;
    }
}
