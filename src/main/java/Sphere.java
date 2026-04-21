public class Sphere extends Object3D {
    private Vector3D position;
    private double radius;

    public Sphere(Vector3D position, double radius) {
        this.position = position;
        this.radius = radius;
    }

    public Intersection intersect(Ray ray) {
        // Step 1: L = C - O
        Vector3D L = Vector3D.sub(position, ray.getOrigin());

        // Step 2: tca = L · D
        double tca = Vector3D.dot(L, ray.getDirection());

        // Step 3: if tca < 0, sphere is behind the ray
        if (tca < 0) return new Intersection(false);

        // Step 4: d² = L·L - tca²
        double dSquared = Vector3D.dot(L, L) - tca * tca;

        // Step 5: if d² > r², ray misses the sphere
        if (dSquared > radius * radius) return new Intersection(false);

        // Step 6: thc = √(r² - d²)
        double thc = Math.sqrt(radius * radius - dSquared);

        // Step 7: t0 and t1
        double t0 = tca - thc;
        double t1 = tca + thc;

        // Compute the actual hit point using the closer t
        Vector3D point = Vector3D.add(ray.getOrigin(), Vector3D.mult(ray.getDirection(), t0));

        return new Intersection(point, t0, t1);
    }
}