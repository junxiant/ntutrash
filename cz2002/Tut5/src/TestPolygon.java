public class TestPolygon {

//    public static void printArea(Rectangle rect) // Method Overload
//    {
//        float area = rect.calArea();
//        System.out.println("Area of: "+rect.getPolytype() + " is " + area);
//    }
//
//    public static void printArea(Triangle tri) // Method Overload
//    {
//        float area = tri.calArea();
//        System.out.println("Area of: "+tri.getPolytype() + " is " + area);
//    }

    public static void printArea(Polygon p) // Method Overload, Dynamic binding
    {
        float area = p.calArea();
        System.out.println("Area of: "+p.getPolytype() + " is " + area);
    }

    public static void main(String[] args) {

        Polygon p = new Polygon();
        Rectangle rect = new Rectangle("Rect", 10, 10);
        Triangle tri = new Triangle("Tri", 10, 10);

        printArea(rect); // Static, look to "Rectangle" Reference type for method
        printArea(tri); // Static, look to "Rectangle" Reference type for method

//        tri.printWidthHeight();
//        tri.printArea();
//
//        rect.printWidthHeight();
//        rect.printArea();


    }
}
