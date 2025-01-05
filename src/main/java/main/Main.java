package main;

import java.io.File;
import java.util.Map;

import model.Rule;
import parser.RuleParser;
import reader.G4Reader;

public class Main {

    private static final String PATH = new File("src/test/resources/SQLParser.g4").getAbsolutePath();

    private static Map<String, Rule> rules;

    public static void main(String[] args) {
        var parserRules = G4Reader.readParser(PATH);
        rules = RuleParser.parse(parserRules);
        System.out.println(rules.size());
        System.out.println(rules.get("vex"));
    }
}
