package cl.intelliware.smartlab.controllers;

import cl.intelliware.smartlab.utils.PyInterpreter.PyInterpreter;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/python", produces="application/json")
public class PythonInterpreterAPI
{
    @PostMapping(path = "/")
    public @ResponseBody PythonResponse postSubmission(@RequestBody PythonRequest request){
        PyInterpreter interpreter = new PyInterpreter(request.getCode());

        String response;
        try {
            response = interpreter.run();
        }catch(Exception ex){
            ex.printStackTrace();
            response = ex.getMessage();
        }
        PythonResponse objResponse = new PythonResponse();
        objResponse.setResponse(response.trim());
        return objResponse;
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

class PythonResponse{
    private String response;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
