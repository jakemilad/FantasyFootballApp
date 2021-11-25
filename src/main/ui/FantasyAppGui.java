package ui;

import model.Event;
import model.EventLog;
import model.League;
import model.Player;
import model.Team;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

// A Fantasy football application with a graphical user interface implemented with Java Swing
public class FantasyAppGui extends JFrame implements ActionListener {

    // Initialize data fields
    private League guiLeague = new League();
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/fantasy.json";
    // Player and team gui lists
    private static ArrayList<Player> allPlayersGui;
    private static ArrayList<Team> allTeamsGui;
    // All Player Objects
    private Player messi = new Player("Messi", "ATT", 15.0);
    private Player ronaldo = new Player("Ronaldo", "ATT", 15.0);
    private Player salah = new Player("Salah", "ATT", 14.5);
    private Player kante = new Player("Kante", "MID", 10.0);
    private Player fernandes = new Player("Fernandes", "MID", 13.5);
    private Player debruyne = new Player("De Bruyne", "MID", 14.0);
    private Player pogba = new Player("Pogba", "MID", 11.5);
    private Player hernandez = new Player("Hernandez", "DEF", 12.0);
    private Player walker = new Player("Walker", "DEF", 13.0);
    private Player jaitly = new Player("Jaitly", "MID", 14.5);
    private Player davies = new Player("Davies", "DEF", 11.0);
    private Player ramos = new Player("Ramos", "MID", 8.5);
    private Player emile = new Player("Emile", "MID", 9.0);
    private Player neuer = new Player("Neuer", "DEF", 13.0);
    private Player oblak = new Player("Oblak", "DEF", 13.0);
    private Player neymar = new Player("Neymar", "ATT", 15.0);
    private Player benzema = new Player("Benzema", "ATT", 13.0);
    private Player saka = new Player("Saka", "MID", 8.5);
    private Player kane = new Player("Kane", "ATT", 12.5);
    private Player mbappe = new Player("Mbappe", "ATT", 12.0);
    private Player mane = new Player("Mane", "ATT", 11.0);
    private Player antonio = new Player("Antonio", "ATT", 7.0);
    private Player hazard = new Player("Hazard", "ATT", 9.5);
    private Player milad = new Player("Milad", "ATT", 10.5);
    private Player khan = new Player("Khan", "ATT", 11.0);
    // Initialize graphics fields
    protected static final int WIDTH = 900;
    protected static final int HEIGHT = 650;
    // JFrames
    private JFrame mainFrame;
    private JFrame createTeamFrame;
    private JFrame viewPlayersFrame;
    private JFrame viewLeagueFrame;
    private JFrame mesiFrame;
    // JPanels
    private JPanel menuPanel = new JPanel();
    private JPanel viewPlayersPanel;
    private JPanel createTeamPanel;
    private JPanel addPlayerToTeamPanel;
    private JPanel viewLeaguePanel;
    private JSplitPane viewPlayersSplitPane;
    private JPanel mesiPanel;
    // JLabels
    private JLabel menuLabel = new JLabel("Fantasy Super League");
    private JLabel teamNameLabel;
    private JLabel teamCreatedSuccessfully;
    private JLabel addPlayerToTeam;
    private JLabel playerAddedSuccessfully;
    private JLabel addPlayerToWhichTeam;
    private JLabel mesiLabel;
    private JLabel splashScreen;
    // JTexts
    private JTextField teamNameText;
    private JTextField addPlayerText;
    private JTextField addPlayerToTeamText;
    // JButtons
    private JButton viewLeagueGui;
    private JButton viewPlayersGui;
    private JButton createTeamGui;
    private JButton saveStateGui;
    private JButton loadStateGui;
    private JButton completeCreateTeam;
    private JButton completeAddPlayer;
    private JButton mesiButton;
    // Gui models
    private PlayersGui allPlayersToView;
    private TeamsGui allTeamsInLeague;
    // JTables
    private JTable viewPlayersTable;
    private JTable allTeamsTable;
    // Images
    private BufferedImage mesiImage;
    private BufferedImage siuImage;
    private BufferedImage splashIcon;


