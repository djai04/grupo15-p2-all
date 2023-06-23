package uy.edu.um.prog2.entities;

import uy.edu.um.prog2.adt.hash.HashTable;
import uy.edu.um.prog2.adt.linkedlist.LinkedList;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLOutput;
import java.util.Scanner;

import static java.lang.Thread.sleep;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        String menuOption;
        boolean isDataLoaded = false;
        HashTable<String, User> userList = null;
        HashTable<Long, Tweet> tweetList = null;
        HashTable<Long, Driver> driverList = null;

        System.out.println("Bienvenido al sistema de consultas de tweets!");
        do {
            System.out.println("1. Cargar datos.");
            System.out.println("2. Realizar consultas.");
            System.out.println("3. Finalizar programa.");
            Scanner scanner = new Scanner(System.in);
            System.out.println("Ingrese la opcion que desea ejecutar: ");
            menuOption = scanner.nextLine();

            switch (menuOption) {
                case "1":
                    if (!isDataLoaded) {
                        System.out.println("Cargando datos...");
                        LoadCSV data = new LoadCSV();
                        userList = new HashTable<>(1000000);
                        tweetList = new HashTable<>(1000000);
                        driverList = new HashTable<>(50);
                        LoadCSV.loadDataIntoList(userList, tweetList, driverList);
                        isDataLoaded = true;
                        System.out.println("Datos cargados con exito!");
                        sleep(1000);
                    } else {
                        System.out.println("Los datos ya estan cargados.");
                    }
                    break;

                case "2":
                    System.out.println("1. Diez pilotos activos mas mencionados en los tweets en un mes.");
                    System.out.println("2. Quince usuarios con mas tweets.");
                    System.out.println("3. Cantidad de hashtags distintos para un dia dado.");
                    System.out.println("4. Hashtag mas usado para un dia dado.");
                    System.out.println("5. Siete cuentas con mas favoritos.");
                    System.out.println("6. Cantidad de tweets con una palabra o frase especifico.");

                    Scanner scanner2 = new Scanner(System.in);
                    System.out.println("Ingrese la opcion que desea ejecutar: ");
                    String queryOption = scanner2.nextLine();

                    switch (queryOption) {
                        case "1":
                            if (isDataLoaded) {
                                Queries.diezPilotosMasMencionados(driverList);
                            } else {
                                System.out.println("Los datos no fueron cargados aun.");
                            }
                            break;

                        case "2":
                            if (isDataLoaded) {
                                Queries.quinceUsuariosConMasTweets(userList);
                            } else {
                                System.out.println("Los datos no fueron cargados aun.");
                            }
                            break;

                        case "3":
                            if (isDataLoaded) {
                                Queries.cantidadDeHashtagsDistintosParaUnDiaDado(tweetList);
                            } else {
                                System.out.println("Los datos no fueron cargados aun.");
                            }
                            break;

                        case "4":
                            if (isDataLoaded) {
                                Queries.hashtagMasUsadoParaUnDiaDado(tweetList);
                            } else {
                                System.out.println("Los datos no fueron cargados aun.");
                            }
                            break;

                        case "5":
                            if (isDataLoaded) {
                                Queries.sieteCuentasConMasFavoritos(userList);
                            } else {
                                System.out.println("Los datos no fueron cargados aun.");
                            }
                            break;

                        case "6":
                            if (isDataLoaded) {
                                Queries.encontrarTweetSegunPalabra(tweetList);
                            } else {
                                System.out.println("Los datos no fueron cargados aun.");
                            }
                            break;

                        default:
                            System.out.println("Debe ingresar 1, 2, 3, 4, 5 o 6.");
                    }
                    break;

                default:
                    System.out.println("Debe ingresar 1 o 2.");
            }
        } while (menuOption != "3");

    }
}
