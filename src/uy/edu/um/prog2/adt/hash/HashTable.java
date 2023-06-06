package uy.edu.um.prog2.adt.hash;

public class HashTable<K, V> implements IHashTable<K, V> {

    // El atributo table es un array estatico donde guardamos todos los elementos, y la capacity es el largo del array.
    private HashMapNode<K, V>[] table;
    private int capacity = 2;

    // Modificando HASH_FUNCTION podemos elegir alguna funcion hash especifica a nuestro tipo de datos.
    // Modificando COLLISION_FUNCTION podemos elegir alguna function de probe especifica.
    // Faltan las funciones para modificar estas variables.
    private String HASH_FUNCTION =  "default";
    private String COLLISION_FUNCTION = "linear";

    // Constructor usual, si usamos este la capacity inicia en 2.
    public HashTable() {
        this.table = new HashMapNode[this.capacity];
    }

    // Constructor especifico, se usa internamente en el resize.
    public HashTable(int varCapacity) {
        this.table = new HashMapNode[varCapacity];
        this.capacity = varCapacity;
    }

    // Function para conseguir el hash de la clave.
    public int getHash(K key) {
        if (this.HASH_FUNCTION == "default") {
            return key.hashCode() % this.capacity;
        } else {
            return key.hashCode() % this.capacity;
        }
    }

    // Falta implementar el load factor.
    @Override
    public void put(K key, V value) {
        // Conseguimos el hash de la clave, que corresponde a la posicion, y creamos el nodo a ingresar.
        int position = getHash(key);
        HashMapNode<K, V> node = new HashMapNode<>(key, value);

        // Si no hay nada en la posicion donde queremos ingresar el nodo, simplemente lo ingresamos.
        if (this.table[position] == null) {
            this.table[position] = node;
        } else {
            // Mientras la posicion donde queremos ingresarlo no este vacia, se ejecuta el loop.
            do {

                // Si vemos que el elemento en la posicion ocupada habia un nodo eliminado logicamente, lo sobreescribimos.
                if (this.table[position].isDeleted()) {
                    this.table[position] = node;
                    break;
                }

                // Usamos la funcion de dispersion para buscar la siguiente posible posicion.
                position = probe(position);

                // Si vemos que la nueva posicion excede el tamano del hash, lo resizeamos, rehasheando cada elemento,
                //  actualizamos la capacity de nuestra tabla, y reinsertamos el elemento recursivamente.
                if (this.table.length <= position) {
                    this.table = this.resize();
                    this.capacity = this.table.length;
                    this.put(key, value);
                    break;
                }

                // Si vemos que en la nueva posicion no hay nada, simplemente insertamos el elemento.
                if (this.table[position] == null) {
                    this.table[position] = node;
                    break;
                }

            } while (this.table[position] != null);
            // Al salir del loop termina la funcion.

        }
    }

    @Override
    public boolean contains(K key) {
        // Conseguimos el hash de la clave, que corresponde a la posicion.
        int position = getHash(key);

        // Mientras la posicion no sea mayor al largo de la tabla, ejecutamos el loop.
        while (this.table.length > position) {

            // Si no hay nada en la posicion, significa que el elemento no esta en el hash, y devolvemos false.
            if (this.table[position] == null) {
                return false;
            }

            // Si la clave del elemento buscado es igual a la clave del elemento de la posicion, y este no esta borrado,
            //  significa que lo encontramos y devolvemos true.
            if (this.table[position].getKey().equals(key) && !this.table[position].isDeleted()) {
                return true;
            }

            // Si no lo encontramos en esta iteracion, probeamos a la siguiente posicion y volvemos al inicio del loop.
            position = probe(position);
        }

        // No se bien si este return se va a ejecutar alguna vez.
        return false;
    }

    // No esta demasiado testeado esto.
    @Override
    public void remove(K key) {
        // Conseguimos el hash de la clave, que corresponde a la posicion.
        int position = this.find(key);

        // Este if es por la implementacion del find, que no es muy buena.
        // Basicamente dice que si existe, lo elimina logicamente, y si no, escribe un mensaje por la salida estandar.
        if (position != -1) {
            this.table[position].delete();
        } else {
            System.out.println("No element with that key was found.");
        }
    }

    // Retorna -1 si no existe
    public int find(K key) {
        // Primero chequeamos si la clave esta en el hash, y de lo contrario, retorna -1.
        if (this.contains(key)) {

            // Conseguimos el hash de la clave, que corresponde a la posicion.
            int position = getHash(key);

            // Mientras la posicion no sea mayor al largo de la tabla, ejecutamos el loop.
            while (this.table.length > position) {

                // Si la clave del elemento buscado es igual a la clave del elemento de la posicion, y este no esta borrado,
                //  significa que lo encontramos, entonces breakeamos y lo devolvemos abajo.
                if (this.table[position].getKey().equals(key) && !this.table[position].isDeleted()) {
                    break;
                }

                // Si no lo encontramos en esta iteracion, probeamos a la siguiente posicion y volvemos al inicio del loop.
                position = probe(position);
            }
            return position;
        } else {
            return -1;
        }
    }

    @Override
    public V get(K key) {
        // Primero chequeamos si la clave esta en el hash, y de lo contrario, retorna null.
        if (this.contains(key)) {

            // Conseguimos el hash de la clave, que corresponde a la posicion.
            int position = getHash(key);

            // Mientras la posicion no sea mayor al largo de la tabla, ejecutamos el loop.
            while (this.table.length > position) {

                // Si la clave del elemento buscado es igual a la clave del elemento de la posicion, y este no esta borrado,
                //  significa que lo encontramos, entonces breakeamos y lo devolvemos abajo.
                if (this.table[position].getKey().equals(key) && !this.table[position].isDeleted()) {
                    break;
                }

                // Si no lo encontramos en esta iteracion, probeamos a la siguiente posicion y volvemos al inicio del loop.
                position = probe(position);
            }
            return table[position].getValue();
        } else {
            return null;
        }
    }

    // Ni ganas de comentar esto, pero crea un nuevo HashTable con el doble de capacidad, luego loopea
    //  el HashTable actual y va rehasheando cada elemento y posicionandolo en el nuevo.
    public HashMapNode[] resize() {
        HashTable<K, V> newHashTable = new HashTable<>(this.capacity * 2);
        for (HashMapNode<K, V> node: this.table) {
            if (node != null) {
                newHashTable.put(node.getKey(), node.getValue());
            }
        }
        return newHashTable.table;
    }

    // La funcion que se usa para encontrar nuevas posiciones en caso de colision.
    public int probe(int position) {
        if (this.COLLISION_FUNCTION == "linear") {
            return position + 1;
        } else {
            return position + 1;
        }
    }
}
