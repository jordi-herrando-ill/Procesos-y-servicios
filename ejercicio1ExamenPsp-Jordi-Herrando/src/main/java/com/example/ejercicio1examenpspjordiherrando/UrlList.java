package com.example.ejercicio1examenpspjordiherrando;

import java.util.*;

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







