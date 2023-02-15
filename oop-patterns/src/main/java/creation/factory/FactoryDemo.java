package creation.factory;

class Point {
    private double x,y;

    private Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static class Factory {
        // factory methods are used to construct the new object based on the creation method you might need
        public static Point newCartesianPoint(double x, double y) {
            return new Point(x, y);
        }

        public static Point newPolarPoint(double rho, double theta) {
            return new Point(rho * Math.cos(theta), rho * Math.sin(theta));
        }
    }

}

public class FactoryDemo {

    Point point = Point.Factory.newCartesianPoint(3, 4);
    Point point2 = Point.Factory.newPolarPoint(12, 36);

}
