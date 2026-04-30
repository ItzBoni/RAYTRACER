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

        ArrayList<Object3D> objects = new ArrayList<>();
        String loc = "C:/Users/luchy/OneDrive/Desktop/RAYTRACER/public/Lowpoly_tree_sample.obj";
        Topology cubo = ObjReader.reader(loc);

        objects.add(cubo);

        Camera camera = new Camera(new Vector3D(0,0,-200),new Vector3D(0,0,1),60f,3000,3000, 100, 3000);

        Scene s = new Scene(camera, objects, Raytracer.convertToVector(Color.white.getRGB()));

        Raytracer r = new Raytracer(s);

        BufferedImage render = r.rayTrace();
        r.exportToPNG(render);
    }
}
