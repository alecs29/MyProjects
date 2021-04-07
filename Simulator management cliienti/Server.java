import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable {
    private BlockingQueue<Client> tasks;            //task-urile
    private Thread backupThread;                    //pt regenerarea threadurilor
    private AtomicInteger waitingPeriod;            //per asteptare pentru lista task-uri
    private String name;                            //numele serverului

    private boolean okk;
    private boolean closed;
    private int timerOfQueue;

    public Server(String name, boolean closed){
        //-------------------------------------------------initialzare coada
        tasks = new LinkedBlockingDeque<Client>(1000);
        waitingPeriod = new AtomicInteger(0);

        this.name = name;
        this.closed = closed;
        timerOfQueue = 0;
    }

    public synchronized void addTask(Client newTask){

        tasks.add(newTask);
        waitingPeriod.set( waitingPeriod.get() + newTask.getTimeService() );
    }



    @Override
    public void run() {

        okk = true;

        while(okk == true ){
            //----------------------------------------------------ne uitam in coada, preluam clientul
            Client t = new Client();

                t  =  tasks.peek();

                if(t == null){

                    synchronized (this) {//-----------daca nu avem task-uri asteptam dupa notify
                        try {
                            closed = true;
                            this.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
            if(okk == false)
                break;
            t  =  tasks.peek();    //cand ne trezim verificam iar coada


            //System.out.println("processing for " + tasks.peek() + " sec");

            try {//---------------------------------------somn cat dupreaza procesarea
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            t.setTimeRemaining(t.getTimeRemaining() - 1 );//-------------scadem timpul de asteptare
            if(t.getTimeRemaining() == 0) {
                tasks.remove(tasks.peek());//------------------------eliberam clientul daca e 0

            }


            waitingPeriod.set(waitingPeriod.get() - 1);            //se updateaza waiting time ul din coada

            if (timerOfQueue != 0){
                okk = false;
                break;

            }

        }


        //System.out.println("ies din simulare");


    }

    public BlockingQueue<Client> getTasks() {
        return tasks;
    }

    public AtomicInteger getWaitingPeriod() {
        return waitingPeriod;
    }

    public String getName() {
        return name;
    }

    public void setOkk(boolean okk) {
        this.okk = okk;
    }



    public void setClosed(boolean closed) {
        this.closed = closed;
    }


    public void setTimerOfQueue(int timerOfQueue) {
        this.timerOfQueue = timerOfQueue;
    }




}
