package main;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.Rule;
import reader.G4Reader;

public class Main {

    private static final Pattern RULE_NAME = Pattern.compile("\n\\u0020?[a-z][_a-z]*\n\\u0020*:");
    private static final Pattern CHILD_PATTERN = Pattern.compile("[a-z][_a-z]*");

    private static final String PATH = "D:\\Work\\IT\\java\\eclipse_rcp\\pgcodekeeper"
            + "\\ru.taximaxim.codekeeper.core\\antlr-src\\SQLParser.g4";
    private static final Map<String, Rule> rules = new HashMap<>();

    public static void main(String[] args) {
        var parserRules = G4Reader.readParser(PATH);
        Matcher m = RULE_NAME.matcher(parserRules);
        String start = null;
        String end = null;
        while (m.find()) {
            if (start == null) {
                start = m.group();
            } else if (end == null) {
                end = m.group();
                getRule(start, end, parserRules);
            } else {
                start = end;
                end = m.group();
                getRule(start, end, parserRules);
            }
        }
        if (end != null) {
            // collect the last rule
            getRule(end, end, parserRules);
        }

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

    private static void getRule(String start, String end, String sb) {
        var name = start;
        name = name.replace("\n", "").replace(" ", "").replace(":", "");
        var rule = new Rule(name);
        String body;
        int startIndex = sb.indexOf(start) + start.length();
        if (start.equals(end)) {
            // use this logic if this last rule
            body = sb.substring(startIndex);
            rule.setBody(body);
            rules.put(name, rule);
            return;
        }
        int endIndex = sb.indexOf(end);
        body = sb.substring(startIndex, endIndex);
        rule.setBody(body);
        rules.put(name, rule);
    }
}
