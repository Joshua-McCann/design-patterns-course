package solid.dependency_inversion_principle;

import org.javatuples.Triplet;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

enum Relationship {
    PARENT, CHILD, SIBLING
}

class Person {
    public String name;

    public Person(String name) {
        this.name = name;
    }
}

// this is a low-level module
class Relationships implements RelationshipBrowser {
    private List<Triplet<Person, Relationship, Person>> relations = new ArrayList<>();

    public List<Triplet<Person, Relationship, Person>> getRelations() {
        return relations;
    }

    public void addParentAndChild(Person parent, Person child) {
        relations.add(new Triplet<>(parent, Relationship.PARENT, child));
        relations.add(new Triplet<>(child, Relationship.CHILD, parent));
    }

    @Override
    public List<Person> findAllChildrenOf(String name) {
        return relations.stream()
                .filter(x -> x.getValue0().name.equals(name) && x.getValue1() == Relationship.PARENT)
                .map(Triplet::getValue2)
                .collect(Collectors.toList());
    }
}
/*
    this is a high-level module, and it depends on a lower-level module, but implements logic that the other class depends on
    this can be fixed by using an abstraction and pushing the stream logic directly into the concrete class instead of
    doing the logic in a high-level module.  This makes the high-level module depend on the low-level module instead of
    the other way around.

    class Research {
        public Research(Relationships relationships) {
            List<Triplet<Person, Relationship, Person>> relations = relationships.getRelations();
            relations.stream()
                    .filter(x -> x.getValue0().name.equals("John") && x.getValue1() == Relationship.PARENT)
                    .forEach(ch -> System.out.printf("John has a child called %s.%n", ch.getValue2().name));
        }
    }
*/

interface RelationshipBrowser {
    List<Person> findAllChildrenOf(String name);
}

class Research {

    public Research(RelationshipBrowser relationships) {
        relationships
                .findAllChildrenOf("John")
                .forEach(p -> System.out.printf("John has a child named %s.%n", p.name));
    }
}

public class Demo {
    public static void main(String[] args) {
        Person john = new Person("John");
        Person child1 = new Person("Chris");
        Person child2 = new Person("Matt");

        Relationships relationships = new Relationships();
        relationships.addParentAndChild(john, child1);
        relationships.addParentAndChild(john, child2);

        new Research(relationships);
    }
}
