package org.example.sistemalogin.model;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Worker extends User implements Serializable {

    private static final long serialVersionUID = 1L;

    public Worker(String name, String surname, String dni, String mail, String password) {
        super(name, surname, dni, mail, password);
    }

    public void register() {
        File file = new File("src/main/java/org/example/sistemalogin/ressources/horas.txt");
        PrintWriter printWriter = null;
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String currentTime = now.format(formatter);
        try {
            printWriter = new PrintWriter(new FileWriter(file, true));
            printWriter.println(String.format("%s %s - DNI: %s -> %s", super.getName(), super.getSurname(), super.getDni(), currentTime));
        } catch (IOException e) {
            System.out.println("Error en la escritura");
        } finally {
            try {
                printWriter.close();
            } catch (Exception e) {
                System.out.println("Error en el cerrado del escritor");
            }
        }
    }

    @Override
    public String toString() {
        return String.format("%s %s - DNI: %s", super.getName(), super.getSurname(), super.getDni());
    }
}
