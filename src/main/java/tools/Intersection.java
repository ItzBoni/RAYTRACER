package tools;

import geometry.Object3D;

public class Intersection {
    private Vector3D point;
    private double t0;
    private double t1;
    private boolean exists;
    private Object3D object;

    // For a hit
    // For a hit
    public Intersection(Vector3D point, double t0, double t1, Object3D object) {
        this.exists = true;
        this.point = point;
        this.t0 = t0;
        this.t1 = t1;
        this.object = object;
    }

    public Object3D getObject() { return object; }

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