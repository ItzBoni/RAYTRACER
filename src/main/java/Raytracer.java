import lights.Light;
import tools.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static java.lang.Math.clamp;

public class Raytracer{
    private Scene scene;

    public Raytracer(Scene s){
        setScene(s);
    }

    public static Vector3D calculateShading(Intersection intersection, ArrayList<Light> lights, Scene scene){
        Vector3D objectColor = intersection.getObject().getObjectColor();
        Vector3D normal = intersection.getNormal();
        Vector3D finalColor = new Vector3D(0,0,0);

        for (Light light : lights) {
            double nDotL = light.calculateNDotL(intersection);

            Intersection shadow = shadowIntersection(intersection, light, scene);

            if (shadow != null && shadow.exists() && shadow.getT0() < light.getShadowMaxDistance(intersection.getPoint())) continue;

            Vector3D computedColor = Vector3D.clampColor(Vector3D.hadamard(objectColor, light.getColor()));
            double computedLight = light.getFalloffIntensity(intersection.getPoint()) * nDotL;
            Vector3D temp = Vector3D.mult(computedColor, computedLight);
            finalColor = Vector3D.clampColor(Vector3D.add(finalColor, temp));
        }

        return finalColor;
    }

    public static Intersection shadowIntersection(Intersection intersection, Light light, Scene scene){
        Vector3D point = intersection.getPoint();
        Vector3D offsetPoint = Vector3D.add( point, Vector3D.mult(intersection.getNormal(), 1e-4));

        Ray shadowRay = new Ray(offsetPoint, light.getShadowDirection(point));

        Intersection i = scene.calculateNearIntersection(shadowRay);

        if (i != null && i.exists()) return i;

        return new Intersection(false);
    }

    public void setScene(Scene s){
        this.scene = s;
    }

    public BufferedImage rayTrace(){
        int sceneHeight = this.scene.getCamera().getImageHeight();
        int sceneWidth = this.scene.getCamera().getImageWidth();

        int totalPixels = sceneHeight + sceneWidth;
        System.out.println("Total pixels = " + totalPixels);
        int counter = 0;

        BufferedImage result = new BufferedImage(sceneWidth, sceneHeight, BufferedImage.TYPE_INT_RGB);

        for (int x = 0; x < sceneWidth; x++){
            for (int y = 0; y < sceneHeight; y++){
                Ray ray = scene.getCamera().sendRay(x,y);
                Intersection i = scene.calculateNearIntersection(ray);
                ArrayList<Light> lights = scene.getLights();

                if (i != null && i.exists()) {
                    Vector3D color = calculateShading(i, lights, scene);
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

                System.out.println("Pixels rendered" + counter);
                counter++;
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

}
