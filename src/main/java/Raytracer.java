import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static java.lang.Math.clamp;

public class Raytracer{
    private Scene scene;

    public Raytracer(Scene s){
        setScene(s);
    }

    public void setScene(Scene s){
        this.scene = s;
    }

    public BufferedImage rayTrace(){
        int sceneHeight = this.scene.getCamera().getImageHeight();
        int sceneWidth = this.scene.getCamera().getImageWidth();

        BufferedImage result = new BufferedImage(sceneWidth, sceneHeight, BufferedImage.TYPE_INT_RGB);

        for (int x = 0; x < sceneWidth; x++){
            for (int y = 0; y < sceneHeight; y++){
                Ray ray = scene.getCamera().sendRay(x,y);
                Intersection i = scene.calculateNearIntersection(ray);

                if (i != null && i.exists()) {
                    Object3D hitObject = i.getObject();
                    Vector3D color = hitObject.getObjectColor();
                    result.setRGB(x, y, convertToRGB(color));
                } else {
                    Vector3D color = scene.getBackgroundColor();
                    result.setRGB(x,y,convertToRGB(color));
                }
            }
        }

        return result;
    }

    private static int convertToRGB(Vector3D rgbVector){
        int r = (int) clamp(rgbVector.getX() * 255, 0, 255);
        int g = (int) clamp(rgbVector.getY() * 255, 0, 255);
        int b = (int) clamp(rgbVector.getZ() * 255, 0, 255);
        return new Color(r, g, b).getRGB();
    }

    public static Vector3D convertToVector(int rgb){
        Color color = new Color(rgb);
        double r = color.getRed() / 255.0;
        double g = color.getGreen() / 255.0;
        double b = color.getBlue() / 255.0;

        return new Vector3D(r, g, b);
    }

    public void exportToPNG(BufferedImage img){
        try {
            File output = new File("render.png");
            ImageIO.write(img, "PNG", output);
            System.out.println("Render saved to " + output.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Failed to save render: " + e.getMessage());
        }
    }
}
