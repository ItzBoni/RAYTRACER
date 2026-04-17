import vector.Vector3D;

public class Intersection {
    private double x, y, z;
    private boolean exists;

    public Intersection(Vector3D origin1, Vector3D dir1, Vector3D origin2, Vector3D dir2) {
        calculateIntersection(origin1, dir1, origin2, dir2);
    }

    private void calculateIntersection(Vector3D o1, Vector3D d1, Vector3D o2, Vector3D d2) {
        Vector3D cross = Vector3D.cross(d1, d2);
        double denom = cross.lengthSquared();

        if (denom < 1e-10) {
            exists = false;
            return;
        }

        Vector3D delta = Vector3D.sub(o2, o1);
        double t = Vector3D.dot(Vector3D.cross(delta, d2), cross) / denom;
        double s = Vector3D.dot(Vector3D.cross(delta, d1), cross) / denom;

        Vector3D pointOnRay1 = Vector3D.add(o1, Vector3D.mult(d1, t));
        Vector3D pointOnRay2 = Vector3D.add(o2, Vector3D.mult(d2, s));

        Vector3D diff = Vector3D.sub(pointOnRay1, pointOnRay2);
        if (diff.lengthSquared() > 1e-10) {
            exists = false;
            return;
        }

        exists = true;
        this.x = pointOnRay1.getX();
        this.y = pointOnRay1.getY();
        this.z = pointOnRay1.getZ();
    }

    public boolean exists() { return exists; }
    public double getX() { return x; }
    public double getY() { return y; }
    public double getZ() { return z; }
}
