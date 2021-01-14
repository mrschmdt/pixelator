import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Pixelator{
    public static void pixelate(String path, Main.Type type) {
        BufferedImage target, source = null;
        int width,height;

        File inputFile = new File(path);
        try {
            FileInputStream is = new FileInputStream(inputFile);
            source = ImageIO.read(is);
        } catch (IOException e){
            e.printStackTrace();

        }

        width = source.getWidth();
        height = source.getHeight();

        target = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Thread t = new Thread(new PixelatorRunnable(source, target, 10, 10, 0, 1, type));
        t.start();
        try {
           t.join();
        }catch(InterruptedException e) {

        }
        System.out.println(inputFile.getParent());
        try{
            File outputfile = new File(inputFile.getParent()+"\\" + inputFile.getName().substring(0, inputFile.getName().lastIndexOf(".")) + " - " + ((int)(Math.random()*100)) + " pixelated.jpg");
            ImageIO.write(target, "jpg", outputfile);
        } catch (IOException e) {

        }
    }
}
