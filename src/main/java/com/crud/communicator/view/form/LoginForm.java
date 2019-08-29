package com.crud.communicator.view.form;

import com.crud.communicator.client.CommunicatorClient;
import com.crud.communicator.domain.LoginDto;
import com.crud.communicator.factory.LabelFactory;
import com.crud.communicator.view.MainView;
import com.crud.communicator.view.confirmation.ConfirmationDialog;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;
import org.springframework.web.client.HttpClientErrorException;

@Route
public class LoginForm extends FormLayout {

    private CommunicatorClient communicatorClient = new CommunicatorClient();

    private TextField login = new TextField("login");
    private PasswordField password = new PasswordField("password");

    private Button signIn = new Button("Sign in");
    private Button signUp = new Button("Sign up");
    private Binder<LoginDto> binder = new Binder<>(LoginDto.class);
    private MainView mainView;

    private LoginDto loginDto = new LoginDto();

    public LoginForm(final MainView mainView) {
        this.mainView = mainView;

        HorizontalLayout horizontalLayout = new HorizontalLayout(signIn, signUp);
        LabelFactory labelFactory = new LabelFactory();
        Label header = labelFactory.makeLabel(LabelFactory.HEADER, "LOG IN");
        VerticalLayout verticalLayout = new VerticalLayout(header, login, password, horizontalLayout);
        verticalLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        add(verticalLayout);
        binder.bindInstanceFields(this);
        binder.setBean(loginDto);
        login.focus();
        signIn.addClickListener(event -> logIn());
        signUp.addClickListener(event -> signUp());
        getStyle().set("background-color", "white");
    }



    private void signUp() {
        ConfirmationDialog confirmationDialog = new ConfirmationDialog("Confirm", "Are you sure you want to create new account?",
                event -> mainView.setVisibleOnNewAccountForm(true));
        confirmationDialog.open();
    }

    private void logIn() {
        LoginDto login = binder.getBean();
        try {
            communicatorClient.logIn(login);
            mainView.setVisibleOnCommunicator(true);
            mainView.setLogger(login.getLogin());
        } catch (HttpClientErrorException e) {
            String message = "LOGIN OR PASSWORD IS INCORRECT!";
            communicatorClient.showMessage(message);
        }
    }
}
