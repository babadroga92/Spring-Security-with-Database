package com.solvd.hospitalpatientrecoverymodel.string;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;


public class Homework {


    public Map<String, Long> metoda(String putanja) {
        try {
            String c = FileUtils.readFileToString(new File(putanja), "UTF-8").replaceAll("\n", " ");
            Map<String, Long> counts = Arrays.asList(c.split(" ")).stream().sorted().collect(Collectors.groupingBy(s -> s.toLowerCase(), Collectors.counting()));
            return counts;
        } catch (Exception e) {
            return null;
        }
    }
}

