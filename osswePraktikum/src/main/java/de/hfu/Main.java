
import java.util.Scanner;
import java.lang.System;

/* Hauptklasse für das OSSWE Praktikum, Blatt 5 "Build Management"
*  @author Florian Brunen <florian.brunen@hs-furtwangen.de>
*  @version 1.0
*
*  Diese Klasse liest Text von der Tastatur ein, wandelt sie vollständig in
*  Großbuchstaben um und auf dem Terminal wieder ausgegeben.
*/

public class Main{

    /* Hauptfunktion für die Main-Klasse. Auch wenn es nicht Best-Practice
    *  ist, wird in dieser Funktion alles ausgeführt, was das Programm tut,
    *  da es sich um ein simples kurzes Programm handelt.
    * 
    *  @param String[] args    Die Parameter die mit übergeben werden.
    *                          Diese werden nicht verarbeitet.
    */
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        System.out.println("Bitte hier Text einfügen:\n");

        String input = scan.nextLine();

        String output = input.toUpperCase();

        System.out.println(output);
    }

}