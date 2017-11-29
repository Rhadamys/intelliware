package cl.intelliware.smartlab.utils.PyInterpreter;

import cl.intelliware.smartlab.models.TestCase;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.concurrent.*;

import org.python.util.PythonInterpreter;
import org.python.core.*;

import java.io.ByteArrayOutputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Properties;

public class PyInterpreter {
    private String code;
    private TestCase[] testCases;
    private int timeout = 10;

    public PyInterpreter(String code){
        this.code = code;
    }
    public PyInterpreter(String code, TestCase[] testCases){
        this.code = code;
        this.testCases = testCases;
    }

    public String run(String input){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<String> future = executor.submit(new JythonTask(this.code,input));

        String output;

        try {
            output = future.get(this.timeout, TimeUnit.SECONDS);
        }catch (TimeoutException e){
            output = "Esto se esta demorando mucho.\n¿El código tendrá algun ciclo infinito?";
        } catch (InterruptedException e) {
            e.printStackTrace();
            output = "El servidor tuvo un problema. Intenta de nuevo.";
        } catch (ExecutionException e) {
            output = e.getCause().toString();
        }

        executor.shutdownNow();

        return output;
    }

    public String run(){
        return this.run("");
    }

    public void runTestCases(){
        for (TestCase testCase:testCases) {
            String output = run(testCase.getInput());
            System.out.println(output);
        }
    }

    public void setTestCases(TestCase[] testCases) {
        this.testCases = testCases;
    }
    public void setTimeout(Integer timeoutInSeconds){ this.timeout = timeoutInSeconds; }
}

