package solid.single_responsibility_principle;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

class Journal {
    private final List<String> entries = new ArrayList<>();
    private static int count = 0;

    public void addEntry(String text) {
        entries.add(String.format("%s: %s", ++count, text));
    }

    public void removeEntry(int index) {
        entries.remove(index);
    }

    @Override
    public String toString() {
        return String.join(System.lineSeparator(), entries);
    }

    /*

    The Journal class has now taken on a new responsibility where it's now concerned with Journal stuff,
    and it's concerned with Persistence of data...this should be moved to its own class.

    public void save(String filename) {
        try (PrintStream out = new PrintStream(filename)) {
            out.println(this);
        } catch (FileNotFoundException ex) {

        }
    }

    public void load(String filename) {
        try (Stream<String> lines = Files.lines(Path.of(filename))){
            entries.addAll(lines.collect(Collectors.toList()));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    */
}

class JournalPersistence {

    public void save(Journal journal, String filename) {
        try (PrintStream out = new PrintStream(filename)) {
            out.println(journal);
        } catch (FileNotFoundException ex) {

        }
    }

    public Journal load(String filename) {
        Journal journal = new Journal();

        try (Stream<String> lines = Files.lines(Path.of(filename))){
            lines.forEach(journal::addEntry);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return journal;
    }

}

public class Demo {

    public static void main(String[] args) {
        JournalPersistence p = new JournalPersistence();
        String file = "./text/journals.txt";
        Journal j = p.load(file);
        System.out.println(j);
    }


}
