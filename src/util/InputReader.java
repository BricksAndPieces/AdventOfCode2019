package util;

import java.io.File;
import java.util.Scanner;

public class InputReader {

    public static String getInput(int day) {
        try {
            String fileName = "inputs/Day" + day + ".txt";
            Scanner scanner = new Scanner(new File(fileName));

            StringBuilder input = new StringBuilder();
            while(scanner.hasNext())
                input.append(scanner.nextLine()).append("\n");

            scanner.close();
            return input.substring(0, input.length() - 1);
        }catch(Exception e) { throw new IllegalArgumentException(e); }
    }
}