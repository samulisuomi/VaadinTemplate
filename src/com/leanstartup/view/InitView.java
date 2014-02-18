package com.leanstartup.view;

import com.leanstartup.database.DatabaseHelper;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

public class InitView extends UI implements View {

    public Navigator navigator = new Navigator(this, this);

    @Override
    protected void init(VaadinRequest request) {

        navigator.addView("MainView", new MainView());
        navigator.navigateTo("MainView");

        /**
         * DB:n alustus + mockin luonti testailua varten
         */
        try {
            // Databasen initialisointi ja mökkidatan luonti:
            DatabaseHelper.init();
            // DatabaseHelper.buildNoteMockData(10);
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

    }

    @Override
    public void enter(ViewChangeEvent event) {
    }

}