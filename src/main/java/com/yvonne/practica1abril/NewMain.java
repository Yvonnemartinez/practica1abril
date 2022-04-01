package com.mycompany.practica1bril;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;


public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Scanner leer = new Scanner(System.in);
        File archivo1 = new File("C:\\Users\\Ivonne\\Documents\\NetBeansProjects\\practica1abril\\DIR\\archivo1.txt");
        File archivo2 = new File("C:\\Users\\Ivonne\\Documents\\NetBeansProjects\\practica1abril\\DIR\\archivo2.txt");
        File directorioActual = new File("C:\\Users\\Ivonne\\Documents\\NetBeansProjects\\practica1abril");
        File DIR = new File("C:\\Users\\Ivonne\\Documents\\NetBeansProjects\\practica1abril\\DIR");
        MiniFileManager fileManager = null;

        try {

            boolean bucleMenu = true;

            while (bucleMenu) {

                System.out.println("=====");
                System.out.println("Que acción quieres realizar?");
                System.out.println("   ");
                System.out.println("1. pwd ");
                System.out.println("2. cd <DIR> ");
                System.out.println("3. ls ");
                System.out.println("4. ll ");
                System.out.println("5. mkdir <DIR> ");
                System.out.println("6. rm <FILE> ");
                System.out.println("7. mv <FILE1> <FILE2> ");
                System.out.println("8. help ");
                System.out.println("9. info <FILE>");
                System.out.println("10. salir.");
                System.out.println("====");

                int opcion;

                while (!leer.hasNextInt()) {

                    System.out.println("Error, introduce un número del '1' al '10' ");
                    leer.next();
                }
                opcion = leer.nextInt();


                switch (opcion) {
                    case 1:
                        System.out.println("----------------------------------------");
                        fileManager.getPWD(directorioActual);
                        break;
                    case 2:
                        System.out.println("----------------------------------------");
                        fileManager.cd(directorioActual, DIR);
                        break;
                    case 3:
                        System.out.println("----------------------------------------");
                        fileManager.ls(directorioActual);
                        break;
                    case 4:
                        System.out.println("----------------------------------------");
                        fileManager.ll(directorioActual);
                        break;
                    case 5:
                        System.out.println("----------------------------------------");
                        System.out.println(fileManager.mkdir(directorioActual) ? "Se ha creado correctamente" : "No se puede.");
                        break;
                    case 6:
                        System.out.println("----------------------------------------");
                        fileManager.rm(archivo1);
                        break;
                    case 7:
                        System.out.println("----------------------------------------");
                        System.out.println(fileManager.mv(archivo1, archivo2) ? "Se ha renombrado correctamente" : "No se puede.");
                        break;
                    case 8:
                        System.out.println("----------------------------------------");
                        fileManager.help();
                        break;
                    case 9: 
                         System.out.println("----------------------------------------");
                         fileManager.info(directorioActual);
                        break;
                    case 10:
                        bucleMenu = false;
                        break;

                    default:
                        System.out.println("ERROR!Esta operacion no existe.");
                }

            }
        } catch (FileNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
        }
        System.out.println("Final");

    }
}

