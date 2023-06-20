package uy.edu.um.prog2.entities;

import uy.edu.um.prog2.adt.hash.HashTable;

public class Queries {
    public static void diezPilotosMasMencionados(HashTable<Long, Driver> allDrivers) {
        // Recorres allDrivers (usando getKeys)
        // Los vas poniendo en un MAXHeap con la length de su tweet list como clave y el driver entero como value
        // haces un loop de 10 iteraciones, vas poppeando y printeando el nombre
    }
}
