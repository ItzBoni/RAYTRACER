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
        setOrigin(origin);
    }

    @Override
    public Intersection intersect(Ray ray) {
        Intersection closest = null;

        Vector3D localOrigin = Vector3D.sub(ray.getOrigin(), this.origin);
        Ray localRay = new Ray(localOrigin, ray.getDirection());

        for (Triangle triangle : triangles) {
            Intersection i = triangle.intersect(localRay);

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