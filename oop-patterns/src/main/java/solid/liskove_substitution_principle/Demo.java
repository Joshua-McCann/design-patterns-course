package solid.liskove_substitution_principle;

class Rectangle {
    protected int width, height;

    public Rectangle() {
    }

    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getArea() { return width * height; }

    public boolean isSquare() { return width == height; }

    @Override
    public String toString() {
        return "Rectangle{" +
                "width=" + width +
                ", height=" + height +
                '}';
    }
}

//class Square extends Rectangle {
//    public Square() {
//    }
//
//    public Square(int size) {
//        this.width = this.height = size;
//    }
//
//    @Override
//    public void setWidth(int width) {
//        super.setWidth(width);
//        super.setHeight(width);
//    }
//
//    @Override
//    public void setHeight(int height) {
//        super.setWidth(height);
//        super.setHeight(height);
//    }
//}

class RectangleFactory {
    public static Rectangle newRecTangle(int width, int height) {
        return new Rectangle(width, height);
    }

    public static Rectangle newSquare(int side) {
        return new Rectangle(side, side);
    }
}

public class Demo {

    public static void main(String[] args) {
        Rectangle rc = RectangleFactory.newRecTangle(2, 3);
        userIt(rc);

        Rectangle sq = RectangleFactory.newSquare(5);
        userIt(sq);
    }

    // because the square's implementation is too specific, the code below breaks when using a square.
    // this can be fixed with a factory method
    static void userIt(Rectangle r) {
        int width = r.getWidth();
        r.setHeight(10);
        System.out.println("Expected area of " + (width * 10) + ", got " + r.getArea());
    }
}
