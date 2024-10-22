package comp.graph.project;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class HelloController {
    @FXML
    AnchorPane anchorPane;
    @FXML
    private Canvas canvas;

    @FXML
    private void initialize() {
        anchorPane.prefWidthProperty().addListener((ov, oldValue, newValue) -> canvas.setWidth(newValue.doubleValue()));
        anchorPane.prefHeightProperty().addListener((ov, oldValue, newValue) -> canvas.setHeight(newValue.doubleValue()));

        Rasterization.drawRectangle(canvas.getGraphicsContext2D(), 200, 300, 200, 100, Color.ORANGE);
        Rasterization.drawRectangle(canvas.getGraphicsContext2D(), 250, 250, 50, 200, Color.AQUA);
    }
}