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
        Vector3D direction = Vector3D.normalize(Vector3D.sub(intersection.getPoint(), this.getPosition()));

        return Math.max(Vector3D.dot(intersection.getNormal(), direction), 0);
    }

    public void setPosition(Vector3D position){
        this.position = position;
    }

    public Vector3D getPosition(){
        return this.position;
    }
}
