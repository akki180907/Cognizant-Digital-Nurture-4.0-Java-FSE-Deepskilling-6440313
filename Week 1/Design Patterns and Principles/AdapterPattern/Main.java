public class Main {
    public static void main(String[] args) {
        PaymentProcessor paypal = new PayPalAdapter(new PayPalGateway());
        PaymentProcessor stripe = new StripeAdapter(new StripeGateway());

        System.out.println("Processing payments:");
        paypal.processPayment(1500.00);
        stripe.processPayment(2750.50);
    }
}
