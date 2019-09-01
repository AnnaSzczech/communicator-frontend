package com.crud.communicator.view.component;

import com.vaadin.flow.component.Component;

public class ComponentLook {
    private Component layaut;

    public ComponentLook(Component layaut) {
        this.layaut = layaut;
    }

    public void setComponentLook(String width, String height, String backgroundColor, boolean isCenter){
        layaut.getElement().getStyle().set("height", height);
        layaut.getElement().getStyle().set("width", width);

        if (backgroundColor != null) {
            layaut.getElement().getStyle().set("background-color", backgroundColor);
        }

        if (isCenter) {
            layaut.getElement().getStyle().set("margin-left", "auto");
            layaut.getElement().getStyle().set("margin-right", "auto");
        }
    }
}
