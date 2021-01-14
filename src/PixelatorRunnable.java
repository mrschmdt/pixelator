import java.awt.*;
import java.awt.image.BufferedImage;

public class PixelatorRunnable implements Runnable{
    private final Main.Type type;
    private BufferedImage source, target;
    private int blockWidth, blockHeight, index, rows, columns, numThreads;

    public PixelatorRunnable(BufferedImage source, BufferedImage target, int blockWidth, int blockHeight, int index, int numThreads, Main.Type type) {
        this.source = source;
        this.target = target;
        this.blockWidth = blockWidth;
        this.blockHeight = blockHeight;
        this.index = index;
        this.rows = source.getHeight()/blockHeight;
        this.columns = source.getWidth()/blockWidth;
        this.numThreads = numThreads;
        this.type = type;
    }

    @Override
    public void run() {
        Graphics2D graphics = target.createGraphics();
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        while(index < rows*columns) {
            int zeile = index / columns;
            int spalte = index % columns;
            int rot = 0;
            int gruen = 0;
            int blau = 0;

            for(int i = zeile*blockWidth; i<zeile*blockWidth+blockWidth; i++) {
                for(int j = spalte*blockHeight; j<spalte*blockHeight+blockHeight; j++) {
                    int clr = source.getRGB(j,i);
                    rot += (clr & 0x00ff0000) >> 16;
                    gruen += (clr & 0x0000ff00) >> 8;
                    blau +=   clr & 0x000000ff;
                }
            } rot = rot/(blockHeight*blockWidth);
            gruen = gruen / (blockHeight*blockHeight);
            blau = blau / (blockHeight * blockWidth);

            //spalte, zeile rechteck malen mit der farbe average_rot, average_blau, average_grün
            graphics.setColor(new Color(rot, gruen, blau));

            switch(type) {
                case CIRCLE: {
                    graphics.fillOval(spalte * blockWidth, zeile * blockHeight, blockWidth, blockHeight);
                    break;
                }
                case RECTANGLE: {
                    graphics.fillRect(spalte*blockWidth, zeile*blockHeight, blockWidth, blockHeight);
                    break;
                }
            }
            index += numThreads;
        }
        graphics.dispose();

    }
}
/*
import java.io.*;
        import java.awt.*;
        import javax.imageio.ImageIO;
        import java.awt.image.BufferedImage;

public class GetPixelColor {
    public static void main(String args[]) throws IOException {
        File file = new File("your_file.jpg");
        BufferedImage image = ImageIO.read(file);
        // Getting pixel color by position x and y
        int clr = image.getRGB(x, y);
        int red =   (clr & 0x00ff0000) >> 16;
        int green = (clr & 0x0000ff00) >> 8;
        int blue =   clr & 0x000000ff;
        System.out.println("Red Color value = " + red);
        System.out.println("Green Color value = " + green);
        System.out.println("Blue Color value = " + blue);
    }
}*/