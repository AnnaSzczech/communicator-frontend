package com.crud.communicator.view.users;

import com.crud.communicator.client.AccountClient;
import com.crud.communicator.domain.UserDto;
import com.crud.communicator.factory.LabelFactory;
import com.crud.communicator.view.component.ComponentLook;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.listbox.ListBox;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Route;

@Route
public class UserView extends VerticalLayout {
    private AccountClient accountClient = new AccountClient();
    private ComponentLook componentLook = new ComponentLook(this);
    private ListBox<UserDto> users;
    private UserDto selectedUser;

    public UserView(){
        LabelFactory labelFactory = new LabelFactory();
        add(labelFactory.makeLabel(LabelFactory.SMALL_HEADER, "ALL USERS"));
        this.users = createUserList();

        users.setMaxHeight("740px");
        add(users);
        componentLook.setComponentLook("10%", "100%", "#484848", false);

        setSpacing(false);
    }

    private ListBox<UserDto> createUserList(){
        ListBox<UserDto> users = new ListBox<>();
        users.setRenderer(new ComponentRenderer<>(user -> {
            Label login = new Label("  " + user.getLogin());
            Icon icon = new Icon(VaadinIcon.CIRCLE);
            icon.setSize("8px");
            login.getStyle().set("color", "white");
            if (user.isOnline()) {
                icon.setColor("#b3b3b3");
            } else {
                icon.setColor("#595959");
            }
            return new Div(icon, login);
        }));
        return users;
    }

    public void refresh(String logger){
        users.setItems(accountClient.getUsers(logger));
    }

    public UserDto getSelectedUser(){
        return selectedUser;
    }

    public void setSelectedUser(UserDto selectedUser) {
        this.selectedUser = selectedUser;
    }

    public ListBox<UserDto> getUsers() {
        return users;
    }
}
