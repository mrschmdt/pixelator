import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Pixelator{
    public static void pixelate(String path, String outputpath) {
        BufferedImage source = null;
        BufferedImage target;
        int width,height;

        try {
            source = ImageIO.read(new File(path));
        } catch (IOException e){
            e.printStackTrace();

        }

        width = source.getWidth();
        height = source.getHeight();
        target = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        //leeres Bild erstellen als BildObjekt
        Thread t = new Thread(new PixelatorRunnable(source, target, 10, 10, 0, 1));
        t.start();
        try {
           t.join();
        }catch(InterruptedException e) {

        }
        try{
            File outputfile = new File(outputpath);
            ImageIO.write(target, "jpg", outputfile);
        } catch (IOException e) {

        }
    }
}
