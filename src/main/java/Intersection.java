import vector.Vector3D;

public class Intersection {
    private Vector3D point;
    private double t;
    private double s;
    private boolean exists;

    public Intersection(Vector3D origin1, Vector3D dir1, Vector3D origin2, Vector3D dir2) {
        calculateIntersection(origin1, dir1, origin2, dir2);
    }

    private void calculateIntersection(Vector3D o1, Vector3D d1, Vector3D o2, Vector3D d2) {
        Vector3D cross = Vector3D.cross(d1, d2);
        double denom = cross.lengthSquared();

        if (denom < 1e-10) {
            exists = false;
            point = null;
            return;
        }

        Vector3D delta = Vector3D.sub(o2, o1);
        this.t = Vector3D.dot(Vector3D.cross(delta, d2), cross) / denom;
        this.s = Vector3D.dot(Vector3D.cross(delta, d1), cross) / denom;

        Vector3D pointOnRay1 = Vector3D.add(o1, Vector3D.mult(d1, t));
        Vector3D pointOnRay2 = Vector3D.add(o2, Vector3D.mult(d2, s));

        Vector3D diff = Vector3D.sub(pointOnRay1, pointOnRay2);
        if (diff.lengthSquared() > 1e-10) {
            exists = false;
            point = null;
            return;
        }

        exists = true;
        this.point = pointOnRay1;
    }

    public boolean exists() { return exists; }
    public Vector3D getPoint() { return point; }
    public double getT() { return t; }
    public double getS() { return s; }
}