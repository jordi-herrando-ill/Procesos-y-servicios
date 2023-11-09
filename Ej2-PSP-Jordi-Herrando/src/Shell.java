import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Clase principal que representa el shell.
 */
public class Shell {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                System.out.print("> ");
                String input = reader.readLine();

                if (input.equals("exit")) {
                    System.out.println("Saliendo del shell.");
                    break;
                } else if (input.equals("last-command")) {
                    System.out.println("No hay comandos ejecutados todavía.");
                } else {
                    Command command;
                    if (input.contains(">")) {
                        String[] parts = input.split("\\s*>\\s*");
                        command = new Command(new String[]{parts[0]}, parts[1]);
                    } else {
                        command = new Command(input);
                    }

                    String output = command.execute();
                    System.out.println(output);
                }
            }
        } catch (IOException e) {
            System.out.println("Error de entrada/salida: " + e.getMessage());
        }
    }
}