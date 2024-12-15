package modellingAndSimulation;

import java.util.*;

public class IPTournament {

    private static final int[][] PAYOFF_MATRIX = {
            {4, 0},
            {5, 2}
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of players for Scenario 1:");

        System.out.print("Enter the number of TYPE 1 players: ");
        int type1CountScenario1 = scanner.nextInt();

        System.out.print("Enter the number of TYPE 2 players: ");
        int type2CountScenario1 = scanner.nextInt();

        System.out.print("Enter the number of TYPE 3 players: ");
        int type3CountScenario1 = scanner.nextInt();

        System.out.print("Enter the number of TYPE 4 players: ");
        int type4CountScenario1 = scanner.nextInt();

        System.out.println("\nEnter the number of players for Scenario 2:");

        System.out.print("Enter the number of TYPE 1 players: ");
        int type1CountScenario2 = scanner.nextInt();

        System.out.print("Enter the number of TYPE 2 players: ");
        int type2CountScenario2 = scanner.nextInt();

        System.out.print("Enter the number of TYPE 3 players: ");
        int type3CountScenario2 = scanner.nextInt();

        System.out.print("Enter the number of TYPE 4 players: ");
        int type4CountScenario2 = scanner.nextInt();

        System.out.println("\nSimulating Scenario 1...");
        simulateScenario("Scenario 1", type1CountScenario1, type2CountScenario1, type3CountScenario1, type4CountScenario1);

        System.out.println("\nSimulating Scenario 2...");
        simulateScenario("Scenario 2", type1CountScenario2, type2CountScenario2, type3CountScenario2, type4CountScenario2);
    }

    private static void simulateScenario(String scenarioName, int type1Count, int type2Count, int type3Count, int type4Count) {
        System.out.println("\n" + scenarioName);

        List<Player> players = new ArrayList<>();
        addPlayers(players, type1Count, Strategy.TYPE_1);
        addPlayers(players, type2Count, Strategy.TYPE_2);
        addPlayers(players, type3Count, Strategy.TYPE_3);
        addPlayers(players, type4Count, Strategy.TYPE_4);

        for (Strategy testStrategy : Strategy.values()) {
            Player testPlayer = new Player(testStrategy);
            players.add(testPlayer);

            simulateTournament(players);

            System.out.printf("Total score for %s as test player: %d\n", testStrategy, testPlayer.getScore());
            players.remove(testPlayer);
        }
    }

    private static void addPlayers(List<Player> players, int count, Strategy strategy) {
        for (int i = 0; i < count; i++) {
            players.add(new Player(strategy));
        }
    }

    private static void simulateTournament(List<Player> players) {
        for (int i = 0; i < players.size(); i++) {
            for (int j = i + 1; j < players.size(); j++) {
                playContest(players.get(i), players.get(j));
            }
        }
    }

    private static void playContest(Player player1, Player player2) {
        char lastMoveP1 = 'C';
        char lastMoveP2 = 'C';

        for (int round = 0; round < 10; round++) {
            char moveP1 = player1.getNextMove(lastMoveP2);
            char moveP2 = player2.getNextMove(lastMoveP1);

            int scoreP1 = PAYOFF_MATRIX[moveP1 == 'C' ? 0 : 1][moveP2 == 'C' ? 0 : 1];
            int scoreP2 = PAYOFF_MATRIX[moveP2 == 'C' ? 0 : 1][moveP1 == 'C' ? 0 : 1];

            player1.addScore(scoreP1);
            player2.addScore(scoreP2);

            lastMoveP1 = moveP1;
            lastMoveP2 = moveP2;
        }
    }

    enum Strategy {
        TYPE_1,
        TYPE_2,
        TYPE_3,
        TYPE_4
    }

    static class Player {
        private final Strategy strategy;
        private int score;

        public Player(Strategy strategy) {
            this.strategy = strategy;
        }

        public Strategy getStrategy() {
            return strategy;
        }

        public int getScore() {
            return score;
        }

        public void addScore(int score) {
            this.score += score;
        }

        public char getNextMove(char opponentLastMove) {
            switch (strategy) {
                case TYPE_1:
                    return 'C';
                case TYPE_2:
                    return 'D';
                case TYPE_3:
                    return Math.random() < 0.5 ? 'C' : 'D';
                case TYPE_4:
                    return opponentLastMove;
                default:
                    throw new IllegalStateException("Unknown strategy: " + strategy);
            }
        }
    }
}
