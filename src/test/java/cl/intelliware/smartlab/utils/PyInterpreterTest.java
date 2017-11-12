package cl.intelliware.smartlab.utils;

import cl.intelliware.smartlab.models.TestCase;
import cl.intelliware.smartlab.utils.PyInterpreter;
import org.dom4j.CharacterData;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.python.core.PyException;

public class PyInterpreterTest {
    private PyInterpreter interprete;

    private TestCase testCase1;
    private TestCase testCase2;
    private TestCase testCase3;
    @Before
    public void create(){
        testCase1=new TestCase("","2");
        testCase2= new TestCase("6","6");
        testCase3=new TestCase("5","25");
    }
    @Test
    public void testVariable() throws PyException {
        System.out.println("testVariable");
        String code = "i=2\nprint i";
        interprete = new PyInterpreter(code);
        String output = interprete.run(testCase1.getInput());
        Assert.assertEquals(output,testCase1.getOutput());
    }
    @Test
    public void testInputDataAndLoop() throws PyException {
        System.out.println("testInputDataAndLoop");
        String code="limit=input()\ni=0\nwhile i<limit:" +
                "\n\ti=i+1\nprint i";
        interprete = new PyInterpreter(code);
        String output=interprete.run(testCase2.getInput());
        Assert.assertEquals(output,testCase2.getOutput());
    }
    @Test
    public void testFunction() throws PyException {
        String code="def alCuadrado(numero):\n" +
                "\treturn numero*numero\n" +
                "entrada=input()\n" +
                "cuadrado=alCuadrado(entrada)\n" +
                "print cuadrado";
        interprete = new PyInterpreter(code);
        String exitTest=interprete.run(testCase3.getInput());
        Assert.assertEquals(exitTest,testCase3.getOutput());
    }
}
