import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;

import java.util.Scanner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

public class DescargarMostrarPaginaWeb {

    public static void main(String[] args) {
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
            try {
                String url = obtenerURLDelUsuario();
                String contenido = descargarPaginaWeb(url);
                mostrarContenido(contenido);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        });

        future.join(); // Espera a que la tarea se complete
    }

    private static String descargarPaginaWeb(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        try (InputStream inputStream = connection.getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {

            StringBuilder content = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                content.append(line);
            }

            return content.toString();
        } finally {
            connection.disconnect();
        }
    }

    private static void mostrarContenido(String contenido) {
        System.out.println("Contenido de la página web:\n" + contenido);
    }

    private static String obtenerURLDelUsuario() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Por favor, ingresa la URL: ");
        return scanner.nextLine();
    }
}

