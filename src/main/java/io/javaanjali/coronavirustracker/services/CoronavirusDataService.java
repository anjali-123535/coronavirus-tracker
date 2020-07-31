package io.javaanjali.coronavirustracker.services;

import io.javaanjali.coronavirustracker.model.LocationData;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class CoronavirusDataService {
    private static String BASE_URL="https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
    List<LocationData>list=new ArrayList<LocationData>();
    int sum=0;
    public List<LocationData> getList() {
        return list;
    }

    public int getSum() {
        return sum;
    }

    @PostConstruct
@Scheduled(cron = "* * 1 * * *")
    void fetchData() throws IOException, InterruptedException {
    List<LocationData>newlist=new ArrayList<LocationData>();

        HttpClient client=HttpClient.newHttpClient();
        HttpRequest request=HttpRequest.newBuilder().uri(URI.create(BASE_URL)).build();
        HttpResponse<String>response=client.send(request, HttpResponse.BodyHandlers.ofString());

    StringReader in =new StringReader(response.body());
    Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);
    for (CSVRecord record : records)
        {
            LocationData data=new LocationData();
            data.setState(record.get("Province/State"));
            data.setCountry(record.get("Country/Region"));
           int today=Integer.parseInt(record.get(record.size()-1));
            data.setToday_total(today);
            sum+=Integer.parseInt(record.get(record.size()-1));
        int prev=Integer.parseInt(record.get(record.size()-2));
            data.setPrev_total(prev);
         data.setDif_fromprevday(today-prev);
            newlist.add(data);
        }
    list=newlist;
    }
}
