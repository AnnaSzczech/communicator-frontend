package com.crud.communicator.view.form;

import com.crud.communicator.client.CommunicatorClient;
import com.crud.communicator.view.MainView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.web.client.HttpClientErrorException;

@Route
public class Communicator extends FormLayout {

    private CommunicatorClient communicatorClient = new CommunicatorClient();


    private Label logger = new Label();
    private Button signOut = new Button("Sign out");
    private Button deleteAccount = new Button("Delete account");
    private MainView mainView;


    private Label a = new Label();

    public Communicator(final MainView mainView){
        a.setWidth("300px");
        setVisible(false);
        this.mainView = mainView;
        HorizontalLayout horizontalLayout = new HorizontalLayout(a, logger, signOut, deleteAccount);
        add(horizontalLayout);
        signOut.addClickListener(event -> logOut());
        signOut.setMinWidth("150px");
        deleteAccount.addClickListener(event -> deleteTheAccount());
        deleteAccount.setMinWidth("150px");
    }

    private void deleteTheAccount() {
        try {
            communicatorClient.deleteTheAccount(logger.getText());
            setVisible(false);
            mainView.setVisibleOnLoginForm(true);
            setLogger("");
        } catch (HttpClientErrorException e) {
            String message = "LOGIN NOT EXIST";
            CommunicatorClient.LOGGER.error(message);
            Notification.show(message).setPosition(Notification.Position.MIDDLE);
        }
    }

    private void logOut(){
        try {
            communicatorClient.logOut(logger.getText());
            setVisible(false);
            mainView.setVisibleOnLoginForm(true);
            setLogger("");
        } catch (HttpClientErrorException e) {
            String message = "LOGIN NOT EXIST";
            CommunicatorClient.LOGGER.error(message);
            Notification.show(message).setPosition(Notification.Position.MIDDLE);
        }
    }

    public void setLogger(String logger){
        this.logger.setText(logger);
    }
}
