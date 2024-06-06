import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class RR
{
    //Main algorithm
    public void rrSchudling(ArrayList<jobs> list)
    {
        System.out.println("====RR===="); //Print algorithm name for graph
        jobs copy;
        int time = 0;
		int jobCounter = 0;
		jobs unfinished = new jobs("empty",0,0);
        //Copy list
        //ArrayList<jobs> newList = new ArrayList<>();
		Queue<jobs> queue = new LinkedList<>();

        

        //Create a queue for the round robin scheduling
        //Queue<jobs> queue = new LinkedList<>();
		//
		while(jobCounter > list.size())
		{
			//add into queue if job has arrive at that time
			for(jobs job: list)
        	{
				if(job.getArrivalTime() == time)
				{
					queue.add(job);
					jobCounter++;
				}
        	}
			if(!(unfinished.equals(new jobs("empty",0,0))))
				{
					queue.add(unfinished);
					unfinished = new jobs("empty",0,0);
				}
			if(!queue.isEmpty())
				{
					copy = queue.remove();
					if(copy.getDuration() > 1)
					{
						copy.setDuration(copy.getDuration()-1);
						copy.setSpaceCount(time);
						unfinished = copy;
						
						
						//queue.add(copy);
					}
					else
					{
						copy.setFinishTime(time);
					}
					time +=1;
			}
			
		}
		for(jobs job: list)
		{
			job.printMatrix();
		}

        
        // Print the jobs after all have been processed
        
	}
}

    //Helper function to find min
    /*public jobs min(ArrayList<jobs> list)
    {
        jobs selected = null;
        int min = 10000;
        int index = 0;

        for(int i = 0; i < list.size(); i++)
        {
            if(min >= list.get(i).getArrivalTime() && list.list.get(i).getArrivalTime() == time)
            {
                index = i;
                min = list.get(i).getArrivalTime();
                selected = list.get(i);
            }
        }

        list.remove(index);

        return selected;
    }*/
