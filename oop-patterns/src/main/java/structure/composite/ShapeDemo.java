package structure.composite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// fundamental difference: this class is not abstract, and can be instantiated itself
class GraphicObject {
    protected String name = "Group";
    public String color;
    public List<GraphicObject> children = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GraphicObject() {
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        print(sb, 0);
        return sb.toString();
    }

    // with a recursive object, all children are treated the same as the higher level
    private void print(StringBuilder sb, int depth) {
        sb.append(String.join("", Collections.nCopies(depth, "*")))
            .append(depth > 0 ? " " : "")
            .append(color == null || color.isEmpty() ? "" : color + " ")
            .append(name)
            .append(System.lineSeparator());
        children.forEach(c -> c.print(sb, depth + 1));
    }
}

class Circle extends GraphicObject {
    public Circle(String color) {
        name = "Circle";
        this.color = color;
    }
}

class Square extends GraphicObject {
    public Square(String color) {
        name = "Square";
        this.color = color;
    }
}

public class ShapeDemo {
    public static void main(String[] args) {
        GraphicObject drawing = new GraphicObject();
        drawing.setName("My Drawing");
        drawing.children.add(new Square("Red"));
        drawing.children.add(new Circle("Yellow"));

        GraphicObject group = new GraphicObject();
        group.children.add(new Circle("Blue"));
        group.children.add(new Circle("Blue"));

        drawing.children.add(group);
    }
}
