package com.crud.communicator.view;

import com.crud.communicator.view.communicator.Communicator;
import com.crud.communicator.view.form.LoginForm;
import com.crud.communicator.view.form.NewAccountForm;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;


@Route
public class MainView extends VerticalLayout {
    private LoginForm loginForm = new LoginForm(this);
    private Communicator communicator = new Communicator(this);
    private NewAccountForm newAccountForm = new NewAccountForm(this);

    private Label gap = new Label();

    public MainView(){
        gap.setMinHeight("50px");
        gap.getStyle().set("background-color", "white");
        gap.setWidthFull();
        add(gap);
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setSizeFull();
        horizontalLayout.add(loginForm, communicator, newAccountForm);
        horizontalLayout.getStyle().set("margin-left", "auto");
        horizontalLayout.getStyle().set("margin-right", "auto");
        horizontalLayout.setAlignItems(Alignment.CENTER);
        add(horizontalLayout);
        setSizeFull();
        getStyle().set("background-color", "#EEEEEE");
//        setSpacing(false);

    }

    public void setVisibleOnLoginForm(boolean isVisible){
        loginForm.setVisible(isVisible);
        newAccountForm.setVisible(!isVisible);
        communicator.setVisible(!isVisible);
    }

    public void setVisibleOnCommunicator(boolean isVisible){
        communicator.setVisible(isVisible);
        loginForm.setVisible(!isVisible);
    }

    public void setLogger(String logger) {communicator.setLogger(logger);}

    public void setVisibleOnNewAccountForm(boolean isVisible) {
        newAccountForm.setVisible(isVisible);
        loginForm.setVisible(!isVisible);
    }
}
