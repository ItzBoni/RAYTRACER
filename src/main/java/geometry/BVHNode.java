package geometry;

import tools.*;

import java.util.List;

public class BVHNode implements Intersectable{
    private AABB boundingBox;
    private Intersectable left, right; //recursive approach

    public BVHNode(List<Intersectable> objects){
        // 1. compute centroid AABB to pick split axis
        // 2. nth_element / Collections.sort on centroid along axis
        // 3. split at median index
        // 4. recurse: left = new BVHNode(leftHalf), right = new BVHNode(rightHalf)
        // 5. this.bbox = AABB.of(objects)  ← tight geometry bbox, not centroid bbox
    }

    @Override
    public Intersection intersect(Ray ray){
        if (!this.boundingBox.intersects(ray)) return null;

        Intersection hitLeft = this.left.intersect(ray);
        Intersection hitRight = this.right.intersect(ray);
    }

    @Override
    public AABB getBounds(){
        return this.boundingBox;
    }
}
