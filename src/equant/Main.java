package equant;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Scanner;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    private static String searchString;
    private static ExecutorService executorService = Executors.newFixedThreadPool(8);

    public static void main(String[] args) {

        System.out.println("Введи строку для поиска...");
        Scanner scanner = new Scanner(System.in);
        searchString = scanner.nextLine();
        System.out.println("Поиск строки: " + searchString);


        for (File fDir : File.listRoots()) {
            System.out.println(fDir);
            listDir(fDir);
        }
    }

    static public void listDir(File fDir) {
        File file = new File(fDir.getPath());
        if (fDir.list() != null) {
            String[] list = file.list();
            for (int i = 0; i < list.length; i++) {
                File f1 = new File(fDir + File.separator + list[i]);

                if (f1.isFile()) {
                    executorService.submit(new TaskFind(fDir + File.separator + list[i], searchString));
                } else {
                    listDir(f1);
                }
            }
        }
    }


}
