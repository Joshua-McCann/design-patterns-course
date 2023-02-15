package structure.flyweight;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

class User {
    private String fullName;

    public User(String fullName) {
        this.fullName = fullName;
    }
}

class User2 {
    static List<String> strings = new ArrayList<>();
    private int[] names;

    public User2(String fullName) {
        Function<String, Integer> getOrAdd = s -> {
            int idx = strings.indexOf(s);
            if (idx != -1) return idx;
            else {
                strings.add(s);
                return strings.size()-1;
            }
        };

        names = Arrays.stream(fullName.split(" "))
                .mapToInt(getOrAdd::apply)
                .toArray();
    }

    public String getFullName() {
        StringBuilder sb = new StringBuilder();
        for (int i : names) {
            sb.append(strings.get(i)).append(" ");
        }
        return sb.substring(0, sb.length() - 1);
    }
}

public class RepeatingNames {
    public static void main(String[] args) {
        User2 user = new User2("John Smith");
        User2 user2 = new User2("Jane Smith");
        System.out.println(user.getFullName());
        System.out.println(user2.getFullName());
    }
}
