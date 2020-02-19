import com.google.gson.JsonObject;
import com.sun.prism.PresentableState;
import connect.Api;
import javafx.geometry.Pos;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



public class PostRequest {
    static volatile int j = 0;
    private static final String COMPANY = "don6uwbg3rgmw";
    private static final String URL = "https://" + COMPANY + ".quickbpm.ru/pub/v1";
    private static final String URLcreate = "/app/finally/final/create";
    private static final String X_TOKEN = "a71032a0-db8e-44fe-8aa3-4b823a457932";
    static ArrayList<String> firstnames = new ArrayList<String>();
    static ArrayList<String> lastnames = new ArrayList<String>();
    static ArrayList<String> middlenames = new ArrayList<String>();
    static ArrayList<String> logins = new ArrayList<String>();
    static List<Double> runtime = new ArrayList<Double>();

    static {
        for (int i = 0; i < 999; i++) {
            firstnames.add("Ivan" + i);
            lastnames.add("Ivanov" + i);
            middlenames.add("Ivanovich" + i);
            logins.add("ivanushka" + i);

        }
    }

    private static double calculateAverage(List<Double> avgs) {
        Double sum = 0.0;
        for (Double avg : avgs) {
            sum += avg;
        }
        return sum.doubleValue() / avgs.size();
    }

    public static void main(String[] args) throws IOException {
        Api api = new Api(URL, X_TOKEN);

        for (int i = 0; i < 999; i++) {
            String payload = "{\n" + "  \"context\": {\n" + "    \"__name\": \"" + logins.get(i) + "\",\n" + "          \"f_i_o\": {\n" +
                    "            \"lastname\": \"" + lastnames.get(i) + "\",\n" + "            \"middlename\": \"" + middlenames.get(i) + "\",\n" +
                    "            \"firstname\": \"" + firstnames.get(i) + "\"\n" + "     }\n" + "  }\n" + "}";

            Runnable task = () -> {
                while (j < 1) {
                    try {
                        long startTime = System.currentTimeMillis();
                        api.post(URLcreate, payload);
                        long endTime = System.currentTimeMillis();
                        double resultTime = (endTime - startTime) * 1.0 / 1000;
                        runtime.add(resultTime);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    j++;
                }
            };
            Thread thread1 = new Thread(task);
            Thread thread2 = new Thread(task);
            Thread thread3 = new Thread(task);
            Thread thread4 = new Thread(task);
            thread1.start();
            thread2.start();
            thread3.start();
            thread4.start();
        }
                    Double max = (Collections.max(runtime));
            Double min = (Collections.min(runtime));
            Double avg = PostRequest.calculateAverage(runtime);
            System.out.println("Максимальное время выполнения: " + max + " sec");
            System.out.println("Минимальное время выполнения: " + min + " sec");
            System.out.println("Среднее время выполнения: " + avg + " sec");
        }
}

