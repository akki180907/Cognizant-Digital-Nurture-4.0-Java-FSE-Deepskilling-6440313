
public class Main {
    public static void main(String[] args) {
        Logger logger1 = Logger.getInstance();
        logger1.log("Logging from main thread.");

        Logger logger2 = Logger.getInstance();
        logger2.log("Still using the same logger.");

        System.out.println(logger1 == logger2 ? "Single instance confirmed." : "Multiple logger instances.");
    }
}
