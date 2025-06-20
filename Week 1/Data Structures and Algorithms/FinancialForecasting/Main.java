public class Main {
    public static void main(String[] args) {
        double principal = 10000;
        double rate = 0.07; // 7% growth
        int years = 5;

        double futureValue = FinancialForecast.calculateFutureValue(principal, rate, years);
        System.out.printf("Predicted future value after %d years: ₹%.2f\n", years, futureValue);

        double optimizedValue = FinancialForecast.calculateFutureValueTail(principal, rate, years);
        System.out.printf("Optimized calculation: ₹%.2f\n", optimizedValue);
    }
}
