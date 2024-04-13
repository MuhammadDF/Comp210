package assn05;

public class Main {

    public static void main(String[] args) {
        testP1();
        testP2();
//        testP3();
        testP4();
    }

    // test Part 1
    public static void testP1(){
        /*
        Part 1 - Write some tests to convince yourself that your code for Part 1 is working
         */
    }

    // test Part 2
    public static void testP2(){
       /*
        Part 2 - Write some tests to convince yourself that your code for Part 2 is working
         */
    }

    /*
    Part 3
     */
    public static void testP3(){
        MaxBinHeapER transfer = new MaxBinHeapER(makePatients());
        Prioritized[] arr = transfer.getAsArray();
        for(int i = 0; i < transfer.size(); i++) {
            System.out.println("Value: " + arr[i].getValue()
                    + ", Priority: " + arr[i].getPriority());
        }
    }

    /*
    Part 4
     */
    public static void testP4() {
               /*
        Part 4 - Write some tests to convince yourself that your code for Part 4 is working
         */
        double[] results = compareRuntimes();
        System.out.println("Total time to dequeue all patients from SimpleEmergencyRoom: " + results[0] + " ns");
        System.out.println("Average time to dequeue a patient from SimpleEmergencyRoom: " + results[1] + " ns");
        System.out.println("Total time to dequeue all patients from MaxBinHeapER: " + results[2] + " ns");
        System.out.println("Average time to dequeue a patient from MaxBinHeapER: " + results[3] + " ns");

    }

    public static void fillER(MaxBinHeapER complexER) {
        for(int i = 0; i < 100000; i++) {
            complexER.enqueue(i);
        }
    }
    public static void fillER(SimpleEmergencyRoom simpleER) {
        for(int i = 0; i < 100000; i++) {
            simpleER.addPatient(i);
        }
    }


    public static Patient[] makePatients() {
        Patient[] patients = new Patient[10];
        for(int i = 0; i < 10; i++) {
            patients[i] = new Patient(i);
        }
        return patients;
    }

    public static double[] compareRuntimes() {
        // Array which you will populate as part of Part 4
        double[] results = new double[4];

        SimpleEmergencyRoom simplePQ = new SimpleEmergencyRoom();
        fillER(simplePQ);

        // Code for (Task 4.1) Here
        long startTime = System.nanoTime();
        while (simplePQ.size() > 0) {
            simplePQ.dequeue();
        }
        long endTime = System.nanoTime();
        results[0] = endTime - startTime; // Total time for SimpleEmergencyRoom
        results[1] = results[0] / 100000.0; // Average time per dequeue operation




        MaxBinHeapER binHeap = new MaxBinHeapER();
        fillER(binHeap);

        // Code for (Task 4.2) Here
        startTime = System.nanoTime();
        while (binHeap.size() > 0) {
            binHeap.dequeue();
        }
        endTime = System.nanoTime();
        results[2] = endTime - startTime; // Total time for MaxBinHeapER
        results[3] = results[2] / 100000.0; // Average time per dequeue operation


        return results;
    }

}
