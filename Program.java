package Program;

import EliotMoss.Gen;
import EliotMoss.Jęz;
import org.w3c.dom.ls.LSOutput;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Program {
    public static void main(String[] args) throws FileNotFoundException {
        Jęz j = new Jęz(6);
        j.uczSię( "JęzykMił");
        System.out.println(j.dajSłownik());
        System.out.println(j.dajNazwę());
        Gen g = new Gen(j);
        try (PrintWriter writer = new PrintWriter("JęzykPierwszejMiłości.txt")){
            String uno = g.dajSłowo();
            writer.print(uno.substring(0,1).toUpperCase()+uno.substring(1)+" ");
            for (int k = 0; k<34; k++){
            for (int stuck = 21; stuck>0; stuck--){
            writer.print(g.dajSłowo()+" ");}
                writer.println();}
            writer.print(".");
        }
        catch(Exception e){
            System.out.println(e);
        }


    }
}
