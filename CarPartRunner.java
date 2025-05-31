import java.util.Scanner;

class CarPart {
    // Private attributes
    private String modelNumber;
    private String partNumber;
    private String cost;

    // Method to set parameters
    public void setParameter(String x, String y, String z) {
        modelNumber = x;
        partNumber = y;
        cost = z;
    }

    // Method to display information
    public void display() {
        System.out.println("Model Number: " + modelNumber +
                ", Part Number: " + partNumber +
                ", Cost: " + cost);
    }
}

// Runner class
public class CarPartRunner {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        CarPart car1 = new CarPart();

        // Taking user input
        System.out.println("What is the model number?");
        String x = sc.nextLine();

        System.out.println("What is the part number?");
        String y = sc.nextLine();

        System.out.println("What is the cost?");
        String z = sc.nextLine();

         // Setting parameters and displaying output
        car1.setParameter(x, y, z);
        car1.display();

        // Close the scanner
        sc.close();
    }
}
