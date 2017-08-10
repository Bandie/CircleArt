/*
 * Copyright (c) 2017, bandie
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 * * Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package org.bandie.circleart;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import static org.bandie.circleart.CircleArt.MSGS;

/**
 *
 * @author bandie
 */
public class CircleArtGUI extends Application {

    private Stage stageSettingsGUI;
    private SettingsGUI sg;
    static final Pen P = new Pen();

    @Override
    public void start(Stage stage) {

        final KeyCodeCombination kcNew = new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN);
        final KeyCodeCombination kcSave = new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN);
        final KeyCodeCombination kcQuit = new KeyCodeCombination(KeyCode.Q, KeyCombination.CONTROL_DOWN);
        final KeyCodeCombination kcFullscreen = new KeyCodeCombination(KeyCode.F, KeyCombination.CONTROL_DOWN);

        BorderPane root = new BorderPane();

        Pane drawPane = new Pane();
        ScrollPane scrollPane = new ScrollPane(drawPane);
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);

        GridPane statusPane = new GridPane();

        //MENU - File
        MenuItem menuIFNew = new MenuItem(MSGS.getString("NEW"));
        menuIFNew.setAccelerator(kcNew);
        menuIFNew.setOnAction((ActionEvent t) -> {
            drawPane.getChildren().clear();
        });

        MenuItem menuIFSave = new MenuItem(MSGS.getString("SAVE"));
        menuIFSave.setAccelerator(kcSave);
        menuIFSave.setOnAction((ActionEvent t) -> {
            FileUtil.saveFile(stage, drawPane);
        });

        MenuItem menuIFQuit = new MenuItem(MSGS.getString("QUIT"));
        menuIFQuit.setAccelerator(kcQuit);
        menuIFQuit.setOnAction(t -> {
            if (stageSettingsGUI != null) {
                stageSettingsGUI.close();
            }
            stage.close();
        });

        Menu menuFile = new Menu(MSGS.getString("FILE"));
        menuFile.getItems().addAll(menuIFNew, menuIFSave, new SeparatorMenuItem(), menuIFQuit);

        //MENU - View
        MenuItem menuIVFullscreen = new MenuItem(MSGS.getString("FULLSCREEN"));
        menuIVFullscreen.setAccelerator(kcFullscreen);
        menuIVFullscreen.setOnAction((ActionEvent t) -> {
            toggleFullscreen(stage);
        });

        Menu menuView = new Menu(MSGS.getString("VIEW"));
        menuView.getItems().addAll(menuIVFullscreen);

        //MENU - Tools
        MenuItem menuITSettings = new MenuItem(MSGS.getString("SETTINGS"));
        menuITSettings.setOnAction(t -> {
            sg = new SettingsGUI();
            stageSettingsGUI = new Stage();
            if(stage.isFullScreen())
                toggleFullscreen(stage);
            sg.start(stageSettingsGUI);
        });

        Menu menuTools = new Menu(MSGS.getString("TOOLS"));
        menuTools.getItems().addAll(menuITSettings);

        //MENU - Help
        MenuItem menuIHAbout = new MenuItem(MSGS.getString("ABOUT"));
        menuIHAbout.setOnAction((ActionEvent t) -> {
            About.show(stage);
        });

        Menu menuHelp = new Menu(MSGS.getString("HELP"));
        menuHelp.getItems().addAll(menuIHAbout);

        //MENUBAR
        MenuBar menu = new MenuBar();
        menu.getMenus().addAll(menuFile, menuView, menuTools, menuHelp);

        //Statusbar
        Label lHelp = new Label(MSGS.getString("HELP_DRAWING"));
        lHelp.setFont(new Font(18));

        Label lStatus = new Label(MSGS.getString("DRAWING_OFF"));
        lStatus.setFont(new Font(18));
        lStatus.setTextFill(Color.DARKRED);

        statusPane.setHgap(30);
        statusPane.addColumn(1, lStatus);
        statusPane.addColumn(2, lHelp);

        root.setTop(menu);
        root.setBottom(statusPane);
        root.setCenter(scrollPane);

        root.setFocusTraversable(true);

        root.addEventHandler(MouseEvent.MOUSE_MOVED, (MouseEvent t) -> {

            if (P.isOn()) {
                double x = t.getSceneX();
                double y = t.getSceneY();
                drawPane.getChildren().add(new Circle(x, y,
                        P.getSize(x, y),
                        P.getColor(x, y)
                )
                );
            }
        });

        Scene scene = new Scene(root);

        root.addEventHandler(KeyEvent.KEY_PRESSED, (KeyEvent t) -> {
            if (t.getCode() == KeyCode.D) {
                if (P.toggle()) {
                    lStatus.setTextFill(Color.DARKGREEN);
                    lStatus.setText(MSGS.getString("DRAWING_ON"));
                } else {
                    lStatus.setTextFill(Color.DARKRED);
                    lStatus.setText(MSGS.getString("DRAWING_OFF"));
                }
            }

        });

        stage.setOnCloseRequest(eh -> {
            Platform.setImplicitExit(true);
            Platform.exit();
        });

        stage.setWidth(800);
        stage.setHeight(600);

        stage.setScene(scene);
        stage.setTitle("CircleArt");

        stage.show();
        stage.requestFocus();
        scrollPane.requestFocus();

    }

    // Fullscreen toggle
    private void toggleFullscreen(Stage stage) {
        stage.setFullScreen(!stage.isFullScreen());

    }

}