    // EFFECTS: Fantasy App constructor that initializes all data and graphics.
    public FantasyAppGui() throws FileNotFoundException {
        super("Super League Fantasy App");
        initializeData();
        initializeGraphics();
    }


    // EFFECTS: Initializes the data structure for players and teams and assigns JsonWriter and JsonReader
    // a destination for storing data.
    public void initializeData() {
        allPlayersGui = guiLeague.getPlayersInLeague();
        allTeamsGui = guiLeague.getTeamsInLeague();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        addAllPlayers();
    }

    // MODIFIES: this
    // EFFECTS: adds all Player objects to the list of Players in the game.
    public void addAllPlayers() {
        allPlayersGui.add(messi);
        allPlayersGui.add(ronaldo);
        allPlayersGui.add(salah);
        allPlayersGui.add(kante);
        allPlayersGui.add(fernandes);
        allPlayersGui.add(debruyne);
        allPlayersGui.add(pogba);
        allPlayersGui.add(davies);
        allPlayersGui.add(jaitly);
        allPlayersGui.add(emile);
        allPlayersGui.add(neuer);
        allPlayersGui.add(oblak);
        allPlayersGui.add(neymar);
        allPlayersGui.add(hernandez);
        allPlayersGui.add(walker);
        allPlayersGui.add(ramos);
        allPlayersGui.add(benzema);
        allPlayersGui.add(saka);
        allPlayersGui.add(mbappe);
        allPlayersGui.add(antonio);
        allPlayersGui.add(mane);
        allPlayersGui.add(kane);
        addThreeMorePlayers();
    }

    // MODIFIES: this
    // EFFECTS: adds rest of Player objects to the list of Players in the game.
    public void addThreeMorePlayers() {
        allPlayersGui.add(hazard);
        allPlayersGui.add(khan);
        allPlayersGui.add(milad);
    }


