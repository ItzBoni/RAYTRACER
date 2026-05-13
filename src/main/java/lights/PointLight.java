package lights;

import tools.*;

public class PointLight extends Light{
    private Vector3D position;

    public PointLight(Vector3D position, Vector3D color, double intensity){
        super(color, intensity);
        setPosition(position);
    }

    @Override
    public double calculateNDotL(Intersection intersection){
        Vector3D direction = Vector3D.normalize(Vector3D.sub(this.getPosition(), intersection.getPoint()));

        return Math.max(Vector3D.dot(intersection.getNormal(), direction), 0);
    }

    @Override
    public double getFalloffIntensity(Vector3D point){
        double distance = Vector3D.sub(this.getPosition(), point).getMagnitude();

        return this.getIntensity() / (distance * distance);
    }

    @Override
    public Vector3D getShadowDirection(Vector3D point) {
        return Vector3D.normalize(Vector3D.sub(this.position, point));
    }

    @Override
    public double getShadowMaxDistance(Vector3D point) {
        return Vector3D.sub(this.position, point).getMagnitude();
    }

    public void setPosition(Vector3D position){
        this.position = position;
    }

    public Vector3D getPosition(){
        return this.position;
    }
}
