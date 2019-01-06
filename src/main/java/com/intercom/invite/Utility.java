package com.intercom.invite;
import java.util.List;
import java.util.ArrayList;
import java.nio.file.Files;
import java.util.stream.Stream;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.charset.Charset;
import java.io.IOException;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Utility {
    
    /**
     * Method to read a file and return a list of strings, one for each line.
     */
    public List<String> readFile(String filename) {
        List<String> lineStrs = new ArrayList<>();
        try {
            Path path = Paths.get(getClass().getClassLoader().getResource(filename).toURI());
            Stream<String> lines = Files.lines(path); 
            lines.forEachOrdered(line -> lineStrs.add(line));
            
        } catch (Exception ex) {
            // If I have more time, I'd log this instead of printing to STDOUT
            System.out.println("Exception reading file: " + ex.getStackTrace());
        }
        return lineStrs;
    }

}
