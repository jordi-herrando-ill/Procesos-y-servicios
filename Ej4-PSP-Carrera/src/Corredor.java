import java.util.Random;

public class Corredor {
    private String nombre;
    private char simbolo;
    private int velocidadBase;
    private int turbo;
    private int posibilidadTropezar;
    private int posicion;

    public Corredor(String nombre, char simbolo, int velocidadBase, int turbo, int posibilidadTropezar) {
        this.nombre = nombre;
        this.simbolo = simbolo;
        this.velocidadBase = velocidadBase;
        this.turbo = turbo;
        this.posibilidadTropezar = posibilidadTropezar;
        this.posicion = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPosicion() {
        return posicion;
    }

    public synchronized boolean avanzar() {
        Random rand = new Random();
        while (posicion < 100) {
            int avance = velocidadBase + (rand.nextInt(turbo + 1));
            posicion += avance;
            imprimirEstado();
            try {
                Thread.sleep(1000); // Simulación de un segundo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    public synchronized boolean tropezar() {
        Random rand = new Random();
        while (posicion < 100) {
            int probabilidadTropezar = rand.nextInt(10) + 1;
            if (probabilidadTropezar <= posibilidadTropezar) {
                System.out.println(nombre + " ha tropezado y no avanza este turno.");
            }
            try {
                Thread.sleep(1000); // Simulación de un segundo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    private void imprimirEstado() {
        StringBuilder estado = new StringBuilder();
        for (int i = 0; i < 100; i++) {
            estado.append(i == posicion ? simbolo : '-');
        }
        System.out.println(nombre + ": " + estado.toString());
    }


}
