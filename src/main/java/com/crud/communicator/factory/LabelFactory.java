package com.crud.communicator.factory;


import com.vaadin.flow.component.html.Label;

public final class LabelFactory {
    public static final String HEADER = "HEADER";

    public final Label makeLabel(String label, String text) {
        switch (label) {
            case HEADER:
                return createHeader(text);
            default:
                return null;
        }
    }

    private Label createHeader(String text) {
        Label header = new Label(text);
        header.getStyle().set("color", "#0066FF");
        header.getStyle().set("font-weight", "bold");
        header.getStyle().set("font-size", "22px");
        header.getStyle().set("letter-spacing", "2px");
        return header;
    }
}
