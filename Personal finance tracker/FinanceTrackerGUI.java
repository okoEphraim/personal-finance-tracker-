import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class FinanceTrackerGUI {
    private JFrame frame;
    private JTextField descriptionField;
    private JTextField amountField;
    private JTextField dateField;
    private JTextArea transactionsArea;
    private FinanceTracker financeTracker;

    public FinanceTrackerGUI() {
        financeTracker = new FinanceTracker();
        frame = new JFrame("Personal Finance Tracker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 2));

        inputPanel.add(new JLabel("Date (YYYY-MM-DD):"));
        dateField = new JTextField();
        inputPanel.add(dateField);

        inputPanel.add(new JLabel("Description:"));
        descriptionField = new JTextField();
        inputPanel.add(descriptionField);

        inputPanel.add(new JLabel("Amount:"));
        amountField = new JTextField();
        inputPanel.add(amountField);

        JButton addButton = new JButton("Add Transaction");
        addButton.addActionListener(new AddButtonListener());
        inputPanel.add(addButton);

        frame.add(inputPanel, BorderLayout.NORTH);

        transactionsArea = new JTextArea();
        transactionsArea.setEditable(false);
        frame.add(new JScrollPane(transactionsArea), BorderLayout.CENTER);

        updateTransactionsArea();
        frame.setVisible(true);
    }

    private class AddButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String date = dateField.getText();
            String description = descriptionField.getText();
            double amount;

            try {
                amount = Double.parseDouble(amountField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid amount entered. Please enter a numeric value.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (date.isEmpty() || description.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Date and description cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Transaction transaction = new Transaction(description, amount, date);
            financeTracker.addTransaction(transaction);
            updateTransactionsArea();
            dateField.setText("");
            descriptionField.setText("");
            amountField.setText("");
        }
    }

    private void updateTransactionsArea() {
        StringBuilder sb = new StringBuilder();
        for (Transaction transaction : financeTracker.getTransactions()) {
            sb.append(transaction.toString()).append("\n");
        }
        transactionsArea.setText(sb.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(FinanceTrackerGUI::new);
    }
}
