package creation.singleton;

import java.io.*;

class BasicSingleton implements Serializable {
    private BasicSingleton(){}

    private static final BasicSingleton INSTANCE = new BasicSingleton();

    public static BasicSingleton getInstance() {
        return INSTANCE;
    }

    private int value = 0;

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    // this will help the jvm to understand anytime serialization should happen, then the same object should be used.
    protected Object readResolve() {
        return INSTANCE;
    }
}

public class BasicSingletonDemo {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        BasicSingleton singleton = BasicSingleton.getInstance();
        singleton.setValue(123);

        /* there are two basic problems with the singleton pattern:
        1. reflection
        2. serialization
        both will break the contract. */

        String filename = "singleton.bin";
        saveToFile(singleton, filename);

        BasicSingleton singleton2 = readFromFile(filename);
        System.out.println(singleton2 == singleton);
        singleton2.setValue(456);
        System.out.println(singleton.getValue());
        System.out.println(singleton2.getValue());
    }

    static void saveToFile(BasicSingleton singleton, String filename) throws IOException {
        try(FileOutputStream fileOut = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(singleton);
        }
    }

    static BasicSingleton readFromFile(String filename) throws IOException, ClassNotFoundException {
        try(FileInputStream fileIn = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(fileIn)) {
            return (BasicSingleton) in.readObject();
        }
    }

}
