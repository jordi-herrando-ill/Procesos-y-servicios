import java.util.Random;

public class Utilidades {

    // Método para llenar un array con números aleatorios
    public static void llenarArrayConAleatorios(int[] array) {
        Random rand = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = rand.nextInt(Integer.MAX_VALUE / 10); // Números aleatorios hasta el 10% de Integer.MAX_VALUE
        }
    }

    // Método para verificar si un número es primo (método poco eficiente a propósito)
    public static boolean esPrimo(int numero) {
        if (numero < 2) {
            return false;
        }
        for (int i = 2; i < numero; i++) {
            if (numero % i == 0) {
                return false;
            }
        }
        return true;
    }
}

