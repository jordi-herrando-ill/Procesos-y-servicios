package org.example;


// Clase main que ejecuta la simulación
public class SimulacionJardines {
    public static void main(String[] args) {
        // Crear array de objetos Jardin
        Jardin[] jardines = new Jardin[10];
        for (int i = 0; i < 10; i++) {
            jardines[i] = new Jardin(i + 1);
        }

        // Creamos tres jardineros
        Jardinero jardinero1 = new Jardinero(jardines);
        Jardinero jardinero2 = new Jardinero(jardines);
        Jardinero jardinero3 = new Jardinero(jardines);

        // Se inician los tres hilos (jardineros)
        jardinero1.start();
        jardinero2.start();
        jardinero3.start();

        try {
            // Esperamos a que los tres hilos finalicen
            jardinero1.join();
            jardinero2.join();
            jardinero3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Finalmente, se muestran cuántos jardines ha trabajado cada jardinero al final de la simulación
        System.out.println("El jardinero 1 ha trabajado " + jardinero1.getId() + " jardines.");
        System.out.println("El jardinero 2 ha trabajado " + jardinero2.getId() + " jardines.");
        System.out.println("El jardinero 3 ha trabajado " + jardinero3.getId() + " jardines.");
    }
}
