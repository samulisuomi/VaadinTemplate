package com.leanstartup.database;

import java.sql.SQLException;
import java.util.List;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.leanstartup.bean.User;

public class DatabaseHelper {

    /*
     * private static final String databaseUrl =
     * "jdbc:mysql://supersipuli.keitetytsipulit.fi:3306/samuli", "user",
     * "peewee";
     */
    private static final String databaseUrl = "jdbc:h2:mem:account";
    public static Dao<User, String> userDao;
    public static ConnectionSource connectionSource;

    public static void init() throws Exception {

        // create a connection source to our database
        getDatabaseConnection();

        // instantiate the daos
        userDao = DaoManager.createDao(connectionSource, User.class);

        // create the 'User' table
        TableUtils.createTableIfNotExists(connectionSource, User.class);

    }

    public static void storeUser(User user) throws SQLException {
        getDatabaseConnection();
        userDao.createOrUpdate(user);
        System.out.println("Varastoitiin user " + user.toString());
    }

    public static User getUser(String username) throws SQLException {
        getDatabaseConnection();
        return userDao.queryForId(username);
    }

    /**
     * 
     * @param username
     * @param password
     * @return null, if not valid username or password
     * @throws SQLException
     */
    public static User validateUser(String username, String password)
            throws SQLException {
        getDatabaseConnection();
        User user = userDao.queryForId(username);

        if (user != null) {
            if (user.getPassword().compareTo(password) == 0) {
                return user;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    private static ConnectionSource getDatabaseConnection() throws SQLException {
        if (connectionSource == null) {
            connectionSource = new JdbcConnectionSource(databaseUrl);
        }
        return connectionSource;
    }

    public static void printUsersOnConsole() {
        List<User> queryResult = null;
        try {
            queryResult = userDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < queryResult.size(); i++) {
            System.out.println(queryResult.get(i));
        }
    }
}