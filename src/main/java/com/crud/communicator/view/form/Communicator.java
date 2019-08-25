package com.crud.communicator.view.form;

import com.crud.communicator.client.CommunicatorClient;
import com.crud.communicator.view.MainView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.web.client.HttpClientErrorException;

@Route
public class Communicator extends FormLayout {

    private CommunicatorClient communicatorClient = new CommunicatorClient();


    private Label logger = new Label();
    private Button signOut = new Button("Sign out");
    private MainView mainView;

    public Communicator(final MainView mainView){
        setVisible(false);
        this.mainView = mainView;
        VerticalLayout verticalLayout = new VerticalLayout(logger, signOut);
        add(verticalLayout);
        signOut.addClickListener(event -> logOut());
    }

    private void logOut(){
        try {
            communicatorClient.logOut(logger.getText());
            setVisible(false);
            mainView.setVisibleOnLoginForm(true);
            setLogger("");
        } catch (HttpClientErrorException e) {
            System.out.println(e);
        }
    }

    public void setLogger(String logger){
        this.logger.setText(logger);
    }
}
