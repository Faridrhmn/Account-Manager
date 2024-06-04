package id.seigan.dojo;

import java.io.*;

public class ObjectSaver implements Serializable {

    public static String fileName;


    public static void saveObject(Object object){
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(object);
            objectOutputStream.flush();

            objectOutputStream.close();
            fileOutputStream.close();
        } catch (FileNotFoundException e){
            throw new RuntimeException(e);
        } catch (IOException s){
            throw new RuntimeException(s);
        }
    }

    public static Object retrieveObject(){
        FileInputStream fileInputStream = null;
        Object object = null;
        try {
            fileInputStream = new FileInputStream(fileName);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            object = objectInputStream.readObject();

            fileInputStream.close();
            objectInputStream.close();
        }catch (FileNotFoundException e){
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return object;
    }

//    end of class
}
