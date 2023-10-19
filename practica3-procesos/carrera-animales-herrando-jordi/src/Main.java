// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import java.util.Random;
public class Main {


    public static class CarreraAnimales {
        public void main(String[] args) {
            Animal tortuga = new Animal("Tortuga", 1, 0.1);
            Animal liebre = new Animal("Liebre", 10, 0.2);
            Animal caballo = new Animal("Caballo", 20, 0.15);
            Animal perro = new Animal("Perro", 5, 0.05);

            tortuga.start();
            liebre.start();
            caballo.start();
            perro.start();
        }
    }

    static class Animal extends Thread {
        private final String nombre;
        private final int velocidad;
        private final double probabilidadResbalar;
        private int distanciaRecorrida;

        public Animal(String nombre, int velocidad, double probabilidadResbalar) {
            this.nombre = nombre;
            this.velocidad = velocidad;
            this.probabilidadResbalar = probabilidadResbalar;
        }

        @Override
        public void run() {
            for (int i = 0; i < 1000; i++) {
                if (Math.random() < probabilidadResbalar) {
                    System.out.println(nombre + " resbaló!");
                    distanciaRecorrida -= 10;
                } else {
                    distanciaRecorrida += velocidad;
                }

                System.out.println(nombre + " ha recorrido " + distanciaRecorrida + " metros.");
            }

            System.out.println(nombre + " ha terminado la carrera.");
        }
    }


}