package com.intercom.invite;
import java.util.List;
import java.util.ArrayList;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Utility {
    
    /**
     * Method to read a file and return a list of strings, one for each line.
     */
    public List<String> readFile(String filename) {
        List<String> lineStrs = new ArrayList<>();
        InputStream resource = Utility.class.getClassLoader().getResourceAsStream(filename);
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(resource))) {
            String line = null;
            while((line = reader.readLine()) != null){
                lineStrs.add(line);
            }
        } catch (Exception ex) {
            // If I have more time, I'd use a logger instead of printing to STDOUT
            System.out.println("Exception reading file");
            ex.printStackTrace();
        }
        return lineStrs;
    }

}
