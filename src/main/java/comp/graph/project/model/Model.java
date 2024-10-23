package comp.graph.project.model;

import com.sun.javafx.scene.shape.ArcHelper;
import comp.graph.project.math.Vector2f;
import comp.graph.project.math.Vector3f;
import javafx.scene.shape.TriangleMesh;

import java.util.ArrayList;

public class Model {

    public ArrayList<Vector3f> vertices = new ArrayList<Vector3f>();
    public ArrayList<Vector2f> textureVertices = new ArrayList<Vector2f>();
    public ArrayList<Vector3f> normals = new ArrayList<Vector3f>();
    public ArrayList<Polygon> polygons = new ArrayList<Polygon>();

    public Polygon getPolygon(int id){
        return polygons.get(id);
    }




}
