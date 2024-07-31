public class Transaction {
    private String description;
    private double amount;
    private String date;

    public Transaction(String description, double amount, String date) {
        this.description = description;
        this.amount = amount;
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return date + " - " + description + ": N" + amount;
    }
}
