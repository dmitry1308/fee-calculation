package WorkWithFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadFileFromCSV {
    public static ArrayList<String> readFile(String path) {
       ArrayList<String> dataInRows = new ArrayList<>();
        File file = new File(path);
        try (Scanner scanner = new Scanner(file)) {
            scanner.nextLine();
            while (scanner.hasNext()) {
                String lineOfTable = scanner.nextLine();
                dataInRows.add(lineOfTable);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        return dataInRows;
    }
}
