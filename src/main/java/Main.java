import geometry.Object3D;
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
        System.out.println("Input absolute path of .obj path");
        String loc = sc.nextLine();
        Topology cubo = ObjReader.reader(loc);

        objects.add(cubo);

        Light light = new Light(new Vector3D(0,0,100), new Vector3D(0,0,1), Vector3D.convertToVector(Color.white.getRGB()), 0.75);
        Camera camera = new Camera(new Vector3D(0,-20,-25),new Vector3D(0,0,1),60f,3000,3000, 100, 3000);

        Scene s = new Scene(camera, objects, Vector3D.convertToVector(Color.black.getRGB()), light);

        Raytracer r = new Raytracer(s);

        BufferedImage render = r.rayTrace();
        r.exportToPNG(render);
    }
}
