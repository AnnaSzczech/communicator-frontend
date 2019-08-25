package com.crud.communicator.view;

import com.crud.communicator.view.form.Communicator;
import com.crud.communicator.view.form.LoginForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.stereotype.Component;


@Component
@Route
public class MainView extends VerticalLayout {
    private LoginForm loginForm = new LoginForm(this);
    private Communicator communicator = new Communicator(this);

    public MainView(){
        add(loginForm, communicator);

    }

    public void setVisibleOnLoginForm(boolean isVisible){
        loginForm.setVisible(isVisible);
    }

    public void setVisibleOnCommunicator(boolean isVisible){
        communicator.setVisible(isVisible);
    }

    public void setLogger(String logger) {communicator.setLogger(logger);}
}
