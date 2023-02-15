package creation.singleton;

import java.util.HashMap;

enum Subsystem {
    PRIMARY,
    AUXILIARY,
    FALLBACK
}

class Printer {
    private Printer() {
        instanceCount++;
        System.out.println("A total of " + instanceCount + " instances created so far.");
    }

    private static HashMap<Subsystem, Printer> instances = new HashMap<>();
    private static int instanceCount = 0;

    public static Printer get(Subsystem subSys) {
        if (instances.containsKey(subSys)) {
            return instances.get(subSys);
        }

        Printer instance = new Printer();
        instances.put(subSys, instance);
        return instance;
    }
}

public class MultitonDemo {

    public static void main(String[] args) {
        Printer main = Printer.get(Subsystem.PRIMARY);
        Printer aux = Printer.get(Subsystem.AUXILIARY);
        Printer aux2 = Printer.get(Subsystem.AUXILIARY);

        System.out.println(main);
        System.out.println(aux);
        System.out.println(aux2);
    }
}
