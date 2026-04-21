import java.lang.reflect.Array;
import java.util.ArrayList;

public class Scene {
    ArrayList<Object3D> objects;
    Camera camera;
    Vector3D backgroundColor;

    public Scene(Camera camera, ArrayList<Object3D> obj, Vector3D bgColor){
        setCamera(camera);
        setObjects(obj);
        setBackgroundColor(bgColor);
    }

    public Intersection calculateNearIntersection(Ray ray){
        Intersection closest = null;

        for (Object3D obj : this.objects) {
            Intersection i = obj.intersect(ray);

            if (!i.exists()) continue;

            if (closest == null || i.getT0() < closest.getT0()) {
                closest = i;
            }
        }

        return closest;
    }
    //Getters and Setters
    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public void setObjects(ArrayList<Object3D> objects) {
        this.objects = objects;
    }

    public void setBackgroundColor(Vector3D bgColor){
        this.backgroundColor = bgColor;
    }

    public Camera getCamera() {
        return this.camera;
    }

    public Vector3D getBackgroundColor(){
        return this.backgroundColor;
    }
}
