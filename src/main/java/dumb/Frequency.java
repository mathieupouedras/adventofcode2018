package dumb;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Frequency {

    public static void main(String[] args) {
        Frequency frequency = new Frequency();
        String[] lines = frequency.getContent("input.txt");
        int count = 0;
        for (String line: lines) {
            String operator = line.substring(0, 1);
            int amount = Integer.valueOf(line.substring(1, line.length()));
            switch (operator) {
                case "+": count += amount;break;
                case "-": count -= amount;break;
                default: throw new RuntimeException("invalid operator");
            }
        }

        System.out.println(count);

    }

    private String[] getContent(String fileName) {
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
