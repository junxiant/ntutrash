public class Rectangle extends Polygon {


    public Rectangle(String theName, float theWidth, float theHeight) {
        super(theName, theWidth, theHeight);
        this.polytype = KindofPolygon.POLY_RECT;
    }

//    @Override
//    public void setPolytype(KindofPolygon value) {
//        super.setPolytype(polytype);
//    }

    public float calArea() { return (width*height); } // Method Overriding

    //public void printArea() { System.out.println("Rectangle method Area: " + calArea()); } // Method Overriding

}
