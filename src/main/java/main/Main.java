package main;

import java.util.Map;

import model.Rule;
import parser.RuleParser;
import reader.G4Reader;

public class Main {

    private static final String PATH = "D:\\git\\pgcodekeeper\\ru.taximaxim.codekeeper.core\\antlr-src\\SQLParser.g4";

    private static Map<String, Rule> rules;

    public static void main(String[] args) {
        var parserRules = G4Reader.readParser(PATH);
        rules = RuleParser.parse(parserRules);

//        for (var r : rules) {
//            if (r.getName().equals("operator_args_parser")) System.out.println(true);
//        }
//        System.out.println(rules.get(0));
        System.out.println(rules.size());
        System.out.println(rules.get("vex"));

//        for (var r : rules.values()) {
//            System.out.println("-------------------------------------------------");
//            System.out.println(r);
//        }

//        for (var s : rules.entrySet()) {
//            var parent = s.getValue();
//            m = CHILD_PATTERN.matcher(parent.getBody());
//            if (parent.getName().equals("indirection")) {
//                System.out.println("hi");
//            }
//            while (m.find()) {
//                var childName = m.group();
//                var child = rules.get(childName);
//                if (child == null) {
//                    System.out.println(parent.getName());
//                    System.out.println(childName);
//                }
//                parent.addChild(child);
//            }
//        }

//        for (var r : rules.values()) {
//            System.out.println("----------------------------------------------------");
//            System.out.println(r.getName());
//            System.out.println(r.getChildren().size());
//            System.out.println(r.getParents().size());
//        }
    }
}
