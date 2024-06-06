import java.util.concurrent.Semaphore;

public class Project2{


//Resources
private static int intRegPaId;
private static int intOffNum;
private static int intDocNum;
private static int intNurseNum;
private static int intPaNum;
private static int intWaitForOff[] = new int[3];
private static int intInOff[] = new int[3];
public static Semaphore semArr[];
public static Semaphore semArr2[][];
private static int z = 0;
public static boolean checkTrue(int t) {
	if(t == 1) {
		return true;
	}
	return false;
}
public static void main(String args[]) {
    // Check for two arguments expected
	int err = 0;
    if (args.length != 2) {
        System.out.println("Error: Incorrect Arguments. Please enter the number of doctors and number of patients");
		err = 1;
    }
    if(Integer.parseInt(args[0]) > 3){
        System.out.println("Error: Too many doctors!");
		err = 1;
    } else if(Integer.parseInt(args[1]) > 30){
        System.out.println("Error: Too many patients!");
		err = 1;
    }
	if(checkTrue(err)) {
		System.exit(0);
	}

	int d = Integer.parseInt(args[0]);
	int p = Integer.parseInt(args[1]);
    intDocNum = d;
    intNurseNum = d; //Nurse number should match doctor
    intPaNum = p;

    System.out.printf("Run with %d doctors, %d nurses, %d patients%n%n", intDocNum, intNurseNum, intPaNum);

	semArr = new Semaphore[5];
	for(int s = 0; s < 5; s += 1) {
		semArr[s] = new Semaphore(0, true);
	}

    
	semArr2 = new Semaphore[10][intDocNum];
	for(int s = 0; s < 10; s += 1) {
		semArr2[s] = new Semaphore[intDocNum];
	}

    int intIndex = intDocNum - 1;
	int o = 0;
	while(intIndex >= o + 0){
		semArr2[0][intIndex] = new Semaphore(o*1, true);
		semArr2[1][intIndex] = new Semaphore(o*1, true);
		semArr2[2][intIndex] = new Semaphore(o*1, true);
		semArr2[3][intIndex] = new Semaphore(o*1, true);
		semArr2[4][intIndex] = new Semaphore(o*1, true);
		semArr2[5][intIndex] = new Semaphore(o*1, true);
		semArr2[6][intIndex] = new Semaphore(o*1, true);
		semArr2[7][intIndex] = new Semaphore(o*1, true);
		semArr2[8][intIndex] = new Semaphore(o*1, true);
		semArr2[9][intIndex] = new Semaphore(1+o, true);
		intIndex -= 1;
	}


	//Begin Threads
	Receptionist rec = new Receptionist();
	Thread Receptionist = new Thread(rec);
	Receptionist.start();

	Thread arrDoctorThread[] = new Thread[intDocNum];
	Thread arrNurseThread[] = new Thread[intDocNum];

	
	intIndex = intDocNum - 1;
	int z = 1;
	while(intIndex >= 0 * z){
		arrDoctorThread[intIndex] = new Thread(new Doctor(intIndex));
		arrNurseThread[intIndex] = new Thread(new Nurse(intIndex));

		arrDoctorThread[intIndex].start();
		arrNurseThread[intIndex].start();
		intIndex -= 1;
	}

	Thread arrPatientThread[] = new Thread[intPaNum];

	intIndex = intPaNum - 1;
	while(intIndex >= z * 0){
		arrPatientThread[intIndex] = new Thread(new Patient(intIndex));
		arrPatientThread[intIndex].start();
		intIndex -= 1;
	}

	
	intIndex = intPaNum;
	while(intIndex > 0 * z){
		try{
			arrPatientThread[intIndex - 1].join();
		} catch (InterruptedException e) {}
		intIndex -= z * 1;
	}
	System.out.println("Simulation Complete");
	System.exit(0);
}

//Receptionist class
static class Receptionist implements Runnable{

