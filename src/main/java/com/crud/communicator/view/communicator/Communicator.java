package com.crud.communicator.view.communicator;

import com.crud.communicator.client.AccountClient;
import com.crud.communicator.view.MainView;
import com.crud.communicator.view.confirmation.ConfirmationDialog;
import com.crud.communicator.view.users.UserView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.web.client.HttpClientErrorException;

import java.util.stream.Stream;

@Route
public class Communicator extends VerticalLayout {

    private AccountClient accountClient = new AccountClient();

    private Label logger = new Label();
    private Button signOut = new Button("Sign out");
    private Button deleteAccount = new Button("Delete account");
    private MainView mainView;
    private UserView userView = new UserView();

    public Communicator(final MainView mainView){
        setVisible(false);
        this.mainView = mainView;
        createView();
        setBackground("white");
    }

    private void createView(){
//        HorizontalLayout horizontalLayout = new HorizontalLayout();
        MenuBar menuBar = new MenuBar();
        Stream.of(logger, signOut, deleteAccount).forEach(menuBar::addItem);
//        horizontalLayout.add(logger, signOut, deleteAccount);
//        horizontalLayout.setMinWidth("1100px");
//        horizontalLayout.setAlignItems(FlexComponent.Alignment.END);
//        horizontalLayout.setFlexGrow(1, logger);
//        add(horizontalLayout);
        add(menuBar);
        signOut.addClickListener(event -> logOut());
        signOut.setMinWidth("150px");
        deleteAccount.addClickListener(event -> confirmAccountDeletion());
        deleteAccount.setMinWidth("150px");
        setWidthFull();

        add(userView);
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
            accountClient.deleteTheAccount(logger.getText());
            mainView.setVisibleOnLoginForm(true);
            setLogger("");
        } catch (HttpClientErrorException e) {
            String message = "LOGIN NOT EXIST";
            accountClient.showMessage(message);
        }
    }

    private void logOut(){
        try {
            accountClient.logOut(logger.getText());
            mainView.setVisibleOnLoginForm(true);
            setLogger("");
        } catch (HttpClientErrorException e) {
            String message = "LOGIN NOT EXIST";
            accountClient.showMessage(message);
        }
    }

    public void setLogger(String logger){
        this.logger.setText(logger);
        if (!logger.equals("")) {
            userView.refresh(logger);
        }
    }
}
