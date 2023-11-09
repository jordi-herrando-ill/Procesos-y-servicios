import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Número de corredores: ");
        int numCorredores = scanner.nextInt();

        Corredor[] corredores = new Corredor[numCorredores];

        // Crear corredores con sus datos
        for (int i = 0; i < numCorredores; i++) {
            System.out.println("\nCorredor " + (i + 1));
            System.out.print("Nombre: ");
            String nombre = scanner.next();
            System.out.print("Símbolo: ");
            char simbolo = scanner.next().charAt(0);
            System.out.print("Velocidad base (1-5): ");
            int velocidadBase = scanner.nextInt();
            System.out.print("Turbo (1-5): ");
            int turbo = scanner.nextInt();
            System.out.print("Posibilidad de tropezar (1-5): ");
            int posibilidadTropezar = scanner.nextInt();

            // Verificar que la suma de las variables no exceda 10
            if (velocidadBase + turbo + posibilidadTropezar > 10) {
                System.out.println("Error: La suma de velocidad base, turbo y posibilidad de tropezar no debe exceder 10.");
                return;
            }

            corredores[i] = new Corredor(nombre, simbolo, velocidadBase, turbo, posibilidadTropezar);
        }

        // Iniciar la simulación
        for (Corredor corredor : corredores) {
            new Thread(() -> {
                while (corredor.avanzar() && corredor.tropezar()) {
                }
            }).start();
        }

        // Esperar a que todos los corredores lleguen a la meta
        boolean todosLlegaron = false;
        while (!todosLlegaron) {
            todosLlegaron = true;
            for (Corredor corredor : corredores) {
                if (corredor.getPosicion() < 100) {
                    todosLlegaron = false;
                    break;
                }
            }
        }

        // Encontrar y mostrar al vencedor
        Corredor vencedor = encontrarVencedor(corredores);
        System.out.println("\n¡" + vencedor.getNombre() + " es el vencedor!");
    }

    private static Corredor encontrarVencedor(Corredor[] corredores) {
        Corredor vencedor = corredores[0];
        for (Corredor corredor : corredores) {
            if (corredor.getPosicion() > vencedor.getPosicion()) {
                vencedor = corredor;
            }
        }
        return vencedor;
    }
}
