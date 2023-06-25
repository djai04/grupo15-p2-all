# Obligatorio P2 2023 - Anselmo, Carrasco
### Ejecucion del programa
Para ejecutar el programa, simplemente se debe correr el main.
## Decisiones tomadas
- Clase Driver: Una de las decisiones mas importantes que tomamos es la de crear una nueva clase donde guardamos los pilotos, que tiene dos atributos.
Uno es el nombre del piloto, y el otro es la lista de tweets que lo mencionan. Para conseguir este ultimo atributo correctamente, al realizar la carga de datos, vamos checkeando si cada tweet menciona al driver, y de ser asi, lo añadimos a la lista.
- Estructuras de datos: En la mayoria de los casos donde teniamos que guardar una gran cantidad de datos, elegimos usar Hashtable.
Esto se debe a que es O(1) en promedio al añadir y al buscar (y consiguientemente, el contains), por lo que resulta de mucha utilidad, ya que constantemente tenemos que estar viendo si un hash ya contiene o no un elemento.
Para las listas que tenian una menor cantidad de elementos, decidimos usar linked list.
  - Recorrer el Hash: Un gran  problema que enfrentamos, fue que al recorrer los hashes grandes, las funciones tomaban muchisimo tiempo. Luego de un poco de investigacion, averiguamos por que era asi.
  Inicialmente, para recorrer el hash, usamos el metodo getKeys, que devolvia una linked list con todas las keys del hash, y luego la recorriamos para ir accediendo a cada elemento del hash.
  El problema yacia en que el get en una linked list es de O(n), y en el caso de la lista de tweets lo estabamos haciendo para 600.000 elementos, lo que causaba que tuvieramos que recorrer 600.000! (factorial) nodos. Para solucionar este cuello de botella, decidimos implementar funciones que no devolvieran las keys en una linked list, si no en un array normal de Java. Pero, como en Java no se permiten instanciar arrays genericos, recurrimos a crear funciones para cada tipo de dato de las keys que nos hacian falta (Long, String).
- Minisculas (toLowerCase): Usamos la funcion toLowerCase en el caso del hashtag, esto porque en Twitter es exactamente lo mismo buscar segun un hashtag en mayuscula a un hashtag en minuscula.
Tambien la estabamos usando en la consulta 6, pero descubrimos que consumia una enorme cantidad de memoria, ya que instanciaba un nuevo objeto de tipo String por cada iteracion del recorrido de la lista de tweets, entonces lo descartamos.
- Claves: Tomamos el username de cada Tweet como unico, asi que esa es la clave de usuario.
En el caso de tweet, creamos una ID autoincrement.
En el caso de Hashtag, descartamos la opcion de hacer un ID autoincrement como lo planteaba el diagrama de clases inicial, y optamos por usar el nombre del hashtag (el tag) como la clave.

## Procesos de carga de datos
Los metodos para la carga de datos se encuentran en una clase llamada LoadCSV, bajo el paquete entities.
Para realizar la carga de datos, usamos la libreria de Apache Commons CSV, que instalamos el binario y luego instalamos, cada uno en nuestra computadora.
En la clase LoadCSV, se encuentra un metodo llamado loadDataIntoList, que toma 3 parametros:

- allUsers: Este es un Hashtable que toma como key un String (en nuestro caso el nombre de usuario) y como value un Usuario.
- allTweets: Este es un Hashtable que toma como key un Long (en nuestro caso el id del tweet) y como value un Tweet.
- allDrivers: Este es un Hashtable que toma como key un Long (en nuestro caso el id del piloto) y como value un Driver.

Los archivos para la carga de datos se encuentran bajo el directorio /dataset en la raiz del proyecto, pero los ignoramos en GitHub para no tener problema con los tamaños.
A raiz de esto, para ejecutar el programa correctamente, se debera crear dicho directorio e incluir alli los archivos drivers.txt y f1_dataset.csv (si se desea, tambien se puede incluir f1_dataset_test.csv.)

Lo primero que hacemos es usar las clases File y Scanner para recorrer el .txt con los pilotos, crear objetos de tipo Driver y añadirlos a allDrivers (dada la poca cantidad de pilotos, podria ser una linked list, pero para extensibilidad del programa a futuro lo dejamos como un hashtable).

