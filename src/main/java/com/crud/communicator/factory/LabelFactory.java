package com.crud.communicator.factory;


import com.vaadin.flow.component.html.Label;

public final class LabelFactory {
    public static final String HEADER = "HEADER";
    public static final String SMALL_HEADER = "SMALL_HEADER";

    public final Label makeLabel(String label, String text) {
        switch (label) {
            case HEADER:
                return createHeader(text);
            case SMALL_HEADER:
                return createSmallHeader(text);
            default:
                return null;
        }
    }

    private Label createSmallHeader(String text) {
        Label smallHeader = new Label(text);
        smallHeader.setMaxHeight("13px");
        smallHeader.getStyle().set("color", "white");
        smallHeader.getStyle().set("font-weight", "bold");
        smallHeader.getStyle().set("font-size", "14px");
        smallHeader.getStyle().set("text-decoration", "underline");
        smallHeader.getStyle().set("text-align", "left");
        return smallHeader;
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
