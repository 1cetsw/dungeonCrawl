package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.MapLoader;
import com.codecool.dungeoncrawl.logic.actors.Monster;
import com.codecool.dungeoncrawl.logic.actors.Ork;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.Random;

public class Main extends Application {
    GameMap mapLevel1;
    GameMap map = MapLoader.loadMap();
    Canvas canvas = new Canvas(
            map.getWidth() * Tiles.TILE_WIDTH,
            map.getHeight() * Tiles.TILE_WIDTH);
    GraphicsContext context = canvas.getGraphicsContext2D();
    Label healthLabel = new Label();
    Label nameLabel = new Label();
    Stage stage;
    Button pickUpBtn;
    Label inventoryLabel = new Label();

    public void mainMenu(Stage primaryStage) throws FileNotFoundException, RuntimeException {
        Button startButton = new Button("New Game");
        Button creditsButton = new Button("Credits");
        Button exitGameButton = new Button("Exit Game");

        startButton.setId("buttons"); //we use the resources/style.css to style like in html
        creditsButton.setId("buttons");
        exitGameButton.setId("buttons");

        startButton.addEventFilter(MouseEvent.MOUSE_CLICKED, (e) -> {
            try {
                gameProperties(primaryStage);
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });
        exitGameButton.addEventFilter(MouseEvent.MOUSE_CLICKED, (e) -> {
            System.exit(0);
        });
        creditsButton.addEventFilter(MouseEvent.MOUSE_CLICKED, (e) -> {
            try {
                credits(primaryStage);
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });

        VBox buttons = new VBox(startButton, creditsButton, exitGameButton);

        buttons.setAlignment(Pos.CENTER);
        buttons.setSpacing(10);

        BorderPane menuLayout = new BorderPane();

        menuLayout.setCenter(buttons);
        menuLayout.setBackground(new Background(new BackgroundFill(Color.rgb(100, 100, 100), CornerRadii.EMPTY, Insets.EMPTY)));
        menuLayout.setPrefWidth(1024);
        menuLayout.setPrefHeight(600);

        Scene scene = new Scene(menuLayout);
        scene.getStylesheets().add("style.css");

        primaryStage.setScene(scene);
        primaryStage.setTitle("CodeCool Project May 2022: Dungeon Crawl");
        primaryStage.show();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.stage = primaryStage;
        mainMenu(primaryStage);
    }

    public void startGame(Stage primaryStage) throws Exception {
        pickUpBtn = new Button("Pick up item");
        canvas.setFocusTraversable(false);

        nameLabel.setId("name-label");
        nameLabel.setText("" + (map.getPlayer().getName()));

        GridPane ui = new GridPane();
        ui.setPrefWidth(200);
        ui.setPadding(new Insets(10));
        ui.setStyle("-fx-background-color: rgba(28, 28, 28, 1); -fx-background-radius: 10;");
        ui.add(nameLabel, 0, 0);
        ui.add(new Label((map.getPlayer().getName()) + "@Health:~$ "), 0, 3);
        ui.add(new Label((map.getPlayer().getName()) + "@Inventory:~$ "), 0, 7);
        ui.add(inventoryLabel, 0, 8);

        BorderPane borderPane = new BorderPane();

        borderPane.setCenter(canvas);
        borderPane.setRight(ui);

        Scene scene = new Scene(borderPane);
        scene.getStylesheets().add("style.css");

        primaryStage.setScene(scene);
        refresh();
        scene.setOnKeyPressed(this::onKeyPressed);
        primaryStage.setTitle("CodeCool Project May 2022: Dungeon Crawl");
        primaryStage.show();
    }
    public void credits(Stage primaryStage) throws FileNotFoundException{

        Button backButton = new Button("Back to Menu");


        backButton.setId("buttons");


        HBox buttons = new HBox(backButton);
        buttons.setSpacing(25);
        Text nameLabel = new Text("CREDITS");
        nameLabel.setId("text");
        Text rulesLabel = new Text("cos tu mozna naskrobac");
        rulesLabel.setId("text");
        
        VBox credits = new VBox(nameLabel,rulesLabel, buttons);
        credits.setAlignment(Pos.CENTER);

        backButton.addEventFilter(MouseEvent.MOUSE_CLICKED, (e) -> {
            try {
                mainMenu(primaryStage);
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });

        BorderPane menu = new BorderPane();

        menu.setBackground(new Background(new BackgroundFill(Color.rgb(100, 100, 100), CornerRadii.EMPTY, Insets.EMPTY)));
        menu.setPrefWidth(1024);
        menu.setPrefHeight(600);
        menu.setCenter(credits);

        buttons.setAlignment(Pos.CENTER);
        credits.setSpacing(25);
        Scene scene = new Scene(menu);

        scene.getStylesheets().add("style.css");

        primaryStage.setScene(scene);
        primaryStage.setTitle("CodeCool Project May 2022: Dungeon Crawl");
        primaryStage.show();
    }

    public void gameProperties(Stage primaryStage) throws FileNotFoundException {
        Button startButton = new Button("Play Game");
        Button backButton = new Button("Back to Menu");

        startButton.setId("buttons");
        backButton.setId("buttons");


        HBox buttons = new HBox(startButton, backButton);
        buttons.setSpacing(25);
        Text nameLabel = new Text("Enter Your Name");
        nameLabel.setId("text");

        TextField textField = new TextField();
        textField.setId("input");
        textField.setPrefWidth(100);

        VBox settings = new VBox(nameLabel, textField, buttons);
        settings.setAlignment(Pos.CENTER);

        startButton.addEventFilter(MouseEvent.MOUSE_CLICKED, (e) -> {
            try {
                mapLevel1 = MapLoader.loadMap();
                map = mapLevel1;
                map.getPlayer().setName(textField.getText());
                startGame(primaryStage);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });

        backButton.addEventFilter(MouseEvent.MOUSE_CLICKED, (e) -> {
            try {
                mainMenu(primaryStage);
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });

        BorderPane menu = new BorderPane();

        menu.setBackground(new Background(new BackgroundFill(Color.rgb(100, 100, 100), CornerRadii.EMPTY, Insets.EMPTY)));
        menu.setPrefWidth(1024);
        menu.setPrefHeight(600);
        menu.setCenter(settings);

        buttons.setAlignment(Pos.CENTER);
        settings.setSpacing(25);
        Scene scene = new Scene(menu);

        scene.getStylesheets().add("style.css");

        primaryStage.setScene(scene);
        primaryStage.setTitle("CodeCool Project May 2022: Dungeon Crawl");
        primaryStage.show();
    }


    private void step(int x, int y) {
        map.getPlayer().move(x, y);
        enemyMove();
        refresh();
    }


    //double control : arrows and WSAD
    private void onKeyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case ESCAPE:
                System.exit(0);
            case UP:
            case W:
                step(0, -1);
                break;
            case DOWN:
            case S:
                step(0, 1);
                break;
            case LEFT:
            case A:
                step(-1, 0);
                break;
            case RIGHT:
            case D:
                step(1, 0);
                break;
            case SPACE:
//                pickUP()

        }
    }
    private void enemyMove(String direction, Cell cell) {
        switch (direction) {
            case "UP":
                map.getCell(cell.getX(), cell.getY()).getActor().move(0, -1);
                break;
            case "DOWN":
                map.getCell(cell.getX(), cell.getY()).getActor().move(0, 1);
                break;
            case "LEFT":
                map.getCell(cell.getX(), cell.getY()).getActor().move(-1, 0);
                break;
            case "RIGHT":
                map.getCell(cell.getX(), cell.getY()).getActor().move(1, 0);
                break;
        }
    }

    private void enemyMove() {
        String[] directions = {"UP", "DOWN", "LEFT", "RIGHT"};
        Random random = new Random();

        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                Cell cell = map.getCell(x, y);
                if (cell.getActor() instanceof Ork || cell.getActor() instanceof Monster) {
                    String direction = directions[random.nextInt(4)];
                    enemyMove(direction, cell);
                }
            }
        }
    }
    private void refresh() {
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                Cell cell = map.getCell(x, y);
                if (cell.getActor() != null) {
                    Tiles.drawTile(context, cell.getActor(), x, y);
                } else {
                    Tiles.drawTile(context, cell, x, y);
                }
            }
        }
        refreshLabel();

    }
    private void refreshLabel() {
        healthLabel.setText("" + map.getPlayer().getHealth());

    }
    public static void main(String[] args) {

        launch(args);
    }

}

