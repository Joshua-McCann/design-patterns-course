package creation.singleton;

class LazySingleton {
    private static LazySingleton instance;

    private LazySingleton() {
        System.out.println("initializaing");
    }

//    public static LazySingleton getInstance() {
//        if (instance == null) {
//            // not thread safe
//            instance = new LazySingleton();
//        }
//        return instance;
//    }

    // double-checked locking
    // this is outdated, so don't do it
    public static synchronized LazySingleton getInstance() {
        if (instance == null) {
            synchronized (LazySingleton.class) {
                if (instance == null) {
                    instance = new LazySingleton();
                }
            }
        }
        return instance;
    }
}

public class LazySingletonDemo {


}
