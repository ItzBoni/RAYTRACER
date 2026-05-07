package geometry;

import tools.*;

public abstract class Object3D implements Intersectable{
    protected Vector3D objectColor;
    protected Vector3D normal;

    public Object3D(Vector3D objectColor){
        setObjectColor(objectColor);
    }

    public Vector3D getObjectColor(){
        return this.objectColor;
    }

    public void setObjectColor(Vector3D objectColor){ this.objectColor = objectColor;}
}
