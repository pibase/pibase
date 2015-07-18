/**
 * by Justin O'Boyle
 * www.justinoboyle.com
 * on Jul 18, 2015
 * @formatter-on
 */
package com.justinoboyle.pibase.core.commands;

import java.io.PrintStream;

public class VariablePrintStream {
    public static final String AUTHOR = "www.justinoboyle.com";
    public static void setNewOut(String varName) {
        System.setOut(new PrintStream(System.out) {
            public void print(String s) {
                String temp = "";
                if(CommandInterpreter.vars.containsKey(varName)) {
                    temp = CommandInterpreter.vars.get(varName);
                    CommandInterpreter.vars.remove(varName);
                }
                temp += s;
                CommandInterpreter.vars.put(varName, temp);
            }
            public void println(String s) {
                String temp = "";
                if(CommandInterpreter.vars.containsKey(varName)) {
                    temp = CommandInterpreter.vars.get(varName);
                    CommandInterpreter.vars.remove(varName);
                }
                temp += s + "\n";
                CommandInterpreter.vars.put(varName, temp);
            }
            public void print(Object s) {
                print(s.toString());
            }
            public void println(Object s) {
                println(s.toString());
            }
            public void print(int i) {
                print(i + "");
            }
            public void println(int i) {
                println(i + "");
            }
            public void print(boolean b) {
                print(b + "");
            }
            public void println(boolean b) {
                println(b + "");
            }
            public void print(char c) {
                print(c + "");
            }
            public void println(char c) {
                println(c + "");
            }
            public void print(char[] c) {
                print(new String(c));
            }
            public void println(char[] c) {
                println(new String(c));
            }
            public void print(double d) {
                print(d + "");
            }
            public void println(double d) {
                println(d + "");
            }
            public void print(float f) {
                print(f + "");
            }
            public void println(float f) {
                println(f + "");
            }
        });
    }
}