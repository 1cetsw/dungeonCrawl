package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.MapLoader;
import com.codecool.dungeoncrawl.logic.actors.Ghost;
import com.codecool.dungeoncrawl.logic.actors.Monster;
import com.codecool.dungeoncrawl.logic.actors.Ork;
import com.codecool.dungeoncrawl.logic.actors.Boss;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.Random;

public class Main extends Application {
    GameMap mapLevel1;
    GameMap mapLevel2;
    GameMap mapLevel3;
    GameMap map;
    Canvas canvas = new Canvas(
            30 * Tiles.TILE_WIDTH, //map.getWidth() * Tiles.TILE_WIDTH,
            21 * Tiles.TILE_WIDTH);// map.getHeight() * Tiles.TILE_WIDTH); nie wiem czemu nie dziala
    GraphicsContext context = canvas.getGraphicsContext2D();
    Label healthLabel = new Label();
    Label defenseLabel = new Label();
    Label strengthLabel = new Label();
    Label nameLabel = new Label();
    Stage stage;
    Label inventoryLabel = new Label();

    Button pickUpBanner;
    Button nextLevelBanner;
    Button monsterDetected;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.stage = primaryStage;
        mainMenu(this.stage);
    }

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
            Platform.exit();
//            System.exit(0);
        });
        creditsButton.addEventFilter(MouseEvent.MOUSE_CLICKED, (e) -> {
            try {
                credits(primaryStage);
            } catch (Exception ex) {
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



    public void startGame(Stage primaryStage) {
        pickUpBanner = new Button("Press 'E' to pick up item");
        canvas.setFocusTraversable(false);
        pickUpBanner.setFocusTraversable(false);
        pickUpBanner.setId("pick-up-banner");
        nextLevelBanner = new Button("Press 'ENTER' to next level");
        canvas.setFocusTraversable(false);
        nextLevelBanner.setFocusTraversable(false);
        nextLevelBanner.setId("next-level-banner");
        inventoryLabel.setId("inventory-label");
        nameLabel.setId("name-label");
        nameLabel.setText("" + (map.getPlayer().getName()));



        GridPane ui = new GridPane();
        ui.setPrefWidth(250);
        ui.setPadding(new Insets(10));
        ui.setStyle("-fx-background-color: rgba(28, 28, 28, 1); -fx-background-radius: 10;");
        ui.add(nameLabel, 0, 0);
        ui.add(new Label("  HP: "), 0, 3);
        ui.add(healthLabel, 0, 4);
        ui.add(new Label("  ATT: "), 0, 5);
        ui.add(strengthLabel, 0, 6);
        ui.add(new Label("  DEF: "), 0, 7);
        ui.add(defenseLabel, 0, 10);
        ui.add(inventoryLabel, 0, 22);
        ui.add(pickUpBanner, 0, 600);
        ui.add(nextLevelBanner, 0, 600);
        hideElement(nextLevelBanner);
        hideElement(pickUpBanner);
        BorderPane borderPane = new BorderPane();

        borderPane.setCenter(canvas);
        borderPane.setRight(ui);

        Scene scene = new Scene(borderPane);
        scene.getStylesheets().add("style.css");

        primaryStage.setScene(scene);
        refresh();
        scene.setOnKeyPressed(this::onKeyPressed);
        //TODO atack mosnter
        //change pickUPBanner  , meybe "monster detected"?
        monsterDetected.setOnKeyPressed((e) -> {
            if (e.getCode() == KeyCode.SPACE) {
                System.out.println("fight!");
//                atack();
            }
        });

        pickUpBanner.setOnKeyPressed((e) -> {
            if (e.getCode() == KeyCode.E) {
                System.out.println("pick up item");
                pickUp();
            }

        });
        nextLevelBanner.setOnKeyPressed((e) -> {
            if (e.getCode() == KeyCode.ENTER) {
                System.out.println("jump to next level");
                nextLevel();
            }

        });


        primaryStage.setTitle("CodeCool Project May 2022: Dungeon Crawl");
        primaryStage.show();
    }

    private void displayElement(Node element) {
        element.setVisible(true);
    }

    private void hideElement(Node element) {
        element.setVisible(false);
    }


    private void pickUp() {
        map.getPlayer().pickUpItem(map.getCell(map.getPlayer().getX(),
                map.getPlayer().getY()).getItem());
        hideElement(pickUpBanner);
        refreshLabel();
    }

    private void nextLevel() {

        map.getPlayer().removeKey();
        mapLevel2 = MapLoader.loadMap(2);
        Cell playerCell = mapLevel2.getPlayer().getCell();
        mapLevel1.getPlayer().setCell(playerCell);
        mapLevel2.setPlayer(mapLevel1.getPlayer());
        map = mapLevel2;




        refreshLabel();
        refresh();
        hideElement(nextLevelBanner);
    }


    public void credits(Stage primaryStage) throws Exception {

        Button backButton = new Button("Back to Menu");
        backButton.setId("buttons");
        HBox buttons = new HBox(backButton);
        buttons.setSpacing(25);
        Text nameLabel = new Text("CREDITS");
        nameLabel.setId("text");
        Text rulesLabel = new Text("cos tu mozna naskrobac");
        rulesLabel.setId("text");

        VBox credits = new VBox(nameLabel, rulesLabel, buttons);
        credits.setAlignment(Pos.CENTER);

        MouseClick(primaryStage, backButton, buttons, credits);
    }
    public void gameOver(Stage primaryStage) throws Exception {
        Button startButton = new Button("Play Again");
        startButton.setId("buttons");
        Button backButton = new Button("Back to Menu");
        backButton.setId("buttons");
        HBox buttons = new HBox(backButton);
        buttons.setSpacing(25);
        Text nameLabel = new Text("\uD83D\uDC80 GAME OVER \uD83D\uDC80");
        nameLabel.setId("gameOver1");
        Text rulesLabel = new Text("\uD83D\uDC80");
        rulesLabel.setId("gameOver2");

        VBox credits = new VBox(nameLabel, rulesLabel, buttons);
        credits.setAlignment(Pos.CENTER);

        MouseClick(primaryStage, backButton, buttons, credits);
    }

    private void MouseClick(Stage primaryStage, Button backButton, HBox buttons, VBox credits) {
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
                mapLevel1 = MapLoader.loadMap(1);
                map = mapLevel1;
                map.getPlayer().setName(textField.getText());
                startGame(primaryStage);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });

        MouseClick(primaryStage, backButton, buttons, settings);
    }


    private void step(int x, int y) {

        map.getPlayer().move(x, y);
        map.getPlayer().fight(x, y);

        if (map.getCell(map.getPlayer().getX(), map.getPlayer().getY()).isItem()) {
            displayElement(pickUpBanner);
        } else {
            hideElement(pickUpBanner);//TODO should be on pickup event listener
            hideElement(nextLevelBanner);//TODO: should be on going trough the door event listener
        }

        if (map.getCell(map.getPlayer().getX(), map.getPlayer().getY()).isDoor()) {
            if (map.getPlayer().isOpen()) {
                displayElement(nextLevelBanner);
            } else {
                hideElement(nextLevelBanner);
            }
        }
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
            case E: //its working !!! , Block spam drop items, only pickup 1 item
                if (map.getCell(map.getPlayer().getX(), map.getPlayer().getY()).isItem()){
                pickUp();
                }
                break;
            case ENTER: //its working !!!  if player standing in door, display banner if key in inventory
                if (map.getCell(map.getPlayer().getX(), map.getPlayer().getY()).isDoor()) {
                    if (map.getPlayer().isOpen()) {
                        nextLevel();
                    }
                }

                break;
            case SPACE:
//                attack(); TODO
                break;

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
        String[] directions4 = {"UP", "DOWN", "LEFT", "RIGHT"};
        String[] directions2 = {"LEFT", "RIGHT"};
        Random random = new Random();

        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                Cell cell = map.getCell(x, y);
                if (cell.getActor() instanceof Ork) {
                    String direction = directions4[random.nextInt(4)]; //ORk movement random 4 directions
                    enemyMove(direction, cell);
                }
                if (cell.getActor() instanceof Monster) {
                    String direction = directions2[random.nextInt(2)];  //monster movement 2 directions  random(LEFT RIGHT)
                    enemyMove(direction, cell);
                }
                if (cell.getActor() instanceof Ghost) {
                    String direction = directions4[random.nextInt(4)];  //ghost walk in wall
                    enemyMove(direction, cell);
                }
            }
        }
    }

    private void refresh() {
        if(map.getPlayer().getHealth() <=0 ) {//if HP <= 0  =GAME OVER
            try {
                gameOver(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        context.setFill(Color.rgb(0, 0, 0, 0.3));
        context.fillRect(1, 1, canvas.getWidth(), canvas.getHeight());
        //orginal view
//        for (int x = 0; x < map.getWidth(); x++) {
//            for (int y = 0; y < map.getHeight(); y++) {
//                Cell cell = map.getCell(x, y);
//                if (cell.getActor() != null) {
//                    Tiles.drawTile(context, cell.getActor(), x, y);
//                } else {
//                    Tiles.drawTile(context, cell, x, y);
//                }
//            }

        for (int x = map.getPlayer().getX() - 13; x < map.getPlayer().getX() + 13; x++) {
            for (int y = map.getPlayer().getY() - 13; y < map.getPlayer().getY() + 13; y++) {
                Cell cell;
                try {
                    cell = map.getCell((x + map.getPlayer().getX()) - 11, y + map.getPlayer().getY() - 8);
                } catch (IndexOutOfBoundsException e) {
                    cell = new Cell(map, 1, 1, CellType.EMPTY);
                }
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
        healthLabel.setText("♥" + map.getPlayer().getHealth() + "/" + map.getPlayer().getMaxHealth());
        strengthLabel.setText("\uD83D\uDCAA" + map.getPlayer().getStrength());
        defenseLabel.setText("\uD83D\uDEE1" + map.getPlayer().getDefense() + "/" + map.getPlayer().getMaxDefense());
        inventoryLabel.setText("\n⬇⬇⬇⬇BAG⬇⬇⬇⬇⬇\n" +
                map.getPlayer().itemInInventory()
                + "⬆⬆⬆⬆⬆⬆⬆⬆⬆⬆⬆" +
                "\n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n ");
    }



}

