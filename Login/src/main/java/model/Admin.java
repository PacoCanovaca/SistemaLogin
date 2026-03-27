package model;

import java.util.ArrayList;
import java.util.Optional;

public class Admin extends User {

    public Admin(String name, String surname, String dni, String mail, String password) {
        super(name, surname, dni, mail, password);
    }

    public void registerWorker(ArrayList<Worker> workers, String name, String surname, String dni, String mail, String password) {
        workers.add(new Worker(name, surname, dni, mail, password));
    }

    public void registerAdmin(ArrayList<Admin> admins, String name, String surname, String dni, String mail, String password) {
        admins.add(new Admin(name, surname, dni, mail, password));
    }

    public void deleteWorker(ArrayList<Worker> workers, String dni) {
        for (int i = 0; i < workers.size(); i++) {
            Worker worker = workers.get(i);
            if (worker.getDni().equalsIgnoreCase(dni)) {
                System.out.println("Trabajador eliminado con éxito.");
                workers.remove(i);
                return;
            }
        }
        System.out.println("Trabajador no encontrado.");
    }

    public void deleteAdmin(ArrayList<Admin> admins, String dni) {
        for (int i = 0; i < admins.size(); i++) {
            Admin admin = admins.get(i);
            if (admin.getDni().equalsIgnoreCase(dni)) {
                System.out.println("Administrador eliminado con éxito.");
                admins.remove(i);
                return;
            }
        }
        System.out.println("Administrador no encontrado.");
    }

}
