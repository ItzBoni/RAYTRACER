package tools;

public class Light {
    private Vector3D position;
    private Vector3D target;
    private Vector3D color;
    private double intensity;

    public Light(Vector3D position, Vector3D target, Vector3D color, double intensity){
        setPosition(position);
        setTarget(target);
        setColor(color);
        setIntensity(intensity);
    }

    public void setIntensity(double intensity){
        this.intensity = intensity;
    }

    public double getIntensity(){
        return intensity;
    }

    public Vector3D getPosition() {
        return position;
    }

    public void setPosition(Vector3D position) {
        this.position = position;
    }

    public Vector3D getTarget() {
        return target;
    }

    public void setTarget(Vector3D target) {
        this.target = target;
    }

    public Vector3D getColor() {
        return color;
    }

    public void setColor(Vector3D color) {
        this.color = color;
    }
}
