package cl.intelliware.smartlab;

import org.python.util.PythonInterpreter;
import org.python.core.*;

//SINGLETON
public class Python {
    private static PythonInterpreter instance;

    private Python(){}

    public static PythonInterpreter getInstance(){
        if(instance == null){
            instance = new PythonInterpreter();
        }
        return instance;
    }
}
