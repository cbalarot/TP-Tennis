import clavier.In;

import java.util.Arrays;

public class Ihm {
    void main(String[] args) {
        String[] CLASSEMENT = {"NC", "40", "30/5", "30/4", "30/3", "30/2", "30/1", "30", "15/5", "15/4"};
        int classement = 0, maxVictoire = 0, pointCapital = 0, victoires, defaites, pts;

        do {
            if (classement == 0) {
                System.out.println("[1] NC");
                System.out.println("[2] 40");
                System.out.println("[3] 30/5");
                System.out.println("[4] 30/4");
                System.out.println("[5] 30/3");
                System.out.println("[6] 30/2");
                System.out.println("[7] 30/1");
                System.out.println("[8] 30");
                System.out.println("[9] 15/5");
                System.out.println("[10] 15/4");
                System.out.print("Entrez votre classement : ");

                classement = In.readInteger();
            }


            switch (classement) {
                case 1:
                    maxVictoire = 5;
                    pointCapital = 0;
                    break;
                case 2:
                    maxVictoire = 5;
                    pointCapital = 2;
                    break;
                case 3:
                    maxVictoire = 5;
                    pointCapital = 5;
                    break;
                case 4:
                    maxVictoire = 5;
                    pointCapital = 10;
                    break;
                case 5:
                    maxVictoire = 6;
                    pointCapital = 20;
                    break;
                case 6:
                    maxVictoire = 6;
                    pointCapital = 30;
                    break;
                case 7:
                    maxVictoire = 6;
                    pointCapital = 50;
                    break;
                case 8:
                    maxVictoire = 6;
                    pointCapital = 80;
                    break;
                case 9:
                    maxVictoire = 6;
                    pointCapital = 120;
                    break;
                case 10:
                    maxVictoire = 6;
                    pointCapital = 160;
                    break;
                default:
                    System.out.println("Classement non valide");
            }

            System.out.print("Entrez votre nombre de victoire : ");
            victoires = In.readInteger();

            System.out.print("Entrez votre nombre de défaites : ");
            defaites = In.readInteger();
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
            for (int i = 0; i < victoires; i++) {
                int addPts = 0;
                switch (askEchelon(true, i + 1)) {
                    case 1:
                        addPts = 150;
                        break;
                    case 2:
                        addPts = 100;
                        break;
                    case 3:
                        addPts = 50;
                        break;
                    case 4:
                        addPts = 30;
                        break;
                    case 5:
                        addPts = 20;
                        break;
                    case 6:
                        addPts = 15;
                        break;
                    case 7:
                        break;
                }
                pts += addPts;
                System.out.println("A l'issue de ce match vous avez gagné " + ConsoleColors.ANSI_BLUE_BOLD + addPts + ConsoleColors.ANSI_RESET + " points\n");
            }
            System.out.println("Vous avez un total de " + ConsoleColors.ANSI_BLUE_BOLD + pts + ConsoleColors.ANSI_RESET + " points");

            int min = 0, max = 0;
            switch (classement) {
                case 1:
                    max = 50;
                    break;
                case 2:
                    min = 30;
                    max = 80;
                    break;
                case 3:
                    min = 50;
                    max = 150;
                    break;
                case 4:
                    min = 90;
                    max = 260;
                    break;
                case 5:
                    min = 145;
                    max = 340;
                    break;
                case 6:
                    min = 205;
                    max = 410;
                    break;
                case 7:
                    min = 245;
                    max = 480;
                    break;
                case 8:
                    min = 290;
                    max = 510;
                    break;
                case 9:
                    min = 325;
                    max = 580;
                    break;
                case 10:
                    min = 395;
                    max = 660;
                    break;
            }

            if (pts >= max) {
                System.out.println("Bravo ! Vous monter de 1 dans le classement");
                System.out.println("Vous passez donc " + CLASSEMENT[classement]);
                classement++;
            } else if (pts <= min) {
                System.out.println("Dommage ! Vous perdez de 1 dans le classement");
                System.out.println("Vous passez donc " + CLASSEMENT[classement - 2]);
                System.out.println("Vous ne pouvez pas plus décendre donc c'est la fin du match");
                break;
            } else {
                System.out.println("Vous ne bougez pas dans le classement");
                System.out.println("Vous etes donc " + CLASSEMENT[classement - 1]);
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
}
