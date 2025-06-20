public class Main {
    public static void main(String[] args) {
        Notifier notifier = new EmailNotifier();
        Notifier smsNotifier = new SMSNotifierDecorator(notifier);
        Notifier fullNotifier = new SlackNotifierDecorator(smsNotifier);

        fullNotifier.send("System alert: Server down.");
    }
}
