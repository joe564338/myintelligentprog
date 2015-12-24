
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joe on 12/23/2015.
 */
public class Main {
    public static void main(String [] args){

        //replication station
        List<String> lines = new ArrayList<String>();
        try {
            for (String line : Files.readAllLines(Paths.get("src/Main.java"))) {
                String s = line;
                if(s.equals("public class Main {")){
                    System.out.println("Fixing line");
                    s = "public class MainNew {";
                }
                lines.add(s);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
            Path file = Paths.get("My intelligent prog/MainNew.java");
        try {
            Files.write(file, lines, Charset.forName("UTF-8"));
        } catch (IOException e){
            e.printStackTrace();
        }
        ProcessBuilder pb = new ProcessBuilder("javac", "My intelligent prog/MainNew.java");
        pb.redirectErrorStream(true);
        Process process = null;
        try{
            process = pb.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedReader inStreamReader = new BufferedReader(
                new InputStreamReader(process.getInputStream()));
        try {
            String line = inStreamReader.readLine();
            while (line != null) {
                System.out.println(line);
                line = inStreamReader.readLine();
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        try {
            inStreamReader.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
