import java.util.ArrayList;

public class Convert implements Base {

    @Override
    public String[] convert(int input, int base) {
        if (input == 0) return new String[]{"0", "0 ÷ " + base + " = 0 remainder 0"}; 

        ArrayList<String> steps = new ArrayList<>();
        StringBuilder result = new StringBuilder();

        while (input > 0) {
            int digit = input % base;  
            steps.add(input + " ÷ " + base + " = " + (input / base) + " remainder " + digit);
            result.insert(0, Integer.toString(digit)); 
            input /= base;
        }

        // Build the output array with result and steps
        String[] output = new String[steps.size() + 1];
        output[0] = result.toString();
        for (int i = 0; i < steps.size(); i++) {
            output[i + 1] = steps.get(i);
        }

        return output;
    }
}
