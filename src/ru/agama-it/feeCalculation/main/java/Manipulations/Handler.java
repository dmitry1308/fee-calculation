package Manipulations;

import ObjectsWithData.ArrearsPerMonth;
import ObjectsWithData.SelectedDataInRow;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Handler {
    public static boolean isFindPeriod(ArrayList<String> lineInTable) {
        for (int i = 1; i < lineInTable.size(); i++) {
            String period = lineInTable.get(i).split(";")[0];
            if (period.equals(Const.SETTLEMENT_PERIOD)) {
                return true;
            }
        }
        return false;
    }

    public static ArrayList<SelectedDataInRow> filterDataInRow(ArrayList<String> lineInTable) throws ParseException {
        ArrayList<SelectedDataInRow> listSelectedDataInRow = new ArrayList<>();
        for (int i = 1; i < lineInTable.size(); i++) {
            String[] line = lineInTable.get(i).split(";");
            GregorianCalendar calendar = DateFormatter.formatInClassGregorianCalendar(line[0]);
            double amountMoney = Double.parseDouble(line[6]);
            SelectedDataInRow selectedDataInRow = new SelectedDataInRow(calendar, amountMoney);
            listSelectedDataInRow.add(selectedDataInRow);
        }
        return listSelectedDataInRow;
    }

    public static ArrayList<SelectedDataInRow> selectPeriod(ArrayList<SelectedDataInRow> listSelectedDataInRow) throws ParseException {
        GregorianCalendar neededPeriod = DateFormatter.formatInClassGregorianCalendar(Const.SETTLEMENT_PERIOD);
        ArrayList<SelectedDataInRow> listForPeriod = new ArrayList<>();
        for (int i = 0; i < listSelectedDataInRow.size(); i++) {
            if (listSelectedDataInRow.get(i).getAccountingMonth().compareTo(neededPeriod) <= 0) {
                listForPeriod.add(listSelectedDataInRow.get(i));
            }
        }
        return listForPeriod;
    }

    public static ArrayList<ArrearsPerMonth> calculateListOfOverdueDebts(ArrayList<SelectedDataInRow> listForPeriod) {
        ArrayList<ArrearsPerMonth> listOfOverdueDebts = new ArrayList<>();
        for (int i = 0; i < listForPeriod.size(); i++) {
            ArrearsPerMonth arrearsPerMonth = new ArrearsPerMonth();
            arrearsPerMonth.setAccountingMonth(listForPeriod.get(i).getAccountingMonth());
            arrearsPerMonth.setAmountDue(listForPeriod.get(i).getAmountDue());

            arrearsPerMonth.setDayOfDebtForThePeriod(calculateDayOfDebtForThePeriod(listForPeriod.get(i).getAccountingMonth()));
            arrearsPerMonth.setAmountOfArrears(listForPeriod.get(i).getAmountDue());

            listOfOverdueDebts.add(arrearsPerMonth);
        }
        ArrearsPerMonth lastArrearsPerMonth = listOfOverdueDebts.get(listOfOverdueDebts.size() - 1);
        lastArrearsPerMonth.setAmountOfArrears(0);
        return listOfOverdueDebts;
    }


    private static GregorianCalendar calculateDayOfDebtForThePeriod(GregorianCalendar accountingMonth) {
        GregorianCalendar dayOfDebtForPeriod = new GregorianCalendar();
        dayOfDebtForPeriod.set(Calendar.YEAR, accountingMonth.get(Calendar.YEAR));
        dayOfDebtForPeriod.set(Calendar.MONTH, accountingMonth.get(Calendar.MONTH));
        dayOfDebtForPeriod.set(Calendar.DAY_OF_MONTH, accountingMonth.get(Calendar.DAY_OF_MONTH));

        dayOfDebtForPeriod.add(Calendar.MONTH, 1);
        dayOfDebtForPeriod.set(Calendar.DAY_OF_MONTH, Const.FEE_DAY);
        dayOfDebtForPeriod.add(Calendar.DAY_OF_MONTH, Const.NUMBER_OF_DAYS_OF_DELAY_FROM_DAY_OF_CHARGING_FEE
        );
        return dayOfDebtForPeriod;
    }
}
