package creation.builder;

class Person {
    public String name;
    public String position;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", position='" + position + '\'' +
                '}';
    }
}

abstract class AbstractPersonBuilder<SELF extends AbstractPersonBuilder<SELF>> {
    protected Person person = new Person();

    public Person build() {
        return person;
    }

    public SELF withName(String name) {
        person.name = name;
        return self();
    }

    protected SELF self() {
        return (SELF) this;
    }
}

abstract class AbstractEmployeeBuilder<SELF extends AbstractEmployeeBuilder<SELF>> extends AbstractPersonBuilder<SELF> {

    public SELF worksAt(String position) {
        person.position = position;
        return self();
    }

    protected SELF self() {
        return (SELF) this;
    }
}

class PersonBuilder extends AbstractPersonBuilder<PersonBuilder> {

    @Override
    protected PersonBuilder self() {
        return this;
    }
}

class EmployeeBuilder extends AbstractEmployeeBuilder<EmployeeBuilder> {

    @Override
    protected EmployeeBuilder self() {
        return this;
    }
}

public class FluentBuilderDemo {

    public static void main(String[] args) {
        EmployeeBuilder builder = new EmployeeBuilder();
        Person person = builder
                .withName("Josh")
                .worksAt("Dev10")
                .build();
        System.out.println(person);
    }
}
