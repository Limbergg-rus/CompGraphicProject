package comp.graph.project.model;

import java.util.ArrayList;

public class Triangle {
    private ArrayList<Integer> vertexIndices;

    Triangle(ArrayList<Integer> vertexIndices) {
        assert vertexIndices.size() == 3;
        this.vertexIndices = vertexIndices;
    }

    public ArrayList<Integer> getVertexIndices() {
        return vertexIndices;
    }

    public Integer getVertexIndex(int vertexIndex) {
        return vertexIndices.get(vertexIndex);
    }
}
