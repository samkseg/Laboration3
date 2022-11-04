package se.iths.laboration3.model;

import javafx.beans.Observable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import se.iths.laboration3.shapes.Shape;
import se.iths.laboration3.shapes.ShapeType;

import java.util.Objects;

public class Model {
    public ObjectProperty<ShapeType> currentShapeType = new SimpleObjectProperty<>(ShapeType.CIRCLE);
    private ObservableList<ObsShape> shapes = FXCollections.observableArrayList(param -> new Observable[]{
            param.colorProperty(),
            param.borderColorProperty()
    });

    private ObservableList<ObsShape> selectedShapes = FXCollections.observableArrayList();

    public void setCurrentShapeType(ShapeType currentShapeType) {
        this.currentShapeType.set(currentShapeType);
    }
    public ObservableList<? extends Shape> getShapes() {
        return shapes;
    }
    public ObservableList<? extends Shape> getSelectedShapes() {
        return selectedShapes;
    }

    public void addSelectedList(Shape shape) {
        if (true) {
            removeFromSelectedList();
            var oShape = new ObsShape(shape);
            selectedShapes.add(oShape);
            oShape.select();
        }
    }

    public void removeFromSelectedList(){
        for (int i = 0; i < getSelectedShapes().size(); i++) {
            getSelectedShapes().get(i).deSelect();
        }
        selectedShapes.clear();
    }

    public Shape addShape(Shape shape) {
        var oShape = new ObsShape(shape);
        shapes.add(oShape);
        return shape;
    }

    public void remove(Shape shape){
        var oShape = new ObsShape(shape);
        if (shapes.size() > 0) {
            shapes.remove(oShape);
        }
    }
    public void removeAll(){
        shapes.clear();
        selectedShapes.clear();
    }

}
class ObsShape extends Shape {
    Shape shape;
    ObjectProperty<Color> color = new SimpleObjectProperty<>();
    ObjectProperty<Color> borderColor = new SimpleObjectProperty<>();

    public ObsShape(Shape shape){
        super(shape.getX(),shape.getY());
        this.shape = shape;
        color.set(shape.getColor());
        borderColor.set(shape.getBorderColor());
    }

    public ObjectProperty<Color> colorProperty() {
        return color;
    }
    public ObjectProperty<Color> borderColorProperty() {
        return borderColor;
    }
    @Override
    public boolean onClick(MouseEvent mouseEvent) {
        return shape.onClick(mouseEvent);
    }

    @Override
    public Color getColor() {
        return color.get();
    }
    @Override
    public Color getBorderColor() {
        return borderColor.get();
    }
    @Override
    public void setColor(Color color) {
        shape.setColor(color);
        this.color.set(color);
    }
    @Override
    public void setBorderColor(Color borderColor) {
        shape.setBorderColor(borderColor);
        this.borderColor.set(borderColor);
    }
    @Override
    public void draw(GraphicsContext context) {
        this.shape.draw(context);
    }


}


