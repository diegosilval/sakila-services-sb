= Ejemplo de Spring Boot =

== Compilación desde la línea de comandos ==
Para poder construir la aplicación desde la línea de comandos, bastará con ejecutar el comando

`mvn clean package`

Esto compilará el proyecto, ejecutará las pruebas y si todo está correcto, el resultado será un archivo .jar en la carpeta `target`

== Ejecución desde la línea de comandos ==

Para ejecutar la aplicación construida, se ejecuta el siguiente comando desde la línea de comandos, asumiendo que nos encontramos en la carpeta del proyecto

`java -jar target\sakila-services-sb.jar`

== Ejecución de perfiles ==
La configuración de cada perfil está definido como subfijo del archivo `application.properties`. Por omisión, siempre tomará el contenido del archivo `application.properties`.
En este ejemplo se ha creado el perfil `dbcp2`. Para ejecutar ese perfil, deberá indicarse el siguiente comando. 

`java -Dspring.profiles.active=dbcp2 -jar target\sakila-services-sb.jar`

Un perfil tomará la configuración definida en `application.properties` y luego sobreescribirá y combinará los valores del archivo del perfil, en este caso de `application-dbcp2.properties`
