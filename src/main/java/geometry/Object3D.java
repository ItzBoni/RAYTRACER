package geometry;

import tools.*;

public abstract class Object3D {
    protected Vector3D objectColor;

    public abstract Intersection intersect(Ray ray);
    public Vector3D getObjectColor(){
        return this.objectColor;
    }
    public void setObjectColor(Vector3D objectColor){ this.objectColor = objectColor;}
}
