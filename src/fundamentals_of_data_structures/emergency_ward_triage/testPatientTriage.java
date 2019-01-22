package fundamentals_of_data_structures.emergency_ward_triage;

/**
 * Example test program for PatientTriage
 * @author elder
 */
public class testPatientTriage {
    public static void main(String[] args) throws BoundaryViolationException, EmptyQueueException {
        String patient;
        
        PatientTriage patientTriage = new PatientTriage(new Time (5, 0)); //Time limit of 3 hours
        patientTriage.add(new Patient(1, 4, new Time (0, 30)));
        patientTriage.add(new Patient(2, 3, new Time (1, 45)));
        patientTriage.add(new Patient(3, 2, new Time (2, 45)));
        patientTriage.add(new Patient(4, 6, new Time (2, 45)));
        patientTriage.add(new Patient(5, 5, new Time (2, 45)));
        patientTriage.add(new Patient(7, 5, new Time (2, 45)));      
        patientTriage.add(new Patient(6, 1, new Time (7, 45)));
        
        patient = patientTriage.remove(new Time (8, 15)).toString();
        System.out.print("Now seeing: "); 
        System.out.println(patient); //Should be patient 1
        
        patient = patientTriage.remove(new Time (8, 15)).toString();
        System.out.print("Now seeing: "); 
        System.out.println(patient); //Should be patient 1

        patient = patientTriage.remove(new Time (8, 15)).toString();
        System.out.print("Now seeing: "); 
        System.out.println(patient); //Should be patient 1
        
        patient = patientTriage.remove(new Time (8, 15)).toString();
        System.out.print("Now seeing: "); 
        System.out.println(patient); //Should be patient 1
        
        patient = patientTriage.remove(new Time (8, 15)).toString();
        System.out.print("Now seeing: "); 
        System.out.println(patient); //Should be patient 1
        
        patient = patientTriage.remove(new Time (8, 15)).toString();
        System.out.print("Now seeing: "); 
        System.out.println(patient); //Should be patient 1
        
        patient = patientTriage.remove(new Time (8, 15)).toString();
        System.out.print("Now seeing: "); 
        System.out.println(patient); //Should be patient 1
   


    }
    
}
