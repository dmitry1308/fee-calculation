package ObjectsWithData;

import Manipulations.DateFormatter;

import java.util.GregorianCalendar;

public class SelectedDataInRow implements Comparable<SelectedDataInRow> {
    private GregorianCalendar accountingMonth;
    private double amountDue;


    public SelectedDataInRow(GregorianCalendar accountingMonth, double amountDue) {
        this.accountingMonth = accountingMonth;
        this.amountDue = amountDue;
    }


    public GregorianCalendar getAccountingMonth() {
        return accountingMonth;
    }

    public void setAccountingMonth(GregorianCalendar accountingMonth) {
        this.accountingMonth = accountingMonth;
    }

    public double getAmountDue() {
        return amountDue;
    }

    public void setAmountDue(double amountDue) {
        this.amountDue = amountDue;
    }

    @Override
    public String toString() {
        return "ObjectsWithData.SelectedDataInRow{" +
                "accountingMonth=" + DateFormatter.formatInStringFromGregorianCalendar(accountingMonth) +
                ", amountDue=" + amountDue +
                '}';
    }

    @Override
    public int compareTo(SelectedDataInRow o) {
       return getAccountingMonth().compareTo(o.getAccountingMonth());
       }
}
