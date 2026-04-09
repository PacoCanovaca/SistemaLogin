package org.example.sistemalogin.controller;

import javafx.collections.ObservableList;
import org.example.sistemalogin.model.Worker;

import java.io.*;

public class FileController {

    // Esta clase controla la importación y exportación de listas de usuarios (admins por un lado y trabajadores por otro) a los archivos .obj correspondientes

    public void importWorkers(ObservableList<Worker> workers) {
        File file = new File("src/main/java/org/example/sistemalogin/ressources/workers.obj");
        ObjectInputStream objectInputStream = null;

        try {
            objectInputStream = new ObjectInputStream(new FileInputStream(file));
            while (true) {
                try {
                    Worker worker = (Worker) objectInputStream.readObject();
                    workers.add(worker);
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: no se ha podido encontrar el archivo.");
        } catch (IOException e) {
            System.out.println("Error: no hay permiso de lectura en el archivo.");
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Error: la clase indicada no se ha encontrado.");
        } finally {
            try {
                objectInputStream.close();
            } catch (IOException e) {
                System.out.println("Error: no se ha podido cerrar el flujo de entrada.");
            }
        }
    }

    public void exportWorkers(ObservableList<Worker> workers) {
        File file = new File("src/main/java/org/example/sistemalogin/ressources/workers.obj");
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
            for (Worker worker : workers) {
                objectOutputStream.writeObject(worker);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: no se ha podido encontrar el archivo.");
        } catch (IOException e) {
            System.out.println("Error: no hay permiso de escritura en el archivo.");
        } finally {
            try {
                objectOutputStream.close();
            } catch (IOException e) {
                System.out.println("Error: no se ha podido cerrar el flujo de salida.");
            }
        }
    }

}
