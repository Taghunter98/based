import java.util.ArrayList;

public class Convert implements Base {

    @Override
    public String convert(int input, int base) {
        if (input == 0) return "0"; // Special case

        ArrayList<String> number = new ArrayList<>();

        while (input > 0) {
            int digit = input % base;  
            number.add(String.valueOf(digit)); 
            input /= base;  
        }

        // Reverse the collected digits because they are stored in reverse order
        StringBuilder result = new StringBuilder();
        for (int i = number.size() - 1; i >= 0; i--) {
            result.append(number.get(i));
        }

        System.out.println(result.toString());
        return result.toString();
    }
}