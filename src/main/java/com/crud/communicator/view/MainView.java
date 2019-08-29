package com.crud.communicator.view;

import com.crud.communicator.view.form.Communicator;
import com.crud.communicator.view.form.LoginForm;
import com.crud.communicator.view.form.NewAccountForm;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Location;
import com.vaadin.flow.router.Route;
import org.springframework.stereotype.Component;

@Route
public class MainView extends VerticalLayout {
    private LoginForm loginForm = new LoginForm(this);
    private Communicator communicator = new Communicator(this);
    private NewAccountForm newAccountForm = new NewAccountForm(this);

    public MainView(){
        HorizontalLayout horizontalLayout = new HorizontalLayout(loginForm, communicator, newAccountForm);
        horizontalLayout.setWidthFull();
        add(horizontalLayout);

    }

    public void setVisibleOnLoginForm(boolean isVisible){
        loginForm.setVisible(isVisible);
    }

    public void setVisibleOnCommunicator(boolean isVisible){
        communicator.setVisible(isVisible);
    }

    public void setLogger(String logger) {communicator.setLogger(logger);}

    public void setVisibleOnNewAccountForm(boolean isVisible) {
        newAccountForm.setVisible(isVisible);
    }
}
