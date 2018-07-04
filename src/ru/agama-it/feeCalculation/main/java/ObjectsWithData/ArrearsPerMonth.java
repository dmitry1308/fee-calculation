package ObjectsWithData;

import Manipulations.DateFormatter;

import java.util.GregorianCalendar;

public class ArrearsPerMonth {
    private GregorianCalendar accountingMonth;
    private double amountDue;
    private GregorianCalendar dayOfDebtForThePeriod;
    private double amountOfArrears;

    public ArrearsPerMonth() {
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

    public GregorianCalendar getDayOfDebtForThePeriod() {
        return dayOfDebtForThePeriod;
    }

    public void setDayOfDebtForThePeriod(GregorianCalendar dayOfDebtForThePeriod) {
        this.dayOfDebtForThePeriod = dayOfDebtForThePeriod;
    }

    public double getAmountOfArrears() {
        return amountOfArrears;
    }

    public void setAmountOfArrears(double amountOfArrears) {
        this.amountOfArrears = amountOfArrears;
    }

    @Override
    public String toString() {
        return "ObjectsWithData.ArrearsPerMonth{" +
                "accountingMonth=" + DateFormatter.formatInStringFromGregorianCalendar(accountingMonth) +
                ", amountDue=" + amountDue +
                ", dayOfDebtForThePeriod=" + DateFormatter.formatInStringFromGregorianCalendar(dayOfDebtForThePeriod) +
                ", amountOfArrears=" + amountOfArrears +
                '}';
    }
}
