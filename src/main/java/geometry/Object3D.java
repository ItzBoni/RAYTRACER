package geometry;

import tools.*;

public abstract class Object3D implements Intersectable{
    protected Vector3D objectColor;
    protected Vector3D normal;

    public Vector3D getObjectColor(Vector3D lightColor, Vector3D lightDirection, double lightIntensity){
        Vector3D N = Vector3D.normalize(this.normal);
        Vector3D L = Vector3D.normalize(lightDirection);

        double lambertianFactor = Math.max(Vector3D.dot(N, L), 0.0);
        Vector3D computedColor = Vector3D.hadamard(lightColor, this.objectColor);
        double lambertianLight = lightIntensity * lambertianFactor;

        return Vector3D.mult(computedColor, lambertianLight);
    }

    public void setObjectColor(Vector3D objectColor){ this.objectColor = objectColor;}
}
