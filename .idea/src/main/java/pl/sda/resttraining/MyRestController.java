package pl.sda.resttraining;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class MyRestController {

    private List<MyMessage> messages=new ArrayList<>();

    @GetMapping (value="/message")//to reaguje na GET
    @ResponseBody
    public String getMessage(@RequestParam(required = false) String text){

        return messages.stream().map(e->"Hello"+e).collect(Collectors.joining("\n"));
    }
    @PostMapping(value = "/message")
    @ResponseBody
    public String addMessage(@RequestBody MyMessage message){
        messages.add(message);
        return "Hello"+message+"!";
    }
    @GetMapping(value = "/message/(id)")
    public String getMessageById(@PathVariable String id){
        return messages.stream().filter(e->e.getId().equals(Integer.parseInt(id)))
                .findFirst()
                .map(e->e.getMessage())
                .orElse("brak");
    }

}
