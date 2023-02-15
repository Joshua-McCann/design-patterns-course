package creation.singleton;

class InnerStaticSingleton {
    private InnerStaticSingleton() {}

    // creating a secondary class makes all of its stuff only available to the outside class, which is thread safe
    private static class Impl {
        private static final InnerStaticSingleton INSTANCE = new InnerStaticSingleton();
    }

    public InnerStaticSingleton getInstance() {
        return Impl.INSTANCE;
    }
}

public class InnerStaticSingletonDemo {
}
