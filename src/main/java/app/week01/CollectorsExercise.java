package app.week01;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Transaction {
    private int id;
    private double amount;
    private String currency;

    public Transaction(int id, double amount, String currency) {
        this.id = id;
        this.amount = amount;
        this.currency = currency;
    }

    public double getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }
}

public class CollectorsExercise {
    public static void main(String[] args) {
        List<Transaction> transactions = List.of(
                new Transaction(1, 100.0, "USD"),
                new Transaction(2, 150.0, "EUR"),
                new Transaction(3, 200.0, "USD"),
                new Transaction(4, 75.0, "GBP"),
                new Transaction(5, 120.0, "EUR"),
                new Transaction(6, 300.0, "GBP")
        );

        // Calculate the total sum of all transaction amounts
        double totalSum = transactions.stream()
                .mapToDouble(Transaction::getAmount)
                .sum();
        System.out.println("Total sum of all transactions: " + totalSum);

        // Group transactions by currency and calculate sum for each currency
        Map<String, Double> sumByCurrency = transactions.stream()
                .collect(Collectors.groupingBy(Transaction::getCurrency, Collectors.summingDouble(Transaction::getAmount)));
        System.out.println("sum for each transactions currency: " + sumByCurrency);

        // Find the highest transaction amount
        Transaction highestTransaction = transactions.stream()
                .max(Comparator.comparingDouble(Transaction::getAmount))
                .orElse(null);
        if (highestTransaction != null) {
            System.out.println("Highest transaction amount: " + highestTransaction.getAmount());
        }

        // Find the average transaction amount
        double averageAmount = transactions.stream()
                .collect(Collectors.averagingDouble(Transaction::getAmount));
        System.out.println("Average transaction amount: " + averageAmount);
    }
}
