public class Circle {
    private double radius;
    private static final double PI = 3.14159;

    public Circle(double rad)
    {
        this.radius = rad;
    }

    public void setRadius(double rad)
    {
        this.radius = rad;
    }

    public double getRadius()
    {
        return radius;
    }

    public double area()
    {
        return PI*radius*radius;
    }

    public double circumference()
    {
        return 2*PI*radius;
    }

    public void printArea()
    {
        System.out.println("Area is: " +this.area());
    }

    public void printCircumference()
    {
        System.out.println("Circumference is: " +this.circumference());
    }
}
