package com.github.egatlovs.mock;

import com.github.egatlovs.variablemanager.annotations.FieldName;
import com.github.egatlovs.variablemanager.annotations.Ignore;
import com.github.egatlovs.variablemanager.annotations.ObjectValue;
import org.camunda.bpm.engine.variable.Variables;

import java.math.BigDecimal;

@ObjectValue(storeFields = true)
public class ResultObjectMockNestedObject {

    private String someString = "string";
    @FieldName(name = "fieldName", prefix = "fieldPrefix")
    private String annotated = "annotatedString";
    @FieldName(name = "myDecimal")
    private BigDecimal decimal = BigDecimal.ONE;
    @Ignore
    private Object ignoredField;
    @ObjectValue(serializationFormat = Variables.SerializationDataFormats.XML)
    private NestedObject nestedObject;

    public ResultObjectMockNestedObject() {
        this.nestedObject = new NestedObject();
    }

    public NestedObject getNestedObject() {
        return nestedObject;
    }

    public void setNestedObject(NestedObject nestedObject) {
        this.nestedObject = nestedObject;
    }

    public String getSomeString() {
        return someString;
    }

    public void setSomeString(String someString) {
        this.someString = someString;
    }

    public String getAnnotated() {
        return annotated;
    }

    public void setAnnotated(String annotated) {
        this.annotated = annotated;
    }

    public BigDecimal getDecimal() {
        return decimal;
    }

    public void setDecimal(BigDecimal decimal) {
        this.decimal = decimal;
    }

    public Object getIgnoredField() {
        return ignoredField;
    }

    public void setIgnoredField(Object ignoredField) {
        this.ignoredField = ignoredField;
    }

}
