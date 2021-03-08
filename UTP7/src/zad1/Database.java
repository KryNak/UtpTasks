package zad1;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class Database {

    final private String url;
    final public TravelData travelData;
    final private Properties properties;
    private Connection connection;

    public Database(String url, TravelData travelData) {
        this.url = url;
        this.travelData = travelData;

        //todo: Wpisac nazwe uzytkownika i haslo
        this.properties = new Properties();
        properties.setProperty("user", "");
        properties.setProperty("password", "");
    }

    public void create() {

        final String driver = "org.postgresql.Driver";
        connection = null;

        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, properties);
            connection.createStatement().execute("DROP TABLE IF EXISTS offer");
            connection.createStatement().execute("" +
                    "CREATE TABLE IF NOT EXISTS offer(" +
                    "   id SERIAL PRIMARY KEY," +
                    "   localization VARCHAR(30)," +
                    "   nation VARCHAR(30)," +
                    "   departure_date VARCHAR(30)," +
                    "   arrival_date VARCHAR(30)," +
                    "   place VARCHAR(30)," +
                    "   price VARCHAR(30)," +
                    "   currency_symbol VARCHAR(5)" +
                    ")"
            );

            PreparedStatement statement = connection.prepareStatement("INSERT INTO offer VALUES(?,?,?,?,?,?,?,?);");

            int counter = 0;
            for (String loc : Arrays.asList("pl_PL", "en_GB")) {
                for (Offer offer : travelData.getOffers()) {

                    String[] formattedOfferList = offer.toFormattedString(loc, "yyyy-MM-dd").split("\t");

                    statement.setInt(1, counter);
                    statement.setString(2, loc);
                    statement.setString(3, formattedOfferList[0]);
                    statement.setString(4, formattedOfferList[1]);
                    statement.setString(5, formattedOfferList[2]);
                    statement.setString(6, formattedOfferList[3]);
                    statement.setString(7, formattedOfferList[4]);
                    statement.setString(8, formattedOfferList[5]);
                    statement.executeUpdate();

                    counter++;
                }
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    private List<List<String>> loadFromDatabase(String loc) {
        List<List<String>> resultList = new ArrayList<>();

        try {
            String query = "" +
                    "SELECT nation, departure_date, arrival_date, place, price, currency_symbol FROM offer " +
                    "WHERE localization = ?";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, loc);
            ResultSet resultSet = statement.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (resultSet.next()) {
                List<String> row = new ArrayList<>();
                for (int i = 1; i <= columnCount; i++) {
                    row.add(resultSet.getString(i));
                }
                resultList.add(row);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultList;
    }

    public void showGui() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new Gui();
        });
    }

    private class Gui extends JFrame {

        public Gui() {
            final Dimension appDimension = new Dimension(800, 600);
            final int appWidth = appDimension.width;
            final int appHeight = appDimension.height;
            final Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
            final int screenWidth = screenDimension.width;
            final int screenHeight = screenDimension.height;

            setVisible(true);
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            setTitle("Offers");
            setSize(appDimension);
            setLocation(screenWidth / 2 - appWidth / 2, screenHeight / 2 - appHeight / 2);
            setLayout(new BorderLayout());

            JTableModel model = new JTableModel(travelData);
            JTable table = new JTable(model);
            table.setTableHeader(new JTableHeader());
            JComboBox<String> comboBox = new JComboBox<>(new DefaultComboBoxModel<>(new String[]{"pl_PL", "en_GB"}));
            comboBox.addActionListener(e -> model.setTableList(loadFromDatabase(comboBox.getSelectedItem().toString())));
            comboBox.setSelectedIndex(0);

            add(comboBox, BorderLayout.NORTH);
            add(table, BorderLayout.CENTER);
        }
    }


}
