# IvairlosAirlines

Este proyecto Java surge como solución al Reto 5 del Ciclo 2 del programa MisionTIC2022 Colombia.

Programa para realizar reservaciones con persistencia sobre una base de datos involucrando varias entidades en el proceso.

# Base de Datos

El script SQL para la creación de la base de datos se encuentra en la carpeta _**utils**_, contiene la creacion de todas las tablas utilizadas y algunos registros
sobre las mismas, el nombre predeterminado para este esquema es "pruebasAerolinea".

Para la ejecución del código, la base de datos se conectó a través de WorkBench MySQL y es necesario editar la informacion del archivo _**db_credentials.json**_
ubicado en _**utils**_ (user, password); adicionalmente en la clase _**ConnectionDB**_ es necesario editar el nombre con el que finalmente se registro la base de datos.
