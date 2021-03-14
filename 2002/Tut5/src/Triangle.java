public class Triangle extends Polygon{

    public Triangle(String theName, float theWidth, float theHeight) {
        super(theName, theWidth, theHeight);
        this.polytype = KindofPolygon.POLY_TRIANG;
    }

//    @Override
//    public void setPolytype(KindofPolygon value) {
//        super.setPolytype(polytype);
//    }

    public float calArea() { return (width*height)/2; } // Method Overriding

//    public void printArea() {
//        System.out.println("Triangle method Area: " + calArea());
//    } // Method Overriding

}
