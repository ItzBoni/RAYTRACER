package geometry;

import tools.*;

public class Triangle extends Object3D{
    private Vector3D pointA, pointB, pointC;
    private double u, v, w;

    public Triangle(Vector3D v0, Vector3D v1, Vector3D v2, Vector3D color){
        setPointA(v0);
        setPointB(v1);
        setPointC(v2);
        setObjectColor(color);
    }

    @Override
    public Intersection intersect(Ray ray) {
        Vector3D direction = ray.getDirection();
        Vector3D origin = ray.getOrigin();

        Vector3D e1 = Vector3D.sub(this.pointB, this.pointA);
        Vector3D e2 = Vector3D.sub(this.pointC, this.pointA);
        Vector3D travel = Vector3D.sub(origin, this.pointA);

        Vector3D p = Vector3D.cross(direction, e2);
        double detM = Vector3D.dot(p, e1);

        if (Math.abs(detM) < 1e-8) return new Intersection(false);

        Vector3D q = Vector3D.cross(travel, e1);
        double smallT = Vector3D.dot(q, e2) / detM;

        if (smallT < 0) return new Intersection(false);

        this.u = Vector3D.dot(p, travel) / detM;
        if (this.u < 0 || this.u > 1) return new Intersection(false);

        this.v = Vector3D.dot(q, direction) / detM;
        if (this.v < 0 || this.u + this.v > 1) return new Intersection(false);

        this.w = 1 - (this.u + this.v);

        Vector3D point = Vector3D.add(origin, Vector3D.mult(direction, smallT));
        return new Intersection(point, smallT, smallT, this);
    }

    public Vector3D getPointA() {
        return pointA;
    }

    public void setPointA(Vector3D pointA) {
        this.pointA = pointA;
    }

    public Vector3D getPointB() {
        return pointB;
    }

    public void setPointB(Vector3D pointB) {
        this.pointB = pointB;
    }

    public Vector3D getPointC() {
        return pointC;
    }

    public void setPointC(Vector3D pointC) {
        this.pointC = pointC;
    }
}
