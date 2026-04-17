package vector;

public class Vector3D {
    private double x,y,z;
    private double magnitude;

    public Vector3D(double x, double y, double z){
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

    public static Vector3D sub(Vector3D a, Vector3D b) { return new Vector3D(a.x - b.x, a.y - b.y, a.z - b.z); }
    public static Vector3D add(Vector3D a, Vector3D b) { return new Vector3D(a.x + b.x, a.y + b.y, a.z + b.z); }
    public static Vector3D mult(Vector3D a, double s) { return new Vector3D(a.x * s, a.y * s, a.z * s); }
    public static double dot(Vector3D a, Vector3D b) { return a.x * b.x + a.y * b.y + a.z * b.z; }
    public static Vector3D cross(Vector3D a, Vector3D b) {
        return new Vector3D(a.y * b.z - a.z * b.y, a.z * b.x - a.x * b.z, a.x * b.y - a.y * b.x);
    }

    public double lengthSquared() { return x * x + y * y + z * z; }
}
