# Obligatorio P2 2023 - Anselmo, Carrasco
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
- Hashtag mas usado para un dia dado:
Todo tuyo Guada.
- Top 7 cuentas con más favoritos:
En esencia el mismo funcionamiento que Top 15 usuarios con mas tweets, solo que en vez de ingresarlos al heap por el largo de su lista de tweets, los ingresamos por la cantidad de favoritos.
- Cantidad de tweets con una palabra o frase específicos:
Basicamente, se recorre todo el hash con todos los tweets, nos fijamos si cada tweet contiene a la palabra o frase especifico en su contenido, y de ser asi, incrementamos un counter, que luego imprimimos.

## Medicion de eficiencia de la aplicacion
### Cantidad de memoria ram consumida
Despues vemos

### Tiempo de ejecucion
tuyisimo guada