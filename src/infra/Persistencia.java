package infra;

import business.model.Usuarios;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Persistencia implements Serializable {

    private static FileOutputStream writeUsers;
    private static FileInputStream readUsers;

    private static ObjectInputStream readObj;
    private static ObjectOutputStream writeObj;

    public static HashMap<String, Usuarios> carregaUsuarios() throws InfraException {
        HashMap<String, Usuarios> u;
        try {
            readUsers = new FileInputStream("users.dat");
            readObj = new ObjectInputStream(readUsers);
            u = (HashMap<String, Usuarios>) readObj.readObject();
            readUsers.close();
            readObj.close();
            return u;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Persistencia.class.getName()).log(Level.SEVERE, null, ex);
            throw new InfraException("N�o foi possivel iniciar o sistema\nTente novamente mais tarde");
        } catch (IOException |ClassNotFoundException ex) {
            Logger.getLogger(Persistencia.class.getName()).log(Level.SEVERE, null, ex);
            throw new InfraException("NN�o foi possivel  iniciar o sistema\nTente novamente mais tarde");
        }
    }

    public static void salvaUsuarios(HashMap<String, Usuarios> u) throws InfraException {
        try{
            writeUsers = new FileOutputStream("users.dat");
            writeObj = new ObjectOutputStream(writeUsers);
            writeObj.writeObject(u);
            writeUsers.close();
            writeObj.close();
        }catch (FileNotFoundException ex){
            Logger.getLogger(Persistencia.class.getName()).log(Level.SEVERE, null, ex);
            throw new InfraException("Não foi possível completar o cadastro\nTente novamente mais tarde");
        }catch (IOException ex){
            Logger.getLogger(Persistencia.class.getName()).log(Level.SEVERE, null, ex);
            throw new InfraException("Não foi possível completar o cadastro\nTente novamente mais tarde");
        }
    }
}
