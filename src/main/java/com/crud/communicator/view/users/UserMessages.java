package com.crud.communicator.view.users;

import com.crud.communicator.client.MessageClient;
import com.crud.communicator.domain.MessageDto;
import com.crud.communicator.factory.LabelFactory;
import com.crud.communicator.view.component.ComponentLook;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.listbox.ListBox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;


@Route
public class UserMessages extends VerticalLayout {

    private MessageClient messageClient = new MessageClient();
    private LabelFactory labelFactory = new LabelFactory();
    private ListBox<MessageDto> messages;
    private TextField newMessage;
    private Button send;
    private Button loveCalculator;

    public UserMessages(){
        createLoveCalculatorButton();
        this.messages = createMessagesList();
        add(messages);
        messages.getItemEnabledProvider();
        createTextingField();
        setSpacing(false);

    }

    private void createLoveCalculatorButton() {
        loveCalculator = new Button("LOVE CALCULATOR!");
        ComponentLook componentLook = new ComponentLook(loveCalculator);
        componentLook.setComponentLook("200px", "50px", "blue", false);
        loveCalculator.getStyle().set("color", "white");
        add(loveCalculator);
    }

    private ListBox<MessageDto> createMessagesList() {
        ListBox<MessageDto> messages = new ListBox<>();
        ComponentLook componentLook = new ComponentLook(messages);
        componentLook.setComponentLook("100%", "95%", "white", false);
        messages.setMaxHeight("700px");
        messages.setReadOnly(true);
        messages.setRenderer(new ComponentRenderer<>(message -> {
            Label sender = labelFactory.makeLabel(LabelFactory.TEXT_BOLD, message.getSenderLogin());
            Label date = labelFactory.makeLabel(LabelFactory.TEXT_GRAY, message.getDate());
            Label text = new Label(message.getMessage());
            HorizontalLayout horizontalLayout = new HorizontalLayout();
            horizontalLayout.add(sender, date);

            return new Div(horizontalLayout, text);
        }));
        return messages;
    }

    public void refresh(String sender, String recipient){
        messages.setItems(messageClient.getConversation(sender, recipient));
        newMessage.focus();
    }

    public void clear(){
        messages.setItems(new ArrayList<>());
    }

    private void createTextingField(){
        newMessage = new TextField();
        ComponentLook componentLook = new ComponentLook(newMessage);
        componentLook.setComponentLook("95%", "15px", null, false);
        createSendButton();
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.add(newMessage, send);
        horizontalLayout.setWidthFull();
        add(horizontalLayout);
    }

    private void createSendButton(){
        send = new Button();
        Icon icon = VaadinIcon.ANGLE_DOUBLE_RIGHT.create();
        ComponentLook componentLook = new ComponentLook(send);
        componentLook.setComponentLook("50px", "38px", "blue", false);
        icon.setColor("white");
        send.setIcon(icon);
    }

    public Button getSend() {
        return send;
    }

    public TextField getNewMessage() {
        return newMessage;
    }

    public Button getLoveCalculator(){
        return loveCalculator;
    }
}