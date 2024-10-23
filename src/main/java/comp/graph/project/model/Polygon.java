package comp.graph.project.model;

import java.util.ArrayList;
import java.util.Arrays;

public class Polygon {

    private ArrayList<Integer> vertexIndices;
    private ArrayList<Integer> textureVertexIndices;
    private ArrayList<Integer> normalIndices;
    private ArrayList<Triangle> triangles;

    public ArrayList<Triangle> getTriangles() {
        return triangles;
    }

    public Triangle getTriangle(int id){
        return triangles.get(id);
    }

    public Integer getVertexIndex(int id){
        return vertexIndices.get(id);
    }

    public void setTriangles(ArrayList<Triangle> triangles) {
        this.triangles = triangles;
    }

    public Polygon() {
        vertexIndices = new ArrayList<Integer>();
        textureVertexIndices = new ArrayList<Integer>();
        normalIndices = new ArrayList<Integer>();
        triangles = new ArrayList<>();
    }

    public void triangulation(){
        for(int i = 2; i < vertexIndices.size(); i++){
            triangles.add(new Triangle(new ArrayList(Arrays.asList(
                    vertexIndices.get(0),vertexIndices.get(1),vertexIndices.get(i)))));
        }

    }

    public void setVertexIndices(ArrayList<Integer> vertexIndices) {
        assert vertexIndices.size() >= 3;
        this.vertexIndices = vertexIndices;
    }

    public void setTextureVertexIndices(ArrayList<Integer> textureVertexIndices) {
        assert textureVertexIndices.size() >= 3;
        this.textureVertexIndices = textureVertexIndices;
    }

    public void setNormalIndices(ArrayList<Integer> normalIndices) {
        assert normalIndices.size() >= 3;
        this.normalIndices = normalIndices;
    }

    public ArrayList<Integer> getVertexIndices() {
        return vertexIndices;
    }

    public ArrayList<Integer> getTextureVertexIndices() {
        return textureVertexIndices;
    }

    public ArrayList<Integer> getNormalIndices() {
        return normalIndices;
    }
}
