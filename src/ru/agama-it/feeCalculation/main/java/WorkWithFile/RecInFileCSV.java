package WorkWithFile;

import Manipulations.CalculationOfPenalties;
import ObjectsWithData.Fine;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class RecInFileCSV {
    public static void rec(ArrayList<Fine> listOfPenalties) throws FileNotFoundException {
        String day = " ����";
        String amount = "�����";
        String fine = "����";
        PrintWriter printWriter = new PrintWriter("Result.csv");
        printWriter.println("������ ����");
        printWriter.println(day + ";" + amount + ";" + fine);
        for (Fine x : listOfPenalties) {
            printWriter.println(x.getDay() + ";" + x.getAmountDue() + ";" + x.getAmountOfPenalty());
        }
        printWriter.print(";" + "�����" + ";" + CalculationOfPenalties.formatDoubleInString(CalculationOfPenalties.getSumPenMonth()));
        printWriter.close();

    }
}