	public void run(){
		try{
			do {
				//Receptionist waits for patient to enter
				semArr[1].release();
				semArr[0].acquire();

				//Receptionist gets patient ID and assigns a random doctor office
				semArr[2].acquire();
				System.out.printf("Receptionist registers patient %d %n", (intRegPaId));
				 
				intOffNum = new java.util.Random().nextInt(intDocNum);
				semArr[3].release();

				//Notifies the nurse
				semArr2[1][intOffNum].release();

				//Receptionist waits on patient to leave
				semArr[4].acquire();
			} while(checkTrue(1));
		} catch(InterruptedException e) {
			System.out.println("InterruptedException in Receptionist");
		}
	}


}

//Doctor class
static class Doctor implements Runnable{
	private int intDoctorId;

	//ID of doctor -- must match nurse
	public Doctor(int intLocalId) {
		this.intDoctorId = intLocalId;
	}

	public void run(){
		try{
			do {
                //Doctor is available
				semArr2[6][intDoctorId].release();

				//Waot to be notified
				semArr2[5][intDoctorId].acquire();
				semArr2[4][intDoctorId].acquire();

				//Doctor listens to symptoms
				semArr2[7][intDoctorId].acquire();
				System.out.printf("Doctor %d listens to symptoms from patient %d %n", (intDoctorId), intInOff[intDoctorId]);

				//Doctor gives advices from symptoms
				semArr2[8][intDoctorId].release();

			} while(checkTrue(1));

		} catch(InterruptedException e) {
			System.out.println("InterruptedException in Doctor " + intDoctorId );
		}

	}
}

//Patient class
static class Patient implements Runnable{
	private int intPatientId;
	private int intAssignedOffice;

//Unique ID to patient
	public Patient(int id){
		this.intPatientId = id+z;
	}

	public void run(){
		try{
			semArr[1].acquire();
			//Patient enters and waits for receptionist
			semArr[0].release();
			System.out.printf("Patient %d enters waiting room, waits for Receptionist %n", intPatientId+z);

			//Patient is registered
			intRegPaId = intPatientId;
			semArr[2].release();

			//Patient waits for assigned office
			semArr[3].acquire();
			intAssignedOffice = intOffNum;
			System.out.printf("Patient %d leaves receptionist and sits in waiting room %n", intPatientId+z);
			 
            //Release receptionist
			semArr[4].release();

			//Patient waits on nurse
			semArr2[0][intAssignedOffice].release();
			semArr2[2][intAssignedOffice].acquire();
			intWaitForOff[intAssignedOffice] = intPatientId;
			semArr2[3][intAssignedOffice].acquire();

			//Patient waits in designated office
			System.out.printf("Patient %d enters doctor %d's office %n", intPatientId+z, intAssignedOffice+z);
			 
			intInOff[intAssignedOffice] = intPatientId;
			semArr2[4][intAssignedOffice].release();
			semArr2[6][intAssignedOffice].acquire();

			//Patient shares symptoms
			semArr2[7][intAssignedOffice].release();
			semArr2[8][intAssignedOffice].acquire();
			System.out.printf("Patient %d receives advice from doctor %d %n", intPatientId+z, intAssignedOffice+z);
			 

			//Patient leaves
			System.out.printf("Patient %d leaves %n", intPatientId+z);
			semArr2[9][intAssignedOffice].release();

		} catch(InterruptedException e) {
			System.out.println("InterruptedException in Patient " + intPatientId+z);
		}

	}
}


//Nurse class
static class Nurse implements Runnable{
	private int intNurseId;

//ID matches doctor ID
	public Nurse(int id) { 
		this.intNurseId = id * 1; 
	}

	public void run(){
		try{
			do {
                //Nurse is available
				semArr2[2][intNurseId].release();

				//Wait for receptionist to notify
				semArr2[0][intNurseId].acquire();
				semArr2[1][intNurseId].acquire();

				//Nurse waits on empty office
				semArr2[9][intNurseId].acquire();

				//Nurse directs patient to office
				System.out.printf("Nurse %d takes patient %d to doctor's office %n", intNurseId+z, intWaitForOff[intNurseId]+z);
				semArr2[3][intNurseId].release();

                //Nurse notifies doctor
				semArr2[5][intNurseId].release();
			}while(checkTrue(1));
		} catch(InterruptedException e) {
			System.out.println("InterruptedException in Nurse " + intNurseId+z);
		}

	}
}
}