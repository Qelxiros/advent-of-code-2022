package common;

import java.io.*;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class Preparation {
    public static void initDir(int day) throws IOException {
        final String FILE_INIT = String.format("""
                package day%d;
                                
                import java.io.IOException;
                import java.nio.file.Files;
                import java.nio.file.Paths;
                                
                public class day%d_1 {
                    public static void main(String[] args) throws IOException {
                        String input = Files.readString(Paths.get("src/day%d/input.txt"));
                        String[] lines = input.split("\\r?\\n");
                        
                        
                    }
                }
                                
                """, day, day, day);

        new File(String.format("src/day%d", day)).mkdirs();

        BufferedWriter out = new BufferedWriter(new FileWriter(String.format("src/day%d/day%d_1.java", day, day)));
        out.write(FILE_INIT);
        out.close();
        out = new BufferedWriter(new FileWriter(String.format("src/day%d/day%d_2.java", day, day)));
        out.write(FILE_INIT.replace("_1", "_2"));
        out.close();
        new File(String.format("src/day%d/input.txt", day)).createNewFile();
    }

    public static void main(String[] args) throws IOException {
        ZoneId z = ZoneId.of("America/New_York");
        ZonedDateTime zdt = ZonedDateTime.now(z);
        initDir(zdt.getDayOfMonth()+1);
    }
}
