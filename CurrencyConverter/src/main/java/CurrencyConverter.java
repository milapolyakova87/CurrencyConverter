import java.util.Scanner;

public class CurrencyConverter {

    // Константы для хранения курсов валют
    private static final double USD_TO_EUR = 0.85;
    private static final double USD_TO_RUB = 75.0;
    private static final double EUR_TO_USD = 1.18;
    private static final double EUR_TO_RUB = 88.0;
    private static final double RUB_TO_USD = 0.013;
    private static final double RUB_TO_EUR = 0.011;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ввод суммы для обмена
        double amount = getValidAmount(scanner);

        // Ввод исходной валюты
        String fromCurrency = getValidCurrency(scanner, "исходную");

        // Ввод целевой валюты
        String toCurrency = getValidCurrency(scanner, "целевую");

        // Конвертация валюты
        double convertedAmount = convertCurrency(amount, fromCurrency, toCurrency);

        // Вывод результата
        System.out.printf("%.2f %s равняется %.2f %s%n", amount, fromCurrency, convertedAmount, toCurrency);

        scanner.close();
    }

    // Метод для получения валидной суммы от пользователя
    private static double getValidAmount(Scanner scanner) {
        double amount = 0;
        boolean isValid = false;

        while (!isValid) {
            System.out.print("Введите сумму для обмена: ");
            try {
                amount = Double.parseDouble(scanner.nextLine());
                if (amount > 0) {
                    isValid = true;
                } else {
                    System.out.println("Сумма должна быть положительным числом. Попробуйте снова.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Некорректный ввод. Пожалуйста, введите число.");
            }
        }
        return amount;
    }

    // Метод для получения валидной валюты от пользователя
    private static String getValidCurrency(Scanner scanner, String type) {
        String currency = "";
        boolean isValid = false;

        while (!isValid) {
            System.out.printf("Введите %s валюту (USD, EUR, RUB): ", type);
            currency = scanner.nextLine().toUpperCase();
            if (currency.equals("USD") || currency.equals("EUR") || currency.equals("RUB")) {
                isValid = true;
            } else {
                System.out.println("Некорректный ввод. Пожалуйста, введите одну из валют: USD, EUR, RUB.");
            }
        }
        return currency;
    }

    // Метод для конвертации валют
    private static double convertCurrency(double amount, String fromCurrency, String toCurrency) {
        double rate = 1.0;

        // Определение курса обмена с использованием enhanced switch
        switch (fromCurrency) {
            case "USD" -> {
                switch (toCurrency) {
                    case "EUR" -> rate = USD_TO_EUR;
                    case "RUB" -> rate = USD_TO_RUB;
                }
            }
            case "EUR" -> {
                switch (toCurrency) {
                    case "USD" -> rate = EUR_TO_USD;
                    case "RUB" -> rate = EUR_TO_RUB;
                }
            }
            case "RUB" -> {
                switch (toCurrency) {
                    case "USD" -> rate = RUB_TO_USD;
                    case "EUR" -> rate = RUB_TO_EUR;
                }
            }
        }

        return amount * rate;
    }
}