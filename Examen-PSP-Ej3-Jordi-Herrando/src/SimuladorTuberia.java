import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SimuladorTuberia {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Se esperan dos comandos con parámetros.");
            System.exit(1);
        }

        String comando1 = args[0];
        String comando2 = args[1];

        try {
            // Ejecutamos el primer comando
            Process proceso1 = Runtime.getRuntime().exec(comando1);

            // Obtenemos la salida estándar del primer comando
            BufferedReader reader = new BufferedReader(new InputStreamReader(proceso1.getInputStream()));

            // Construimos el segundo comando con la salida del primero como entrada
            ProcessBuilder builder = new ProcessBuilder("bash", "-c", comando2);
            builder.redirectInput(ProcessBuilder.Redirect.to(ProcessBuilder.Redirect.INHERIT.file())); // Redirigir la entrada

            // Ejecutamos el segundo comando
            Process proceso2 = builder.start();

            // Esperamos a que ambos procesos terminen
            int exitCode1 = proceso1.waitFor();
            int exitCode2 = proceso2.waitFor();

            System.out.println("Proceso 1 terminado con código de salida: " + exitCode1);
            System.out.println("Proceso 2 terminado con código de salida: " + exitCode2);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
