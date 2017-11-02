package cl.intelliware.smartlab.controllers;

import cl.intelliware.smartlab.utils.PyInterpreter;
import cl.intelliware.smartlab.models.Submission;
import cl.intelliware.smartlab.repositories.SubmissionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/python")
public class PythonInterpreterAPI
{
    @PostMapping(path = "/")
    public @ResponseBody Void postSubmission(@RequestBody PythonRequest request){
        PyInterpreter interpreter = new PyInterpreter(request.getCode());

        interpreter.run();

        return null;
    }
    @GetMapping(path="/")
    public @ResponseBody String test(){
        PyInterpreter interpreter=new PyInterpreter("2+2");
        String output= interpreter.run();
        System.out.println(output);
        return output;
    }

}

class PythonRequest{
    private String code;

    public PythonRequest() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
