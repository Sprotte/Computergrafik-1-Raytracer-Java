package cgm.Aufgaben;

import java.util.ArrayList;

import cgm.Gui;
import cgm.World;
import cgm.Camera.PerspectiveCamera;
import cgm.Geometry.AxisAlignedBox;
import cgm.Geometry.Geometry;
import cgm.Geometry.MyColor;
import cgm.Geometry.Node;
import cgm.Geometry.Plane;
import cgm.Geometry.ShapeFromFile;
import cgm.Geometry.Sphere;
import cgm.Light.DirectionalLight;
import cgm.Light.Light;
import cgm.Light.PointLight;
import cgm.Light.SpotLight;
import cgm.Material.LambertMaterial;
import cgm.Material.Material;
import cgm.Material.NightDayMaterial;
import cgm.Material.OrenNayarMaterial;
import cgm.Material.ReflectiveMaterial;
import cgm.Material.SingleColorMaterial;
import cgm.Material.TransparentMaterial;
import cgm.Math.Point3;
import cgm.Math.Transform;
import cgm.Math.Vector3;
import cgm.Texture.ImageTexture;
import cgm.Texture.InterpolatedImageTexture;
import cgm.Texture.SingleColorTexture;


@SuppressWarnings("unused")
public class demo {

	public static void main(String[] args){
		
		
		/*
		PerspectiveCamera cam = new PerspectiveCamera(new Point3(0, 0, -50), new Vector3(0, 0, 1), new Vector3(0, 1, 0), Math.PI / 8);

        final Material worldTexture = new SingleColorMaterial(new ImageTexture(new MyColor(1, 1, 1), "earth_day.jpg"));
        final Material worldTextureSmall = new SingleColorMaterial(new InterpolatedImageTexture(new MyColor(1, 1, 1), "earth_day_small.jpg"));
        final Material worldTextureInter = new SingleColorMaterial(new InterpolatedImageTexture(new MyColor(1, 1, 1), "earth_day.jpg"));
        
        final Material worldTextureNight = new SingleColorMaterial(new ImageTexture(new MyColor(1, 1, 1), "earth_night.jpg"));
        final Material worldTextureSmallNight = new SingleColorMaterial(new InterpolatedImageTexture(new MyColor(1, 1, 1), "earth_night_small.jpg"));
        final Material worldTextureInterNight = new SingleColorMaterial(new InterpolatedImageTexture(new MyColor(1, 1, 1), "earth_night.jpg"));
        
        Sphere sphereNormal = new Sphere(worldTexture);
        Sphere sphereSmall = new Sphere(worldTextureSmall);
        Sphere sphereInter = new Sphere(worldTextureInter);
        
        Sphere sphereNormalNight = new Sphere(worldTextureNight);
        Sphere sphereSmallNight = new Sphere(worldTextureSmallNight);
        Sphere sphereInterNight = new Sphere(worldTextureInterNight);
        
        ArrayList<Geometry> geoListNormal = new ArrayList<Geometry>();
        ArrayList<Geometry> geoListSmall = new ArrayList<Geometry>();
        ArrayList<Geometry> geoListInter = new ArrayList<Geometry>();
        
        ArrayList<Geometry> geoListNormalNight = new ArrayList<Geometry>();
        ArrayList<Geometry> geoListSmallNight = new ArrayList<Geometry>();
        ArrayList<Geometry> geoListInterNight = new ArrayList<Geometry>();
        
        ArrayList<Geometry> geoNodeList = new ArrayList<Geometry>();

        geoListNormal.add(sphereNormal);
        geoListSmall.add(sphereSmall);
        geoListInter.add(sphereInter);
        
        geoListNormalNight.add(sphereNormalNight);
        geoListSmallNight.add(sphereSmallNight);
        geoListInterNight.add(sphereInterNight);
        
        Point3 s = new Point3(4, 4, 4);
        Point3 sphereDayNormalPoint = new Point3(2.1, 1.3, 0);
        Point3 sphereDaySmallPoint = new Point3(0, 1.3, 0);
        Point3 sphereDayInterPoint = new Point3(-2.1,1.3, 0);
        
        Point3 sphereDayNormalPointNight = new Point3(2.1, -0.8, 0);
        Point3 sphereDaySmallPointNight = new Point3(0, -0.8, 0);
        Point3 sphereDayInterPointNight = new Point3(-2.1, -0.8, 0);
        
        double ry = 5;
        Transform transform = new Transform();
        Node sphereDayNormalSphereNode = new Node(transform.scale(s).translate(sphereDayNormalPoint).rotateY(ry), geoListNormal);
        Node sphereDaySmallSphereNode = new Node(transform.scale(s).translate(sphereDaySmallPoint).rotateY(ry), geoListSmall);
        Node sphereDayInterSphereNode = new Node(transform.scale(s).translate(sphereDayInterPoint).rotateY(ry), geoListInter);

        Node sphereDayNormalNightSphereNode = new Node(transform.scale(s).translate(sphereDayNormalPointNight).rotateY(ry), geoListNormalNight);
        Node sphereDaySmallNightSphereNode = new Node(transform.scale(s).translate(sphereDaySmallPointNight).rotateY(ry), geoListSmallNight);
        Node sphereDayInterNightSphereNode = new Node(transform.scale(s).translate(sphereDayInterPointNight).rotateY(ry), geoListInterNight);
        
        ArrayList<Light> lights = new ArrayList<Light>();
        Light pointLight = new PointLight(new MyColor(1, 1, 1), new Point3(5, 5, 5), true);
        Light directionalLight = new DirectionalLight(new MyColor(0.4, 0.4, 0.4), new Vector3(1, -1, 0).normalized(), true);
        lights.add(pointLight);
        lights.add(directionalLight);

        geoNodeList.add(sphereDayNormalSphereNode);
        geoNodeList.add(sphereDaySmallSphereNode);
        geoNodeList.add(sphereDayInterSphereNode);
        
        geoNodeList.add(sphereDayNormalNightSphereNode);
        geoNodeList.add(sphereDaySmallNightSphereNode);
        geoNodeList.add(sphereDayInterNightSphereNode);
        
        MyColor ambientLight = new MyColor(0.25, 0.25, 0.25);
        World world = new World(geoNodeList, lights, ambientLight, 1.33);
        
        
        
        
        
		new Gui(world, cam);
		*/
		/*
		PerspectiveCamera camera = new PerspectiveCamera(new Point3(5, 5, 5), new Vector3(-1, -1, -1), new Vector3(0, 1, 0), Math.PI / 4);

        final Material worldTexture = new NightDayMaterial(new ImageTexture(new MyColor(1, 1, 1), "earth_day.jpg"), new ImageTexture(new MyColor(1, 1, 1), "earth_night.jpg"));
        Sphere sphere = new Sphere(worldTexture);

        ArrayList<Geometry> geoList = new ArrayList<Geometry>();
        ArrayList<Geometry> geoNodeList = new ArrayList<Geometry>();

        
        geoList.add(sphere);

       

        Point3 s = new Point3(1, 1, 1);
        Point3 p = new Point3(0, 0, 0);
        double ry = 5;
        Transform transform = new Transform();
        Node sphereNode = new Node(transform.scale(s).translate(p).rotateY(ry), geoList);

        ArrayList<Light> lights = new ArrayList<Light>();
        Light pointLight = new PointLight(new MyColor(1, 1, 1), new Point3(2, 2, 2), true);
        Light spotLight = new SpotLight(new MyColor(1, 1, 1), new Point3(2, 2, 2), new Vector3(0, -1, 0), 10,	true);
        lights.add(spotLight);

        geoNodeList.add(sphereNode);

        MyColor ambientLight = new MyColor(0.25, 0.25, 0.25);
        World world = new World(geoNodeList, lights, ambientLight, 1.33);
        new Gui(world, camera);
		*/
		
		
		PerspectiveCamera cam = new PerspectiveCamera(new Point3(0, 0, -1000), new Vector3(0, 0, 1), new Vector3(0, 1, 0), Math.PI / 2);
		
		MyColor specColor = new MyColor(1, 1, 1);
        int exp = 10;
        MyColor reflection = new MyColor(1, 0.5, 0.5);
        MyColor reflection2 = new MyColor(0.5, 0, 1);
        
		final Material sunTexture = new TransparentMaterial(1.33);
		final Material merkurTexture = new TransparentMaterial(6);
		final Material venusTexture = new SingleColorMaterial(new ImageTexture(new MyColor(1, 1, 1), "venus.jpg"));
		final Material erdeTexture = new SingleColorMaterial(new ImageTexture(new MyColor(1, 1, 1), "earth_night.jpg"));
		final Material mondTexture = new SingleColorMaterial(new ImageTexture(new MyColor(1, 1, 1), "mond.jpg"));
		final Material marsTexture = new ReflectiveMaterial(new MyColor(1, 0, 0), specColor, exp, reflection);
		final Material jupiterTexture = new SingleColorMaterial(new ImageTexture(new MyColor(1, 1, 1), "jupiter.jpg"));
		final Material saturnTexture = new TransparentMaterial(6);
		final Material uranusTexture = new SingleColorMaterial(new ImageTexture(new MyColor(1, 1, 1), "uranus.jpg"));
		final Material neptunTexture = new ReflectiveMaterial(new MyColor(0.5, 0.5, 1), specColor, exp, reflection);
		final Material plutoTexture = new ReflectiveMaterial(new MyColor(1, 0, 1), specColor, exp, reflection2);
		final Material spaceTexture = new SingleColorMaterial(new ImageTexture(new MyColor(1, 1, 1), "space2.jpg"));
		final Material ref1 = new ReflectiveMaterial(new MyColor(0,0,1), specColor, exp, reflection);		
		final Material ref2 = new ReflectiveMaterial(new MyColor(0,1,0), specColor, exp, reflection);		
		final Material ref3 = new ReflectiveMaterial(new MyColor(1,0.58,0), specColor, exp, reflection);		
		final Material ref4 = new ReflectiveMaterial(new MyColor(0.58,1,1), specColor, exp, reflection);		
		final Material ref5 = new ReflectiveMaterial(new MyColor(1,1,0.72), specColor, exp, reflection);		
		final Material ref6 = new ReflectiveMaterial(new MyColor(1,0.25,1), specColor, exp, reflection);		
		final Material ref7 = new ReflectiveMaterial(new MyColor(0,0.5,1), specColor, exp, reflection);		
		final Material ref8 = new ReflectiveMaterial(new MyColor(0.8,0,1), specColor, exp, reflection);		
		final Material ref9 = new ReflectiveMaterial(new MyColor(0.2,0.8,1), specColor, exp, reflection);		
			
		Sphere sphereSun = new Sphere(sunTexture);
		AxisAlignedBox sphereMerkur = new AxisAlignedBox(merkurTexture);
		Sphere sphereVenus = new Sphere(venusTexture);
		Sphere sphereErde = new Sphere(erdeTexture);
		Sphere sphereMond = new Sphere(mondTexture);
		Sphere sphereMars = new Sphere(marsTexture);
		Sphere sphereJupiter = new Sphere(jupiterTexture);
		AxisAlignedBox sphereSaturn = new AxisAlignedBox(saturnTexture);
		Sphere sphereUranus = new Sphere(uranusTexture);
		Sphere sphereNeptun = new Sphere(neptunTexture);
		Sphere spherePluto = new Sphere(plutoTexture);
		AxisAlignedBox planeSpace = new AxisAlignedBox(spaceTexture);
				
		Sphere sp1 = new Sphere(ref1);
		Sphere sp2 = new Sphere(ref2);
		Sphere sp3 = new Sphere(ref3);
		Sphere sp4 = new Sphere(ref4);
		Sphere sp5 = new Sphere(ref5);
		Sphere sp6 = new Sphere(ref6);
		Sphere sp7 = new Sphere(ref7);
		Sphere sp8 = new Sphere(ref8);
		Sphere sp9 = new Sphere(ref9);
		
		
		
        ArrayList<Geometry> geoListSun = new ArrayList<Geometry>();
        ArrayList<Geometry> geoListMerkur = new ArrayList<Geometry>();
        ArrayList<Geometry> geoListVenus = new ArrayList<Geometry>();
        ArrayList<Geometry> geoListErde = new ArrayList<Geometry>();
        ArrayList<Geometry> geoListMond = new ArrayList<Geometry>();
        ArrayList<Geometry> geoListMars = new ArrayList<Geometry>();
        ArrayList<Geometry> geoListJupiter = new ArrayList<Geometry>();
        ArrayList<Geometry> geoListSaturn = new ArrayList<Geometry>();
        ArrayList<Geometry> geoListUranus = new ArrayList<Geometry>();
        ArrayList<Geometry> geoListNeptun = new ArrayList<Geometry>();
        ArrayList<Geometry> geoListPluto = new ArrayList<Geometry>();
        ArrayList<Geometry> geoListSpace = new ArrayList<Geometry>();
        ArrayList<Geometry> geoList1 = new ArrayList<Geometry>();
        ArrayList<Geometry> geoList2 = new ArrayList<Geometry>();
        ArrayList<Geometry> geoList3 = new ArrayList<Geometry>();
        ArrayList<Geometry> geoList4 = new ArrayList<Geometry>();
        ArrayList<Geometry> geoList5 = new ArrayList<Geometry>();
        ArrayList<Geometry> geoList6 = new ArrayList<Geometry>();
        ArrayList<Geometry> geoList7 = new ArrayList<Geometry>();
        ArrayList<Geometry> geoList8 = new ArrayList<Geometry>();
        ArrayList<Geometry> geoList9 = new ArrayList<Geometry>();
        ArrayList<Geometry> geoNodeList = new ArrayList<Geometry>();
        
        
        geoListSun.add(sphereSun);
        geoListMerkur.add(sphereMerkur);
        geoListVenus.add(sphereVenus);
        geoListErde.add(sphereErde);
        geoListMond.add(sphereMond);
        geoListMars.add(sphereMars);
        geoListJupiter.add(sphereJupiter);
        geoListSaturn.add(sphereSaturn);
        geoListUranus.add(sphereUranus);
        geoListNeptun.add(sphereNeptun);
        geoListPluto.add(spherePluto);
        geoListSpace.add(planeSpace);
        geoList1.add(sp1);
        geoList2.add(sp2);
        geoList3.add(sp3);
        geoList4.add(sp4);
        geoList5.add(sp5);
        geoList6.add(sp6);
        geoList7.add(sp7);
        geoList8.add(sp8);
        geoList9.add(sp9);
        
        
        
        Point3 sunScale = new Point3(500,500, 500);
        Point3 merkurScale = new Point3(500, 250, 20);
        Point3 venusScale = new Point3(100, 100, 100);
        Point3 erdeScale = new Point3(100, 100, 100);
        Point3 mondScale = new Point3(200, 200, 200);
        Point3 marsScale = new Point3(250, 250, 250);
        Point3 jupiterscale = new Point3(300,300, 300);
        Point3 saturnScale = new Point3(250, 250, 250);
        Point3 uranusScale = new Point3(50, 50, 50);
        Point3 neptunScale = new Point3(600, 600, 600);
        Point3 plutoScale = new Point3(500, 300, 20);
        Point3 spaceScale = new Point3(2000, 2000, 2000);
        
        Point3 scale2 = new Point3(80, 80, 80);
        
        Point3 sphereSunPoint = new Point3(1.7, 0, 0);
        Point3 sphereMerkurPoint = new Point3(0, -2, 3);
        Point3 sphereVenusPoint = new Point3(0, 0.7, 0);
        Point3 sphereErdePoint = new Point3(4, -4, -2.5);
        Point3 sphereMondPoint = new Point3(-2, 2, 2);
        Point3 sphereMarsPoint = new Point3(0, 3, 3);
        Point3 sphereJupiterPoint = new Point3(2, 2, 0);
        Point3 sphereSaturnPoint = new Point3(0, 0.5, 0);
        Point3 sphereUranusPoint = new Point3(-3, 3, 1);
        Point3 sphereNeptunPoint = new Point3(-1.2, -1.8, 0);
        Point3 spherePlutoPoint = new Point3(0, 3, 0);
        Point3 planeSpacePoint = new Point3(0, 0, 0);
        
        
        
        
        
        Point3 p1 = new Point3(-9, 5, -3);
        Point3 p2 = new Point3(-7, 3, 2);
        Point3 p3 = new Point3(-8, -1, 1);
        Point3 p4 = new Point3(-5, -4, -3);
        Point3 p5 = new Point3(-9, 2, 0);
        Point3 p6 = new Point3(-6, -3, 1);
        Point3 p7 = new Point3(-8, -5, -2);
        Point3 p8 = new Point3(-6, 1, 2);
        Point3 p9 = new Point3(-7, 0,3);
        
        double ry = 5;
        double ryy = 90;
        Transform transform = new Transform();
        Node sunNode = new Node(transform.scale(sunScale).translate(sphereSunPoint).rotateY(ry), geoListSun);
        Node merkurNode = new Node(transform.scale(merkurScale).translate(sphereMerkurPoint).rotateY(ry), geoListMerkur);
        Node venusNode = new Node(transform.scale(venusScale).translate(sphereVenusPoint).rotateY(ry), geoListVenus);
        Node erdeNode = new Node(transform.scale(erdeScale).translate(sphereErdePoint).rotateY(ry), geoListErde);
        Node mondNode = new Node(transform.scale(mondScale).translate(sphereMondPoint).rotateY(ry), geoListMond);
        Node marsNode = new Node(transform.scale(marsScale).translate(sphereMarsPoint).rotateY(ry), geoListMars);
        Node jupiterNode = new Node(transform.scale(jupiterscale).translate(sphereJupiterPoint).rotateY(ry), geoListJupiter);
        Node saturnNode = new Node(transform.scale(saturnScale).translate(sphereSaturnPoint).rotateY(ry), geoListSaturn);
        Node uranusNode = new Node(transform.scale(uranusScale).translate(sphereUranusPoint).rotateY(ry), geoListUranus);
        Node neptunNode = new Node(transform.scale(neptunScale).translate(sphereNeptunPoint).rotateY(ry), geoListNeptun);
        Node plutoNode = new Node(transform.scale(plutoScale).translate(spherePlutoPoint).rotateY(ry), geoListPluto);
        Node spaceNode = new Node(transform.scale(spaceScale).translate(planeSpacePoint), geoListSpace);
        
        Node s1Node = new Node(transform.scale(scale2).translate(p1), geoList1);
        Node s2Node = new Node(transform.scale(scale2).translate(p2), geoList2);
        Node s3Node = new Node(transform.scale(scale2).translate(p3), geoList3);
        Node s4Node = new Node(transform.scale(scale2).translate(p4), geoList4);
        Node s5Node = new Node(transform.scale(scale2).translate(p5), geoList5);
        Node s6Node = new Node(transform.scale(scale2).translate(p6), geoList6);
        Node s7Node = new Node(transform.scale(scale2).translate(p7), geoList7);
        Node s8Node = new Node(transform.scale(scale2).translate(p8), geoList8);
        Node s9Node = new Node(transform.scale(scale2).translate(p9), geoList9);
        
        //ShapeFromFile test = new ShapeFromFile("D:/workspace/Raytracer/Models/teddy.obj", new LambertMaterial(new MyColor(1, 1, 1)));
		
		//Node testnode =  test.OBJLoader();
		
        
        
        ArrayList<Light> lights = new ArrayList<Light>();
        Light pointLight = new PointLight(new MyColor(1, 1, 1), new Point3(5, 5, 5), true);
        Light directionalLight = new DirectionalLight(new MyColor(0.4, 0.4, 0.4), new Vector3(1, -1, 0).normalized(), true);
        lights.add(pointLight);
        lights.add(directionalLight);

        geoNodeList.add(sunNode);
        geoNodeList.add(merkurNode);
        geoNodeList.add(venusNode);
        geoNodeList.add(erdeNode);
        geoNodeList.add(mondNode);
        geoNodeList.add(marsNode);
        geoNodeList.add(jupiterNode);
        geoNodeList.add(saturnNode);
        geoNodeList.add(uranusNode);
        geoNodeList.add(neptunNode);
        geoNodeList.add(plutoNode);
        geoNodeList.add(spaceNode);
        geoNodeList.add(s1Node);
        geoNodeList.add(s2Node);
        geoNodeList.add(s3Node);
        geoNodeList.add(s4Node);
        geoNodeList.add(s5Node);
        geoNodeList.add(s6Node);
        geoNodeList.add(s7Node);
        geoNodeList.add(s8Node);
        geoNodeList.add(s9Node);
        //geoNodeList.add(testnode);
        
        
        
        MyColor ambientLight = new MyColor(0.25, 0.25, 0.25);
        World world = new World(geoNodeList, lights, ambientLight, 1.33);
        new Gui(world, cam);
        

}

}
