Random Number API

Esta es una API desarrollada en Java con el framework Spring Boot que permite trabajar con números aleatorios.
Endpoints
Obtener 100 Números Aleatorios


GET /random/numbers

Devuelve un listado de 100 números aleatorios.
Obtener un Número Aleatorio de N Dígitos


GET /random/number/{d}

Devuelve un número aleatorio que tendrá d dígitos, donde {d} es la cantidad de dígitos especificada en la URL.
Generar un Número Aleatorio


PUT /random/number

Recibe un número aleatorio (en formato JSON en el cuerpo de la solicitud) y devuelve otro número aleatorio con la misma cantidad de dígitos que el número recibido.

El JSON para cada número aleatorio es:

{
    "random": 12345
}

Ejecución

Para ejecutar la aplicación, sigue estos pasos:

    Clona o descarga este repositorio en tu máquina local.

    Asegúrate de tener instalado Java Development Kit (JDK) 8 o superior.

    Navega hasta el directorio del proyecto en tu terminal.

    Ejecuta el siguiente comando para compilar y ejecutar la aplicación:


./gradlew bootRun

O si estás utilizando Windows:

    gradlew bootRun

    Una vez que la aplicación esté en funcionamiento, puedes acceder a los endpoints mencionados anteriormente utilizando un cliente HTTP como cURL, Postman, o cualquier navegador web.

Dependencias

La aplicación utiliza las siguientes dependencias de Spring Boot:

    Spring Web
    Spring Boot DevTools
    Spring Boot Starter Test

Estas dependencias están especificadas en el archivo build.gradle del proyecto.
Notas

    Asegúrate de tener una conexión a Internet activa para ejecutar la API, ya que algunos endpoints pueden realizar solicitudes externas.
    Para fines de demostración, la generación de números aleatorios se basa en la clase java.util.Random de Java, que no es completamente aleatoria en un sentido criptográfico. Si se requiere una generación de números aleatorios más segura, se recomienda utilizar bibliotecas especializadas.
