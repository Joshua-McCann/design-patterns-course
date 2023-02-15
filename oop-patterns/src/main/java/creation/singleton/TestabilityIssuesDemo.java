package creation.singleton;

import com.google.common.collect.Iterables;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertEquals;


interface Database {
    int getPopulation(String name);
}
class SingletonDatabase implements Database {
    private Dictionary<String, Integer> capitals = new Hashtable<>();
    private static int instanceCount = 0;
    private static final SingletonDatabase INSTANCE = new SingletonDatabase();

    public static int getCount() {
        return instanceCount;
    }

    private SingletonDatabase() {
        instanceCount++;
        System.out.println("Initializing database");

        try {
            File f = new File(SingletonDatabase.class.getClassLoader().getResource("capitals.txt").toURI());
            Path fullPath = Paths.get(f.getPath());
            List<String> lines = Files.readAllLines(fullPath);

            Iterables.partition(lines, 2).forEach(kv -> capitals.put(kv.get(0).trim(), Integer.parseInt(kv.get(1))));

        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public int getPopulation(String name) {
        return capitals.get(name);
    }

    public static SingletonDatabase getInstance() {
        return INSTANCE;
    }
}

class SingletonRecordFinder {

    // only problem with this is this project has a dependency on the database implementation
    // the implementation is not flexible, and we're testing both the finder and the database
    public int getTotalPopulation(List<String> names) {
        return names.parallelStream()
                .mapToInt(SingletonDatabase.getInstance()::getPopulation)
                .sum();
    }
}

class ConfigurableRecordFinder {

    private Database db;

    public ConfigurableRecordFinder(Database db) {
        this.db = db;
    }

    public int getTotalPopulation(List<String> names) {
        return names.parallelStream()
                .mapToInt(db::getPopulation)
                .sum();
    }

}

class DummyDatabase implements Database {

    private Dictionary<String, Integer> data = new Hashtable<>();

    public DummyDatabase() {
        data.put("alpha", 1);
        data.put("beta", 2);
        data.put("gamma", 3);
    }

    @Override
    public int getPopulation(String name) {
        return data.get(name);
    }
}

public class TestabilityIssuesDemo {

    @Test
    public void singletonTotalPopulationTest() {
        SingletonRecordFinder rf = new SingletonRecordFinder();
        List<String> names = List.of("Seoul", "Mexico City");
        int tp = rf.getTotalPopulation(names);
        assertEquals(1750000+1740000, tp);
    }

    @Test
    public void dependentPopulationTest() {
        DummyDatabase db = new DummyDatabase();
        ConfigurableRecordFinder rf = new ConfigurableRecordFinder(db);
        List<String> names = List.of("alpha", "gamma");
        int tp = rf.getTotalPopulation(names);
        assertEquals(4, tp);
    }
}
