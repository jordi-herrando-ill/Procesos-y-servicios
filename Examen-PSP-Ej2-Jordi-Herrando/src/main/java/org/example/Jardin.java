package org.example;

import java.util.Random;



import java.util.ArrayList;
import java.util.List;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// Clase que representa un jardín
class Jardin {
    private int id;
    boolean ocupado;
    private long tiempoUltimoTrabajo;

    // Constructor de la clase Jardin
    public Jardin(int id) {
        this.id = id;
        this.ocupado = false;
        this.tiempoUltimoTrabajo = System.currentTimeMillis();
    }

    // Método synchronized para que solo un jardinero pueda trabajar en el jardín a la vez
    public synchronized void comenzarTrabajo(int jardineroID) {
        while (ocupado) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // Aquí el jardinero comienza a trabajar en el jardín y lo marca como ocupado
        ocupado = true;
        System.out.println("El jardinero " + jardineroID + " ha empezado a trabajar en el jardín " + id);
    }

    // Método synchronized para indicar que el jardinero ha terminado de trabajar en el jardín
    public synchronized void terminarTrabajo(int jardineroID) {
        ocupado = false;
        tiempoUltimoTrabajo = System.currentTimeMillis();
        System.out.println("El jardinero " + jardineroID + " ha terminado de trabajar en el jardín " + id);
        notifyAll();
    }

    // Método para calcular el tiempo desde el último trabajo
    public long tiempoDesdeUltimoTrabajo() {
        return System.currentTimeMillis() - tiempoUltimoTrabajo;
    }
}

// Clase que representa a un ciudadano (hilo)
class Ciudadano extends Thread {
    private static int ciudadanoCount = 0;
    private int id;
    private Jardin[] jardines;
    private List<Integer> quejas;

    // Constructor de la clase Ciudadano
    public Ciudadano(Jardin[] jardines) {
        this.id = ++ciudadanoCount;
        this.jardines = jardines;
        this.quejas = new ArrayList<>();
    }

    // Método run que se ejecuta cuando se inicia el hilo
    @Override
    public void run() {
        for (int i = 0; i < 15; i++) { // Cada día durante 30 días (se ejecuta cada 2 días)
            int jardinID = elegirJardin();
            if (jardines[jardinID].tiempoDesdeUltimoTrabajo() > 2000) { // Más de 2 días sin trabajo
                quejas.add(jardinID);
            }
            try {
                Thread.sleep(2000); // Simulación de dos días (cada 2 segundos)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Método privado para elegir un jardín de manera aleatoria
    private int elegirJardin() {
        Random random = new Random();
        return random.nextInt(jardines.length);
    }

    // Método para obtener la lista de quejas
    public List<Integer> getQuejas() {
        return quejas;
    }
}

// Clase que representa a un jardinero (hilo)
class Jardinero extends Thread {
    private static int jardineroCount = 0;
    private int id;
    private Jardin[] jardines;

    // Constructor de la clase Jardinero
    public Jardinero(Jardin[] jardines) {
        this.id = ++jardineroCount;
        this.jardines = jardines;
    }

    @Override
    public void run() {
        for (int i = 0; i < 30; i++) { // Trabaja cada día durante 30 días
            int jardinID = elegirJardinOptimo();
            jardines[jardinID].comenzarTrabajo(id);
            try {
                Thread.sleep(1000); // Simulación de un día de trabajo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            jardines[jardinID].terminarTrabajo(id);
        }
    }

    // Método privado para elegir el jardín que lleva más tiempo sin ser trabajado
    private int elegirJardinOptimo() {
        int jardinID = 0;
        long tiempoMaximo = 0;

        for (int i = 0; i < jardines.length; i++) {
            long tiempoDesdeUltimoTrabajo = jardines[i].tiempoDesdeUltimoTrabajo();
            if (!jardines[i].ocupado && tiempoDesdeUltimoTrabajo > tiempoMaximo) {
                tiempoMaximo = tiempoDesdeUltimoTrabajo;
                jardinID = i;
            }
        }

        return jardinID;
    }
}











