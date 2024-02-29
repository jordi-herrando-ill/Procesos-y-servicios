Aplicación de Gestión de Descargas y Compresión de URLs

Esta es una aplicación simple en Java que permite a los usuarios ingresar una lista de URLs que desean descargar y comprimir. La aplicación genera una cadena aleatoria de 20 caracteres para cada URL ingresada y muestra un mensaje indicando que la URL ha sido encolada con éxito.
Requisitos del Sistema

    Java JDK 8 o superior instalado en el sistema.

Instrucciones de Uso

    Clonar el Repositorio: Clona este repositorio en tu máquina local utilizando el siguiente comando:


git clone https://github.com/tu_usuario/nombre_del_repositorio.git

Compilación del Código: Navega al directorio del proyecto y compila el código Java usando el siguiente comando:


javac Main.java

Ejecución del Programa: Una vez compilado, ejecuta el programa con el siguiente comando:


    java Main

    Introducir URLs: Cuando se le solicite, introduce las URLs que deseas descargar y comprimir. Introduce una URL por línea y presiona Enter después de cada una. Para iniciar la descarga y compresión, simplemente presiona Enter sin introducir ninguna URL.

    Visualización de la Salida: El programa mostrará mensajes en la consola indicando el estado de las URLs ingresadas y las acciones tomadas.

Estructura del Código Fuente

    Main.java: Contiene la clase principal del programa que maneja la entrada de usuario y coordina las operaciones.
    URLListObservable.java: Define la clase que almacena la lista de URLs y notifica a los observadores cuando se añade una URL nueva.
    URLWithRandomString.java: Clase para representar una URL junto con una cadena aleatoria de 20 caracteres.
    DownloaderAndZipper.java: Implementa el observador que escucha la lista de URLs y muestra mensajes por consola.
