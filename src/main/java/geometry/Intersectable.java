package geometry;

import tools.Intersection;
import tools.Ray;

public interface Intersectable {
    public Intersection intersect(Ray ray);
}
