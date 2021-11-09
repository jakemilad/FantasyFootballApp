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
import java.util.ArrayList;

public class FantasyAppGui extends JFrame implements ActionListener {

    // Initialize data fields
    private League theLeague;
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
    // Initialize graphics fields
    protected static final int WIDTH = 900;
    protected static final int HEIGHT = 650;
    private JFrame mainFrame = new JFrame("Super League Fantasy App");
    // JPanels
    private JPanel menuPanel = new JPanel();
    private JSplitPane mainPane = new JSplitPane();
    // JLabels
    private JLabel menuLabel = new JLabel("Welcome to Fantasy Super League");
    private JLabel teamNameLabel;
    private JLabel teamCreatedSuccessfully;
    // JTexts
    private JTextField teamNameText;
    // JButtons
    private JButton viewLeagueGui;
    private JButton viewPlayersGui;
    private JButton createTeamGui;
    private JButton saveStateGui;
    private JButton loadStateGui;
    private JButton completeCreateTeam;
    // Gui models
    private PlayersGui viewPlayers;
    // JTables
    private JTable viewPlayersTable;



    public FantasyAppGui() throws FileNotFoundException {
        super("Super League Fantasy App");
        initializeData();
        initializeGraphics();
    }

    public void initializeData() {
        theLeague = new League();
        allPlayersGui = theLeague.getPlayersInLeague();
        allTeamsGui = theLeague.getTeamsInLeague();
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
    }


    //TODO: this
    public void initializeGraphics() {
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
        menuLabel.setFont(new Font("Times New Roman", Font.PLAIN, 22));
        menuLabel.setBorder(BorderFactory.createEmptyBorder(300,200,300,200));

        menuPanel.setLayout(new GridLayout(0, 1));
        menuPanel.setSize(new Dimension(3, 3));
        menuPanel.setBorder(BorderFactory.createEmptyBorder(120, 80, 120, 80));
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
        }
    }

    private void pressViewPlayers() {
        JFrame viewPlayersFrame = new JFrame();
        viewPlayersFrame.setPreferredSize(new Dimension(500,700));
        viewPlayersFrame.setTitle("All Players");
        viewPlayersFrame.setLocationRelativeTo(menuPanel);
    }

    private void pressLoadState() {
    }

    private void pressSaveState() {
    }

    public void pressCreateTeam() {
        JFrame createTeamFrame = new JFrame();
        createTeamFrame.setPreferredSize(new Dimension(300,200));
        createTeamFrame.setTitle("Create new Team");
        createTeamFrame.setLocationRelativeTo(menuPanel);

        JPanel createTeamPanel = new JPanel();
        createTeamPanel.setBackground(Color.lightGray);
        createTeamPanel.setBorder(BorderFactory.createMatteBorder(15,15,15,15,Color.BLACK));

        teamNameLabel = new JLabel("Enter Team Name:");
        teamNameLabel.setBounds(80,40,200,50);

        teamNameText = new JTextField(20);
        teamNameText.setBounds(120,40,200,25);

        completeCreateTeam = new JButton("Add Team to League");
        completeCreateTeam.setBounds(100,150,150,70);

        teamCreatedSuccessfully = new JLabel("");

        completeCreateTeam.addActionListener(this::actionPerformed);

        createTeamPanel.add(teamNameLabel);
        createTeamPanel.add(teamNameText);
        createTeamPanel.add(teamCreatedSuccessfully);
        createTeamPanel.add(completeCreateTeam);

        createTeamFrame.add(createTeamPanel);
        createTeamFrame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        createTeamFrame.setVisible(true);
        createTeamFrame.pack();
    }

    public void completeCreateTeamButton() {
        String input = teamNameText.getText();
        allTeamsGui.add(new Team(input));
        teamCreatedSuccessfully.setText("Team successfully added to league");
    }

    private void pressViewLeague() {
    }

}
