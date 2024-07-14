package main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import model.Rule;
import reader.G4Reader;

public class Main {

    private static final Pattern RULE_NAME = Pattern.compile("[_|a-z]*\n\s\s\s\s:");
    private static final String PATH = "D:\\Work\\IT\\java\\eclipse_rcp\\pgcodekeeper"
            + "\\ru.taximaxim.codekeeper.core\\antlr-src\\SQLParser.g4";

    public static void main(String[] args) throws IOException {
        Stream<String> lines = G4Reader.readFile(PATH);
        List<String> tempLines = new ArrayList<>();
        lines.forEach(tempLines::add);
        var sb = new StringBuilder();
        List<Rule> rules = new ArrayList<>();

        tempLines.forEach(e -> sb.append(e).append("\n"));
        Matcher m = RULE_NAME.matcher(sb);
        String start = null;
        String end = null;
        while (m.find()) {
            if (start == null) {
                start = m.group();
            } else if (end == null) {
                end = m.group();
                rules.add(getRule(start, end, sb));
            } else {
                start = end;
                end = m.group();
                rules.add(getRule(start, end, sb));
            }
        }
        if (end != null) {
            // collect the last rule
            rules.add(getRule(end, end, sb));
        }

//        for (var r : rules) {
//            if (r.getName().equals("operator_args_parser")) System.out.println(true);
//        }
//        System.out.println(rules.get(0));
//        System.out.println(rules.size());
        for (var r : rules) {
            System.out.println("-------------------------------------------------");
            System.out.println(r);
        }
        G4Reader.clouseReader();
    }

    private static Rule getRule(String start, String end, StringBuilder sb) {
        var name = start.substring(0, start.indexOf('\n'));
        var rule = new Rule(name);
        String body;
        int startIndex = sb.indexOf(start) + start.length();
        if (start.equals(end)) {
            // use this logic if this last rule
            body = sb.substring(startIndex);
            rule.setBody(body);
            return rule;
        }
        int endIndex = sb.indexOf(end);
        // TODO find the way how remove this checks. maybe change pattern in RULE_NAME can fix it.
        if (startIndex > endIndex) {
            // start index can be larger than end index some other parse rule ends by argument end
            endIndex = sb.indexOf('\n' + end);
            if (endIndex < 0) {
                // end index can be less than 0 when before parser rule have indent
                endIndex = sb.indexOf("\n " + end);
            }
        }
        body = sb.substring(startIndex, endIndex);
        rule.setBody(body);
        return rule;
    }
}
