package com.example.ejercicio1examenpspjordiherrando;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        URLListObservable urlList = new URLListObservable();
        DownloaderAndZipper downloaderAndZipper = new DownloaderAndZipper();
        urlList.addListener(downloaderAndZipper);

        System.out.println("Introduce las URLs (vacío para comenzar la descarga y compresión):");
        String url;
        while (!(url = scanner.nextLine()).isEmpty()) {
            urlList.addUrl(url);
        }
        urlList.addUrl(""); // Agregamos una URL vacía para comenzar la descarga y compresión
    }
}
