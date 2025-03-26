package org.example.workflow.core;

import java.util.HashMap;
import java.util.Map;

public class Context {
    private Map<String, Object> attributes = new HashMap<>();

    public Context withActor(Object actor) {
        attributes.put("actor", actor);
        return this;
    }

    public Context withComment(String comment) {
        attributes.put("comment", comment);
        return this;
    }

    public Object getActor() {
        return attributes.get("actor");
    }

    public String getComment() {
        return (String) attributes.get("comment");
    }

    public Context withAttribute(String key, Object value) {
        attributes.put(key, value);
        return this;
    }

    public Object getAttribute(String key) {
        return attributes.get(key);
    }
}