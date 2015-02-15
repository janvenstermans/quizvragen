/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package janv.small.app.quizvragen2;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author janv
 */
public class QuizvragenPropertiesImpl implements QuizvragenProperties {
    
    private Properties properties;
    private Properties propertiesSavedFileLocation;

    private static String propertiesFileName = "quizvragen.properties";
    private static String propertiesSavedFileLocationFileName = "savedfilelocation.properties";

    public QuizvragenPropertiesImpl() {
        properties = new Properties();
        propertiesSavedFileLocation = new Properties();
        loadPropertiesFile(propertiesFileName, properties);
        loadPropertiesFile(propertiesSavedFileLocationFileName, propertiesSavedFileLocation);
    }    

    @Override
    public List<Integer> getSuccessIndicatorIncrementChoiceList() {
        String value = properties.getProperty("success.indicator.increment.choice.values.list");
        String[] valueArray = value.split(",");
        List<Integer> resultValues = new ArrayList<>();
        for (String element : valueArray) {
            resultValues.add(Integer.parseInt(element));
        }
        return resultValues;
    }

    @Override
    public int getSuccessIndicatorIncrementDefaultValue() {
        String value = properties.getProperty("success.indicator.increment.default.value");
        return Integer.parseInt(value);
    }

    @Override
    public int getSuccessIndicatorMinValue() {
        String value = properties.getProperty("success.indicator.minimum.value");
        return Integer.parseInt(value);
    }

    @Override
    public int getSuccessIndicatorMaxValue() {
        String value = properties.getProperty("success.indicator.maximum.value");
        return Integer.parseInt(value);
    }

    @Override
    public String getSavedFileLocation() {
        return propertiesSavedFileLocation.getProperty("file.location");
    }
    
    //-----------------------------------------
    // PRIVATE METHODS
    //-----------------------------------------
    
    private void loadPropertiesFile(String resourceFileName, Properties properties) {
        InputStream input = null;
        try {
            input = QuizvragenPropertiesImpl.class.getClassLoader().getResourceAsStream(resourceFileName);
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
