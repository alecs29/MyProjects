import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Scheduler {

    private ArrayList<Server> servers;
    private int maxNoServers;
    private ArrayList<Thread> qThreads;
    private FileWriter wr;

    public Scheduler(FileWriter wr, int maxNoServers){

        qThreads = new ArrayList<Thread>(maxNoServers); //initializarea
        servers = new ArrayList<Server>(maxNoServers);
        this.wr = wr;

        for(int i=0; i < maxNoServers; i++){

            Server s = new Server("q" + i,true);
            servers.add(s);

            Thread e = new Thread(s);
            qThreads.add(e);
            qThreads.get(i).start();
        }
    }

    public void show(){
        for (int i = 0; i < servers.size(); i++) {
            if (servers.get(i).getTasks().isEmpty()) {
                try {
                    System.out.println("Queue " + servers.get(i).getName() + ":" + " Closed");
                    wr.write("Queue " + servers.get(i).getName() + ":" + " Closed\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else {
                try {
                    System.out.println("Queue " + servers.get(i).getName() + ":" + servers.get(i).getTasks().peek().toString2());
                    wr.write("Queue " + servers.get(i).getName() + ":" + servers.get(i).getTasks().peek().toString2() + "\n");
                } catch (IOException e) {
                    System.out.println("s-a rupt la i = " + i + ", servers.get(i).getTasks().peek() = " + servers.get(i).getTasks().size());
                    e = new IOException("s-a rupt la i = " + i + ", servers.get(i).getTasks().peek() = " + servers.get(i).getTasks().size());
                    e.printStackTrace();

                }
            }

        }
    }

    public synchronized void kill(){
        for(Server t : servers){

            t.setOkk(false);
            t.setTimerOfQueue(1);
            synchronized (t) {
                t.notify();
            }
        }
//        for( Thread q : qThreads){
//            q.interrupt();
//        }
    }


    public synchronized void dispatchTask(Client t){
        int minWaitingPeriod = 99999;

        for(Server e : servers){
            if(e.getWaitingPeriod().get() < minWaitingPeriod){
                minWaitingPeriod = e.getWaitingPeriod().get();

            }
        }
        for(Server e : servers){
            if(e.getWaitingPeriod().get() == minWaitingPeriod){
                //System.out.println("Sunt clientul cu id=" + t.getId() + " - merg la coada " + e.getName());
                e.addTask(t);
                e.setClosed(false);
                synchronized (e) {
                    e.notify();
                }
                break;

            }
        }
        
    }


}
