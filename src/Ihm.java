import clavier.In;

import java.util.ArrayList;

public class Ihm {
    private final String[] RANKINGS = {"", "NC", "40", "30/5", "30/4", "30/3", "30/2", "30/1", "30", "15/5", "15/4"};
    private final String[] STATS = {"4 échelons en dessous et plus", "3 échelons en dessous", "2 échelons en dessous",
            "1 échelon en dessous", "échelon égal", "1 échelon au dessus", "2 échelons au dessus et plus"};

    void main(String[] args) {
        int rankIndex = -1, maxVictoire, pointCapital, victoires = 0, defaites = 0, pts;
        ArrayList<Integer> victoiresStats = new ArrayList<>();

        do {
            if (rankIndex == -1) rankIndex = askRanking(false);
            pointCapital = getCapitalFromRank(rankIndex);
            maxVictoire = getMaxVictoryFromRank(rankIndex);

            if (victoiresStats.isEmpty()) {
                System.out.print("Entrez votre nombre de victoire : ");
                victoires = In.readInteger();
            }

            if (victoires > maxVictoire) {
                System.out.print("Entrez votre nombre de défaites : ");
                defaites = In.readInteger();
            }

            System.out.println();

            //Bonus calcule
            int echelonEgale = 0;
            int echelon1Inf = 0;
            int echelon2Inf = 0;
            for (int i = 0; i < defaites; i++) {
                System.out.println("Défaite n°" + (i + 1));
                int def = this.askRanking(true);
                int diff = rankDiff(rankIndex, def);
                switch (diff) {
                    case -2:
                        echelon2Inf++;
                        break;
                    case -1:
                        echelon1Inf++;
                        break;
                    case 0:
                        echelonEgale++;
                        break;
                }
                System.out.println("Vous avez donc perdu a " + rankIToStatsText(diff));
            }
            if (victoires > maxVictoire) {
                // V – e – 2i - 5G
            /*
                • V = nombre de victoires
                • E = nombre de défaites à échelon égal
                • I = nombre de défaites à 1 échelon inférieur ;
                • G = nombre de défaites à 2 échelons
            * */
                int scoreBonus = victoires - echelonEgale - 2 * echelon1Inf - 5 * echelon2Inf;
                if (scoreBonus >= 25) {
                    maxVictoire += 6;
                } else if (scoreBonus > 20) {
                    maxVictoire += 5;
                } else if (scoreBonus > 15) {
                    maxVictoire += 4;
                } else if (scoreBonus > 10) {
                    maxVictoire += 3;
                } else if (scoreBonus > 5) {
                    maxVictoire += 2;
                } else if (scoreBonus > 0) {
                    maxVictoire += 1;
                }
                System.out.println("V = " + victoires);
                System.out.println("E = " + echelonEgale);
                System.out.println("I = " + echelon1Inf);
                System.out.println("G = " + echelon2Inf);
                System.out.println("V - E - 2 * I - 5 * G = point bonus");
                System.out.println(victoires + "-" + echelonEgale + "-" + 2 * echelon1Inf + "-" + 5 * echelon2Inf + "=" + scoreBonus);
                System.out.println("Vous avez un score bonus de " + ConsoleColors.ANSI_BLUE_BOLD + scoreBonus + ConsoleColors.ANSI_RESET);
                System.out.println("Cela vous donne droit a " + ConsoleColors.ANSI_BLUE_BOLD + maxVictoire + ConsoleColors.ANSI_RESET + " victoires au total.");

                if (victoires > maxVictoire) {
                    victoires = maxVictoire;
                }
            }

            System.out.println("Vous avez " + ConsoleColors.ANSI_BLUE_BOLD + victoires + ConsoleColors.ANSI_RESET + " victoires et vous commencerez avec " + ConsoleColors.ANSI_BLUE_BOLD + pointCapital + ConsoleColors.ANSI_RESET + " points");
            System.out.println();

            pts = pointCapital;
            int addPts;
            if (victoiresStats.isEmpty()) {
                for (int i = 0; i < victoires; i++) {
                    System.out.println("Victoire n°" + (i + 1));
                    victoiresStats.add(askRanking(true));
                    addPts = getPtsFromVictory(rankDiff(rankIndex, victoiresStats.get(i)));
                    pts += addPts;
                    System.out.println("Vous avez donc gagné a " + rankIToStatsText(victoiresStats.get(i)));
                    System.out.println("A l'issue de ce match vous avez gagné " + ConsoleColors.ANSI_BLUE_BOLD + addPts + ConsoleColors.ANSI_RESET + " points\n");
                }
            } else {
                for (int vic : victoiresStats) {
                    addPts = getPtsFromVictory(rankDiff(rankIndex, vic));
                    pts += addPts;
                    System.out.println("A l'issue de ce match vous avez gagné " + ConsoleColors.ANSI_BLUE_BOLD + addPts + ConsoleColors.ANSI_RESET + " points\n");
                }
            }

            System.out.println("Vous avez un total de " + ConsoleColors.ANSI_BLUE_BOLD + pts + ConsoleColors.ANSI_RESET + " points");

            int min = 0, max = 0;
            max = switch (rankIndex) {
                case 1 -> 50;
                case 2 -> {
                    min = 30;
                    yield 80;
                }
                case 3 -> {
                    min = 50;
                    yield 150;
                }
                case 4 -> {
                    min = 90;
                    yield 260;
                }
                case 5 -> {
                    min = 145;
                    yield 340;
                }
                case 6 -> {
                    min = 205;
                    yield 410;
                }
                case 7 -> {
                    min = 245;
                    yield 480;
                }
                case 8 -> {
                    min = 290;
                    yield 510;
                }
                case 9 -> {
                    min = 325;
                    yield 580;
                }
                case 10 -> {
                    min = 395;
                    yield 660;
                }
                default -> max;
            };

            if (pts >= max) {
                rankIndex++;
                System.out.println("Bravo ! Vous montez de 1 dans le classement");
                System.out.println("Vous passez donc " + RANKINGS[rankIndex]);
            } else if (pts <= min) {
                System.out.println("Dommage ! Vous perdez de 1 dans le classement");
                System.out.println("Vous passez donc " + RANKINGS[rankIndex - 1]);
                System.out.println("Vous ne pouvez pas plus décendre donc c'est la fin du match");
                break;
            } else {
                System.out.println("Vous ne bougez pas dans le classement");
                System.out.println("Vous êtes donc " + RANKINGS[rankIndex]);
                break;
            }
        } while (true);
    }

