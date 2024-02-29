Aplicación de Descarga y Compresión de Archivos desde URLs

Esta aplicación Java permite al usuario ingresar URLs desde las cuales descargar archivos. Una vez que el usuario proporciona una URL en blanco, la aplicación descarga los archivos desde las URLs proporcionadas y los comprime en un archivo ZIP.

Requisitos

    Java Development Kit (JDK) 8 o superior
    Conexión a Internet

Instrucciones de Uso

Clone o descargue el repositorio de la aplicación.


git clone https://github.com/tu_usuario/tu_repositorio.git

Navegue hasta el directorio de la aplicación.


cd tu_repositorio

Compile los archivos de código fuente.


javac *.java

Ejecute la aplicación.


    java Main

    Siga las instrucciones en la consola para ingresar las URLs de los archivos que desea descargar. Deje la entrada en blanco para comenzar la descarga y compresión de los archivos.

Características

    Descarga de archivos desde URLs de Internet.
    Compresión de archivos descargados en un archivo ZIP.
    Interfaz de línea de comandos fácil de usar.
    Utiliza Futures de Java para la descarga y compresión asíncronas de archivos.

Notas

    Asegúrese de tener una conexión a Internet estable para descargar los archivos correctamente.
    El proceso de descarga y compresión puede tardar según el tamaño de los archivos y la velocidad de conexión a Internet.
