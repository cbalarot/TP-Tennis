import clavier.In;

import java.util.ArrayList;

public class Ihm {
    private final String[] RANKINGS = {"", "NC", "40", "30/5", "30/4", "30/3", "30/2", "30/1", "30", "15/5", "15/4"};

    void main(String[] args) {
        final String[] STATS = {"2 échelons au dessus et plus", "1 échelon au dessus", "échelon égal",
                "1 échelon en dessous", "2 échelons en dessous", "3 échelons en dessous", "4 échelons en dessous et plus"};
        int rankIndex = -1, maxVictoire, pointCapital, victoires = 0, defaites = 0, pts;
        ArrayList<Integer> victoiresStats = new ArrayList<>();
        ArrayList<Integer> defaitesStats = new ArrayList<>();

        do {
            if (rankIndex == -1) rankIndex = askRanking();
            pointCapital = getCapitalFromRank(rankIndex);
            maxVictoire = getMaxVictoryFromRank(rankIndex);

            if (victoiresStats.isEmpty()) {
                System.out.print("Entrez votre nombre de victoire : ");
                victoires = In.readInteger();
            }

            if (defaitesStats.isEmpty()) {
                System.out.print("Entrez votre nombre de défaites : ");
                defaites = In.readInteger();
            } else {
                System.out.println("Voici vos défaites actuelles :");
                int i = 0;
                for (int def : defaitesStats) {
                    System.out.println(i + " " + def);
                    i++;
                }
                System.out.println("Avez vous de nouvelle défaites? [0-9]");
            }
            System.out.println();

            //Bonus calcule
            int echelonEgale = 0;
            int echelon1Inf = 0;
            int echelon2Inf = 0;
            for (int i = 0; i < defaites; i++) {
                switch (this.askEchelon(false, i + 1)) {
                    case 3: //echelon égale
                        echelonEgale++;
                        break;
                    case 4:
                        echelon1Inf++;
                        break;
                    case 5:
                        echelon2Inf++;
                        break;
                }
            }
            if (victoires > maxVictoire) {
                // V – e – 2i - 5G
            /*
                • V = nombre de victoires
                • E = nombre de défaites à échelon égal
                • I = nombre de défaites à 1 échelon inférieur ;
                • G =nombre de défaite à 2 échelons
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
            if (victoiresStats.isEmpty()) {
                for (int i = 0; i < maxVictoire; i++) {
                    victoiresStats.add(askEchelon(true, i + 1));
                }
            }

            for (int vic : victoiresStats) {
                int addPts = getPtsFromVictory(vic);
                pts += addPts;
                System.out.println("A l'issue de ce match vous avez gagné " + ConsoleColors.ANSI_BLUE_BOLD + addPts + ConsoleColors.ANSI_RESET + " points\n");
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
                System.out.println("Bravo ! Vous monter de 1 dans le rankIndex");
                System.out.println("Vous passez donc " + RANKINGS[rankIndex]);
                rankIndex++;
            } else if (pts <= min) {
                System.out.println("Dommage ! Vous perdez de 1 dans le rankIndex");
                System.out.println("Vous passez donc " + RANKINGS[rankIndex - 2]);
                System.out.println("Vous ne pouvez pas plus décendre donc c'est la fin du match");
                break;
            } else {
                System.out.println("Vous ne bougez pas dans le rankIndex");
                System.out.println("Vous etes donc " + RANKINGS[rankIndex - 1]);
                break;
            }
        } while (true);
    }

    /**
     * @param victoire boolean => Echelon de victoire ou défaite
     * @return int Numéro de l'echellon
     * <p>
     * 1 => Victoire à 2 échelons au dessus et plus
     * 2 => Victoire à 1 échelon au dessus
     * 3 => Victoire à échelon égal
     * 4 => Victoire à 1 échelon en dessous
     * 5 => Victoire à 2 échelons en dessous
     * 6 => Victoire à 3 échelons en dessous
     * 7 => Victoire à 4 échelons en dessous et plus
     */
    private int askEchelon(boolean victoire, int num) {
        String type = victoire ? "Victoire" : "Défaite";
        System.out.println(type + " n°" + num + " :");
        System.out.println("[1] " + type + " à 2 échelons au dessus et plus");
        System.out.println("[2] " + type + " à 1 échelon au dessus");
        System.out.println("[3] " + type + " à échelon égal");
        System.out.println("[4] " + type + " à 1 échelon en dessous");
        System.out.println("[5] " + type + " à 2 échelons en dessous");
        System.out.println("[6] " + type + " à 3 échelons en dessous");
        System.out.println("[7] " + type + " à 4 échelons en dessous et plus");

        System.out.print("\nEntrez votre type de " + type + " : ");
        return In.readInteger();
    }

    private int askRanking() {
        for (int i = 0; i < this.RANKINGS.length; i++) {
            if (i == 0) continue;
            System.out.println("[" + (i) + "] " + this.RANKINGS[i]);
        }
        System.out.print("Entrez votre classement : ");
        return In.readInteger();
    }

    private int getPtsFromVictory(int vic) {
        return switch (vic) {
            case 1 -> 150;
            case 2 -> 100;
            case 3 -> 50;
            case 4 -> 30;
            case 5 -> 20;
            case 6 -> 15;
            default -> 0;
        };
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
}
