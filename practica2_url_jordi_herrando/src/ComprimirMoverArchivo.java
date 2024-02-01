import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ComprimirMoverArchivo {

    public static void main(String[] args) {
        try {
            String rutaOrigen = obtenerRutaDelUsuario();
            String rutaDestino = obtenerRutaDelUsuario();

            File archivoZip = comprimir(rutaOrigen);

            if (archivoZip != null) {
                moverArchivo(archivoZip, rutaDestino);
                System.out.println("Archivo comprimido y movido con éxito a: " + rutaDestino);
            } else {
                System.out.println("Error al comprimir la carpeta/fichero.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static File comprimir(String rutaOrigen) throws Exception {
        File carpetaOrigen = new File(rutaOrigen);

        if (carpetaOrigen.exists()) {
            File archivoZip;

            if (carpetaOrigen.isDirectory()) {
                archivoZip = new File(carpetaOrigen.getParentFile(), carpetaOrigen.getName() + ".zip");
            } else {
                archivoZip = new File(carpetaOrigen.getParentFile(), carpetaOrigen.getName() + "_file.zip");
            }

            try (FileOutputStream fos = new FileOutputStream(archivoZip);
                 ZipOutputStream zos = new ZipOutputStream(fos)) {

                if (carpetaOrigen.isDirectory()) {
                    comprimirCarpeta(carpetaOrigen, zos, "");
                } else {
                    comprimirArchivo(carpetaOrigen, zos, "");
                }
            }

            return archivoZip;
        } else {
            System.out.println("La carpeta/fichero de origen no existe.");
            return null;
        }
    }

    private static void comprimirArchivo(File archivo, ZipOutputStream zos, String rutaBase) throws Exception {
        ZipEntry zipEntry = new ZipEntry(rutaBase + archivo.getName());
        zos.putNextEntry(zipEntry);

        try (FileInputStream fis = new FileInputStream(archivo)) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = fis.read(buffer)) > 0) {
                zos.write(buffer, 0, length);
            }
        }

        zos.closeEntry();
    }


    private static void comprimirCarpeta(File carpeta, ZipOutputStream zos, String rutaBase) throws Exception {
        File[] archivos = carpeta.listFiles();

        if (archivos != null) {
            for (File archivo : archivos) {
                if (archivo.isDirectory()) {
                    comprimirCarpeta(archivo, zos, rutaBase + archivo.getName() + File.separator);
                } else {
                    ZipEntry zipEntry = new ZipEntry(rutaBase + archivo.getName());
                    zos.putNextEntry(zipEntry);

                    try (FileInputStream fis = new FileInputStream(archivo)) {
                        byte[] buffer = new byte[1024];
                        int length;
                        while ((length = fis.read(buffer)) > 0) {
                            zos.write(buffer, 0, length);
                        }
                    }

                    zos.closeEntry();
                }
            }
        }
    }

    private static void moverArchivo(File archivoOrigen, String rutaDestino) throws Exception {
        File carpetaDestino = new File(rutaDestino);

        if (!carpetaDestino.exists()) {
            carpetaDestino.mkdirs();
        }

        Files.move(archivoOrigen.toPath(), new File(carpetaDestino, archivoOrigen.getName()).toPath(), StandardCopyOption.REPLACE_EXISTING);
    }

    private static String obtenerRutaDelUsuario() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Por favor, ingresa la ruta: ");
        String ruta = scanner.nextLine();

        // Convertir la URL a la ruta de archivo si es necesario
        if (ruta.startsWith("file://")) {
            ruta = ruta.substring(7); // Eliminar "file://"
        }

        return ruta;
    }

}
