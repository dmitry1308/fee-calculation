import Manipulations.CalculationOfPenalties;
import Manipulations.Handler;
import ObjectsWithData.ArrearsPerMonth;
import ObjectsWithData.Fine;
import ObjectsWithData.SelectedDataInRow;
import WorkWithFile.ReadFileFromCSV;
import WorkWithFile.RecInFileCSV;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) throws ParseException, FileNotFoundException {
        String fileName = "หั 1.csv";
        ArrayList<String> listOfLinesInTable = ReadFileFromCSV.readFile(fileName);
        if (Handler.isFindPeriod(listOfLinesInTable)) {
            ArrayList<SelectedDataInRow> listSelectedDataInRows = Handler.filterDataInRow(listOfLinesInTable);

            Collections.sort(listSelectedDataInRows);

            ArrayList<SelectedDataInRow> listForPeriod = Handler.selectPeriod(listSelectedDataInRows);

            ArrayList<ArrearsPerMonth> listOfOverdueDebts = Handler.calculateListOfOverdueDebts(listForPeriod);

            ArrayList<Fine> listOfPenalties = CalculationOfPenalties.calculate(listOfOverdueDebts);
            if (listOfPenalties.isEmpty()) {
                System.out.println("Penalty is absent!");
            }
            RecInFileCSV.rec(listOfPenalties);
        } else {
            System.out.println("Data for calculation not found!");
        }
    }
}
