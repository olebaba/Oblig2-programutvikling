import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Oppgave3 {
}

//3.3.4 Designmønsteret Decorator kan brukes for å legge til en ramme på formene
class Frame extends Shape{
    private Shape framed;
    private int frameSize;

    public Frame(String type, Shape framed, int frameSize) {
        super(type);
        this.framed = framed;
        this.frameSize = frameSize;
    }

    @Override
    public void draw() {
        //tegn ramme
    }
}

//3.3.3
interface Drawable{
    String getType();
}

abstract class Shape implements Drawable{
    private String type;

    public Shape(String type){
        this.type = type;
    }

    @Override
    public String getType() {
        return type;
    }

    //3.3.1 draw() burde være abstrakt ettersom alle klassene har metoden, men med forskjellig implementasjon.
    public abstract void draw();
}

class Circle extends Shape{
    int radius;

    Circle(int radius){
        super("Circle");
        this.radius = radius;
    }

    @Override
    public void draw() {
        //tegn sirkel på skjerm
    }
}

class Square extends Shape{
    int sideLength;

    Square(int sideLength){
        super("Square");
        this.sideLength = sideLength;
    }

    @Override
    public void draw() {
        //tegn kvadrat på skjerm
    }
}

//3.3.3
class Drawings implements Drawable{
    private List<Drawable> drawings;

    public Drawings(Drawable... items) {
        drawings = Arrays.asList(items);
    }

    @Override
    public String getType() {
        return drawings.stream().map(Drawable::getType).collect(Collectors.joining("|"));
    }
}

class DrawingProgram{
    //3.3.2 Observer designmønsteret kan ...
    static void drawShapes(List<Shape> shapes){
        for (Shape s : shapes){
            s.draw();
        }
    }
    public static void main(String[] args) {
        //3.3.2
        List<Shape> shapes = new ArrayList<>();
        Circle c1 = new Circle(3);
        Square s1 = new Square(2);
        shapes.add(c1);
        shapes.add(s1);
        drawShapes(shapes);

        Circle c2 = new Circle(5);
        Square s2 = new Square(6);

        //3.3.3
        Drawings drawings = new Drawings(c1, c2, s1, s2);
    }
}
