readcdb
=======

Descripci�n
-----------

Este desarrollo permite recoger los n�meros de c�digos de barras y QR Code

se debe seleccionar una imagen y el sistema devuelve el c�digo asociada.

La imagen deberia ser en formato PNG. Las imagenes JPG no las reconoce bien. El sistema cuando se encuentra una imagen JPG la intenta convertir a PNG y la procesa como tal. Esto no es seguridad de que funciones porque ha podido perder los metadatos o no tenerlos.


Ejecutar
--------


java -jar readcdb.jar <fichero imagen>

p.e.

java -jar readcdb.jar cdb.png

C�digos soportados
------------------

| 1D product | 1D industrial | 2D
| ---------- | ------------- | --------------
| UPC-A      | Code 39       | QR Code
| UPC-E      | Code 93       | Data Matrix
| EAN-8      | Code 128      | Aztec (beta)
| EAN-13     | Codabar       | PDF 417 (beta)
|            | ITF           | MaxiCode
|            | RSS-14        |
|            | RSS-Expanded  |


Librerias utilizadas
--------------------

* ZXing
	* javase-3.3.1: JavaSE-specific client code
	* core-3.3.1: The core image decoding library, and test code


Otras observaciones
-------------------

QR code is trademarked by Denso Wave, inc. 