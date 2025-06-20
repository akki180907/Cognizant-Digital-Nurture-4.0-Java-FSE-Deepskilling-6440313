public class FinancialForecast {

    // Recursive method to calculate future value
    public static double calculateFutureValue(double principal, double rate, int years) {
        if (years == 0) return principal;
        return calculateFutureValue(principal * (1 + rate), rate, years - 1);
    }

    // Optimized (Tail-Recursive Style with Helper)
    public static double calculateFutureValueTail(double principal, double rate, int years) {
        return helper(principal, rate, years);
    }

    private static double helper(double principal, double rate, int years) {
        if (years == 0) return principal;
        return helper(principal * (1 + rate), rate, years - 1);
    }
}
