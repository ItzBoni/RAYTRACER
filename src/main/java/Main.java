import geometry.Object3D;
import geometry.Sphere;
import tools.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import tools.*;
import geometry.*;

public class Main {
    public static void main(String[] args) {
        Object3D sphere1 = new Sphere(new Vector3D(0, 0, 500), 50, Raytracer.convertToVector(Color.red.getRGB()));
        Object3D sphere2 = new Sphere(new Vector3D(150,0, 250), 100, Raytracer.convertToVector(Color.blue.getRGB()));
        Object3D triangle = new Triangle(
                new Vector3D(-100, -100, 300),
                new Vector3D( 100, -100, 300),
                new Vector3D(   0,  100, 300),
                Raytracer.convertToVector(Color.green.getRGB())
        );

        ArrayList<Object3D> objects = new ArrayList<>();
        objects.add(sphere1);
        objects.add(sphere2);
        objects.add(triangle);

        Camera camera =new Camera(new Vector3D(0,0,0),new Vector3D(0,0,1),60f,2000,2000);

        Scene s = new Scene(camera, objects, Raytracer.convertToVector(Color.white.getRGB()));

        Raytracer r = new Raytracer(s);

        BufferedImage render = r.rayTrace();
        r.exportToPNG(render);
    }
}
