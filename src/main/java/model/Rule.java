package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Rule {
    private String name;
    private String body;
    private List<Rule> parents = new ArrayList;
    private List<Rule> children = new ArrayList<>();

    public Rule(String name) {
        this.name = name;
    }

    public void setBody(String body){
        this.body = body;
    }

    public void addChild(Rule child) {
        children.add(child);
        child.parents.add(this);
    }

    @Override
    public String toString() {
        return "Rule{" +
                "name='" + name + '\'' +
                ", body='" + body + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Rule)) {
            return false;
        }
        Rule rule = (Rule) o;
        return Objects.equals(name, rule.name)
                && Objects.equals(body, rule.body)
                && Objects.equals(parents, rule.parents)
                && Objects.equals(children, rule.children);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, body, parents, children);
    }
}
