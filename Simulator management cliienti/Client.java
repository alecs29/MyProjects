import java.util.ArrayList;
import java.util.Random;
import java.util.TimerTask;

public class Client extends TimerTask {  //runnable

    protected static int idMax;
    protected static int minArrival;
    protected static int maxArrival;
    protected static int minService;
    protected static int maxService;

    private int id;
    private int timeArrival;
    private int timeService;
    private int timeRemaining;

    public Client(int id, int timeArrival, int timeService) {
        this.id = id;
        this.timeArrival = timeArrival;
        this.timeService = timeService;
        timeRemaining = timeService;
    }
    public Client() {

    }


    @Override
    public void run() {

    }


    protected static ArrayList<Client> randomClientListGenerator(int nRClients, int minArrival, int maxArrival, int minService, int maxService){


        ArrayList<Client> myList = new ArrayList<Client>();
        for(int i=0; i < nRClients; i++){


            Random rand = new Random();

            Client c = new Client(i,
                    minArrival + rand.nextInt(maxArrival - minArrival),
                    minService  + rand.nextInt(maxService - minService)
            );

            myList.add(c);
        }

        System.out.println("A list of clients has been generated!\n");
        return myList;

    }

    protected static ArrayList<Client> clientListMadeByMe(int nRClients, int minArrival, int maxArrival, int minService, int maxService){


        ArrayList<Client> myList = new ArrayList<Client>();

        Client c = new Client(1,2,2);
        myList.add(c);
         c = new Client(2,2,3);
        myList.add(c);
         c = new Client(3,4,3);
        myList.add(c);
         c = new Client(4,10,2);
        myList.add(c);


        return myList;

    }

    protected static Client randomClientGenerator(int idMax, int minArrival, int maxArrival, int minService, int maxService){

        Random rand = new Random();
        rand.nextInt(idMax);

        Client c = new Client(rand.nextInt(idMax),
                minArrival + rand.nextInt(maxArrival - minArrival),
                minService  + rand.nextInt(maxService - minService)
        );

        return c;
    }

    public int getId() {
        return id;
    }

    public int getTimeArrival() {
        return timeArrival;
    }

    public int getTimeService() {
        return timeService;
    }

    public int getTimeRemaining() {
        return timeRemaining;
    }

    public void setTimeRemaining(int timeRemaining) {
        this.timeRemaining = timeRemaining;
    }


    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", timeArrival=" + timeArrival +
                ", timeService=" + timeService +
                '}';
    }

    public String toString2() {
        return "(" + id + "," + timeArrival + "," + timeService + ");";
    }
}
