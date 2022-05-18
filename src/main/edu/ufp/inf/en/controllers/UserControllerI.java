package main.edu.ufp.inf.en.controllers;

import java.io.IOException;

import javafx.event.ActionEvent;


public interface UserControllerI {
    
    void handleMapButtonAction (ActionEvent event) throws IOException;
    
    void handleHistoryButtonAction (ActionEvent event) throws IOException;

    void handleExportButtonAction (ActionEvent event) throws IOException;

    void handleLogoutButtonAction (ActionEvent event) throws IOException;


}
