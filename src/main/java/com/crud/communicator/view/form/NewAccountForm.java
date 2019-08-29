package com.crud.communicator.view.form;

import com.crud.communicator.client.CommunicatorClient;
import com.crud.communicator.domain.AccountDto;
import com.crud.communicator.factory.LabelFactory;
import com.crud.communicator.view.MainView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;
import org.springframework.web.client.HttpClientErrorException;

@Route
public class NewAccountForm extends FormLayout {

    private MainView mainView;
    private Button save = new Button("Create account");

    private TextField name = new TextField("name");
    private TextField surname = new TextField("surname");
    private TextField email = new TextField("email");
    private TextField login = new TextField("login");
    private TextField password = new TextField("password");

    private Binder<AccountDto> binder = new Binder<>(AccountDto.class);
    private AccountDto accountDto = new AccountDto();
    private CommunicatorClient communicatorClient = new CommunicatorClient();

    public NewAccountForm(final MainView mainView) {
        setVisible(false);
        this.mainView = mainView;
        createForm();
        createBinder();
        setBackground("white");
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

    private void setBackground(String color){
        getStyle().set("background-color", color);
    }


    private void save() {
        mainView.setVisibleOnLoginForm(true);
        AccountDto account = binder.getBean();
        try {
            communicatorClient.createNewAccount(account);
            setVisible(false);
        } catch (HttpClientErrorException e) {
            String message = "LOGIN OR EMAIL IS NOT UNIQUE!";
            communicatorClient.showMessage(message);
        }
    }
}
