package bando;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class ReadFile {
    
    public static String readFile(String path) {
        StringBuilder builder = new StringBuilder();
        
        try {
            try (BufferedReader br = new BufferedReader(new FileReader(path))) {
                String line;
                while((line = br.readLine()) != null) {
                    builder.append(line + "  ");
                }
            }
        } catch(IOException e) {
        }
        return builder.toString();
    }
    
    public static int parseInt(String number) {
        try {
            return Integer.parseInt(number);
        } catch(NumberFormatException e) {
            return 0;
        }
    }
}
