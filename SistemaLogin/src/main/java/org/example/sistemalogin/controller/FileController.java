package org.example.sistemalogin.controller;

import javafx.collections.ObservableList;
import org.example.sistemalogin.model.Worker;

import java.io.*;

public class FileController {

    protected void importWorkers(ObservableList<Worker> workers) {
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

    protected void exportWorkers(ObservableList<Worker> workers) {
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

    protected void importRegisters(ObservableList<String> registers) {
        File file = new File("src/main/java/org/example/sistemalogin/ressources/horas.txt");
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                registers.add(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: no se ha podido encontrar el archivo.");
        } catch (IOException e) {
            System.out.println("Error genérico: no se ha podido leer el archivo.");
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                System.out.println("Error: no se ha podido cerrar el flujo de entrada");
            }
        }
    }

}
