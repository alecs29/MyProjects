import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.FileHandler;

public class SimulationManager implements Runnable {

    private static int nrOfClients;
    private static int nrOfQueues;
    private static int tMaxSimulation;
    private static int maxArrivalTime;
    private static int minArrivalTime;
    private static int maxFrontWaitTime;
    private static int minFrontWaitTime;

    private Scheduler scheduler;
    private ArrayList<Client> generatedTasks;
    private double averageWaitingTime;
    private FileWriter wr;

    public SimulationManager(FileWriter wr, int nrOfClients, int nrOfQueues, int tMaxSimulation, int maxArrivalTime, int minArrivalTime, int maxFrontWaitTime, int minFrontWaitTime) {

        this.nrOfClients = nrOfClients;
        this.nrOfQueues = nrOfQueues;
        this.tMaxSimulation = tMaxSimulation;
        this.maxArrivalTime = maxArrivalTime;
        this.minArrivalTime = minArrivalTime;
        this.maxFrontWaitTime = maxFrontWaitTime;
        this.minFrontWaitTime = minFrontWaitTime;
        this.wr = wr;

        averageWaitingTime = 0;
        int count = 0;

        generatedTasks = Client.randomClientListGenerator(nrOfClients,minArrivalTime,maxArrivalTime,minFrontWaitTime,maxFrontWaitTime); //clienti random
        for (int i = 0; i < generatedTasks.size() - 1; i++) {
            for (int j = i + 1; j < generatedTasks.size(); j++) {

                 if ( generatedTasks.get(i).getTimeArrival() > generatedTasks.get(j).getTimeArrival() ){
                      Client aux;
                      aux = generatedTasks.get(i);
                      generatedTasks.set(i, generatedTasks.get(j));
                      generatedTasks.set(j, aux);
                 }
            }
        }
        //generatedTasks = Client.ClientListMadeByMe(nrOfClients,minArrivalTime,maxArrivalTime,minFrontWaitTime,maxFrontWaitTime);   //clienti dati de mine
        //------------------------afisarea clientilor in colsola + averageWaitingTime
        for (Client e : generatedTasks) {
            averageWaitingTime += e.getTimeService();
            count++;
            //System.out.println( e.toString());

        }
        averageWaitingTime = averageWaitingTime / count;

        scheduler = new Scheduler(wr, nrOfQueues); //initialising and starting threads



    }


    @Override
    public void run() {

        //-------------------------------------------------declarare variabile
        int currentTime = 0;
        int minimalArrivalTime = 99999;
        boolean ok = true;

        //--------------------------------------------------simulare
        while( ok ){ // 60 sec


            ///---------------------------------------AFISARE
            try {

                System.out.println("");
                wr.write("\n");
                System.out.println("\nTime " + currentTime );
                wr.write("\nTime " + currentTime + "\n");
                System.out.print("Waiting clients: ");
                wr.write("Waiting clients: ");

            } catch (IOException e) {
                e.printStackTrace();
            }

            //-----------------------------------------------------------------------------------------------------------adaugare task-uri catre cozi
            for(int i=0;i<generatedTasks.size();i++) {                              //parcurgem lista de clienti
                if (currentTime == generatedTasks.get(i).getTimeArrival()) {       //ii luam pe cei cu Arrival time egal

                    scheduler.dispatchTask(generatedTasks.get(i));
                    generatedTasks.remove(generatedTasks.get(i));
                    if(!(generatedTasks.isEmpty())) {
                        i--;
                    }
                    else break;
                }
            }
            try {
                for(int i=0;i<generatedTasks.size();i++) {
                   System.out.print("(" + generatedTasks.get(i).getId() + "," + generatedTasks.get(i).getTimeArrival() + "," + generatedTasks.get(i).getTimeService() + "); ");
                  wr.write("(" + generatedTasks.get(i).getId() + "," + generatedTasks.get(i).getTimeArrival() + "," + generatedTasks.get(i).getTimeService() + "); ");

                }

                System.out.println("");
                wr.write("\n");

            } catch (IOException e) {
                e.printStackTrace();
            }


            scheduler.show();   //afisare
            //-----------------------------------------going to sleep a sec
            try {
                    Thread.sleep(1000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            currentTime++;

            //-------------------------------iesirea din simulare
            if( currentTime >= tMaxSimulation + 1) {
                ok = false;
                scheduler.kill();

            }


        }
        //---------------------end simulare

        System.out.println("\nAverage waiting time:" + averageWaitingTime );
        try {
            wr.write("\nAverage waiting time:" + averageWaitingTime );
            wr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }



}
