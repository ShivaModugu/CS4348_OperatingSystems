
import java.util.*;
import java.io.*;

public class Project3 {
    public static void main(String[] args){

        //Check arguments
        if (args.length != 1) {
            System.out.println("Pass in the correct amount of arguments");
            System.exit(0);
        }

        //Declare job variables
        ArrayList<jobs> jobList = new ArrayList<>();
        String currJob;

        //Read file
        try {
            FileReader fileReader = new FileReader(args[0]);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            //Parse file and add jobs to job list
            while((currJob = bufferedReader.readLine()) != null)
            {
                String[] parseLine = currJob.split("\t",3);
                jobList.add(new jobs(parseLine[0], Integer.parseInt(parseLine[1]), Integer.parseInt(parseLine[2])));
            }
        }
        //Error handling
        catch (FileNotFoundException ex)
        {
            System.out.println("File could not open");
        }
        catch (IOException e)
        {
            System.out.println("Error while reading the file");
        }

        FCFS FCFS_scheduler = new FCFS();
        FCFS_scheduler.fcfsSchudling(jobList);

        RR RR_scheduler = new RR();
        RR_scheduler.rrSchudling(jobList);

        

    }
}
