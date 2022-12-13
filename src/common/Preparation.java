package common;

import java.io.*;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class Preparation {
    public static void initDir(int day) throws IOException {
        String dayString;
        if (day < 10) {
            dayString = "0" + day;
        } else {
            dayString = String.valueOf(day);
        }
        final String FILE_INIT = String.format("""
                package day%s;
                                
                import java.io.IOException;
                import java.nio.file.Files;
                import java.nio.file.Paths;
                                
                public class day%s_1 {
                    public static void main(String[] args) throws IOException {
                        String input = Files.readString(Paths.get("src/input/input%s.txt"));
                        String[] lines = input.split("\\r?\\n");
                        
                        
                    }
                }
                                
                """, dayString, dayString, dayString);

        new File(String.format("src/day%s", dayString)).mkdirs();

        BufferedWriter out = new BufferedWriter(new FileWriter(String.format("src/day%s/day%s_1.java", dayString, dayString)));
        out.write(FILE_INIT);
        out.close();
        out = new BufferedWriter(new FileWriter(String.format("src/day%s/day%s_2.java", dayString, dayString)));
        out.write(FILE_INIT.replace("_1", "_2"));
        out.close();
        System.out.println(new File(String.format("src/day%s/test.txt", dayString)).createNewFile());
        System.out.println(new File(String.format("src/input/input%s.txt", dayString)).createNewFile());
    }

    public static void main(String[] args) throws IOException {
        ZoneId z = ZoneId.of("America/New_York");
        ZonedDateTime zdt = ZonedDateTime.now(z);
        initDir(zdt.getDayOfMonth()+1);
    }
}
