package com.crud.communicator.view.form;

import com.crud.communicator.client.CommunicatorClient;
import com.crud.communicator.domain.AccountDto;
import com.crud.communicator.view.MainView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
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

    public NewAccountForm(final MainView mainView){
        setVisible(false);
        this.mainView = mainView;
        Label header = new Label("SIGN UP");
        VerticalLayout verticalLayout = new VerticalLayout(header, name, surname, email, login, password, save);
        add(verticalLayout);
        binder.bindInstanceFields(this);
        binder.setBean(accountDto);
        name.focus();
        save.addClickListener(event -> save());
    }

    private void save() {
        AccountDto account = binder.getBean();
        try {
            communicatorClient.createNewAccount(account);
            setVisible(false);
        } catch (HttpClientErrorException e) {
            String message = "LOGIN OR EMAIL IS NOT UNIQUE!";
            CommunicatorClient.LOGGER.error(message);
            Notification.show(message).setPosition(Notification.Position.MIDDLE);
        }
    }
}
