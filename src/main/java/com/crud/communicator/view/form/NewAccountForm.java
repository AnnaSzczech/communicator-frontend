package com.crud.communicator.view.form;

import com.crud.communicator.client.AccountClient;
import com.crud.communicator.client.EmailValidator;
import com.crud.communicator.domain.AccountDto;
import com.crud.communicator.factory.LabelFactory;
import com.crud.communicator.view.MainView;
import com.crud.communicator.view.component.ComponentLook;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;
import org.springframework.web.client.HttpClientErrorException;

@Route
public class NewAccountForm extends FormLayout {


    private ComponentLook componentLook = new ComponentLook(this);

    private MainView mainView;
    private Button save = new Button("Create account");

    private TextField name = new TextField("name");
    private TextField surname = new TextField("surname");
    private EmailField email = new EmailField("email");
    private TextField login = new TextField("login");
    private TextField password = new TextField("password");

    private Binder<AccountDto> binder = new Binder<>(AccountDto.class);
    private AccountDto accountDto = new AccountDto();
    private AccountClient accountClient = new AccountClient();

    public NewAccountForm(final MainView mainView) {
        createCloseButton();
        setVisible(false);
        this.mainView = mainView;
        createForm();
        createBinder();
        componentLook.setComponentLook("260px", "680px", "white", true);

    }

    private void createCloseButton() {
        Button close = new Button();
        close.setIcon(VaadinIcon.CLOSE.create());
        close.addClickListener(event -> close());
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.add(close);
        horizontalLayout.setAlignItems(FlexComponent.Alignment.END);
        add(close);
    }

    private void close(){
        mainView.setVisibleOnLoginForm(true);
        clear();
    }

    private void createForm() {
        LabelFactory labelFactory = new LabelFactory();
        Label header = labelFactory.makeLabel(LabelFactory.HEADER, "SIGN UP");
        VerticalLayout verticalLayout = new VerticalLayout(header, name, surname, email, login, password, save);
        verticalLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        add(verticalLayout);
        name.focus();
        save.addClickListener(event -> save());
    }

    private void createBinder() {
        binder.bindInstanceFields(this);
        binder.setBean(accountDto);
    }


    private void save() {
        if (isNewAccountAvailable()) {
            if (isEmailValid()) {
                mainView.setVisibleOnLoginForm(true);
                AccountDto account = binder.getBean();
                try {
                    accountClient.createNewAccount(account);
                    setVisible(false);
                    clear();
                } catch (HttpClientErrorException e) {
                    String message = "LOGIN OR EMAIL IS NOT UNIQUE!";
                    accountClient.showMessage(message);
                }
            } else {
                Notification.show("EMAIL I INCORRECT").setPosition(Notification.Position.MIDDLE);
            }
        } else {
            Notification.show("Fill missing fields").setPosition(Notification.Position.MIDDLE);
        }
    }

    private boolean isEmailValid(){
        EmailValidator emailValidator = new EmailValidator();
        System.out.println(email.getValue());
        return emailValidator.verifyEmail(email.getValue()).isValid();
    }

    private boolean isNewAccountAvailable(){
        return isMoreThanThreeLetters(name) && isMoreThanThreeLetters(surname) && isMoreThanThreeLetters(login) && isMoreThanThreeLetters(password);
    }

    private boolean isMoreThanThreeLetters(TextField textField) {
        return textField.getValue().length() > 0;
    }

    private void clear(){
        name.clear();
        surname.clear();
        email.clear();
        login.clear();
        password.clear();
    }
}
