import java.util.ArrayList;

public class FCFS
{

    //Main algorithm
    public void fcfsSchudling(ArrayList<jobs> list)
    {
        System.out.println("====FCFS===="); //Print algorithm name for graph
        jobs tempJobCopy=null;
        int intTime = 0;

         //Copy list
         ArrayList<jobs> jobsList = new ArrayList<>();
         for(jobs job: list)
         {
             jobsList.add(new jobs(job));
         }

        for(;!(jobsList.isEmpty());)
        {
        	tempJobCopy = max(jobsList);
        	tempJobCopy.setSpaceCount(intTime);
        	tempJobCopy.printMatrix();
            intTime += tempJobCopy.getDuration();
        }
    }

    //Helper function to find max
    public jobs max(ArrayList<jobs> arrJobList)
    {
        jobs selectedJobs = null;
        int intMax = 10000;
        int intIndex = 0;
        int i = 0;
        while(i<arrJobList.size())
        {
            if((intMax > arrJobList.get(i).getArrivalTime()) || (intMax == arrJobList.get(i).getArrivalTime()))
            {
            	intIndex = i;
            	intMax = arrJobList.get(i).getArrivalTime();
            	selectedJobs = arrJobList.get(i);
            }
            i++;
        }
        arrJobList.remove(intIndex);
        return selectedJobs;
    }
}