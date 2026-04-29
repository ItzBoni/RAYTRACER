import tools.Ray;
import tools.Vector3D;

public class Camera {
    private Vector3D position;
    private Vector3D target;
    private static final Vector3D worldUp = new Vector3D(0,1,0);

    private float fov;
    private int imageHeight;
    private int imageWidth;
    private double nearPlane;
    private double farPlane;

    Camera(Vector3D origin, Vector3D target, float fov, int height, int width, double nP, double fP){
        setPosition(origin);
        setTarget(target);
        setFov(fov);
        setImageHeight(height);
        setImageWidth(width);
        setNearPlane(nP);
        setFarPlane(fP);
    }

    public Ray sendRay(int x, int y) {
        double ndcX = (x + 0.5) / imageWidth;
        double ndcY = (y + 0.5) / imageHeight;

        double screenX = 2 * ndcX - 1;
        double screenY = 1 - 2 * ndcY;

        double aspectRatio = (double) imageWidth / imageHeight;
        double fovScale = Math.tan(Math.toRadians(fov / 2));

        double cameraX = screenX * aspectRatio * fovScale;
        double cameraY = screenY * fovScale;

        Vector3D forward = Vector3D.normalize(Vector3D.sub(target, position));
        Vector3D right   = Vector3D.normalize(Vector3D.cross(forward, worldUp));
        Vector3D up      = Vector3D.cross(right, forward);

        Vector3D direction = Vector3D.normalize(
                Vector3D.add(
                        forward,
                        Vector3D.add(
                                Vector3D.mult(right, cameraX),
                                Vector3D.mult(up, cameraY)
                        )
                )
        );

        return new Ray(position, direction);
    }

    //getters and setters
    public void setPosition(Vector3D position){
        this.position = position;
    }

    public void setImageHeight(int height){
        this.imageHeight = height;
    }

    public void setImageWidth(int width){
        this.imageWidth = width;
    }

    public void setTarget(Vector3D target){
        this.target = target;
    }

    public void setFov(float fov){
        this.fov = fov;
    }

    public int getImageHeight() {
        return imageHeight;
    }

    public int getImageWidth(){
        return imageWidth;
    }

    public double getNearPlane() {
        return nearPlane;
    }

    public void setNearPlane(double nearPlane) {
        this.nearPlane = nearPlane;
    }

    public double getFarPlane() {
        return farPlane;
    }

    public void setFarPlane(double farPlane) {
        this.farPlane = farPlane;
    }
}
