import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FinanceTracker {
    private List<Transaction> transactions;
    private static final String FILE_NAME = "transactions.txt";

    public FinanceTracker() {
        transactions = new ArrayList<>();
        loadTransactions();
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
        saveTransactions();
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    private void saveTransactions() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Transaction t : transactions) {
                writer.write(t.getDate() + "," + t.getDescription() + "," + t.getAmount());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving transactions: " + e.getMessage());
        }
    }

    private void loadTransactions() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String date = parts[0];
                    String description = parts[1];
                    double amount = Double.parseDouble(parts[2]);
                    transactions.add(new Transaction(description, amount, date));
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading transactions: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error parsing transaction amount: " + e.getMessage());
        }
    }
}
