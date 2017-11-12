package cl.intelliware.smartlab.utils.PyInterpreter;

import org.python.core.PyException;
import org.python.util.PythonInterpreter;
import org.python.core.*;

import java.io.ByteArrayOutputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.Callable;

public class JythonTask implements Callable<String> {
    private String code;
    private String input;
    private PythonInterpreter interpreter;
    private ByteArrayOutputStream outputStream;


    public JythonTask(String code, String input){
        this.code = code;
        this.input = input;

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

    @Override
    public String call() throws PyException{
        interpreter.setIn(new StringReader(input));
        interpreter.exec(this.code);
        return new String(outputStream.toByteArray());
    }
}
