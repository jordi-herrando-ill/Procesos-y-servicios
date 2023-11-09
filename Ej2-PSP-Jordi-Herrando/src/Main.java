import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Clase que representa un comando.
 */
class Command {
    private String[] arguments;
    private String outputFile;

    /**
     * Constructor para un comando con redirección de salida.
     *
     * @param arguments  Array de argumentos del comando.
     * @param outputFile Nombre del archivo donde se redirige la salida estándar.
     */
    public Command(String[] arguments, String outputFile) {
        this.arguments = arguments;
        this.outputFile = outputFile;
    }

    /**
     * Constructor para un comando sin redirección de salida.
     *
     * @param commandString Cadena con todos los argumentos concatenados.
     */
    public Command(String commandString) {
        this.arguments = commandString.split("\\s+");
        this.outputFile = "";
    }

    /**
     * Devuelve información del proceso.
     *
     * @return Información del proceso.
     */
    @Override
    public String toString() {
        String info = "Comando: " + String.join(" ", arguments) + "\nNúmero de parámetros: " + arguments.length;

        if (outputFile.isEmpty()) {
            info += "\nSalida redirigida a consola";
        } else {
            info += "\nRedirección de salida a: " + outputFile;
        }

        return info;
    }

    /**
     * Ejecuta el comando y devuelve la salida estándar del proceso.
     *
     * @return Salida estándar del proceso.
     */
    public String execute() {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(arguments);
            Process process = processBuilder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder output = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            process.waitFor(); // Espera a que el proceso termine
            int exitValue = process.exitValue();

            if (outputFile.isEmpty()) {
                System.out.println("PID: " + process.pid());
                System.out.println("Salida estándar:\n" + output.toString());
                System.out.println("Comando finalizado con código de salida: " + exitValue);
            }

            return output.toString();
        } catch (IOException | InterruptedException e) {
            return "Error al ejecutar el comando: " + e.getMessage();
        }
    }
}


