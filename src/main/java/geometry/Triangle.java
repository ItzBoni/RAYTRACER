package geometry;

import tools.*;

public class Triangle extends Object3D implements Intersectable{
    private Vector3D[] points;
    private Vector3D[] vertexNormals;
    private Vector3D normal;
    private double u, v, w;

    public Triangle(Vector3D v0, Vector3D v1, Vector3D v2, Vector3D[] vertexNormals, Vector3D color){
        super(color);
        this.points = new Vector3D[]{v0, v1, v2};
        setVertexNormals(vertexNormals);
        calculateFaceNormal();
    }

    @Override
    public Intersection intersect(Ray ray) {
        Vector3D direction = ray.getDirection();
        Vector3D origin = ray.getOrigin();

        Vector3D e1 = Vector3D.sub(this.points[1], this.points[0]);
        Vector3D e2 = Vector3D.sub(this.points[2], this.points[0]);
        Vector3D travel = Vector3D.sub(origin, this.points[0]);

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
        return new Intersection(point, smallT, smallT, calculateFaceNormal(),this);
    }

    //Calculate normal at intersection for Phong shading.
    private void calculateVertexNormals(){
        if (getVertexNormals().length < 3){
            //Compute the vertex normals manually
        } else {
            //Grab vertex normals to return into intersection
        }
    }

    private Vector3D calculateFaceNormal(){
        //Temporary since I'll add smooth-shading normals later.
        Vector3D v = Vector3D.sub(this.points[1], this.points[0]);
        Vector3D w = Vector3D.sub(this.points[0], this.points[2]);

        return Vector3D.normalize(Vector3D.cross(v, w));
    }

    public Vector3D[] getPoints() {
        return points;
    }

    public Vector3D getPoint(int index) {
        return points[index];
    }

    public void setPoint(int index, Vector3D point) {
        this.points[index] = point;
    }

    public Vector3D[] getVertexNormals() {
        return vertexNormals;
    }

    public void setVertexNormals(Vector3D[] normal) {
        this.vertexNormals = normal;
    }

    public Vector3D getNormal() {
        return this.normal;
    }

    public void setNormal(Vector3D normal){
        this.normal = normal;
    }
}
