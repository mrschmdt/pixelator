public class Main {
    public enum Type {
        CIRCLE,
        RECTANGLE
    }

    public static void main(String[] args){
        Pixelator.pixelate("./data/woman with the pearl earring.jpg", Type.RECTANGLE);
    }
}
