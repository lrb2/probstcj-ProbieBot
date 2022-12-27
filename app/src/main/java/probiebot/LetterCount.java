package probiebot;
import java.util.ArrayList;
import java.util.TreeMap;

// NOTE: This will be much easier if we cover Maps. :)
public class LetterCount {

    public static TreeMap count(String s) {
        
        LCount example = new LCount();
        
        for(int i=0; i<s.length(); i++)
            example.update(s.charAt(i));
        
        // Map structure
        TreeMap<Character, Integer> map = new TreeMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                map.put(s.charAt(i), map.get(s.charAt(i))+1);
            }
            else{
                map.put(s.charAt(i), 1);
            }
        }
        return map;
    }
    
    private static class LCount extends ArrayList {
        
        public void update(Object o) {
            char c = (char)o;
            int count = 0;
            boolean updated = false;
            while(count < size() && !updated) {
                if(((FreqCount)get(count)).getLetter() == c) {
                    ((FreqCount)get(count)).increment();
                    updated = true;
                }
                count++;
            }
            if(!updated)
                add(new FreqCount(c));
        }
        
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder("[");
            for(int i=0; i<size(); i++)
                sb.append("(").append(((FreqCount)get(i)).getLetter()).append(",").append(((FreqCount)get(i)).getCount()).append(")");
            sb.append("]");
            return sb.toString();
        }
    } // end of new class: LCount
    
    private static class FreqCount {
        private char letter;
        private int count;
        
        public FreqCount(char c) {
           letter = c;
           count = 1;
        }
        
        public char getLetter() {
            return letter;
        }
        
        public void increment() {
            count++;
        }
               
        public int getCount() {
            return count;
        }
    } // end of FreqCount
    
}
