import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class RockPaperScissorsFrame extends JFrame {
    int pCounter = 1, cCounter = 1, tCounter = 1;
    Random rnd = new Random();

    JFrame frame;
    JPanel main;

    JPanel stats;
    JLabel playerWins;
    JTextField pWText;
    JLabel computerWins;
    JTextField cWText;
    JLabel ties;
    JTextField tText;

    JPanel gameResults;
    JTextArea resultText;
    JScrollPane scroll;

    JPanel choice;
    JButton rock;
    ImageIcon rockIcon;
    JButton paper;
    ImageIcon paperIcon;
    JButton scissors;
    ImageIcon scissorsIcon;
    JButton quit;
    ImageIcon quitIcon;

    public RockPaperScissorsFrame () {
        Toolkit kit = Toolkit.getDefaultToolkit();
        frame = new JFrame();
        main = new JPanel();

        main.setLayout(new BorderLayout());
        frame.setTitle("Rock Paper Scissors Game");

        statistics();
        main.add(stats, BorderLayout.NORTH);

        resultOfGame();
        main.add(gameResults, BorderLayout.CENTER);

        options();
        main.add(choice, BorderLayout.SOUTH);

        frame.add(main);

        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;

        frame.setSize(screenWidth / 2, screenHeight / 2);
        frame.setLocation(screenWidth / 4, screenHeight / 4);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void statistics() {
        stats = new JPanel();

        playerWins = new JLabel("Player wins: ");
        stats.add(playerWins);

        pWText = new JTextField("0", 5);
        pWText.setEditable(false);
        stats.add(pWText);

        computerWins = new JLabel("Computer wins: ");
        stats.add(computerWins);

        cWText = new JTextField("0", 5);
        cWText.setEditable(false);
        stats.add(cWText);

        ties = new JLabel("Ties: ");
        stats.add(ties);

        tText = new JTextField("0", 5);
        tText.setEditable(false);
        stats.add(tText);
    }

    public void resultOfGame() {
        gameResults = new JPanel();

        resultText = new JTextArea(10, 60);
        resultText.setEditable(false);

        scroll = new JScrollPane(resultText);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        gameResults.add(scroll);
    }

    private void options() {
        choice = new JPanel();
        choice.setLayout(new GridLayout(2, 2, 10, 10));
        choice.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(10, 10, 10, 10), BorderFactory.createLineBorder(Color.black)));

        rockIcon =  new ImageIcon("src/rock.png");
        Image conversion = rockIcon.getImage();
        Image scaling = conversion.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);
        rockIcon = new ImageIcon(scaling);
        rock = new JButton("Rock", rockIcon);
        rock.setFocusable(false);
        rock.addActionListener((ActionEvent ae) -> updateGameResult(resultText, 1));
        choice.add(rock);

        paperIcon =  new ImageIcon("src/paper.png");
        Image change = paperIcon.getImage();
        Image size = change.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);
        paperIcon = new ImageIcon(size);
        paper = new JButton("Paper");
        paper.setFocusable(false);
        paper.addActionListener((ActionEvent ae) -> updateGameResult(resultText, 2));
        choice.add(paper);

        scissorsIcon =  new ImageIcon("src/scissors.png");
        Image toImage = scissorsIcon.getImage();
        Image sizer = toImage.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);
        scissorsIcon = new ImageIcon(sizer);
        scissors = new JButton("Scissors");
        scissors.setFocusable(false);
        scissors.addActionListener((ActionEvent ae) -> updateGameResult(resultText, 3));
        choice.add(scissors);

        quitIcon =  new ImageIcon("src/quit.png");
        Image convert = quitIcon.getImage();
        Image scale = convert.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);
        quitIcon = new ImageIcon(scale);
        quit = new JButton("Quit");
        quit.setIcon(quitIcon);
        quit.setFocusable(false);
        quit.addActionListener((ActionEvent ae) -> System.exit(0));

        //Weird, but this resizes all buttons, but not as squares even though it has the same height and width
        quit.setPreferredSize(new Dimension(70, 70));
        choice.add(quit);
    }

    private void updateStats(JTextField player, JTextField computer, JTextField tie, int result) {
        if (result == 1) {
            player.setText(String.valueOf(pCounter));
            pCounter++;
        } else if (result == 2) {
            computer.setText(String.valueOf(cCounter));
            cCounter++;
        } else if (result == 3) {
            tie.setText(String.valueOf(tCounter));
            tCounter++;
        }
    }

    private void updateGameResult(JTextArea resultBox, int userChoice) {
        int computerChoice = rnd.nextInt(3) + 1;

        if (computerChoice == userChoice) {
            resultBox.append("Tie (No One Wins)\n");
            updateStats(pWText, cWText, tText, 3);
        } else if (computerChoice == 1 && userChoice == 2) {
            resultBox.append("Paper covers Rock (Player wins)\n");
            updateStats(pWText, cWText, tText, 1);
        } else if (computerChoice == 1 && userChoice == 3) {
            resultBox.append("Rock breaks Scissors (Computer wins)\n");
            updateStats(pWText, cWText, tText, 2);
        } else if (computerChoice == 2 && userChoice == 1) {
            resultBox.append("Paper covers Rock (Computer wins)\n");
            updateStats(pWText, cWText, tText, 2);
        } else if (computerChoice == 2 && userChoice == 3) {
            resultBox.append("Scissors cuts Paper (Player wins)\n");
            updateStats(pWText, cWText, tText, 1);
        } else if (computerChoice == 3 && userChoice == 1) {
            resultBox.append("Rock breaks Scissors (Player wins)\n");
            updateStats(pWText, cWText, tText, 1);
        } else if (computerChoice == 3 && userChoice == 2) {
            resultBox.append("Scissors cuts Paper (Computer wins)\n");
            updateStats(pWText, cWText, tText, 2);
        } else {
            resultBox.append("Unknown Error\n");
        }
    }
}
