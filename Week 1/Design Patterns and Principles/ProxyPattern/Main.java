public class Main {
    public static void main(String[] args) {
        Image image1 = new ProxyImage("photo1.jpg");
        Image image2 = new ProxyImage("photo2.jpg");

        System.out.println("First call:");
        image1.display();  // loads from server

        System.out.println("\nSecond call:");
        image1.display();  // uses cached version

        System.out.println("\nNew image call:");
        image2.display();  // loads another image
    }
}