    // EFFECTS: initializes the main JFrame and main menu panel of the Fantasy App
    public void initializeGraphics() {
        setSplashScreen();

        mainFrame = new JFrame("Super League Fantasy App");
        mainFrame.setLayout(new BorderLayout(30, 30));
        mainFrame.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createMenu();
        setSiuIcon();

        mainFrame.add(menuPanel);
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
        mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                printLog(EventLog.getInstance());
                e.getWindow().dispose();
            }
        });
    }

    // EFFECTS: accesses the EventLog and prints out each event
    public void printLog(EventLog e) {
        for (Event event : e.getInstance()) {
            System.out.println("\n" + event.toString());
        }
    }

    // MODIFIES: this
    // EFFECTS: sets the splash screen for the application
    public void setSplashScreen() {
        try {
            splashIcon = ImageIO.read(new File("./data/splash.jpeg"));
            splashScreen = new JLabel(new ImageIcon(splashIcon));
            JWindow splashWindow = new JWindow();
            splashWindow.add(splashScreen);
            splashWindow.setBounds(450, 200, 550, 401);
            splashWindow.setVisible(true);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            splashWindow.setVisible(false);
            splashWindow.dispose();
        } catch (IOException e) {
            System.out.println("No file");
        }
    }

    // EFFECTS: sets the default Java icon for the fantasy app.
    public void setSiuIcon() {
        try {
            siuImage = ImageIO.read(new File("./data/siu.jpeg"));
            mainFrame.setIconImage(siuImage);
        } catch (IOException e) {
            System.out.println("invalid file");
        }
    }

    // MODIFIES: this
    // EFFECTS: reads imported image in data folder to be added as a component to JPanel
    public void mesiImage() {
        try {
            mesiImage = ImageIO.read(new File("./data/mesi.jpeg"));
            mesiLabel = new JLabel(new ImageIcon(mesiImage));

            mesiFrame = new JFrame();
            mesiFrame.setTitle("ankara mesi");
            mesiFrame.setPreferredSize(new Dimension(500, 400));
            mesiFrame.setLocation(450, 100);

            mesiPanel = new JPanel();
            mesiPanel.add(mesiLabel);
            mesiPanel.setBackground(Color.lightGray);
            mesiPanel.setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, Color.BLACK));

            mesiFrame.add(mesiPanel);

            mesiFrame.setVisible(true);
            mesiFrame.pack();
            mesiFrame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        } catch (IOException e) {
            System.out.println("nothing here");
        }
    }


    // EFFECTS: Initializes panel of the main menu of the Fantasy App.
    public void createMenu() {
        menuLabel.setFont(new Font("Comic Sans", Font.BOLD, 40));
        menuLabel.setBorder(BorderFactory.createEmptyBorder(300, 200, 300, 200));

        menuPanel.setLayout(new GridLayout(0, 1));
        menuPanel.setBorder(BorderFactory.createEmptyBorder(100, 60, 100, 60));
        menuPanel.setBackground(Color.GRAY);
        menuPanel.add(menuLabel);
        mainFrame.add(menuPanel, BorderLayout.CENTER);

        initializeButtonPanels();
    }

    // EFFECTS: Initializes the all the main menu buttons to press
    public void initializeButtonPanels() {
        viewLeagueGui = new JButton("View League");
        menuPanel.add(viewLeagueGui);
        viewLeagueGui.addActionListener(this::actionPerformed);

        viewPlayersGui = new JButton("View Players");
        menuPanel.add(viewPlayersGui);
        viewPlayersGui.addActionListener(this::actionPerformed);

        createTeamGui = new JButton("Create Team");
        menuPanel.add(createTeamGui);
        createTeamGui.addActionListener(this::actionPerformed);

        saveStateGui = new JButton("Save Team");
        menuPanel.add(saveStateGui);
        saveStateGui.addActionListener(this::actionPerformed);

        loadStateGui = new JButton("Load Team");
        menuPanel.add(loadStateGui);
        loadStateGui.addActionListener(this::actionPerformed);

        mesiButton = new JButton("mesi");
        menuPanel.add(mesiButton);
        mesiButton.addActionListener(this::actionPerformed);
    }


    // EFFECTS: overrides action listener method to initialize button functionality
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        buttonOptions(source);
    }

    // MODIFIES: this, ActionEvent
    // EFFECTS: sets calls to correspond to the
    public void buttonOptions(Object source) {
        if (source == viewLeagueGui) {
            pressViewLeague();
        } else if (source == viewPlayersGui) {
            pressViewPlayers();
        } else if (source == createTeamGui) {
            pressCreateTeam();
        } else if (source == saveStateGui) {
            pressSaveState();
        } else if (source == loadStateGui) {
            pressLoadState();
        } else if (source == completeCreateTeam) {
            completeCreateTeamButton();
        } else if (source == completeAddPlayer) {
            completeAddPlayerButton();
        } else if (source == mesiButton) {
            mesiImage();
        }
    }


    // MODIFIES: this
    // EFFECTS: initializes the functionality of viewing players for two split panels.
    private void pressViewPlayers() {
        viewPlayersFrame = new JFrame();
        viewPlayersFrame.setPreferredSize(new Dimension(500, 800));
        viewPlayersFrame.setTitle("All Players");
        viewPlayersFrame.setLocation(450, 100);

        viewPlayersSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        viewPlayersSplitPane.setMinimumSize(new Dimension(500, 800));

        viewPlayersPanel = new JPanel();
        viewPlayersPanel.setSize(new Dimension(500, 800));
        viewPlayersPanel.setBackground(Color.lightGray);
        viewPlayersPanel.setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, Color.black));

        setPlayerGuiModel();
        setAddPlayerToTeam();

        completeAddPlayer.addActionListener(this::actionPerformed);

        viewPlayersSplitPane.setTopComponent(viewPlayersPanel);
        viewPlayersSplitPane.setBottomComponent(addPlayerToTeamPanel);

        viewPlayersFrame.add(viewPlayersSplitPane);
        viewPlayersFrame.setVisible(true);
        viewPlayersFrame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        viewPlayersFrame.pack();
    }

    // MODIFIES: this, PlayersGui
    // EFFECTS: sets the PlayersGui as a JTable
    public void setPlayerGuiModel() {
        allPlayersToView = new PlayersGui(guiLeague);
        viewPlayersTable = new JTable(allPlayersToView);
        viewPlayersTable.setBackground(Color.gray);
        viewPlayersTable.setFillsViewportHeight(true);
        viewPlayersPanel.add(new JScrollPane(viewPlayersTable));
        viewPlayersTable.getTableHeader().setOpaque(false);
        viewPlayersTable.getTableHeader().setBackground(Color.lightGray);
    }

    // EFFECTS: sets the functionality of adding a player to a team.
    public void setAddPlayerToTeam() {
        addPlayerToTeamPanel = new JPanel();
        addPlayerToTeamPanel.setSize(250, 350);
        addPlayerToTeamPanel.setBackground(Color.lightGray);
        addPlayerToTeamPanel.setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, Color.black));

        addPlayerToTeam = new JLabel("Which player do you want to add?");
        addPlayerText = new JTextField(20);
        addPlayerToTeam.setBounds(100, 20, 300, 30);
        addPlayerText.setBounds(100, 50, 300, 30);

        addPlayerToWhichTeam = new JLabel("Which team do you want to add it to?");
        addPlayerToTeamText = new JTextField(20);
        addPlayerToWhichTeam.setBounds(100, 80, 300, 30);
        addPlayerToTeamText.setBounds(100, 110, 300, 30);

        playerAddedSuccessfully = new JLabel("");
        playerAddedSuccessfully.setBounds(150, 200, 300, 30);

        completeAddPlayer = new JButton("Add Player to Team");
        completeAddPlayer.setBounds(100, 160, 300, 30);

        addPlayerToTeamPanel.setLayout(null);
        addPlayerToTeamPanel.add(addPlayerToTeam);
        addPlayerToTeamPanel.add(addPlayerText);
        addPlayerToTeamPanel.add(addPlayerToWhichTeam);
        addPlayerToTeamPanel.add(addPlayerToTeamText);
        addPlayerToTeamPanel.add(playerAddedSuccessfully);
        addPlayerToTeamPanel.add(completeAddPlayer);
    }

    // MODIFIES: this
    // EFFECTS: sets the button functionality to complete adding a player to a team.
    public void completeAddPlayerButton() {
        String playerName = addPlayerText.getText();
        String teamName = addPlayerToTeamText.getText();

        for (Player p : allPlayersGui) {
            for (Team t : allTeamsGui) {
                if ((p.getName().equals(playerName)) && (t.getTeamName().equals(teamName))) {
                    if ((!(t.inTeamForGivenPlayer(p)))) {
                        t.addPlayer(p);
                        playerAddedSuccessfully.setText(playerName + " added to " + teamName + "'s team.");
                    } else {
                        playerAddedSuccessfully.setText("Unable to Add Player to Team");
                    }
                }
            }
        }
    }

    // EFFECTS: saves the state of the game
    private void pressSaveState() {
        try {
            jsonWriter.open();
            jsonWriter.writeLeague(guiLeague);
            jsonWriter.close();
            System.out.println("Saved league to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save league to " + JSON_STORE);
        }
    }

    // EFFECTS: loads the state of the game
    private void pressLoadState() {
        try {
            System.out.println("Loading...");
            guiLeague = jsonReader.read();
            allPlayersGui = guiLeague.getPlayersInLeague();
            allTeamsGui = guiLeague.getTeamsInLeague();
            allPlayersToView.updateLeagueStatistics(guiLeague);
            allTeamsInLeague.updateLeague(guiLeague);
        } catch (IOException e) {
            System.out.println("Unable to load data from " + JSON_STORE);
        }
    }

    // EFFECTS: initializes the behaviour to add a team to the league
    public void pressCreateTeam() {
        createTeamFrame = new JFrame();
        createTeamFrame.setPreferredSize(new Dimension(300, 200));
        createTeamFrame.setTitle("Create new Team");
        createTeamFrame.setLocationRelativeTo(menuPanel);

        createTeamPanel = new JPanel();
        createTeamPanel.setBackground(Color.lightGray);
        createTeamPanel.setBorder(BorderFactory.createMatteBorder(15, 15, 15, 15, Color.BLACK));

        createTeamLabelTextButton();

        teamCreatedSuccessfully = new JLabel("");

        completeCreateTeam.addActionListener(this::actionPerformed);

        createTeamPanel.add(teamNameLabel);
        createTeamPanel.add(teamNameText);
        createTeamPanel.add(completeCreateTeam);
        createTeamPanel.add(teamCreatedSuccessfully);

        createTeamFrame.add(createTeamPanel);
        createTeamFrame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        createTeamFrame.setVisible(true);
        createTeamFrame.pack();
    }

    // MODIFIES: this
    // EFFECTS: sets the JText fields for creating a team behaviour
    public void createTeamLabelTextButton() {
        teamNameLabel = new JLabel("Enter Team Name:");
        teamNameLabel.setBounds(80, 40, 200, 50);

        teamNameText = new JTextField(20);
        teamNameText.setBounds(120, 40, 200, 25);

        completeCreateTeam = new JButton("Add Team to League");
        completeCreateTeam.setBounds(100, 150, 150, 70);
    }

    // MODIFIES: this
    // EFFECTS: sets the button functionality of creating a team.
    public void completeCreateTeamButton() {
        String input = teamNameText.getText();
        allTeamsGui.add(new Team(input));
        teamCreatedSuccessfully.setText(input + " was added to the league");
    }

    // EFFECTS: initializes the behaviour to view all the teams in the league
    private void pressViewLeague() {
        viewLeagueFrame = new JFrame();
        viewLeagueFrame.setPreferredSize(new Dimension(500, 500));
        viewLeagueFrame.setTitle("League Table");
        viewLeagueFrame.setLocation(450, 100);

        viewLeaguePanel = new JPanel();
        viewLeaguePanel.setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, Color.BLACK));

        setViewLeagueTable();

        viewLeagueFrame.add(viewLeaguePanel);
        viewLeagueFrame.setVisible(true);
        viewLeagueFrame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        viewLeagueFrame.pack();
    }

    // MODIFIES: this, TeamsGui
    // EFFECTS: sets the TeamsGui abstract table model as a JTable
    public void setViewLeagueTable() {
        allTeamsInLeague = new TeamsGui(guiLeague);
        allTeamsTable = new JTable(allTeamsInLeague);
        allTeamsTable.setBackground(Color.lightGray);
        allTeamsTable.setFillsViewportHeight(true);
        viewLeaguePanel.add(new JScrollPane(allTeamsTable));
        allTeamsTable.getTableHeader().setOpaque(false);
        allTeamsTable.getTableHeader().setBackground(Color.lightGray);
    }

}

// SOURCES:
// Alex Lee on YouTube https://www.youtube.com/watch?v=5o3fMLPY7qY&ab_channel=AlexLee
// Bro Code on YouTube https://www.youtube.com/watch?v=-IMys4PCkIA&t=424s&ab_channel=BroCode
// Splash screen https://stackoverflow.com/questions/16134549/how-to-make-a-splash-screen-for-gui
// Image Icon to JPanel https://stackoverflow.com/questions/299495/how-to-add-an-image-to-a-jpanel
// JTable https://docs.oracle.com/javase/tutorial/uiswing/components/table.html
// SimpleDrawingPlayer repository
