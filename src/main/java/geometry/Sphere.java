package geometry;

import tools.*;

public class Sphere extends Object3D {
    private Vector3D position, normal;
    private double radius;

    public Sphere(Vector3D position, double radius, Vector3D sphereColor) {
        super(sphereColor);
        this.position = position;
        this.radius = radius;
        this.objectColor = sphereColor;
    }

    @Override
    public Intersection intersect(Ray ray) {
        // L = C - O
        Vector3D L = Vector3D.sub(position, ray.getOrigin());

        // tca = L · D
        double tca = Vector3D.dot(L, ray.getDirection());

        // if tca < 0, sphere is behind the ray
        if (tca < 0) return new Intersection(false);

        // d² = L·L - tca²
        double dSquared = Vector3D.dot(L, L) - tca * tca;

        // if d² > r², ray misses the sphere
        if (dSquared > radius * radius) return new Intersection(false);

        //thc = √(r² - d²)
        double thc = Math.sqrt(radius * radius - dSquared);

        //t0 and t1
        double t0 = tca - thc;
        double t1 = tca + thc;

        // Compute the actual hit point using the closer t
        Vector3D point = Vector3D.add(ray.getOrigin(), Vector3D.mult(ray.getDirection(), t0));

        Vector3D normal = Vector3D.normalize(Vector3D.sub(point,this.position));

        return new Intersection(point, t0, t1, normal,this);
    }
}