import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class QueuesSimulator {

    private static int nrOfClients;
    private static int nrOfQueues;

    private static int tMaxSimulation;

    private static int maxArrivalTime;
    private static int minArrivalTime;

    private static int maxFrontWaitTime;
    private static int minFrontWaitTime;

    private static String string1;
    private static String string2;

    private static ArrayList<Client> clientArray;

    public QueuesSimulator(String string1, String string2) throws IOException {

        //-----------------------------------------------------------------***********************open si citire din fisier
        FileReader fr = null;
        FileWriter wr = null;

        this.string1 = string1;
        this.string2 = string2;
        try {
//            fr = new FileReader(new File("D:\\faculta\\ANUL 2\\SEMESTRUL2\\TP\\Assignment2\\In-Test-1.txt"));
//            wr = new FileWriter(new File("D:\\faculta\\ANUL 2\\SEMESTRUL2\\TP\\Assignment2\\Out-Test.txt"));

            fr = new FileReader(new File(this.string1));
            wr = new FileWriter(new File(this.string2));

            citireFisier(fr);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //initializare lista clienti
        clientArray = new ArrayList<Client>(nrOfClients);

        //-------------------------------------------------------------------*********************** thread work

        SimulationManager mySimulation = new SimulationManager(wr, nrOfClients, nrOfQueues, tMaxSimulation, maxArrivalTime, minArrivalTime, maxFrontWaitTime, minFrontWaitTime);
        Thread sim = new Thread(mySimulation);
        sim.start();


        fr.close();

    }



    private static void citireFisier(FileReader fr) throws IOException {


        int ok = 0;
        int c = '1';
        c = fr.read();
        while( !(c >= '0' && c<='9') ){
            c = fr.read();   //ultimul c va fii prima cifra din nr de clienti
            ok++;
        }

        int i = 1;
        int cop = 1;
        nrOfClients = 0;
        //if(ok == 0)
            nrOfClients = nrOfClients * 10 + (c - 48);
        nrOfQueues = 0;

        cop = c;
        while( ( c = fr.read() ) != -1 ){

            if( c >= '0' && c <='9' ) {
                if (i == 1) {
                    nrOfClients = nrOfClients * 10 + (c - 48);
                } else if (i == 2) {
                    nrOfQueues = nrOfQueues * 10 + (c - 48);
                }
                else if (i == 3){
                    tMaxSimulation = tMaxSimulation * 10 + (c - 48);
                }
                else if (i == 4){
                    minArrivalTime = minArrivalTime * 10 + (c - 48);
                }
                else if (i == 5){
                    maxArrivalTime = maxArrivalTime * 10 + (c - 48);
                }
                else if (i == 6){
                    minFrontWaitTime = minFrontWaitTime * 10 + (c - 48);
                }
                else if (i == 7){
                    maxFrontWaitTime = maxFrontWaitTime * 10 + (c - 48);
                }
            }


            if( !( c >= '0' && c <='9' ) && (cop >= '0' && cop <='9')){
                i++;
            }
            cop = c;

        }
        //System.out.println("reading from file done!");
    }


}
