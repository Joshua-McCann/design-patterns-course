package structure.decorator;

import java.util.function.Supplier;

class SColoredShape<T extends Shape> implements Shape {
    private Shape shape;
    private String color;

    public SColoredShape(Supplier<? extends T> ctor, String color) {
        shape = ctor.get();
        this.color = color;
    }

    @Override
    public String info() {
        return shape.info() + " has the color " + color;
    }
}

class STransparentShape<T extends Shape> implements Shape {
    private Shape shape;
    private int transparency;

    public STransparentShape(Supplier<? extends T> ctor, int transparency) {
        shape = ctor.get();
        this.transparency = transparency;
    }

    @Override
    public String info() {
        return shape.info() + " has " + transparency + "% transparency";
    }
}

public class StaticDecorator {
    public static void main(String[] args) {
        SColoredShape<Square> blueSquare = new SColoredShape<>(() -> new Square(20), "blue");
        System.out.println(blueSquare.info());

        STransparentShape<SColoredShape<Circle>> transparentGreenCircle = new STransparentShape<>(() ->
                new SColoredShape<>(
                        () -> new Circle(5),
                        "green"
                ), 50
        );
        System.out.println(transparentGreenCircle.info());
    }
}
