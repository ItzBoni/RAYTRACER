package geometry;

import tools.*;

public abstract class Object3D implements Intersectable{
    protected Vector3D objectColor;
    protected Vector3D normal;

    public Vector3D getObjectColor(Vector3D lightColor, Vector3D lightDirection, double lightIntensity){
        double lambertianFactor = Math.max(Vector3D.dot(this.normal, lightDirection), 0.0);
        Vector3D computedColor = Vector3D.cross(lightColor, this.objectColor);
        double lambertianLight = lightIntensity * lambertianFactor;

        return Vector3D.mult(computedColor, lambertianLight);
    }

    public void setObjectColor(Vector3D objectColor){ this.objectColor = objectColor;}
}
