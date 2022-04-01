package com.mycompany.practica1bril;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class MiniFileManager {

    //pwd: Muestra cual es la carpeta actual.
    //---------------------------------------
    public static void getPWD(File carpetaActual) throws Exception {

        if (carpetaActual.exists()) {
            System.out.println("pwd: " + carpetaActual.getAbsolutePath());
        } else {
            throw new FileNotFoundException("La carpeta no existe");
        }

    }

    //cd: Cambia la carpeta actual a ‘DIR’. Con .. cambia a la carpeta superior.
    public static void cd(File carpetaActual, File DIR) {
        boolean cambiaDIR = carpetaActual.renameTo(DIR);
        System.out.println("Cambiamos a la carpeta DIR. " + cambiaDIR);
        System.out.println("cd .. \n" + DIR.getParent());
    }

    //ls: Muestra la lista de directorios y archivos de la carpeta actual
    public static void ls(File carpeta) throws Exception {
        if (carpeta.isDirectory()) {
            File[] vectorArchivos = carpeta.listFiles();

            for (int i = 0; i < vectorArchivos.length; i++) {

                File f = vectorArchivos[i];

                if (f.isDirectory()) {
                    System.out.println("*: " + f.getName());
                }
            }
            for (int t = 0; t < vectorArchivos.length; t++) {
                File o = vectorArchivos[t];
                if (o.isFile()) {
                    System.out.println("A: " + o.getName());
                }
            }
        } else {
            throw new FileNotFoundException("La ruta introducida no existe");
        }
    }

    //ll: Similar a ls pero muestra también el tamaño y la fecha de la última modificación
    public static void ll(File fichero) {
        long ultimaMod = fichero.lastModified();
        Date fecha = new Date(ultimaMod);

        /*list()
        Returns an array of strings naming the files and directories in the directory denoted by this abstract pathname.*/
        //System.out.println(Arrays.toString(fichero.list()));
        System.out.println("Última modificación (ms) : " + ultimaMod);
        System.out.println("Última modificación (fecha): " + fecha);
        System.out.println("Tamaño del archivo: " + fichero.length()); //solo archivos
    }

    //mkdir <DIR>: Crea el directorio ‘DIR’ en la carpeta actual.
    public static boolean mkdir(File directorioActual) {
        System.out.println("Introduzca el nombre de la carpeta que quieres crear: ");
        Scanner lector = new Scanner(System.in);
        String nombreNuevaCarpeta = lector.nextLine();
        File nuevaCarpeta = new File(directorioActual.getAbsolutePath() + "//" + nombreNuevaCarpeta);
        boolean crearDirectorio = nuevaCarpeta.mkdir();
        return crearDirectorio;
    }

    //rm <FILE>: Borra ‘FILE’ (borra archivos)
    public static void rm(File fichero) {
        if (fichero.isFile()) {
            boolean borrar = fichero.delete();
            System.out.println("Se ha podido borrar el archivo? " + borrar);
        } else if (fichero.isDirectory()) {
            File[] vectorArchivos = fichero.listFiles();
            boolean tieneSubcarpetas = false;

            for (File f : vectorArchivos) {

                if (f.isDirectory()) {
                    tieneSubcarpetas = true;
                    System.out.println("No se puede borrar porque tiene subcarpetas. ");
                    break;
                }

            }
            if (!tieneSubcarpetas) {
                for (int i = 0; i < vectorArchivos.length; i++) {
                    File f = vectorArchivos[i];
                    boolean borrar = f.delete();
                    System.out.println("Se borraron todos los archivos mi ciela. " + borrar);

                }

                boolean borrar2 = fichero.delete();
                System.out.println("La carpeta madre/padre se ha borrado. " + borrar2);
            }
        }
    }

    //mv <FILE1> <FILE2>: Mueve o renombra ‘FILE1’ a ‘FILE2’
    public static boolean mv(File rutaInicial, File rutaNueva) {
        boolean renombrar = rutaInicial.renameTo(rutaNueva);
        return renombrar;
    }
    
     /**
     *Conversor de Bytes
     * @param bytes que pasamos a KB o MB
     * @return la cantidad en MB
     */
    public long conversor(long bytes) {
        long kb = 0, mb = 0;
        kb = bytes / 1024;
        mb = kb / 1024;
        return mb;
    }

    // info
    /* Recibe argumento, una ruta y muestra por pantalla el tamaño en bytes y megas de la ruta, asi como la fecha de su última modificación. */
    public static void info(File ruta) throws Exception {

        try {
            // Si es un archivo:
            if (ruta.isFile()) {

                // uso otra función de File(.lastModified) que te develve la fecha de la última modificación del archivo en milisegundos
                // damos el formato en el que queremos expresar la fecha
                DateFormat fechaFormato = new SimpleDateFormat("dd MMM yyyy HH:mm:ss:SSS z");
                // convertir milisegundos a una fecha:
                Date fechaModificacion = new Date(ruta.lastModified());

                // Obtengo la longitud en bytes de la ruta:
                long tamanoBytes = ruta.length();

                // Muestra el tamaño en bytes:
                System.out.print(tamanoBytes + " bytes ");
                // Muestra el tamaño en megas:
                System.out.print(tamanoBytes / (1024 * 1024) + " megabytes ");
                // Muestra la fecha de su última modificación:
                System.out.print(fechaFormato.format(fechaModificacion));
                System.out.println(" ");
            }
            // Si es una carpeta / directorio:
            if (ruta.isDirectory()) {

                // Creo la lista para saber cuantos archivos hay en el directorio:
                File[] listado = ruta.listFiles();

                // listado.length es igual al numero de elementos que tengamos en nuestra carpeta.
                // y ahora recorro el directorio para mostrar el nombre de cada uno de los archivos:
                for (int i = 0; i < listado.length; i++) {

                    // Si es un directorio:
                    if (listado[i].isDirectory()) {
                        System.out.print("[*]" + listado[i].getName() + " ");
                        // damos el formato en el que queremos expresar la fecha
                        DateFormat fechaFormato = new SimpleDateFormat("dd MMM yyyy HH:mm:ss:SSS z");
                        // convertir milisegundos a una fecha:
                        Date fechaModificacion = new Date(ruta.lastModified());
                        // Obtengo la longitud en bytes de la ruta:
                        long tamanoBytes = listado[i].length();

                        // Muestra el tamaño en bytes:
                        System.out.print(tamanoBytes + " bytes ");
                        // Muestra el tamaño en megas:
                        System.out.print(tamanoBytes / (1024 * 1024) + " megabytes ");
                        // Muestra la fecha de su última modificación:
                        System.out.print(fechaFormato.format(fechaModificacion));
                        System.out.println(" ");

                    } // Si es un archivo:
                    else {
                        System.out.print("[A]" + listado[i].getName() + " ");
                        // damos el formato en el que queremos expresar la fecha
                        DateFormat fechaFormato = new SimpleDateFormat("dd MMM yyyy HH:mm:ss:SSS z");
                        // convertir milisegundos a una fecha:
                        Date fechaModificacion = new Date(ruta.lastModified());
                        // Obtengo la longitud en bytes de la ruta:
                        long tamanoBytes = listado[i].length();

                        // Muestra el tamaño en bytes:
                        System.out.print(tamanoBytes + " bytes ");
                        // Muestra el tamaño en megas:
                        System.out.print(tamanoBytes / (1024 * 1024) + " megabytes ");
                        // Muestra la fecha de su última modificación:
                        System.out.print(fechaFormato.format(fechaModificacion));
                        System.out.println(" ");
                    }

                }
            }
            // Si la ruta al archivo no existe:
            if (!ruta.exists()) {
                System.out.println("La ruta no existe.");
                throw new FileNotFoundException("Archivo/directorio no encontrado");
            }
            //capturo la excepcion y la muestro con el stacktrace      
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //help: Muestra una breve ayuda con los comandos disponibles
    public static void help() {
        System.out.println(" ");
        System.out.println("pwd: Muestra cual es la carpeta actual.");
        System.out.println(" ");
        System.out.println("cd <DIR>: Cambia la carpeta actual a ‘DIR’. Con .. cambia a la carpeta superior.");
        System.out.println(" ");
        System.out.println("ls: Muestra la lista de directorios y archivos de la carpeta actual (primero directorios, luego\n"
                + "archivos, ambos ordenados alfabéticamente).");
        System.out.println(" ");
        System.out.println("ll: Como ls pero muestra también el tamaño y la fecha de última modificación.");
        System.out.println(" ");
        System.out.println("mkdir <DIR>: Crea el directorio ‘DIR’ en la carpeta actual.");
        System.out.println(" ");
        System.out.println("rm <FILE>: Borra ‘FILE’. Si es una carpeta, borrará primero sus archivos y luego la carpeta. Si\n"
                + "tiene subcarpetas, las dejará intactas y mostrará un aviso al usuario.");
        System.out.println(" ");
        System.out.println("mv <FILE1> <FILE2>: Mueve o renombra ‘FILE1’ a ‘FILE2’ ");
        System.out.println("info <FILE>: muestra el tamaño en bytes, megabytes y la última fecha de modificación.");
    }
}
