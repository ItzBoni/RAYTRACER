package geometry;

import tools.Intersection;
import tools.Ray;
import tools.Vector3D;

import java.util.ArrayList;

public class Topology extends Object3D {
    private Vector3D origin;
    private ArrayList<Triangle> triangles;

    public Topology(Vector3D origin, ArrayList<Triangle> t) {
        setTriangles(t);
    }

    @Override
    public Intersection intersect(Ray ray) {
        Intersection closest = null;

        for (Triangle triangle : triangles) {
            Intersection i = triangle.intersect(ray);

            if (!i.exists()) continue;

            if (closest == null || i.getT0() < closest.getT0()) {
                closest = i;
            }
        }

        if (closest == null) return new Intersection(false);
        return closest;
    }

    public void setTriangles(ArrayList<Triangle> t) {
        this.triangles = t;
    }

    public void setOrigin(Vector3D o){
        this.origin = o;
    }
}