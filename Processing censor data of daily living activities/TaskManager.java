import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class TaskManager{

    private static final String task1Location = "Task_1.txt";
    private static final String task2Location = "Task_2.txt";
    private static final String task3Location = "Task_3.txt";
    private static final String task4Location = "Task_4.txt";
    private static final String task5Location = "Task_5.txt";
    private static final String task6Location = "Task_6.txt";

    private ArrayList<MonitoredData> data = new ArrayList<MonitoredData>();
    private List<String> underFiveMin;
    private Map<String, Integer> occurences;
    private Map<Integer, Map<String, Integer>>  activityCount;
    private Map<Object, Long> nrOfactivitiesPerDay;
    private Map<String, Integer> totalTime;

    private int countLessThan5 = 0;
    private int countMoreThan5 = 0;


    public TaskManager() throws IOException {

        //--------------------------------------------------------------------------------TASK_1
        String fileName = "Activities.txt.txt";
        FileWriter wr = null;
        wr = new FileWriter(task1Location);

        task1(fileName, wr);
        wr.close();

        //--------------------------------------------------------------------------------TASK2
        wr = new FileWriter(task2Location);
        nrOfactivitiesPerDay = task2(wr);
        wr.close();

        //--------------------------------------------------------------------------------TASK3
        wr = new FileWriter(task3Location);
        occurences = task3(wr);
        wr.close();

        //--------------------------------------------------------------------------------TASK4
        wr = new FileWriter(task4Location);
        activityCount = task4();
        writeTask4ToFile(wr);
        wr.close();

        //--------------------------------------------------------------------------------TASK5 and TASK6
        totalTime = task5();

        wr = new FileWriter(task5Location);
        writeTask5ToFile(wr);
        wr.close();

        wr = new FileWriter(task6Location);
        writeTask6ToFile(wr);
        wr.close();
    }

    public void writeTask5ToFile(FileWriter wr) throws IOException {

        for(Map.Entry<String, Integer> entry : totalTime.entrySet()){
            wr.write("Activity " + entry.getKey().trim() + " had a total duration of " + entry.getValue() + " minutes\n");
        }
    }


    public void task1(String fileName, FileWriter wr) throws IOException {
        //System.out.println("------------------------------------------------task1");
        data = null;
        try{

            data = (ArrayList<MonitoredData>) Files.lines(Paths.get(fileName))
                    .map(s -> s.split("\\s{2}"))//each line split
                    .map(d -> new MonitoredData(d[0], d[1], d[2]))
                    .collect(Collectors.toList());

        }catch(IOException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        wr.write("\n\n\tMonitoredData structure contains:\n\n\n");

            for(int i=0; i<data.size(); i++) {
                wr.write(" start_time: " + data.get(i).getStartTime() +
                        "       end_time: " + data.get(i).getEndTime() + "       activity_label: " + data.get(i).getActivity() + "\n");
            }



    }

    public Map<Object, Long> task2(FileWriter wr) throws IOException {

        int count = 0;

        Map<Object, Long> temp = data.stream()
                .map(x -> x.getStartTime().split("\\s"))
                .map(x -> x[0].split("-"))
                .collect(Collectors.groupingBy(x -> Integer.parseInt(x[2]),Collectors.counting()));

        System.out.println("ss");
        for( Map.Entry<Object, Long> entry : temp.entrySet()){
            count++;
        }
        wr.write( "numarul de zile distincte care apar in monitoring data este : " + count);
        wr.close();

        return temp;

    }

    public Map<String, Integer> task3(FileWriter wr) throws IOException {

        //-----------------------------------------------task3
        System.out.println("------------------------------------------------task3");


        Map<String, Long> temp =
                data.stream().collect(Collectors.groupingBy(m -> m.getActivity(), Collectors.counting()));
        Map<String, Integer> occurences = new HashMap<>();

        for(Map.Entry<String, Long> entry : temp.entrySet()){
            occurences.put(entry.getKey(), entry.getValue().intValue());
            wr.write("Activity: " + String.valueOf(entry.getKey()).trim() + " appeared " + entry.getValue() + " times\n");

            System.out.println("Activity: " + String.valueOf(entry.getKey()).trim() + " appeared " + entry.getValue() + " times");
        }
        System.out.println("\n\n");

        return occurences;

    }
    public void writeTask4ToFile(FileWriter wr) throws IOException {
        for(Map.Entry<Integer, Map<String, Integer>> entry : activityCount.entrySet()){
            wr.write("\nday " + entry.getKey() + "\n");

            for(Map.Entry<String, Integer> entry2 : entry.getValue().entrySet()){
                wr.write("\tactivity " + entry2.getKey() + " happened " + entry2.getValue() + " times\n");

            }
        }
    }

    public Map<Integer, Map<String, Integer>> task4() throws IOException {

        int count = 0;

        Map<Integer, Map<String, Integer>> activityCountLocal = new HashMap<Integer, Map<String, Integer>>();


        for(Map.Entry<Object, Long> entry : nrOfactivitiesPerDay.entrySet()){
            List<MonitoredData> value = data.stream()
                    .filter(x -> Integer.parseInt(x.getStartTime().split("\\s")[0].split("-")[2]) == (int)entry.getKey())
                    .collect(Collectors.toList());//filtram pe zile


            Map<Object, Long> map = value.stream().collect(Collectors.groupingBy(x -> x.getActivity().trim(), Collectors.counting()));
            Map<String, Integer> temp = new HashMap<>();

            for(Map.Entry<Object, Long> entry2 : map.entrySet()){
                temp.put((String)entry2.getKey(), Integer.parseInt(Long.toString(entry2.getValue())));
            }



            activityCountLocal.put((Integer) entry.getKey(), temp);
        }
        
        return activityCountLocal;

    }


    public Map<String, Integer> task5() throws IOException {

        Map<String, Integer> returned = new HashMap<>();//list returned in this task
        ArrayList<String> activityListTemp = new ArrayList<String>();
        List<String> activities = new ArrayList<String>();//used for task6

        for(Map.Entry<String,Integer> entry : occurences.entrySet()){
            activityListTemp.add(entry.getKey());
        }

        for(String activ : activityListTemp){

            List<MonitoredData> list = data.stream().filter(x -> (x.getActivity().equals(activ))).collect(Collectors.toList());

            List<LocalDateTime> timeStart = list.stream().map(x ->
                    LocalDateTime.of(
                                     Integer.parseInt(x.getStartTime().split("\\s")[0].split("-")[0]),
                                     Integer.parseInt(x.getStartTime().split("\\s")[0].split("-")[1]),
                                     Integer.parseInt(x.getStartTime().split("\\s")[0].split("-")[2]),
                                     Integer.parseInt(x.getStartTime().split("\\s")[1].split(":")[0]),
                                     Integer.parseInt(x.getStartTime().split("\\s")[1].split(":")[1]),
                                     Integer.parseInt( x.getStartTime().split("\\s")[1].split(":")[2])))
                    .collect(Collectors.toList());

            List<LocalDateTime> timeEnd = list.stream().map(x ->
                    LocalDateTime.of(
                            Integer.parseInt(x.getEndTime().split("\\s")[0].split("-")[0]),
                            Integer.parseInt(x.getEndTime().split("\\s")[0].split("-")[1]),
                            Integer.parseInt(x.getEndTime().split("\\s")[0].split("-")[2]),
                            Integer.parseInt(x.getEndTime().split("\\s")[1].split(":")[0]),
                            Integer.parseInt(x.getEndTime().split("\\s")[1].split(":")[1]),
                            Integer.parseInt( x.getEndTime().split("\\s")[1].split(":")[2])))
                    .collect(Collectors.toList());


            DateWorkshop minFunction = ( List<LocalDateTime> a,  List<LocalDateTime> b) -> {
                int min = 0;
                for(int i=0;i<a.size();i++){
                    min += timeDifference(a.get(i), b.get(i));
                    if(min < 5) countLessThan5++;
                    else countMoreThan5++;
                }

                return min;
            };

            int minutes = minFunction.nrOfMinutes(timeStart, timeEnd);

            activities = task6(activities, activ);

            returned.put(activ, minutes);
        }


        underFiveMin = activities;
        return returned;

    }

    public void writeTask6ToFile(FileWriter wr) throws IOException {

        for(String activ : underFiveMin){
            wr.write("Activity " + activ.trim() + " - has more than 90% of the monitoring records with duration less than 5 minutes\n");
        }
    }

    public List<String> task6(List<String> list, String activ) throws IOException {

        if(countMoreThan5 * 9 < countLessThan5) {
            list.add(activ);
        }

        countLessThan5 = 0;
        countMoreThan5 = 0;

        return list;
    }


    public int timeDifference(LocalDateTime time1, LocalDateTime time2){

        int years = time2.getYear() - time1.getYear();
        int months = time2.getMonthValue()- time2.getMonthValue();
        int days = time2.getDayOfMonth() - time1.getDayOfMonth();
        int hours = time2.getHour() - time1.getHour();
        int minutes = time2.getMinute()- time1.getMinute();
        int seconds = time2.getSecond() - time1.getSecond();

        if(seconds < 0){
            seconds = 60 + seconds;
            minutes--;
        }
        if(minutes < 0){
            minutes = 60 + minutes;
            hours--;
        }
        if(hours < 0){
            hours = 24 + hours;
            days--;
        }
        if(days < 0){
            days = 60 + days;
            months--;
        }
        if(months < 0){
            months = 60 + months;
            years--;
        }

        int totalMin = 0;
        totalMin = hours * 60 + minutes + days * 24 * 60;

        return totalMin;
    }



}
