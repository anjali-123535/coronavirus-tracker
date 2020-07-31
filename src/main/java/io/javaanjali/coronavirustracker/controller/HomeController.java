package io.javaanjali.coronavirustracker.controller;

import io.javaanjali.coronavirustracker.CoronavirusTrackerApplication;
import io.javaanjali.coronavirustracker.model.LocationData;
import io.javaanjali.coronavirustracker.services.CoronavirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

//not a Rest controller becuse if this then all the meethods in this class will return the response in json which I dont want
//either I want to render a HTML page so just a  controller
@Controller
public class HomeController {
    //Autowiring feature of spring framework enables you to inject the object dependency implicitly.
    // It internally uses setter or constructor injection. Autowiring can't be used to inject primitive and string values.
    @Autowired
   CoronavirusDataService service;
    @GetMapping("/")
    public String  home(Model model)
    {
        List<LocationData> list = service.getList();
        int sum=list.stream().mapToInt(stat->stat.getToday_total()).sum();
        int new_cases=list.stream().mapToInt(stat->stat.getDif_fromprevday()).sum();
        model.addAttribute("data_list",list);
        model.addAttribute("totalReportedCases",sum);
        model.addAttribute("totalNewCases",new_cases);
        return "home";
    }
}
