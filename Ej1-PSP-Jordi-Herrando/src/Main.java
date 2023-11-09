public class Main {
    public static void main(String[] args) {
        int[] numeros = new int[10];

        // Llenar el array con números aleatorios
        Utilidades.llenarArrayConAleatorios(numeros);

        // Calcular si cada número es primo y mostrar los resultados
        for (int i = 0; i < numeros.length; i++) {
            boolean esPrimo = Utilidades.esPrimo(numeros[i]);
            System.out.println("Número: " + numeros[i] + " - Es primo: " + esPrimo);
        }

        // Medir el tiempo de ejecución para 10 elementos
        long startTime = System.nanoTime();
        for (int i = 0; i < numeros.length; i++) {
            boolean esPrimo = Utilidades.esPrimo(numeros[i]);
            System.out.println("Número: " + numeros[i] + " - Es primo: " + esPrimo);
        }
        long endTime = System.nanoTime();
        mostrarTiempoEjecucion(startTime, endTime);

        // Ajustar para 100 elementos
        int[] numeros100 = new int[100];
        Utilidades.llenarArrayConAleatorios(numeros100);

        startTime = System.nanoTime();
        for (int i = 0; i < numeros100.length; i++) {
            boolean esPrimo = Utilidades.esPrimo(numeros100[i]);
            System.out.println("Número: " + numeros100[i] + " - Es primo: " + esPrimo);
        }
        endTime = System.nanoTime();
        mostrarTiempoEjecucion(startTime, endTime);
    }

    private static void mostrarTiempoEjecucion(long startTime, long endTime) {
        long elapsedTime = endTime - startTime;

        System.out.println("Tiempo de ejecución:");
        System.out.println("Segundos: " + (double) elapsedTime / 1_000_000_000);
        System.out.println("Milisegundos: " + (double) elapsedTime / 1_000_000);
        System.out.println("Nanosegundos: " + elapsedTime);
    }
}

