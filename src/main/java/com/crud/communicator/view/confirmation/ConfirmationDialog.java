package com.crud.communicator.view.confirmation;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class ConfirmationDialog extends Dialog {
    private Label title;
    private Label question;
    private Button confirm;

    private ConfirmationDialog(){
        createHeader();
        createContent();
        createFooter();
    }

    private void createHeader() {
        this.title = new Label();
        Button close = new Button();
        close.setIcon(VaadinIcon.CLOSE.create());
        close.addClickListener(event -> close());

        HorizontalLayout header = new HorizontalLayout();
        header.add(title, close);
        header.setFlexGrow(1, this.title);
        header.setAlignItems(FlexComponent.Alignment.CENTER);
        add(header);
    }

    private void createContent() {
        question = new Label();

        VerticalLayout content = new VerticalLayout();
        content.add(question);
        content.setPadding(false);
        add(content);
    }

    private void createFooter() {
        Button abort = new Button("Abort");
        abort.addClickListener(buttonClickEvent -> close());
        abort.getStyle().set("background-color", "blue");
        abort.getStyle().set("color", "white");

        confirm = new Button("Confirm");
        confirm.addClickListener(buttonClickEvent -> close());
        confirm.getStyle().set("background-color", "blue");
        confirm.getStyle().set("color", "white");

        HorizontalLayout footer = new HorizontalLayout();
        footer.add(abort, confirm);
        footer.setJustifyContentMode(FlexComponent.JustifyContentMode.END);
        add(footer);
    }

    public ConfirmationDialog(String title, String content, ComponentEventListener listener){
        this();
        setTitle(title);
        setQuestion(content);
        addConfirmationListener(listener);
    }

    private void addConfirmationListener(ComponentEventListener listener) {
        confirm.addClickListener(listener);
    }

    private void setTitle(String title) {
        this.title.setText(title);
    }

    private void setQuestion(String question) {
        this.question.setText(question);
    }
}
