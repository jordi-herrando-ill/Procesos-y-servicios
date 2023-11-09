package org.example;

import java.util.Random;



// Clase que es un jardín
class Jardin {
    private int id;
    private boolean ocupado;

    // Constructor de la clase Jardin
    public Jardin(int id) {
        this.id = id;
        this.ocupado = false;
    }

    // Método synchronized para que solo un jardinero pueda trabajar en el jardín a la vez
    public synchronized void comenzarTrabajo(int jardineroID) {
        // Mientras el jardín esté ocupado, el hilo espera
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
        // El jardín se marca como libre y se notifica a los demás hilos en espera
        ocupado = false;
        System.out.println("El jardinero " + jardineroID + " ha terminado de trabajar en el jardín " + id);
        notifyAll();
    }
}

// Clase que es a un jardinero (hilo)
class Jardinero extends Thread {
    private static int jardineroCount = 0;
    private int id;
    private Jardin[] jardines;

    // Constructor de la clase Jardinero
    public Jardinero(Jardin[] jardines) {
        this.id = ++jardineroCount;
        this.jardines = jardines;
    }

    // Método run que se ejecuta cuando se inicia el hilo
    @Override
    public void run() {
        // El jardinero trabaja en 30 jardines
        for (int i = 0; i < 30; i++) {
            // Elige un jardín de manera aleatoria
            int jardinID = elegirJardin();
            // El jardinero comienza a trabajar en el jardín seleccionado
            jardines[jardinID].comenzarTrabajo(id);
            try {
                Thread.sleep(1000); // Simulación de un día de trabajo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // El jardinero termina de trabajar en el jardín seleccionado
            jardines[jardinID].terminarTrabajo(id);
        }
    }

    // Método privado para elegir un jardín de manera aleatoria
    private int elegirJardin() {
        Random random = new Random();
        return random.nextInt(jardines.length);
    }
}





