package lights;

import tools.*;

public abstract class Light {
    private Vector3D color;
    private double intensity;

    public Light(Vector3D color, double intensity){
        setColor(color);
        setIntensity(intensity);
    }

    public abstract double calculateNDotL(Intersection intersection);

    public abstract double getFalloffIntensity(Vector3D point);

    public abstract Vector3D getShadowDirection(Vector3D point);

    public abstract double getShadowMaxDistance(Vector3D point);


    //Getters and setters
    public void setIntensity(double intensity){
        this.intensity = intensity;
    }

    public double getIntensity(){
        return intensity;
    }

    public Vector3D getColor() {
        return color;
    }

    public void setColor(Vector3D color) {
        this.color = color;
    }
}
