package geometry;

import tools.*;

import java.util.List;

public class AABB {
    private Vector3D min, max;

    public AABB(Vector3D min, Vector3D max) { }

    public static AABB of(List<Intersectable> objects) {
        Vector3D min = new Vector3D(Double.MAX_VALUE,  Double.MAX_VALUE,  Double.MAX_VALUE);
        Vector3D max = new Vector3D(-Double.MAX_VALUE, -Double.MAX_VALUE, -Double.MAX_VALUE);

        for (Intersectable obj : objects) {
            AABB b = obj.getBounds();
            min = Vector3D.min(min, b.min);  // component-wise
            max = Vector3D.max(max, b.max);
        }

        return new AABB(min, max);
    } // compute tight box

    public int longestAxis() {

    } // returns 0=x, 1=y, 2=z

    public boolean intersects(Ray ray) {

    } // slab test
}
