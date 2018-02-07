Delivery_Drone
=====================================================================================
Elaborado por: Andres Felipe Gutierrez Camacho
Identificado con CC: 1019011235

=====================================================================================
=============================Indicaciones Generales =================================
=====================================================================================

La aplicación se desarrollo sobre Eclipse Mars 2.0 usando JDK 7 y Maven 3.3.9
El Proyecto esta dividido en dos etapas, la primera consiste en solo un drone que
transporta almuerzos, con un limite de 3 almuerzos por viaje. La segunda etapa tiene
como objetivo ampliar el numero de drones y la capacidad de cada uno.

Para ejecutar las pruebas unitarias sin necesidad de importar el proyecto, es 
necesario instalar Maven 3.3.9 o superior. Desde la consola, dirigirse a la carpeta
del proyecto y ejecutar el comando "mvn test". Puede fallar las dos o tres primeras
veces ya que maven descarga las librerías necesarias y si no están listas genera
error de compilación.

De igual forma si se desea generar los archivos .jar del proyecto, es necesario 
dirigirse a la carpeta del proyecto y ejecutar el comando "mvn package". 
Después de terminar se genera una carpeta con el nombre Target donde se podrá 
encontrar el archivo deseado.

El enunciado tiene un error en el ejemplo que presenta, se dice que el drone gira 
90 grados a la derecha si se envía el comando "D" y 90 grados a la izquierda si se
envía el comando "I" sin embargo en la segunda ruta del ejemplo para que el 
resultado sea 3,3 sur, el drone no gira a la izquierda si no a la derecha. los 
movimientos correctos se encuentran el caso de prueba "sendExampleDeliveriesEtapa1".

=====================================================================================
============================== Indicaciones Etapa II ================================
=====================================================================================

Precondiciones: Java 1.7 o superior, verificar ejecutando por consola el 
comando "java -version".

Branch: develop_etapa2
Release: etapa1/1.0

Como ejecutarla: Después de ejecutar el comando "mvn package", dirigirse a la carpeta
target y ejecutar el siguiente comando 
"java -jar DeliveryDrone-2.0.1.jar <ruta carpeta con archivos>"

El sistema es parametrizable en el sentido en que se puede modificar la cantidad de 
drones, la cantidad de cuadras a la redonda en las que los drones viajan y la 
capacidad de estos, así como los mensajes de error que se muestran. Estas propiedades 
están dentro del jar que se genera en el archivo "deliveryDrone.properties". 

Al momento de ejecutar el sistema es necesario indicar la ruta de la carpera donde 
estan los archivos de entrada (in01.txt). Esta Carpeta puede tener n cantidad de 
archivos, uno para cada drone, si existen mas archivos que drones configurados en
la propiedad "NUMBER_OF_DRONES" el sistema enviar esta cantidad de drones y el resto
de los archivos no se procesaran.

Estos archivos pueden contener n cantidad de entregas, sin embargo los drones 
volverán al restaurante después de realizar la cantidad de entregas parametrizadas en 
el archivo antes mencionado, propiedad "NUMBER_OF_PACKAGES". Si una de las rutas no 
es valida debido a que no cumple con los tres comando básicos, ejemplo: "AANN", "aaid"; 
el sistema mostrara un mensaje en el archivo de salida en la posición de la ruta, propiedad 
"NOT_IMPLEMENTED_COMMAND_MESSAGE". Si la ruta a ejecutar del drone hace que éste se 
salga del mapa, el sistema moverá al drone hasta la posición previa a la salida del 
mapa y ejecuta la siguiente ruta desde esa posición siempre y cuando no sobrepase la 
capacidad del entregas, mensaje: propiedad "INVALID_MAP_DIMENSIONS_MESSAGE".