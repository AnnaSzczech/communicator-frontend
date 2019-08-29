package com.crud.communicator.view.form;

import com.crud.communicator.client.CommunicatorClient;
import com.crud.communicator.domain.LoginDto;
import com.crud.communicator.view.MainView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;
import org.springframework.web.client.HttpClientErrorException;

@Route
public class LoginForm extends FormLayout {

    private CommunicatorClient communicatorClient = new CommunicatorClient();

    private TextField login = new TextField("login");
    private TextField password = new TextField("password");

    private Button signIn = new Button("Sign in");
    private Button signUp = new Button("Sign up");
    private Binder<LoginDto> binder = new Binder<>(LoginDto.class);
    private MainView mainView;

    private LoginDto loginDto = new LoginDto();

    public LoginForm(final MainView mainView){
        this.mainView = mainView;
        Label header = new Label("LOG IN");
        HorizontalLayout horizontalLayout = new HorizontalLayout(signIn, signUp);
        VerticalLayout verticalLayout = new VerticalLayout(header, login, password, horizontalLayout);
        add(verticalLayout);
        binder.bindInstanceFields(this);
        binder.setBean(loginDto);
        login.focus();
        signIn.addClickListener(event -> logIn());
        signUp.addClickListener(event -> signUp());
    }

    private void signUp() {
        mainView.setVisibleOnNewAccountForm(true);
    }

    private void logIn(){
        LoginDto login = binder.getBean();
        try {
            communicatorClient.logIn(login);
            setVisible(false);
            mainView.setVisibleOnCommunicator(true);
            mainView.setLogger(login.getLogin());
        } catch (HttpClientErrorException e) {
            String message = "LOGIN OR PASSWORD IS INCORRECT!";
            CommunicatorClient.LOGGER.error(message);
            Notification.show(message).setPosition(Notification.Position.MIDDLE);
        }
    }
}
