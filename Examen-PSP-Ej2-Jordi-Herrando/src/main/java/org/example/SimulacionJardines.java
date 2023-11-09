package org.example;


// Clase main que ejecuta la simulación
public class SimulacionJardines {
    public static void main(String[] args) {
        Jardin[] jardines = new Jardin[10];
        for (int i = 0; i < 10; i++) {
            jardines[i] = new Jardin(i + 1);
        }

        Jardinero jardinero1 = new Jardinero(jardines);
        Jardinero jardinero2 = new Jardinero(jardines);
        Jardinero jardinero3 = new Jardinero(jardines);

        Ciudadano ciudadano1 = new Ciudadano(jardines);
        Ciudadano ciudadano2 = new Ciudadano(jardines);
        Ciudadano ciudadano3 = new Ciudadano(jardines);
        Ciudadano ciudadano4 = new Ciudadano(jardines);
        Ciudadano ciudadano5 = new Ciudadano(jardines);

        jardinero1.start();
        jardinero2.start();
        jardinero3.start();

        ciudadano1.start();
        ciudadano2.start();
        ciudadano3.start();
        ciudadano4.start();
        ciudadano5.start();

        try {
            jardinero1.join();
            jardinero2.join();
            jardinero3.join();

            ciudadano1.join();
            ciudadano2.join();
            ciudadano3.join();
            ciudadano4.join();
            ciudadano5.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Se muestran cuántos jardines ha trabajado cada jardinero al final de la simulación
        System.out.println("Respecto a los Trabajadores:");
        System.out.println("El jardinero 1 ha trabajado " + jardinero1.getId() + " jardines.");
        System.out.println("El jardinero 2 ha trabajado " + jardinero2.getId() + " jardines.");
        System.out.println("El jardinero 3 ha trabajado " + jardinero3.getId() + " jardines.");

        // Se muestran las quejas recibidas por los ciudadanos
        System.out.println("\nRespecto a las Quejas recibidas:");
        for (Ciudadano ciudadano : new Ciudadano[]{ciudadano1, ciudadano2, ciudadano3, ciudadano4, ciudadano5}) {
            for (int queja : ciudadano.getQuejas()) {
                System.out.println("El jardín " + (queja + 1) + " está en mal estado.");
            }
        }
    }
}