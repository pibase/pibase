package com.justinoboyle.pibase.core.commands;

import java.util.Scanner;

public class CommandInterpreter {

    public boolean interpret(Scanner sc) {
        String command = sc.nextLine();
        String name = command.split(" ")[0].toLowerCase();
        if(name.equals("stop")) {
            return true;
        }
        return false;
    }
    
}
