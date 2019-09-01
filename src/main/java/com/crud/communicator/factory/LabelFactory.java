package com.crud.communicator.factory;


import com.vaadin.flow.component.html.Label;

public final class LabelFactory {
    public static final String HEADER = "HEADER";
    public static final String SMALL_HEADER = "SMALL_HEADER";
    public static final String TEXT_BOLD = "TEXT_BOLD";
    public static final String TEXT_GRAY = "TEXT_GRAY";

    public final Label makeLabel(String label, String text) {
        switch (label) {
            case HEADER:
                return createHeader(text);
            case SMALL_HEADER:
                return createSmallHeader(text);
            case TEXT_BOLD:
                return createTextBold(text);
            case TEXT_GRAY:
                return createTextGray(text);
            default:
                return null;
        }
    }

    private Label createTextGray(String text) {
        Label textGray = new Label(text);
        textGray.getStyle().set("color", "#D0D0D0");
        textGray.getStyle().set("font-size", "10px");
        textGray.getStyle().set("font-style", "italic");
        textGray.getStyle().set("text-align", "left");
        return textGray;
    }

    private Label createTextBold(String text) {
        Label textBold = new Label(text);
        textBold.getStyle().set("color", "black");
        textBold.getStyle().set("font-weight", "bold");
        textBold.getStyle().set("font-size", "12px");
        textBold.getStyle().set("text-align", "left");
        return textBold;
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