Luego usamos Reader e Iterable de Apache para poder recorrer el CSV record por record dentro de un for.
Salteamos la primera fila para evitar ingresar los nombres de las columnas como parametros. Luego, usamos la funcion get de Record para agarrar todos los datos que nos interesan e inicializarlos en diferentes variables.
Los tweets los creamos, haciendo diversas validaciones para la fecha y los hashtags, y los añadimos a la lista de tweets del usuario correspondiente.
Al crear los usuarios, chequeamos que no exista ya. Si no existe, lo añadimos a allUsers, y si si existe, lo buscamos en allUsers y simplemente le añadimos el tweet. Los tweets siempre se añaden a allTweets porque todos deberian ser diferentes.
Ademas, el tweet tiene una lista de objetos de tipo Hashtag, que decidimos que su clave iba a ser el texto del hashtag (el tag), con el fin de que tenga mas sentido al realizar busquedas, a diferencia de una clave como un id generico.
Finalmente, vemos si en el tweet creado se menciona a alguno de los pilotos, y de ser asi, lo añadimos a la lista de tweets de dicho piloto.
Vale la pena mencionar que todo este proceso esta envuelto por un Try Catch, entonces cualquier tweet fallido no se ingresa (en las pruebas actuales, son 4).
El resto de las funciones de LoadCSV fueron usadas para pruebas.

## Realizacion de reportes
Se realizaron 6 reportes diferentes para el trabajo, cuya implementacion describiremos brevemente a continuacion:
- Listar los 10 pilotos activos en la temporada 2023 más mencionados en los tweets en un mes:
Primero, usando la clase Scanner y DateUtils (implementada por nosotros), pedimos por consola el mes y el año y lo validamos.
Luego, recorremos nuestro hash de drivers, agararamos la lista de tweets de cada uno, ahi filtramos segun la fecha del tweet (si se publico en el mes correspondiente), y finalmente lo añadimos a un heap con el driver como value y la cantidad de tweets en el mes como key.
Finalmente, realizamos 10 pops en el heap para conseguir los 10 pilotos mas mencionados en los tweets. Vale la pena mencionar que se ignoran pilotos con 0 menciones.
- Top 15 usuarios con más tweets:
Este reporte resulto relativamente simple, ya que solo se debio recorrer el hash con todos los usuarios, ingresarlos a un heap con el largo de su lista de tweets como clave, y luego poppear los primeros 15.
- Cantidad de hashtags distintos para un día dado: Primero, realizamos las validaciones de la fecha.
Continuamos creando un hash de tweets filtrados segun dicha fecha. A ese hash lo recorremos, y vamos ingresando los hashtags de cada tweet, si no se repiten, a otro hash. Finalmente, devolvemos el largo del hash final.
- Hashtag mas usado para un dia dado: Primero solicitamos una fecha en formato YYYY-MM-DD y validamos los valores. 
Luego, se recorren todos los tweets almacenados en un HashTable, seleccionando aquellos que coinciden con la fecha proporcionada.
Luego, obtenemos los hashtags de cada tweet filtrado, y las añadimos a un nuevo HashTable, llevando el rastro de cuantas veces se repite.
Finalmente, insertamos los hashtags y su cantidad de repeticiones en un MaxHeap para identificar el más usado. 
Devolvemos el mas usado sin contar f1.
Se imprime el tiempo de ejecución al final.
- Top 7 cuentas con más favoritos:
En esencia el mismo funcionamiento que Top 15 usuarios con mas tweets, solo que en vez de ingresarlos al heap por el largo de su lista de tweets, los ingresamos por la cantidad de favoritos.
- Cantidad de tweets con una palabra o frase específicos:
Basicamente, se recorre todo el hash con todos los tweets, nos fijamos si cada tweet contiene a la palabra o frase especifico en su contenido, y de ser asi, incrementamos un counter, que luego imprimimos.

## Medicion de eficiencia de la aplicacion
### Cantidad de memoria ram consumida
Despues vemos

### Tiempo de ejecucion
- Carga de datos: La primera carga de datos tomo 10015 millis. La segunda tomo 9729.
Promedia 9872 millis.
- Consulta numero 1: La primera corrida de la consulta con mes 08 y año 2021 tomo 9178 millis. 
La segunda corrida con mes 12 y año 2021 tomo 28495 millis. La tercera corrida con mes 07 y año 2022 tomo 13769 millis.
Promedia 17147 millis.
- Consulta numero 2: La primera corrida tomo 277 millis. La segunda corrida tomo 298 millis.
Promedia 287 millis.
- Consulta numero 3: La primera corrida con fecha 2022-07-03 tomo 45333 millis.
La segunda corrida con fecha 2021-12-12 tomo 28605 millis. La tercera corrida con fecha 2021-10-23 tomo 10559 millis.
Promedia 28165 millis.
- Consulta numero 4: La primera corrida con fecha 2021-12-12 tomo 10392 millis. 
La segunda corrida con fecha 2022-07-03 tomo 8280 millis. La tercera corrida con fecha 2021-10-23 tomo 7952 millis.
Promedia 8874 millis.
- Consulta numero 5: La primera corrida tomo 174 millis. La segunda corrida tomo 181 millis.
Promedia 177 millis.
- Consulta numero 6: La primera corrida con la palabra "god" tomo 3430 millis.
La segunda corrida con la frase "happy new year" tomo 6894 millis. La tercera corrida con la frase "Christmas" tomo 4051 millis.
Promedia 4791 millis.
