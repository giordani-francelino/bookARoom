package com.mycompany.bookaroom.bd;

import com.mycompany.bookaroom.cadastro.Campus;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.ArrayList;

public class GeradorBD {

    private String filename = "bookARoom.dat";

    public GeradorBD() {

    }

    public GeradorBD(String filename) {
        this.filename = filename;
    }

    public void save(List<Campus> campuss) {
        try {
            ObjectOutputStream file = new ObjectOutputStream(new FileOutputStream(filename));
            for (Campus campus : campuss) {
                if (campus != null) {
                    file.writeObject(campus);
                }
            }
            file.writeObject(null);
            file.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Campus> load() {
        List<Campus> campuss = new ArrayList<Campus>();
        try {
            Campus campus = null;
            ObjectInputStream file = new ObjectInputStream(new FileInputStream(filename));
            do {
                campus = (Campus) file.readObject();
                if (campus != null) {
                    campuss.add(campus);
                }
            } while (campus != null);
            file.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return campuss;
    }
}
