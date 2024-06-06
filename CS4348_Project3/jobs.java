//import java.util.ArrayList;

public class jobs
{
    private String jobName; //job name
    private int intArrivalTime=0; //arrival time
    private int intDuration=0; //duration time
    private int intSpaceCount=0; //tracks spacing
    private int intFinishTime=0;
   
    //Constructor
    jobs(jobs selectedJob)
    {
    	jobName = selectedJob.jobName;
    	intArrivalTime = selectedJob.intArrivalTime;
    	intDuration = selectedJob.intDuration;
    	intSpaceCount = selectedJob.intSpaceCount;
    }
   
    jobs(String n, int arrTime, int dTime)
    {
    	jobName = n;
        intArrivalTime = arrTime;
        intDuration = dTime;
    }

    //Space Count
    public void setSpaceCount(int num)
    {
        intSpaceCount = num;
    }

    //returns SpaceCount
    public int getSpaceCount()
    {
        return intSpaceCount;
    }

    //Finish Time
    public void setFinishTime(int finishTime)
    {
        this.intFinishTime = finishTime;
    }
    
    //return finishTime
    public int getFinishTime()
    {
        return intFinishTime;
    }

    //Duration
    public void setDuration(int newDuration)
    {
        this.intDuration = newDuration;
    }

    //Returns Duration
    public int getDuration()
    {
        return intDuration;
    }

    //Job Name    
    public void setJobName(String na)
    {
        this.jobName = na;
    }
   
    //Returns name of job
    public String getJobName()
    {
        return jobName;
    }

    //Arrival Time
    public void setArrivalTime(int arrivalTime)
    {
        this.intArrivalTime = arrivalTime;
    }

    //Returns arrival time
    public int getArrivalTime()
    {
        return intArrivalTime;
    }
   
    

    
   
    //Prints graph
    public void printMatrix()
    {
        System.out.print(jobName); //Prints name of job (e.g. 'A', 'B', etc.)

        //Creates proper spacing
        int intIndex=0;
        while(intIndex <= intSpaceCount)
        {
            System.out.print(" ");
            intIndex++;
        }
        intIndex=0;
        //Fills slots with 'X'
        while(intIndex < intDuration)
        {
            System.out.print("X");intIndex++;
        }
        System.out.println(); //New line for next job
    }
}
/*
Coudln't figure out how to add spaces into my RR within the same matrix so I tried to print it seperatly but I couldn't figure it out
    public void printMatrixRR()
    {
        System.out.print(name); //Prints name of job (e.g. 'A', 'B', etc.)
        //System.out.println(list); //New line for next job
        //System.out.println();
    }
    
    /*public void printMatrixRR(ArrayList<jobs> list) {
        // Create a copy of the list to avoid modifying the original list
        ArrayList<jobs> newList = new ArrayList<>(list);
        jobs job;
        int time = 0;

        // Print job names
        for (jobs jobName : newList) {
            System.out.print(jobName.getName() + " ");
        }
        System.out.println();

        while (!newList.isEmpty()) {
            for (int i = 0; i < newList.size(); i++) {
                job = newList.get(i);

                if (job.getDuration() > 0) {
                    System.out.print("X ");
                    job.setDuration(job.getDuration() - 1);
                    if (job.getDuration() == 0) {
                        newList.remove(i); // Remove job from list when duration is 0
                        i--; // Adjust index due to removal
                    }

                }
            }
        }
 */
//}   

