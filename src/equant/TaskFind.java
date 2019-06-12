package equant;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

public class TaskFind implements Runnable {
    String fileName, findTxt;

    public TaskFind(String fileName, String findTxt) {
        this.fileName = fileName;
        this.findTxt = findTxt;
    }


    @Override
    public void run() {

        String s;

        try (LineNumberReader lineNumberReader = new LineNumberReader(new BufferedReader(new FileReader(fileName)))) {
            while (true) {
                s = lineNumberReader.readLine();
                if (s == null) break;

                if (s.indexOf(findTxt) != -1) {
                    System.out.println(fileName);
                    break;
                }
            }
        } catch (IOException e) {
            new RuntimeException(e);
        }
    }
}
