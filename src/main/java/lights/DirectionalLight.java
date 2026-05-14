package lights;

import tools.*;

public class DirectionalLight extends Light{
    private Vector3D direction;

    public DirectionalLight(Vector3D direction, Vector3D color, double intensity){
        super(color, intensity);
        setDirection(direction);
    }

    @Override
    public double calculateNDotL(Intersection intersection){
        return Math.max(Vector3D.dot(intersection.getNormal(), Vector3D.mult(this.direction, -1.0)), 0.0);
    }

    @Override
    public double getFalloffIntensity(Vector3D point){
        return this.getIntensity();
    }

    @Override
    public Vector3D getShadowDirection(Vector3D point) {
        return Vector3D.mult(this.direction, -1.0);
    }

    @Override
    public double getShadowMaxDistance(Vector3D point) {
        return Double.MAX_VALUE;
    }

    public Vector3D getDirection(){
        return this.direction;
    }

    public void setDirection(Vector3D direction){
        this.direction = Vector3D.normalize(direction);
    }
}
