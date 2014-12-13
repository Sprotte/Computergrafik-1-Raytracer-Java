package cgm.Material;

import cgm.Constants;
import cgm.Hit;
import cgm.Ray;
import cgm.Tracer;
import cgm.World;
import cgm.Geometry.MyColor;
import cgm.Math.Normal3;
import cgm.Math.Vector3;

public class TransparentMaterial extends Material {

	private double indexOfRefraction;

	public TransparentMaterial(double indexOfRefraction) {

		this.indexOfRefraction = indexOfRefraction;
	}

	@Override
	public MyColor colorFor(final Hit hit, final World world,
			final Tracer tracer) {

		

		Normal3 normal = hit.normal;
		

		final Vector3 e = hit.ray.direction.mul(-1).reflectedOn(normal);

		

		double eta;
		if (e.dot(normal) < 0) {
			eta = indexOfRefraction / world.refractionIndex;
			normal = normal.mul(-1);
		} else {
			eta = world.refractionIndex / indexOfRefraction;
		}
		
		final double cosTheta = normal.dot(e);

		final double h = 1 - (eta * eta) * (1 - cosTheta * cosTheta);

		if (h < 0) {
			return tracer.colorFor(new Ray(hit.ray.at(hit.t - Constants.EPSILON), e));
		} else {
			
			final double costhetat = Math.sqrt(h);
			final Vector3 t = hit.ray.direction.mul(eta).sub(normal.mul(costhetat - eta * cosTheta));

			final double r0 = Math.pow((world.refractionIndex - indexOfRefraction)/(world.refractionIndex + indexOfRefraction), 2);
			final double rr = r0 + (1 - r0) * Math.pow(1 - cosTheta, 5);
			final double tt = 1 - rr;

			return tracer.colorFor(new Ray(hit.ray.at(hit.t - Constants.EPSILON), e)).mul(rr)
					.add(tracer.colorFor(new Ray(hit.ray.at(hit.t + Constants.EPSILON), t)).mul(tt));
		}

		

	}

	@Override
	public CelShadingMaterial getCelShadingMaterial() {

		return new CelShadingMaterial(new MyColor(1, 1, 1));
	}

	@Override
	public SingleColorMaterial getSingelColorMaterial() {

		return new SingleColorMaterial(new MyColor(1, 1, 1));
	}

}
