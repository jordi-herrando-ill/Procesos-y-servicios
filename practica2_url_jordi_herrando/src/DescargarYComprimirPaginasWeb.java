import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class DescargarYComprimirPaginasWeb {

    public static void main(String[] args) {
        List<String> urls = obtenerListaDeURLs();

        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
            try {
                List<String> contenidos = new ArrayList<>();

                for (String url : urls) {
                    String contenido = descargarPaginaWeb(url);
                    contenidos.add(contenido);
                }

                comprimirContenidos(contenidos);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        });

        future.join(); // Espera a que la tarea se complete
    }

    private static String descargarPaginaWeb(String url) throws IOException {
        HttpURLConnection connection = null;
        BufferedReader reader = null;

        try {
            URL urlObj = new URL(url);
            connection = (HttpURLConnection) urlObj.openConnection();

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder content = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    content.append(line).append("\n");
                }

                return content.toString();
            } else {
                throw new IOException("Error al conectar con la URL. Código de respuesta: " + connection.getResponseCode());
            }
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (connection != null) {
                connection.disconnect();
            }
        }
    }



    private static void comprimirContenidos(List<String> contenidos) throws IOException {
        try (FileOutputStream fos = new FileOutputStream("paginas_web.zip");
             ZipOutputStream zos = new ZipOutputStream(fos)) {

            for (int i = 0; i < contenidos.size(); i++) {
                ZipEntry zipEntry = new ZipEntry("pagina" + (i + 1) + ".html");
                zos.putNextEntry(zipEntry);
                zos.write(contenidos.get(i).getBytes());
                zos.closeEntry();
            }
        }

        System.out.println("Contenidos comprimidos con éxito en: paginas_web.zip");
    }

    private static List<String> obtenerListaDeURLs() {
        List<String> urls = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Por favor, ingresa 10 URLs:");

        for (int i = 0; i < 10; i++) {
            System.out.print("URL #" + (i + 1) + ": ");
            String url = scanner.nextLine();
            urls.add(url);
        }

        return urls;
    }
}
