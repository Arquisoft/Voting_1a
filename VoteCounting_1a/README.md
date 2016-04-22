# Vote Counting & Publishing system

Vote Counting

[![Build Status](https://travis-ci.org/Arquisoft/VoteCounting_1a.svg?branch=master)](https://travis-ci.org/Arquisoft/VoteCounting_1a)
[![Codacy Badge](https://api.codacy.com/project/badge/grade/e97dbcfca9444506b436ed3afa5a82e9)](https://www.codacy.com/app/jelabra/VoteCounting_1a)
[![codecov.io](https://codecov.io/github/Arquisoft/VoteCounting_1a/coverage.svg?branch=master)](https://codecov.io/github/Arquisoft/VoteCounting_1a?branch=master)


# Authors

* Alejandro Rodríguez
* Carlos Lubián

# Guía de uso

* En el proyecto se han usado dos librerías que no están disponibles en los repositorios de Maven. Una de ellas, sqljdbc42.jar está desarrollada por Microsoft para interactuar con bases de datos SQL Server. La otra, jcorlib.jar, está hecha por uno de los miembros del equipo, Carlos Lubián, específicamente para este proyecto, proporcionando una implementación mejorada de los mapas de Java. Para compilar el proyecto con Maven, es necesario añadir dichas librerías (que están incluidas en la carpeta 'lib') al repositorio local, con estas instrucciones:
 * mvn install:install-file -DgroupId=com.microsoft.sqlserver.jdbc -DartifactId=sqljdbc42 -Dversion=6.0 -Dpackaging=jar -DgeneratePom=true -Dfile=sqljdbc42.jar (ruta hasta el fichero)
 * mvn install:install-file -DgroupId=jcorlib -DartifactId=jcorlib -Dversion=1.0 -Dpackaging=jar -DgeneratePom=true -Dfile=jcorlib.jar (ruta hasta el fichero)
* Debido al uso de estas librerías externas, Travis no es capaz de construir el proyecto, ya que no dispone de ellas.

# Funcionamiento

* Iniciar la base de datos local, ejecutando el fichero 'startup.bat' que se encuentra en la carpeta 'data' del proyecto
* Abrir la aplicación de escritorio de Java y pulsar el botón 'Iniciar recuento', que pone en marcha el escrutinio de votos
* Ir a la página http://aswneo.azurewebsites.net para ver los resultados de la votación

# Observaciones

* La página web donde se pueden ver los resultados no está integrada en el proyecto principal, sino que se relaciona con él a través de una base de datos SQL Server que actúa como intermediaria. Una consecuencia de esto es que desde el proyecto no es posible llevar a cabo pruebas web
* Como funcionalidad adicional, también es posible recibir los resultados de la votación mediante Telegram. El funcionamiento se basa en un bot hecho por nosotros, al cual se accede del siguiente modo:
  * Descargar e instalar Telegram
  * Buscar al bot por su nombre: @asw_bot
  * Iniciar una conversación con él
  * Preguntarle: ASW, ¿Cómo van las elecciones?
