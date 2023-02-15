package creation.singleton;

import java.io.*;

enum EnumBasedSingleton {
    INSTANCE;

    EnumBasedSingleton() {
        value = 42;
    }

    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}

public class EnumBasedSingletonDemo {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String fileName = "enumSingleton.bin";

//        EnumBasedSingleton singleton = EnumBasedSingleton.INSTANCE;
//        singleton.setValue(111);
//        saveToFile(singleton, fileName);

        EnumBasedSingleton singleton2 = readFromFile(fileName);
        // value is 111 because the value is set above on line 29
        // but if you just read from the file and comment out lines 28 - 30, then the original value is still set to 42
        // this proves that serialization of the enum does not include the value of properties
        System.out.println(singleton2.getValue());

    }

    static void saveToFile(EnumBasedSingleton singleton, String filename) throws IOException {
        try(FileOutputStream fileOut = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(singleton);
        }
    }

    static EnumBasedSingleton readFromFile(String filename) throws IOException, ClassNotFoundException {
        try(FileInputStream fileIn = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(fileIn)) {
            return (EnumBasedSingleton) in.readObject();
        }
    }
}
