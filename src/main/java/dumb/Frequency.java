package dumb;

import java.io.*;
import java.util.*;

public class Frequency {

    public static void main(String[] args) {
        Frequency frequency = new Frequency();
        String[] lines = frequency.getContent("input.txt");
        Set<Integer> frequencies = new HashSet<>();
        int count = 0;
        int twice = 0;
        frequencies.add(count);
        boolean twiceFound = false;
        for (int j = 0; j < 1000; j++) {
            for (String line : lines) {
                String operator = line.substring(0, 1);
                int amount = Integer.valueOf(line.substring(1, line.length()));
                switch (operator) {
                    case "+":
                        count += amount;
                        if (!frequencies.add(count)) {
                            if (!twiceFound) {
                                twice = count;
                                System.out.println(twice);
                                twiceFound = true;
                            }
                        }
                        break;
                    case "-":
                        count -= amount;
                        if (!twiceFound && !frequencies.add(count)) {
                            if (!twiceFound) {
                                twice = count;
                                System.out.println(twice);
                                twiceFound = true;
                            }
                        }
                        break;
                    default:
                        throw new RuntimeException("invalid operator");
                }
            }

            //System.out.println(count);
            //System.out.println(twice);
        }
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
