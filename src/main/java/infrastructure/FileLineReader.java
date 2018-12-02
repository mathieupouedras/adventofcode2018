package infrastructure;

import domain.LineReader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileLineReader implements LineReader {


    @Override
    public String[] read(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());
        try {
            List<String> lines = new ArrayList<String>();
            BufferedReader bufferedReader =  new BufferedReader(new FileReader(file));
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line);
            }
            String[] results = new String[lines.size()];

            return lines.toArray(results);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
