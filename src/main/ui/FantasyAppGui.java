package ui;

import model.League;
import model.Player;
import model.Team;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

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
    private Player benzema = new Player("Benzema","ATT",13.0);
    private Player saka = new Player("Saka","MID",8.5);
    private Player kane = new Player("Kane","ATT",12.5);
    private Player mbappe = new Player("Mbappe","ATT",12.0);
    private Player mane = new Player("Mane","ATT",11.0);
    private Player antonio = new Player("Antonio","ATT",7.0);
    // Initialize graphics fields
    protected static final int WIDTH = 900;
    protected static final int HEIGHT = 650;
    // JFrames
    private JFrame mainFrame;
    private JFrame createTeamFrame;
    private JFrame viewPlayersFrame;
    private JFrame viewLeagueFrame;
    // JPanels
    private JPanel menuPanel = new JPanel();
    private JPanel viewPlayersPanel;
    private JPanel createTeamPanel;
    private JPanel addPlayerToTeamPanel;
    private JPanel viewLeaguePanel;
    private JSplitPane viewPlayersSplitPane;
    // JLabels
    private JLabel menuLabel = new JLabel("Welcome to Fantasy Super League");
    private JLabel teamNameLabel;
    private JLabel teamCreatedSuccessfully;
    private JLabel addPlayerToTeam;
    private JLabel playerAddedSuccessfully;
    private JLabel addPlayerToWhichTeam;
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
    // Gui models
    private PlayersGui allPlayersToView;
    private TeamsGui allTeamsInLeague;
    // JTables
    private JTable viewPlayersTable;
    private JTable allTeamsTable;


    public FantasyAppGui() throws FileNotFoundException {
        super("Super League Fantasy App");
        initializeData();
        initializeGraphics();
    }

    public void initializeData() {
        allPlayersGui = guiLeague.getPlayersInLeague();
        allTeamsGui = guiLeague.getTeamsInLeague();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        addAllPlayers();
    }

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
        allPlayersGui.add(ronaldo);
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
    }


    public void initializeGraphics() {
        mainFrame = new JFrame("Super League Fantasy App");
        mainFrame.setLayout(new BorderLayout(30,30));
        mainFrame.setMinimumSize(new Dimension(WIDTH,HEIGHT));
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createMenu();
        mainFrame.add(menuPanel);
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }


    public void createMenu() {
        menuLabel.setFont(new Font("Times New Roman", Font.PLAIN, 33));
        menuLabel.setBorder(BorderFactory.createEmptyBorder(300,200,300,200));

        menuPanel.setLayout(new GridLayout(0, 1));
        menuPanel.setSize(new Dimension(3, 3));
        menuPanel.setBorder(BorderFactory.createEmptyBorder(120, 80, 120, 80));
        //menuPanel.setBorder(BorderFactory.createMatteBorder(15,15,15,15,Color.BLACK));
        menuPanel.setBackground(Color.GRAY);
        menuPanel.add(menuLabel);
        add(menuPanel, BorderLayout.CENTER);

        initializeButtonPanels();
    }


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
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        buttonOptions(source);
    }

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
        }
    }

    private void pressViewPlayers() {
        viewPlayersFrame = new JFrame();
        viewPlayersFrame.setPreferredSize(new Dimension(500,800));
        viewPlayersFrame.setTitle("All Players");
        viewPlayersFrame.setLocation(450,100);

        viewPlayersSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        viewPlayersSplitPane.setMinimumSize(new Dimension(500,800));

        viewPlayersPanel = new JPanel();
        viewPlayersPanel.setSize(new Dimension(500,800));
        viewPlayersPanel.setBackground(Color.lightGray);
        viewPlayersPanel.setBorder(BorderFactory.createMatteBorder(10,10,10,10,Color.black));

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


    public void setPlayerGuiModel() {
        allPlayersToView = new PlayersGui(guiLeague);
        viewPlayersTable = new JTable(allPlayersToView);
        viewPlayersTable.setBackground(Color.gray);
        viewPlayersTable.setFillsViewportHeight(true);
        viewPlayersPanel.add(new JScrollPane(viewPlayersTable));
    }

    public void setAddPlayerToTeam() {
        addPlayerToTeamPanel = new JPanel();
        addPlayerToTeamPanel.setSize(250,350);
        addPlayerToTeamPanel.setBackground(Color.lightGray);
        addPlayerToTeamPanel.setBorder(BorderFactory.createMatteBorder(10,10,10,10,Color.black));

        addPlayerToTeam = new JLabel("Which player do you want to add?");
        addPlayerText = new JTextField(20);
        addPlayerToTeam.setBounds(100, 20, 300, 30);
        addPlayerText.setBounds(100,50,300,30);

        addPlayerToWhichTeam = new JLabel("Which team do you want to add it to?");
        addPlayerToTeamText = new JTextField(20);
        addPlayerToWhichTeam.setBounds(100,80,300,30);
        addPlayerToTeamText.setBounds(100,110,300,30);

        playerAddedSuccessfully = new JLabel("");
        playerAddedSuccessfully.setBounds(150,200,300,30);

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

    public void completeAddPlayerButton() {
        String playerName = addPlayerText.getText();
        String teamName = addPlayerToTeamText.getText();

        for (Player p : allPlayersGui) {
            for (Team t : allTeamsGui) {
                if ((p.getName().equals(playerName)) && (t.getTeamName().equals(teamName))) {
                    if ((!(t.inTeamForGivenPlayer(p)))) {
                        t.addPlayer(p);
                        playerAddedSuccessfully.setText("Player Added to Team Successfully");
                    } else {
                        playerAddedSuccessfully.setText("Unable to Add Player to Team");
                    }
                }
            }
        }
    }

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

    public void pressCreateTeam() {
        createTeamFrame = new JFrame();
        createTeamFrame.setPreferredSize(new Dimension(300,200));
        createTeamFrame.setTitle("Create new Team");
        createTeamFrame.setLocationRelativeTo(menuPanel);

        createTeamPanel = new JPanel();
        createTeamPanel.setBackground(Color.lightGray);
        createTeamPanel.setBorder(BorderFactory.createMatteBorder(15,15,15,15,Color.BLACK));

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

    public void createTeamLabelTextButton() {
        teamNameLabel = new JLabel("Enter Team Name:");
        teamNameLabel.setBounds(80,40,200,50);

        teamNameText = new JTextField(20);
        teamNameText.setBounds(120,40,200,25);

        completeCreateTeam = new JButton("Add Team to League");
        completeCreateTeam.setBounds(100,150,150,70);
    }

    public void completeCreateTeamButton() {
        String input = teamNameText.getText();
        allTeamsGui.add(new Team(input));
        teamCreatedSuccessfully.setText("Team Successfully Added to the League");
    }

    private void pressViewLeague() {
        viewLeagueFrame = new JFrame();
        viewLeagueFrame.setPreferredSize(new Dimension(500,700));
        viewLeagueFrame.setTitle("League Table");
        viewLeagueFrame.setLocation(450,100);

        viewLeaguePanel = new JPanel();
        viewLeaguePanel.setBorder(BorderFactory.createMatteBorder(10,10,10,10,Color.BLACK));

        setViewLeagueTable();

        viewLeagueFrame.add(viewLeaguePanel);
        viewLeagueFrame.setVisible(true);
        viewLeagueFrame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        viewLeagueFrame.pack();
    }

    private void setViewLeagueTable() {
        allTeamsInLeague = new TeamsGui(guiLeague);
        allTeamsTable = new JTable(allTeamsInLeague);
        allTeamsTable.setBackground(Color.lightGray);
        allTeamsTable.setFillsViewportHeight(true);
        viewLeaguePanel.add(new JScrollPane(allTeamsTable));
    }

}
