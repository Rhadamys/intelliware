package cl.intelliware.smartlab.utils;

import cl.intelliware.smartlab.models.TestCase;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.python.util.PythonInterpreter;
import org.python.core.*;

import java.io.ByteArrayOutputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Properties;

public class PyInterpreter {
    private PythonInterpreter interpreter;
    private ByteArrayOutputStream outputStream;
    private String code;
    private TestCase[] testCases;

    public PyInterpreter(String code){
        this.code = code;
        //this.testCases = testCases;

        this.interpreter = createInterpreter();

        outputStream = new ByteArrayOutputStream();
        interpreter.setOut(outputStream);
    }

    private PythonInterpreter createInterpreter(){
        Properties props = new Properties();
        //props.put("python.home","./lib/jython-2.7.0");
        props.put("python.console.encoding", "UTF-8"); // Used to prevent: console: Failed to install '': java.nio.charset.UnsupportedCharsetException: cp0.
        props.put("python.security.respectJavaAccessibility", "false"); //don't respect java accessibility, so that we can access protected members on subclasses
        props.put("python.import.site","false");

        Properties preprops = System.getProperties();

        PythonInterpreter.initialize(preprops, props, new String[0]);
        return new PythonInterpreter();
    }

    public String run(String input){
        interpreter.setIn(new StringReader(input));
        String output = null;

        try {
            interpreter.exec(this.code);
            output = new String(outputStream.toByteArray());
            System.out.println(output);
        }
        catch (Exception e){
            System.out.println(e);
        }

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
}