    private int askRanking(boolean match) {
        for (int i = 0; i < this.RANKINGS.length; i++) {
            if (i == 0) continue;
            System.out.println("[" + (i) + "] " + this.RANKINGS[i]);
        }
        if (match) {
            System.out.print("Entrez le classement de votre adversaire : ");
        } else {
            System.out.print("Entrez votre classement : ");
        }
        return In.readInteger();
    }
    private int getPtsFromVictory(int vic) {
        if (vic >= 2) return 150;
        if (vic == 1) return 100;
        if (vic == 0) return 50;
        if (vic == -1) return 30;
        if (vic == -2) return 20;
        if (vic == -3) return 15;
        return 0;
    }
    private int getCapitalFromRank(int rank) {
        return switch (rank) {
            case 2 -> 2;
            case 3 -> 5;
            case 4 -> 10;
            case 5 -> 20;
            case 6 -> 30;
            case 7 -> 50;
            case 8 -> 80;
            case 9 -> 120;
            case 10 -> 160;
            default -> 0;
        };
    }
    private int getMaxVictoryFromRank(int rank) {
        return switch (rank) {
            case 1, 2, 3, 4 -> 5;
            case 5, 6, 7, 8, 9, 10 -> 6;
            default -> 0;
        };
    }
    private int rankDiff(int myRank, int otherRank) {
        return otherRank - myRank;
    }
    private String rankIToStatsText(int rank) {
        if (rank < -4) rank = -4;
        if (rank > 2) rank = 2;
        return STATS[rank + 4];
    }
}
