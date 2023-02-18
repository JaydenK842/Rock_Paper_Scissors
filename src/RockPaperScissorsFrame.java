import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class RockPaperScissorsFrame extends JFrame {
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
    JButton paper;
    JButton scissors;
    JButton quit;

    public RockPaperScissorsFrame () {
        Toolkit kit = Toolkit.getDefaultToolkit();
        frame = new JFrame();
        main = new JPanel();

        main.setLayout(new BorderLayout());
        frame.setTitle("Rock Paper Scissors Game");

        statistics();
        main.add(stats);

        resultOfGame();
        main.add(gameResults, BorderLayout.NORTH);

        options();
        main.add(choice, BorderLayout.CENTER);

        frame.add(main, BorderLayout.SOUTH);

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

        pWText = new JTextField();
        //pWText.setPreferredSize(new Dimension(10, 10));
        playerWins = new JLabel("Player wins: ");

        cWText = new JTextField();
        computerWins = new JLabel("Computer wins: ");

        tText = new JTextField();
        ties = new JLabel("Ties: ");

        stats.add(playerWins);
        stats.add(pWText);
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

        rock = new JButton("Rock");
        rock.addActionListener((ActionEvent ae) -> updateGameResult(resultText, 1));
        choice.add(rock);

        paper = new JButton("Paper");
        paper.addActionListener((ActionEvent ae) -> updateGameResult(resultText, 2));
        choice.add(paper);

        scissors = new JButton("Scissors");
        scissors.addActionListener((ActionEvent ae) -> updateGameResult(resultText, 3));
        choice.add(scissors);

        quit = new JButton("Quit");
        quit.addActionListener((ActionEvent ae) -> System.exit(0));
        choice.add(quit);
    }

    private void updateStats(JTextField player, JTextField computer, JTextField tie, int result) {

    }

    private void updateGameResult(JTextArea resultBox, int userChoice) {
        int computerChoice = rnd.nextInt(3) + 1;

        if (computerChoice == userChoice) {
            resultBox.append("Tie (No One Wins)\n");
            //updateStats(pWText, cWText, tText, 3);
        } else if (computerChoice == 1 && userChoice == 2) {
            resultBox.append("Paper covers Rock (Player wins)\n");
            //updateStats(pWText, cWText, tText, 1);
        } else if (computerChoice == 1 && userChoice == 3) {
            resultBox.append("Rock breaks Scissors (Computer wins)\n");
            //updateStats(pWText, cWText, tText, 2);
        } else if (computerChoice == 2 && userChoice == 1) {
            resultBox.append("Paper covers Rock (Computer wins)\n");
            //updateStats(pWText, cWText, tText, 2);
        } else if (computerChoice == 2 && userChoice == 3) {
            resultBox.append("Scissors cuts Paper (Player wins)\n");
            //updateStats(pWText, cWText, tText, 1);
        } else if (computerChoice == 3 && userChoice == 1) {
            resultBox.append("Rock breaks Scissors (Player wins)\n");
            //updateStats(pWText, cWText, tText, 1);
        } else if (computerChoice == 3 && userChoice == 2) {
            resultBox.append("Scissors cuts Paper (Computer wins)\n");
            //updateStats(pWText, cWText, tText, 2);
        } else {
            resultBox.append("Unknown Error\n");
        }
    }
}
