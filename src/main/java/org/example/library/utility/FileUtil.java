package org.example.library.utility;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class FileUtil {

    public static <T> List<T> readFromFile(String filePath, Function<String, T> mapper) {
        List<T> objects = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                objects.add(mapper.apply(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return objects;
    }

    public static <T> void writeToFile(String filePath, List<T> objects) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (T object : objects) {
                bw.write(object.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
