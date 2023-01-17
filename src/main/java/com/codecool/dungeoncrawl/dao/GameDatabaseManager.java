package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.dao.GameStateDao;
import com.codecool.dungeoncrawl.logic.items.Item;
import com.codecool.dungeoncrawl.model.GameState;
import com.codecool.dungeoncrawl.model.PlayerModel;
import jdk.internal.icu.text.UnicodeSet;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class GameDatabaseManager {
    private PlayerDao playerDao;
    private GameStateDao gameStateDao;

    public static void saveAll() {
    }

    public void setup() throws SQLException {
        DataSource dataSource = connect();
        playerDao = new PlayerDaoJdbc(dataSource);
    }

    public void savePlayer(PlayerModel player) {
        PlayerModel model = new PlayerModel(String.valueOf(player));
        playerDao.add(model);
    }
    private void saveItems(ArrayList<Item> itemsListLinkedList, GameState gameState) {
    }
    public void saveGameState(GameState gameState) {
        gameStateDao.add(gameState);
    }
    public void saveMonsters(){

    }
    public static void saveAll(Player player, String currentMap, String
            savedGameName, ArrayList<Item> itemsListLinkedList) {
    }




    private DataSource connect() throws SQLException {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        String dbName = "dungeoncrawl";
        String user = "postgres";
        String password = "cipka";

        dataSource.setDatabaseName(dbName);
        dataSource.setUser(user);
        dataSource.setPassword(password);

        System.out.println("Trying to connect");
        dataSource.getConnection().close();
        System.out.println("Connection stability.");

        return dataSource;
    }
}
