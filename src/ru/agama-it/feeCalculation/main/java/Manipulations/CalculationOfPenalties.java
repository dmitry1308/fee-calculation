package Manipulations;

import ObjectsWithData.ArrearsPerMonth;
import ObjectsWithData.Fine;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class CalculationOfPenalties {
    private static ArrayList<Fine> listOfPenalties = new ArrayList<>();
    private static double amountOfPenalties;
    private static double sumPenDay;
    private static double sumPenMonth;

    public static ArrayList<Fine> calculate(ArrayList<ArrearsPerMonth> listOfOverdueDebts) throws ParseException {

        int indexLineWhenStartDebtForPeriod = findIndex(listOfOverdueDebts);
        if (indexLineWhenStartDebtForPeriod == -1) {
            return listOfPenalties;
        }
        GregorianCalendar dayCreaseFine = listOfOverdueDebts.get(indexLineWhenStartDebtForPeriod).getDayOfDebtForThePeriod();
        Fine fineBeforeIndex = calculateFineBeforeIndex(listOfOverdueDebts, indexLineWhenStartDebtForPeriod);
        Fine fineStartIndex = calculateFineAfterIndex(listOfOverdueDebts, indexLineWhenStartDebtForPeriod);
        return calculateTableWithFine(fineBeforeIndex, fineStartIndex, dayCreaseFine);
    }

    private static int findIndex(ArrayList<ArrearsPerMonth> listOfOverdueDebts) throws ParseException {
        GregorianCalendar neededPeriod = DateFormatter.formatInClassGregorianCalendar(Const.SETTLEMENT_PERIOD);
        int index = -1;
        for (int i = 0; i < listOfOverdueDebts.size(); i++) {
            if (listOfOverdueDebts.get(i).getDayOfDebtForThePeriod().get(Calendar.MONTH) == neededPeriod.get(Calendar.MONTH) &&
                    listOfOverdueDebts.get(i).getDayOfDebtForThePeriod().get(Calendar.YEAR) == neededPeriod.get(Calendar.YEAR)) {
                index = i;
                break;
            }
        }
        return index;
    }

    private static Fine calculateFineBeforeIndex(ArrayList<ArrearsPerMonth> listOfOverdueDebts, int indexLineWhenStartdebtForPeriod) {
        for (int i = 0; i < indexLineWhenStartdebtForPeriod; i++) {
            amountOfPenalties += listOfOverdueDebts.get(i).getAmountOfArrears();
            sumPenDay = calculateFineForDay(amountOfPenalties);
        }
        return new Fine(formatDoubleInString(amountOfPenalties), formatDoubleInString(sumPenDay));
    }

    private static Fine calculateFineAfterIndex(ArrayList<ArrearsPerMonth> listOfOverdueDebts, int indexLineWhenStartdebtForPeriod) {
        amountOfPenalties += listOfOverdueDebts.get(indexLineWhenStartdebtForPeriod).getAmountOfArrears();
        sumPenDay = calculateFineForDay(amountOfPenalties);
        return new Fine(formatDoubleInString(amountOfPenalties), formatDoubleInString(sumPenDay));
    }

    private static ArrayList<Fine> calculateTableWithFine(Fine fineBeforeIndex, Fine fineStartIndex, GregorianCalendar dayCreaseFine) {
        int dayOfMonth = dayCreaseFine.getActualMaximum(Calendar.DAY_OF_MONTH);
        GregorianCalendar currentCalendar = new GregorianCalendar();
        currentCalendar.set(dayCreaseFine.get(Calendar.YEAR), dayCreaseFine.get(Calendar.MONTH), 1);
        for (int i = 0; i < dayOfMonth; i++) {
            if (i < dayCreaseFine.get(Calendar.DATE)) {
                String date = DateFormatter.formatInStringFromGregorianCalendar(currentCalendar);
                Fine fineBeforeIndexWithDate = new Fine(date, fineBeforeIndex.getAmountDue(), fineBeforeIndex.getAmountOfPenalty());
                listOfPenalties.add(fineBeforeIndexWithDate);
                sumPenMonth += Double.valueOf(fineBeforeIndex.getAmountOfPenalty().replace(",","."));
            } else {
                String date = DateFormatter.formatInStringFromGregorianCalendar(currentCalendar);
                Fine fineStartWithIndexWithDate = new Fine(date, fineStartIndex.getAmountDue(), fineStartIndex.getAmountOfPenalty());
                listOfPenalties.add(fineStartWithIndexWithDate);

                sumPenMonth += Double.valueOf(fineStartIndex.getAmountOfPenalty().replace(",","."));
            }
            currentCalendar.add(Calendar.DATE, 1);
        }
        return listOfPenalties;
    }

    private static double calculateFineForDay(double amountOfPenalties) {
        return Manipulations.Const.REFINE_RATE * amountOfPenalties / 100 / Manipulations.Const.REDUCE;
    }

    public static String formatDoubleInString(double number) {
        return String.format("%.2f", number);
    }


    public static double getSumPenMonth() {
        return sumPenMonth;
    }
}
