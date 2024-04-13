package assn05;



import java.util.ArrayList;
import java.util.List;

public class SimpleEmergencyRoom {
    private List<Patient> patients;

    public SimpleEmergencyRoom() {
        patients = new ArrayList<>();
    }

    // TODO (Task 1): dequeue
    public Patient dequeue() {
        if(patients.isEmpty()){
            return null;
        }else {
            int index = 0;
            for(int i = 0; i< patients.size(); i++){
                if((int) patients.get(i).getPriority() < (int) patients.get(index).getPriority()){
                    index = i;
                }
            }
            Patient patientD = new Patient(patients.get(index).getValue(), patients.get(index).getPriority());
            patients.remove(index);
            return patientD;
        }


    	}
    

    public <V, P> void addPatient(V value, P priority) {
        Patient patient = new Patient(value, (Integer) priority);
        patients.add(patient);
    }

    public <V> void addPatient(V value) {
        Patient patient = new Patient(value);
        patients.add(patient);
    }

    public List getPatients() {
        return patients;
    }

    public int size() {
        return patients.size();
    }

}
