package ObjectsWithData;

public class Fine {
    private String day;
    private String amountDue;
    private String amountOfPenalty;

    public Fine(String amountDue, String amountOfPenalty) {
        this.amountDue = amountDue;
        this.amountOfPenalty = amountOfPenalty;
    }

    public Fine(String day, String amountDue, String amountOfPenalty) {
        this.day = day;
        this.amountDue = amountDue;
        this.amountOfPenalty = amountOfPenalty;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getAmountDue() {
        return amountDue;
    }

    public void setAmountDue(String amountDue) {
        this.amountDue = amountDue;
    }

    public String getAmountOfPenalty() {
        return amountOfPenalty;
    }

    public void setAmountOfPenalty(String amountOfPenalty) {
        this.amountOfPenalty = amountOfPenalty;
    }

    @Override
    public String toString() {
        return "Fine{" +
                "day='" + day + '\'' +
                ", amountDue='" + amountDue + '\'' +
                ", amountOfPenalty='" + amountOfPenalty + '\'' +
                '}';
    }
}
