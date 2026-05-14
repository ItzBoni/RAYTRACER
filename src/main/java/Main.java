import geometry.Object3D;
import lights.*;
import tools.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Scanner;

import geometry.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Object3D> objects = new ArrayList<>();
        ArrayList<Light> lights = new ArrayList<>();

        System.out.println("Input absolute path of .obj path");
        String loc = sc.nextLine();
        System.out.println("Reading object...");
        Topology cubo = ObjReader.reader(loc);
        Object3D sphere = new Sphere(new Vector3D(0, -100, 25), 15, new Vector3D(1,1,1));

        objects.add(cubo);
        objects.add(sphere);

        System.out.println("Adding lights...");
        Light dirLight = new DirectionalLight(new Vector3D(0,1,0), Vector3D.convertToVector(Color.white.getRGB()), 1);
        Light pointLight = new PointLight(new Vector3D(-5, 5, 0), Vector3D.convertToVector(Color.blue.getRGB()), 25);

        lights.add(dirLight);
        lights.add(pointLight);

        Camera camera = new Camera(new Vector3D(-10, 8, 12), new Vector3D(0, 1, 0), 60f, 1000, 1000, 0.1, 3000);

        Scene s = new Scene(camera, objects, Vector3D.convertToVector(Color.black.getRGB()), lights);

        Raytracer r = new Raytracer(s);

        System.out.println("Rendering image...");
        BufferedImage render = r.rayTrace();
        r.exportToPNG(render);
    }
}
