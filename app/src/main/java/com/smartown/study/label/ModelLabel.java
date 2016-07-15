package com.smartown.study.label;

public class ModelLabel {

    String label = "";
    Class aClass;

    public ModelLabel(Class aClass, String label) {
        this.aClass = aClass;
        this.label = label;
    }

    public Class getaClass() {
        return aClass;
    }

    public String getLabel() {
        return label;
    }

}
