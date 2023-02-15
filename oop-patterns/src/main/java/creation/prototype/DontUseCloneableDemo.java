package creation.prototype;

import java.util.Arrays;

class Address implements Cloneable {
    public String streetName;
    public int houseNumber;

    public Address(String streetName, int houseNumber) {
        this.streetName = streetName;
        this.houseNumber = houseNumber;
    }

    @Override
    public String toString() {
        return "Address{" +
                "streetName='" + streetName + '\'' +
                ", houseNumber=" + houseNumber +
                '}';
    }

    /* cloneable interface is not great because it expects a call to super.clone(), doesn't specify if it does a shallow
    or deep copy.  With immutable reference types and primitives, this is fine but with mutable data types this causes
    problems since the reference is pointing to the same object in both copies. */
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

class Person implements Cloneable {
    public String[] names;
    public Address address;

    public Person(String[] names, Address address) {
        this.names = names;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Person{" +
                "names=" + Arrays.toString(names) +
                ", address=" + address +
                '}';
    }

    // here we can manually deep copy the object, but it doesn't call super.clone() and doesn't meet the specification.
    @Override
    public Object clone() throws CloneNotSupportedException {
        // return super.clone() - this causes issues since the objects are not deep cloned as well.
        return new Person(names.clone(), (Address) address.clone());
    }
}

public class DontUseCloneableDemo {

    public static void main(String[] args) throws CloneNotSupportedException {
        Person john = new Person(new String[]{"John", "Smith"},
                new Address("London Road", 123));

        Person jane = (Person) john.clone();
        jane.names[0] = "Jane";
        jane.address.houseNumber = 456;

        System.out.println(john);
        System.out.println(jane);
    }
}
