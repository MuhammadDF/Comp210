package assn02;
import java.util.Scanner;

// Here is a starter code that you may optionally use for this assignment.
// TODO: You need to complete these sections

public class JavaWarmUp {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        String[] categoriesList = {"phone", "laptop", "smart_watch"};

        System.out.println("How many items will be entered into the database?");

        int n = s.nextInt();
        // Date Time1 Category Fee Quantity Time2 AsmCost

        // create corresponding size arrays
        String date[] = new String[n];
        String time1[] = new String[n];
        String category[] = new String[n];
        double fee[] = new double[n];
        int quantity[] = new int[n];
        double time2[] = new double[n];
        double asmCost[] = new double[n];

        // TODO: Fill in the above arrays with data entered from the console.
        for (int i = 0; i < n; i++) {
            date[i] = s.next(); // Reads the date (MM/DD/YY)
            time1[i] = s.next(); // Reads Time1 (HH:MM)
            category[i] = s.next(); // Reads the Category
            fee[i] = s.nextDouble(); // Reads the Fee (per unit)
            quantity[i] = s.nextInt(); // Reads the Quantity (number of items in batch)
            time2[i] = s.nextDouble(); // Reads Time2 (assembly time for batch)
            asmCost[i] = s.nextDouble(); // Reads AsmCost (additional assembling cost)
        }


        // Find items with highest and lowest price per unit
        int highestItemIndex = getMaxPriceIndex(fee);
        int lowestItemIndex = getMinPriceIndex(fee);

        // TODO: Print items with highest and lowest price per unit.
        // make sure the format is correct!
        // Your code starts here:

        if (highestItemIndex != -1) { // Ensure the index is valid
            System.out.println(date[highestItemIndex]);
            System.out.println(time1[highestItemIndex]);
            System.out.println(category[highestItemIndex]);
            System.out.println(fee[highestItemIndex]);
        }

        if (lowestItemIndex != -1) { // Ensure the index is valid
            System.out.println(date[lowestItemIndex]);
            System.out.println(time1[lowestItemIndex]);
            System.out.println(category[lowestItemIndex]);
            System.out.println(fee[lowestItemIndex]);        }


        // Your code ends here.

        // Calculate the # of batches, total Fee, total Quantity, total Labor and Assembly costs by category.
        // Maintain following category-wise total stats in the following Category Arrays
        int[] numOfBatchesC = new int[categoriesList.length];// so numOfBatchesC[0] = # of batches in category categoriesList[0]
        double[] totFeeC = new double[categoriesList.length]; // total fee of each category = sum(fee * qty)
        int[] totQuantityC = new int[categoriesList.length];    // total qty of each category = sum (qty)
        double[] totLaborCostC = new double[categoriesList.length]; // total labor cost of each category = sum(time2 x 16)
        double[] totAsmCostC = new double[categoriesList.length]; // total Assembly cost of each category = sum(AsmCost)

        // TODO: for each item i, incrementally total the values in the above arrays
        for (int i = 0; i < n; i++) {
            int catIndex = 0; // Initialize catIndex to an invalid value

            // Loop through categoriesList to find the matching category
            for (int j = 0; j < categoriesList.length; j++) {
                if (category[i].equals(categoriesList[j])) {
                    catIndex = j; // Set catIndex to the index of the matching category
                    break; // Exit the loop as we found the matching category
                }
            }

            if (catIndex == -1) {
                System.out.println("Category not found for item " + i);
                continue; // Skip further processing for this item
            }

            // Use catIndex for further processing like updating category arrays
            numOfBatchesC[catIndex]++;
            totFeeC[catIndex] += fee[i] * quantity[i];
            totQuantityC[catIndex] += quantity[i];
            totLaborCostC[catIndex] += time2[i] * 16;
            totAsmCostC[catIndex] += asmCost[i];
        }

        // TODO: Calculate & Print Category-wise Statistics
        for (int j = 0; j < categoriesList.length; j++) {
            if (numOfBatchesC[j] > 0) { // Check if there are batches in this category
                System.out.println(categoriesList[j]);
                System.out.println(totQuantityC[j]);
                System.out.printf("%.2f\n", (totFeeC[j] / totQuantityC[j]));
                System.out.printf("%.2f\n", ((totFeeC[j] - (totLaborCostC[j] + totAsmCostC[j])) / totQuantityC[j]));
            }
        }
    }

    // TODO: Find index of item with the highest price per unit.
    static int getMaxPriceIndex(double[] fee) {
        if (fee == null || fee.length == 0) return -1; // Handle empty array

        int maxIndex = 0;
        for (int i = 1; i < fee.length; i++) {
            if (fee[i] > fee[maxIndex]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }


    // TODO: Find index of item with the lowest price per unit.
    static int getMinPriceIndex(double[] fee) {
        if (fee == null || fee.length == 0) return -1; // Handle empty array

        int minIndex = 0;
        for (int i = 1; i < fee.length; i++) {
            if (fee[i] < fee[minIndex]) {
                minIndex = i;
            }
        }
        return minIndex;
    }

}
