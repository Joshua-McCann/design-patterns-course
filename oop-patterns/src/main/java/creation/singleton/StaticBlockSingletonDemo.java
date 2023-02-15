package creation.singleton;

import java.io.File;
import java.io.IOException;

class StaticBlockSingleton {
    private StaticBlockSingleton() throws IOException {
        System.out.println("Singleton is initializing");
        File.createTempFile(".", ".");
    }

    private static StaticBlockSingleton instance;


    // This static block will help to create a new instance when extra things need to be done.
    // This can be handy for object pooling.
    static {
        try {
            instance = new StaticBlockSingleton();
        } catch (IOException e) {
            System.out.println("Failed to create singleton");
        }
    }

    public static StaticBlockSingleton getInstance() {
        return instance;
    }
}

public class StaticBlockSingletonDemo {
}
