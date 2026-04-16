package vector;

public class Vector3D {
    private double x,y,z;
    private double magnitude;

    public Vector3D(float x, float y, float z){
        setX(x);
        setY(y);
        setZ(z);

        double magnitude = Math.sqrt((Math.pow(this.x,2))+(Math.pow(this.y,2))+(Math.pow(this.z,2)));

        setMagnitude(magnitude);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public double getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(double magnitude) {
        this.magnitude = magnitude;
    }
}
