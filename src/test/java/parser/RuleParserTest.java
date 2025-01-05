package parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import reader.TestUtils;

public class RuleParserTest {

    private static final String RULE_ARR = """
Rule{name='arr', body=' '[' value (',' value)* ']'
    | '[' ']'
    ;
'}""";

    @Test
    void testRulesSize() {
        var rules = TestUtils.getRules(TestUtils.JSON);
        assertEquals(5, rules.size());
    }

    @Test
    void testGetRule() {
        assertEquals(RULE_ARR, TestUtils.getRules(TestUtils.JSON).get("arr").toString());
    }
}
