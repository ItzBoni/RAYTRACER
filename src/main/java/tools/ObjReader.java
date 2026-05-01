package tools;

import geometry.Topology;
import geometry.Triangle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ObjReader {
    public static Topology reader(String location) {
        ArrayList<Vector3D> vertices = new ArrayList<>();
        ArrayList<Vector3D> normals = new ArrayList<>();
        ArrayList<Vector3D> vertexTextures = new ArrayList<>();
        ArrayList<Triangle> faces = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(
                new FileReader(location))) {

            String line;
            while ((line = br.readLine()) != null) {
                int delimiter = line.indexOf(" ");
                if (delimiter == -1) continue;

                String identifier = line.substring(0, delimiter);
                String values = line.substring(delimiter + 1);

                if (identifier.equals("v")) {
                    vertices.add(extractCoords(values));
                } else if (identifier.equals("vn")) {
                    normals.add(extractCoords(values));
                } else if (identifier.equals("vt")) {
                    vertexTextures.add(extractCoords(values));
                } else if (identifier.equals("f")) {
                    faces.add(extractFace(values, vertices, normals));
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Vector3D meshCenter = calculateOrigin(vertices);

        for (int i = 0; i < vertices.size(); i++) {
            Vector3D v = vertices.get(i);
            vertices.set(i, Vector3D.sub(v, meshCenter));
        }

        return new Topology(meshCenter, faces);
    }

    private static Vector3D extractCoords(String s) {
        if(s == null) return null;

        String[] values = s.split(" ");
        ArrayList<Double> temp = new ArrayList<>();

        for (String value : values) {
            try {
                temp.add(Double.parseDouble(value));
            } catch (NumberFormatException _){

            }
        }

        if (temp.size() < 2) return null;

        double x = temp.get(0);
        double y = temp.get(1);
        double z = (temp.size() >= 3) ? temp.get(2) : 0.0;

        return new Vector3D(x, y, z);
    }

    private static Triangle extractFace(String s, ArrayList<Vector3D> vertices, ArrayList<Vector3D> normals) {
        String[] groups = s.split(" ");
        Vector3D[] faceVertices = new Vector3D[3];
        Vector3D[] faceNormals  = new Vector3D[3];

        for (int i = 0; i < 3; i++) {
            if (groups[i].contains("/")) {
                String[] indices = groups[i].split("/");
                int vIdx  = Integer.parseInt(indices[0]) - 1;
                int vnIdx = Integer.parseInt(indices[2]) - 1;
                faceVertices[i] = vertices.get(vIdx);
                faceNormals[i]  = normals.get(vnIdx);
            } else {
                faceVertices[i] = vertices.get(Integer.parseInt(groups[i]) - 1);
                faceNormals[i]  = null;
            }
        }

        Vector3D defaultColor = new Vector3D(0, 0, 255);
        return new Triangle(faceVertices[0], faceVertices[1], faceVertices[2],
                faceNormals, defaultColor);
    }

    private static Vector3D calculateOrigin(ArrayList<Vector3D> vertices) {
        double x = 0, y = 0, z = 0;

        for (Vector3D v : vertices) {
            x += v.getX();
            y += v.getY();
            z += v.getZ();
        }

        int n = vertices.size();
        return new Vector3D(x / n, y / n, z / n);
    }
}