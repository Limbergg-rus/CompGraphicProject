package comp.graph.project.render_engine;

import comp.graph.project.math.Vector3f;
import comp.graph.project.model.Model;
import javafx.scene.canvas.GraphicsContext;

import javax.vecmath.*;
import java.util.ArrayList;

import static comp.graph.project.render_engine.GraphicConveyor.*;

public class RenderEngine {

    public static void render(
            final GraphicsContext graphicsContext,
            final Camera camera,
            final Model mesh,
            final int width,
            final int height,
            final Enum<ShowTriangles> showTriangulation)
    {
        Matrix4f modelMatrix = rotateScaleTranslate();
        Matrix4f viewMatrix = camera.getViewMatrix();
        Matrix4f projectionMatrix = camera.getProjectionMatrix();

        Matrix4f modelViewProjectionMatrix = new Matrix4f(modelMatrix);
        modelViewProjectionMatrix.mul(viewMatrix);
        modelViewProjectionMatrix.mul(projectionMatrix);
        if(showTriangulation.equals(ShowTriangles.TRIANGLES)) {
            renderTriangle(graphicsContext, mesh, width, height, modelViewProjectionMatrix);
        } else
            renderPolygons(graphicsContext, mesh, width, height, modelViewProjectionMatrix);

    }

    private static void renderTriangle(GraphicsContext graphicsContext, Model mesh, int width, int height, Matrix4f modelViewProjectionMatrix) {
        //Узнаем количество полигонов
        final int nPolygons = mesh.polygons.size();
        //Проходимся по каждому полигону
        for (int polygonInd = 0; polygonInd < nPolygons; ++polygonInd) {
            //Создаем лист содержащий все точки полигона
            ArrayList<Point2f> resultPoints = new ArrayList<>();
            //Узнаем количество треугольников
            final int nTrianglesInPolygon = mesh.getPolygon(polygonInd).getTriangles().size();
            //Проходимся по всем треугольникам
            for (int TriangleInd = 0; TriangleInd < nTrianglesInPolygon; ++TriangleInd) {
                //Получаем все вершины конкретного полигона
                final int nVerticesInTrinagle = mesh.getPolygon(polygonInd).getTriangle(TriangleInd).getVertexIndices().size();
                //Проходимся по всем вершинам полигона
                for (int vertexInTriangleInd = 0; vertexInTriangleInd < nVerticesInTrinagle; ++vertexInTriangleInd) {
                    //Получаем ссылку на вектор вершины с координатами, проходясь по конкретному полигону и получая номер конкретного вектора
                    Vector3f vertex = mesh.vertices.get(mesh.getPolygon(polygonInd).getTriangle(TriangleInd).getVertexIndex(vertexInTriangleInd));
                    //Переводим самописный вектор в библиотечный вектор
                    javax.vecmath.Vector3f vertexVecmath = new javax.vecmath.Vector3f(vertex.x, vertex.y, vertex.z);
                    //Получаем точку на экране путем перемножения матриц
                    Point2f resultPoint = vertexToPoint(multiplyMatrix4ByVector3(modelViewProjectionMatrix, vertexVecmath), width, height);
                    //Добавляем точку в итоговый массив для вывода
                    resultPoints.add(resultPoint);
                }

                for (int vertexInTriangleInd = 1; vertexInTriangleInd < nVerticesInTrinagle; ++vertexInTriangleInd) {
                    graphicsContext.strokeLine(
                            resultPoints.get(vertexInTriangleInd - 1).x,
                            resultPoints.get(vertexInTriangleInd - 1).y,
                            resultPoints.get(vertexInTriangleInd).x,
                            resultPoints.get(vertexInTriangleInd).y);
                }

                if (nVerticesInTrinagle > 0)
                    graphicsContext.strokeLine(
                            resultPoints.get(nVerticesInTrinagle - 1).x,
                            resultPoints.get(nVerticesInTrinagle - 1).y,
                            resultPoints.get(0).x,
                            resultPoints.get(0).y);
            }
        }
    }

    private static void renderPolygons(GraphicsContext graphicsContext, Model mesh, int width, int height, Matrix4f modelViewProjectionMatrix) {
        //Узнаем количество полигонов
        final int nPolygons = mesh.polygons.size();
        //Проходимся по каждому полигону
        for (int polygonInd = 0; polygonInd < nPolygons; ++polygonInd) {
            //Получаем все вершины конкретного полигона
            final int nVerticesInPolygon = mesh.getPolygon(polygonInd).getVertexIndices().size();
            //Создаем лист содрежащий все точки полигона
            ArrayList<Point2f> resultPoints = new ArrayList<>();
            //Проходимся по всем вершинам полигона
            for (int vertexInPolygonInd = 0; vertexInPolygonInd < nVerticesInPolygon; ++vertexInPolygonInd) {
                //Получаем ссылку на вектор вершины с координатами, проходясь по конкретному полигону и получая номер конкретного вектора
                Vector3f vertex = mesh.vertices.get(mesh.getPolygon(polygonInd).getVertexIndex(vertexInPolygonInd));
                //Переводим самописный вектор в библиотечный вектор
                javax.vecmath.Vector3f vertexVecmath = new javax.vecmath.Vector3f(vertex.x, vertex.y, vertex.z);
                //Получаем точку на экране путем перемножения матриц
                Point2f resultPoint = vertexToPoint(multiplyMatrix4ByVector3(modelViewProjectionMatrix, vertexVecmath), width, height);
                //Добавляем точку в итоговый массив для вывода
                resultPoints.add(resultPoint);
            }

            for (int vertexInPolygonInd = 1; vertexInPolygonInd < nVerticesInPolygon; ++vertexInPolygonInd) {
                graphicsContext.strokeLine(
                        resultPoints.get(vertexInPolygonInd - 1).x,
                        resultPoints.get(vertexInPolygonInd - 1).y,
                        resultPoints.get(vertexInPolygonInd).x,
                        resultPoints.get(vertexInPolygonInd).y);
            }

            if (nVerticesInPolygon > 0)
                graphicsContext.strokeLine(
                        resultPoints.get(nVerticesInPolygon - 1).x,
                        resultPoints.get(nVerticesInPolygon - 1).y,
                        resultPoints.get(0).x,
                        resultPoints.get(0).y);
        }
    }
}