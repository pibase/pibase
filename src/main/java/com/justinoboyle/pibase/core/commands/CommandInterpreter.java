package com.justinoboyle.pibase.core.commands;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Scanner;

import com.justinoboyle.pibase.core.java.core.PiBaseMain;
import com.justinoboyle.pibase.core.java.plugin.PiPlugin;

public class CommandInterpreter {

    public static final HashMap<String, String> vars = new HashMap<String, String>();

    public boolean interpret(Scanner sc) {
        return interpret(sc.nextLine());
    }
    
    public boolean interpret(String command) {
        boolean b = false;
        if(command.contains("\\;"))
        command = command.replace("\\;", "%3B");
        if(!command.contains(";")) return interpretCommand(command);
        for(String s : command.split(";"))
            if(interpretCommand(s)) b = true;
        return b;
    }
    
    public boolean interpretCommand(String command) {
        command = command.replace("%3B", ";");
        if (!command.startsWith("drop"))
            for (String s : vars.keySet()) {
                if(command.contains("$" + s)) {
                    command = command.replace("$" + s, vars.get(s));
                }
            }
        String[] args = command.split(" ");
        String name = args[0].toLowerCase();
        if (name.startsWith("$") && !name.startsWith("exec")) {
            final String varName = name.substring(1).toLowerCase();
            args = shift(args);
            name = args[0];
            
            PrintStream stream = System.out;
            VariablePrintStream.setNewOut(varName);

            boolean ret = runCommand(name, args);
            String temp = "";
            if (vars.containsKey(varName)) {
                temp = vars.get(varName);
                vars.remove(varName);
            }
            if (temp.endsWith("\n")) {
                temp = temp.substring(0, temp.length() - 1);
            }
            vars.put(varName, temp);
            System.setOut(stream);
            System.out.println("Set $" + varName + " to " + (vars.containsKey(varName) ? vars.get(varName).replace("\n", "\\n") : "null"));
            return ret;
        }
        boolean ret = runCommand(name, args);
        return ret;
    }
    
    public static boolean runCommand(String cmd) {
        return runCommand(cmd.split(" ")[0], cmd.split(" "));
    }

    public static boolean runCommand(String name, String[] args) {
        for (PiPlugin plugin : PiBaseMain.plugins) {
            if (plugin.onCommand(name, shift(args)))
                return false;
        }
        if (name.equals("stop")) { return true; }
        System.out.println("Unknown command");
        return false;
    }

    private static String[] shift(String[] input) {
        String[] n = new String[Math.max(input.length - 1, 0)];
        for (int i = 1; i < input.length; i++)
            n[i - 1] = input[i];
        return n;
    }

}
