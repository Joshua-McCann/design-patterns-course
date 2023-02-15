package creation.prototype;

import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;

class Foo implements Serializable {
    public int stuff;
    public String whatever;

    public Foo(int stuff, String whatever) {
        this.stuff = stuff;
        this.whatever = whatever;
    }

    @Override
    public String toString() {
        return "Foo{" +
                "stuff=" + stuff +
                ", whatever='" + whatever + '\'' +
                '}';
    }
}



public class SerializableDemo {
    public static void main(String[] args) {
        Foo foo = new Foo(42, "life");
        Foo clone = SerializationUtils.roundtrip(foo);
        clone.stuff += 1;
        System.out.println(foo);
        System.out.println(clone);

    }
}
