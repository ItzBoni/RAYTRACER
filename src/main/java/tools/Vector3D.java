package tools;

import java.awt.*;

import static java.lang.Math.clamp;

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

    public static int convertToRGB(Vector3D rgbVector){
        int r = (int) clamp(rgbVector.getX() * 255, 0, 255);
        int g = (int) clamp(rgbVector.getY() * 255, 0, 255);
        int b = (int) clamp(rgbVector.getZ() * 255, 0, 255);
        return new Color(r, g, b).getRGB();
    }

    public static Vector3D convertToVector(int rgb){
        Color color = new Color(rgb);
        double r = color.getRed() / 255.0;
        double g = color.getGreen() / 255.0;
        double b = color.getBlue() / 255.0;

        return new Vector3D(r, g, b);
    }

    //Getters and setters
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

    //Definition of essential vector operations (used later)
    public static Vector3D sub(Vector3D a, Vector3D b) { return new Vector3D(a.x - b.x, a.y - b.y, a.z - b.z); }
    public static Vector3D add(Vector3D a, Vector3D b) { return new Vector3D(a.x + b.x, a.y + b.y, a.z + b.z); }
    public static Vector3D mult(Vector3D a, double s) { return new Vector3D(a.x * s, a.y * s, a.z * s); }
    public static double dot(Vector3D a, Vector3D b) { return a.x * b.x + a.y * b.y + a.z * b.z; }
    public static Vector3D cross(Vector3D a, Vector3D b) {
        return new Vector3D(a.y * b.z - a.z * b.y, a.z * b.x - a.x * b.z, a.x * b.y - a.y * b.x);
    }
    public static double lengthSquared(Vector3D v) { return Math.pow(v.getX(),2) + Math.pow(v.getY(),2) + Math.pow(v.getZ(), 2); }

    public static Vector3D hadamard(Vector3D a, Vector3D b ) {
        return new Vector3D(a.x * b.x, a.y * b.y, a.z * b.z);
    }

    public static Vector3D normalize(Vector3D a){
        double x_n = a.getX() / Math.sqrt(lengthSquared(a));
        double y_n = a.getY() / Math.sqrt(lengthSquared(a));
        double z_n = a.getZ() / Math.sqrt(lengthSquared(a));

        return new Vector3D(x_n, y_n, z_n);
    }
}
