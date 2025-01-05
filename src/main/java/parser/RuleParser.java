package parser;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.Rule;

public final class RuleParser {

    private static final Pattern RULE_NAME = Pattern.compile("\n\\u0020?[a-z][_a-z]*\n\\u0020*:");
    private static final Pattern CHILD_PATTERN = Pattern.compile("[a-z][_a-z]*");

    public static Map<String, Rule> parse(String context) {
        Matcher m = RULE_NAME.matcher(context);
        String start = null;
        String end = null;
        Map<String, Rule> rules = new HashMap<>();
        while (m.find()) {
            if (start == null) {
                start = m.group();
            } else if (end == null) {
                end = m.group();
                getRule(start, end, context, rules);
            } else {
                start = end;
                end = m.group();
                getRule(start, end, context, rules);
            }
        }
        if (end != null) {
            // collect the last rule
            getRule(end, end, context, rules);
        }
        return rules;
    }

    private static void getRule(String start, String end, String sb, Map<String, Rule> rules) {
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