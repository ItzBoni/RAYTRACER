package tools;

import geometry.Topology;
import geometry.Triangle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;

public class ObjReader {
    public static Topology ObjReader(){
        Topology top = null;
        ArrayList<Vector3D> vertices = new ArrayList<>();
        ArrayList<Vector3D> normals = new ArrayList<>();
        ArrayList<Vector3D> vertexTextures = new ArrayList<>();
        ArrayList<Triangle> faces = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(
                new FileReader("C:/Users/luchy/OneDrive/Desktop/RAYTRACER/public/Cubolol.obj")))
        {
            String line;
            while ((line = br.readLine()) != null) {
                int delimeter = line.indexOf(" ");
                String value = "";
                if (delimeter != -1) value = line.substring(0, delimeter);

                if (value.equals("v")){

                } else if (value.equals("vn")){

                } else if (value.equals("vt")){

                } else if (value.equals("f")){

                } else if (value.equals("o")){

                } else if (value.equals("g")){

                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return top;
    }
}
