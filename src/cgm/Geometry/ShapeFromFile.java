package cgm.Geometry;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import cgm.Hit;
import cgm.Ray;
import cgm.Material.Material;
import cgm.Math.Normal3;
import cgm.Math.Point3;
import cgm.Math.Transform;

import cgm.Texture.TexCoord2;

public class ShapeFromFile extends Geometry{

    private final static String COMMENT = "#";
    private final static String VERTEX_TEXTURE = "vt";
    private final static String VERTEX_NORMAL = "vn";
    private final static String VERTEX = "v";
    private final static String FACE = "f";

    private final static String SPLITTER = " ";
    private final static String SEPERATOR = "/";

    private ArrayList<Geometry> triangles;
    private ArrayList<Point3> vertexPoints;
    private ArrayList<Normal3> vertexNormalPoints;
    private ArrayList<Point3> vertexTexturePoints;
    private ArrayList<Point3> vertexFaces;
    private ArrayList<Integer> vertexNormalFaces;
    private ArrayList<Point3> vertexTextureFaces;

    private int currentLineNumber;
    @SuppressWarnings("unused")
	private int currentFaceNumber;

    private boolean vtToProcess;
    private boolean vnToProcess;

    /**
     * the name of the file
     */
    public final String fileName;

    /**
     * creates a new ShapeFromFile object having a filname and a material as param
     * @param fileName of the OBJ File
     * @param material for the image
     */
    public ShapeFromFile(final String fileName, final Material material) {
        super(material);
        this.fileName = fileName;
        this.triangles = new ArrayList<Geometry>();
        this.vertexPoints = new ArrayList<Point3>();
        this.vertexNormalPoints = new ArrayList<Normal3>();
        this.vertexTexturePoints = new ArrayList<Point3>();
        this.vertexFaces = new ArrayList<Point3>();
        this.vertexNormalFaces = new ArrayList<Integer>();
        this.vertexTextureFaces = new ArrayList<Point3>();
        this.currentLineNumber = 0;
        this.currentFaceNumber = 0;
        this.vtToProcess = false;
        this.vnToProcess = false;
    }

    /**
     * this method parses the given OBJ File
     * @return a new node object with triangles
     */
        public Node OBJLoader() {
                    try {
                        File f = new File(fileName);
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
                        String line;

                        while ((line = bufferedReader.readLine()) != null) {

                            line = line.trim();

                            if (line.length() == 0) {
                                continue;
                            }

                            if (line.startsWith(ShapeFromFile.COMMENT)) {
                                continue;
                            } else if (line.startsWith(ShapeFromFile.VERTEX_TEXTURE)) {
                                processVertexTexture(line.substring(ShapeFromFile.VERTEX_TEXTURE.length()).trim());
                            } else if (line.startsWith(ShapeFromFile.VERTEX_NORMAL)) {
                                processVertexNormal(line.substring(ShapeFromFile.VERTEX_NORMAL.length()).trim());
                            } else if (line.startsWith(ShapeFromFile.VERTEX)) {
                                processVertex(line.substring(ShapeFromFile.VERTEX.length()).trim());
                            } else if (line.startsWith(ShapeFromFile.FACE)) {
                                processFace(line.substring(ShapeFromFile.FACE.length()).trim());
                            } else {
                                System.out.println("line " + currentLineNumber + " is unknown: " + line + "|");
                            }

                            this.currentLineNumber++;
                        }

                        for (int i = 0; i < vertexFaces.size(); i++) {

                            final Point3 currentVF = vertexFaces.get(i);

                            Point3 a = new Point3(0, 0, 0);
                            Point3 b = new Point3(0, 0, 0);
                            Point3 c = new Point3(0, 0, 0);

                            try {
                                a = vertexPoints.get((int) Math.abs(currentVF.x) - 1);
                                b = vertexPoints.get((int) Math.abs(currentVF.y) - 1);
                                c = vertexPoints.get((int) Math.abs(currentVF.z) - 1);
                            }
                            catch(ArrayIndexOutOfBoundsException msg) {
                                System.err.println("weird indices");
                            }

                            final Normal3 normal;
                            if (this.vnToProcess) {
                                normal = vertexNormalPoints.get(vertexNormalFaces.get(i) - 1).mul(-1);
                            } else {
                                normal = c.sub(a).x(b.sub(a)).normalized().asNormal();
                            }
                            triangles.add(new Triangle(material,a, b, c, normal, normal, normal,new TexCoord2(0, 0),new TexCoord2(0, 0),new TexCoord2(0, 0)));

                        }
                        bufferedReader.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return new Node(new Transform(), triangles);
                }

    private void processFace(final String line) {

        this.currentFaceNumber++;

        StringTokenizer st = new StringTokenizer(line, ShapeFromFile.SPLITTER);
        int count = st.countTokens();

        int v[] = new int[count];
        int vt[] = new int[count];
        int vn[] = new int[count];

        for (int i = 0; i < count; i++) {
            String[] splitted = st.nextToken().split(ShapeFromFile.SEPERATOR);
            v[i] = Integer.parseInt(splitted[0], 10);
            if (splitted.length > 1) {
                if (splitted[1].length() != 0) {
                    this.vtToProcess = true;
                    vt[i] = Integer.parseInt(splitted[1], 10);
                }
                if (splitted.length == 3) {
                    this.vnToProcess = true;
                    vn[i] = Integer.parseInt(splitted[2], 10);
                }
            }
        }

        vertexFaces.add(new Point3(v[0], v[1], v[2]));
        if (this.vtToProcess) {
            vertexTextureFaces.add(new Point3(vt[0], vt[1], vt[2]));
        }
        if (this.vnToProcess) {
            vertexNormalFaces.add(vn[0]);
        }
    }

    private void processVertex(final String line) {
        final float coords[] = new float[3];
        StringTokenizer st = new StringTokenizer(line, ShapeFromFile.SPLITTER);
        for (int i = 0; st.hasMoreTokens(); i++) {
            coords[i] = Float.parseFloat(st.nextToken());
        }
        vertexPoints.add(new Point3(coords[0], coords[1], coords[2]));
    }

    private void processVertexNormal(final String line) {
        final float coords[] = new float[3];
        StringTokenizer st = new StringTokenizer(line, ShapeFromFile.SPLITTER);
        for (int i = 0; st.hasMoreTokens(); i++) {
            coords[i] = Float.parseFloat(st.nextToken());
        }
        vertexNormalPoints.add(new Normal3(coords[0], coords[1], coords[2]));
    }

    private void processVertexTexture(final String line) {
        final float coords[] = new float[3];
        StringTokenizer st = new StringTokenizer(line, ShapeFromFile.SPLITTER);
        for (int i = 0; st.hasMoreTokens(); i++) {
            coords[i] = Float.parseFloat(st.nextToken());
        }
        vertexTexturePoints.add(new Point3(coords[0], coords[1], coords[2]));
    }

	@Override
	public Hit hit(Ray r) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Geometry asCelShading() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Geometry asSingelColor() {
		// TODO Auto-generated method stub
		return null;
	}


   
}