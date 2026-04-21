public class Intersection {
    private Vector3D point;
    private double t0;
    private double t1;
    private boolean exists;

    // For a hit
    public Intersection(Vector3D point, double t0, double t1) {
        this.exists = true;
        this.point = point;
        this.t0 = t0;
        this.t1 = t1;
    }

    // For a miss
    public Intersection(boolean exists) {
        this.exists = false;
        this.point = null;
    }

    public boolean exists() { return exists; }
    public Vector3D getPoint() { return point; }
    public double getT0() { return t0; }
    public double getT1() { return t1; }
}