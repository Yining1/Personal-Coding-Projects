import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by Yining on 7/20/16.
 * 
 * Takes in a LinkedList of names (strings) and matches them another person. Two people will not receive each other.
 *
 */
public class BirthdayBuddies {
    LinkedList<String> names;

    public BirthdayBuddies(LinkedList<String> names) {
        this.names = names;
    }

    public Map<String, String> Sort() {
        LinkedList<String> names1 = (LinkedList<String>) names.clone();
        Map<String, String> pairs = new HashMap<>();

        for(int i = 0; i < names1.size(); i++) {
            int random = (int) (Math.random() * (names1.size()-i));
            names1.addLast(names1.remove(random));
        }


        while (names.size() != 0 && names1.size() != 0) {
            if (!names.peekFirst().equals(names1.peekFirst())) {
                if (pairs.containsKey(names1.peekFirst())) {
                    if (!pairs.get(names1.peekFirst()).equals(names.peekFirst())){
                        pairs.put(names.removeFirst(), names1.removeFirst());
                    } else {
                        names1.addLast(names1.removeFirst());
                    }
                } else {
                    pairs.put(names.removeFirst(), names1.removeFirst());
                }

            } else {
                names1.addLast(names1.removeFirst());
            }

        }
        return pairs;
    }

    public static void main(String[] args) {
        LinkedList<String> names = new LinkedList<>();
        names.add("nameA");
        names.add("nameB");
        names.add("nameC");
        names.add("nameD");
        names.add("nameE");
        names.add("nameF");
        names.add("nameG");
        names.add("nameH");
        BirthdayBuddies mapping = new BirthdayBuddies(names);
        Map<String, String> pairs = mapping.Sort();

        for (Map.Entry entry : pairs.entrySet()) {
            System.out.println(entry.getKey() + " gets cake for " + entry.getValue());
        }
    }
}
