package tools;

import lights.Light;

import java.util.ArrayList;

public class Shader {

    public static Vector3D calculateShading(Intersection intersection, ArrayList<Light> lights){
        Vector3D objectColor = intersection.getObject().getObjectColor();
        Vector3D normal = intersection.getNormal();
        Vector3D finalColor = new Vector3D(0,0,0);

        for (Light light : lights) {
            double nDotL = light.calculateNDotL(intersection);

            Vector3D computedColor = Vector3D.clampColor(Vector3D.hadamard(objectColor, light.getColor()));
            double computedLight = light.getFalloffIntensity(intersection.getPoint()) * nDotL;
            Vector3D temp = Vector3D.mult(computedColor, computedLight);
            finalColor = Vector3D.clampColor(Vector3D.add(finalColor, temp));
        }

        return finalColor;
    }
}
