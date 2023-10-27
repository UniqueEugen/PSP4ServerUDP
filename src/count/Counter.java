package count;

import myLibrary.console.Console;

import java.io.IOException;

public class Counter {
    private double x;
    private double y;
    private double z;
    private Double answer;
    public Counter(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public Counter(String xyz){
        String XYZ[] = xyz.split(" ");
        try {
            this.x = Double.parseDouble(XYZ[0]);
            this.y = Double.parseDouble(XYZ[1]);
            this.z = Double.parseDouble(XYZ[2]);
        }catch (Exception ex){
            Console.log("Exeption! "+ ex);
        }
    }
    public String  count(){
        Console.log("X " + x + " Y " + y + " Z "+ z);
        answer = 6+((Math.pow(Math.E,(x-Math.sin(y))))/
                (y+((Math.tan(Math.pow(x,2)))/
                        (y+(Math.pow(x,7)/
                                z))))*
                (Math.pow((1+(Math.pow((1.0/Math.tan(z/100)),7))),Math.sqrt(Math.abs(y)+3))));
        return answer.toString();
    }
}
