import geometry.Object3D;
import lights.Light;
import tools.*;

import javax.imageio.ImageIO;
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
                Light light = scene.getLight();

                if (i != null && i.exists()) {
                    Vector3D color = calculateFlatShading(i, light);
                    result.setRGB(x, y, Vector3D.convertToRGB(color));
                } else if (i!= null &&
                        (i.getT0() < this.scene.getCamera().getNearPlane() ||
                                i.getT0() < this.scene.getCamera().getFarPlane())){
                    Vector3D color = scene.getBackgroundColor();
                    result.setRGB(x,y, Vector3D.convertToRGB(color));
                } else {
                    Vector3D color = scene.getBackgroundColor();
                    result.setRGB(x,y, Vector3D.convertToRGB(color));
                }
            }
        }

        return result;
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

    public static Vector3D calculateFlatShading(Intersection intersection, Light light){
        Vector3D objectColor = intersection.getObject().getObjectColor();
        Vector3D normal = intersection.getNormal();
        double nDotL = light.calculateNDotL(intersection);

        Vector3D computedColor = Vector3D.hadamard(objectColor, light.getColor());
        double computedLight = light.getIntensity() * nDotL;

        return Vector3D.mult(computedColor, computedLight);
    }
}
