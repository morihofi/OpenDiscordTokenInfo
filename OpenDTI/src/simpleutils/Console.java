package simpleutils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;

public class Console {
    BufferedReader br;
    PrintStream ps;

    public Console(){
        br = new BufferedReader(new InputStreamReader(System.in));
        ps = System.out;
    }

    public String readLine(String out){
        ps.format(out);
        try{
            return br.readLine();
        }catch(IOException e)
        {
            return null;
        }
    }
    public PrintStream format(String format, Object...objects){
        return ps.format(format, objects);
    }
    
    public void print(String string){
        ps.print(string);
    }
    public void println(String string){
        ps.println(string);
    }
}

