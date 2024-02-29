package com.example.ejercicio1examenpspjordiherrando;

import java.net.URL;
import java.util.*;
import java.util.concurrent.*;
import java.io.*;
import java.nio.file.*;
import java.util.zip.*;

// Clase para representar la URL junto con la cadena aleatoria
class URLWithRandomString {
    private String url;
    private String randomString;

    public URLWithRandomString(String url, String randomString) {
        this.url = url;
        this.randomString = randomString;
    }

    public String getUrl() {
        return url;
    }

    public String getRandomString() {
        return randomString;
    }
}

// Clase Observable que almacena las URLs junto con las cadenas aleatorias y notifica a los observadores
class URLListObservable {
    private List<URLWithRandomString> urlList = new ArrayList<>();
    private List<URLListListener> listeners = new ArrayList<>();

    public void addUrl(String url) {
        if (url.isEmpty()) {
            System.out.println("Se va a proceder a descargar y comprimir los ficheros");
            downloadAndCompressFiles();
        } else {
            String randomString = generateRandomString(20);
            URLWithRandomString urlWithRandomString = new URLWithRandomString(url, randomString);
            urlList.add(urlWithRandomString);
            notifyListeners(urlWithRandomString);
        }
    }

    public void addListener(URLListListener listener) {
        listeners.add(listener);
    }

    private void notifyListeners(URLWithRandomString urlWithRandomString) {
        for (URLListListener listener : listeners) {
            listener.onURLAdded(urlWithRandomString);
        }
    }

    private String generateRandomString(int length) {
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder randomString = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            randomString.append(characters.charAt(random.nextInt(characters.length())));
        }
        return randomString.toString();
    }

    private void downloadAndCompressFiles() {
        // Descargar archivos
        CountDownLatch latch = new CountDownLatch(urlList.size());
        ExecutorService executorService = Executors.newFixedThreadPool(urlList.size());
        for (URLWithRandomString urlWithRandomString : urlList) {
            executorService.submit(() -> {
                try {
                    // Descargar el contenido desde la URL
                    URL url = new URL(urlWithRandomString.getUrl());
                    InputStream in = url.openStream();

                    // Crear el archivo local para escribir el contenido descargado
                    String filename = urlWithRandomString.getRandomString();
                    OutputStream out = new FileOutputStream(filename);

                    // Escribir el contenido descargado en el archivo local
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = in.read(buffer)) != -1) {
                        out.write(buffer, 0, bytesRead);
                    }

                    // Cerrar streams
                    in.close();
                    out.close();

                    // Incrementar el contador de descargas completadas
                    latch.countDown();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }

        // Esperar a que todas las descargas finalicen
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Comprimir archivos en un archivo ZIP
        System.out.println("Compresión de archivos...");
        try {
            // Crear archivo ZIP
            FileOutputStream fos = new FileOutputStream("archivos.zip");
            ZipOutputStream zipOut = new ZipOutputStream(fos);

            // Agregar cada archivo descargado al archivo ZIP
            for (URLWithRandomString urlWithRandomString : urlList) {
                File file = new File(urlWithRandomString.getRandomString());
                zipOut.putNextEntry(new ZipEntry(file.getName()));

                // Leer el contenido del archivo descargado y escribirlo en el archivo ZIP
                byte[] bytes = Files.readAllBytes(Paths.get(urlWithRandomString.getRandomString()));
                zipOut.write(bytes, 0, bytes.length);

                // Cerrar la entrada del archivo
                zipOut.closeEntry();
            }

            // Cerrar el archivo ZIP
            zipOut.close();

            // Eliminar los archivos descargados
            for (URLWithRandomString urlWithRandomString : urlList) {
                Files.deleteIfExists(Paths.get(urlWithRandomString.getRandomString()));
            }

            System.out.println("Archivos comprimidos en 'archivos.zip'.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Detener el executorService
        executorService.shutdown();
    }

}

// Interfaz para los observadores
interface URLListListener {
    void onURLAdded(URLWithRandomString urlWithRandomString);
}

// Clase que escucha el listado de URLs y muestra mensajes por consola
class DownloaderAndZipper implements URLListListener {
    @Override
    public void onURLAdded(URLWithRandomString urlWithRandomString) {
        System.out.println(urlWithRandomString.getUrl() + " encolado como " + urlWithRandomString.getRandomString());
    }
}




