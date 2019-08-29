package com.crud.communicator.view.form;

import com.crud.communicator.client.CommunicatorClient;
import com.crud.communicator.view.MainView;
import com.crud.communicator.view.confirmation.ConfirmationDialog;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Label;
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

    public Communicator(final MainView mainView){
        setVisible(false);
        this.mainView = mainView;
        createView();
        setBackground("white");
    }

    private void createView(){
        HorizontalLayout horizontalLayout = new HorizontalLayout(logger, signOut, deleteAccount);
        add(horizontalLayout);
        signOut.addClickListener(event -> logOut());
        signOut.setMinWidth("150px");
        deleteAccount.addClickListener(event -> confirmAccountDeletion());
        deleteAccount.setMinWidth("150px");
    }

    private void setBackground(String color){
        getStyle().set("background-color", color);
    }

    private void confirmAccountDeletion(){
        ConfirmationDialog confirm = new ConfirmationDialog("Confirm", "Are you sure that you want to delete this account?",
                event -> deleteTheAccount());
        confirm.open();
    }


    private void deleteTheAccount() {
        try {
            communicatorClient.deleteTheAccount(logger.getText());
            mainView.setVisibleOnLoginForm(true);
            setLogger("");
        } catch (HttpClientErrorException e) {
            String message = "LOGIN NOT EXIST";
            communicatorClient.showMessage(message);
        }
    }

    private void logOut(){
        try {
            communicatorClient.logOut(logger.getText());
            mainView.setVisibleOnLoginForm(true);
            setLogger("");
        } catch (HttpClientErrorException e) {
            String message = "LOGIN NOT EXIST";
            communicatorClient.showMessage(message);
        }
    }

    public void setLogger(String logger){
        this.logger.setText(logger);
    }
}
