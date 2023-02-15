package structure.proxy;

import java.util.Objects;

class Creature {
    private Property<Integer> agility = new Property<>(10);

    public Creature() {
    }

    public void setAgility(int value) {
        agility.setValue(value);
    }

    public int getAgility() {
        return agility.getValue();
    }

    @Override
    public String toString() {
        return "Creature{" +
                "agility=" + agility.getValue() +
                '}';
    }
}

class Property<T> {
    private T value;

    public Property(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        System.out.println("Value set to " + value);
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Property<?> property = (Property<?>) o;
        return Objects.equals(value, property.value);
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }
}

public class PropertyProxy {
    public static void main(String[] args) {
        Creature creature = new Creature();
        creature.setAgility(12);
        System.out.println(creature);
    }
}
