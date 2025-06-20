public class RealImage implements Image {
    private String fileName;

    public RealImage(String fileName) {
        this.fileName = fileName;
        loadFromServer(fileName);
    }

    private void loadFromServer(String fileName) {
        System.out.println("Loading image from remote server: " + fileName);
    }

    public void display() {
        System.out.println("Displaying image: " + fileName);
    }
}
