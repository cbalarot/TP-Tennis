import clavier.In;

public class Ihm {
    static void main(String[] args) {
        int classement = 0, maxVictoire = 0, pointCapital = 0, victoires = 0, pts = 0;

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

        System.out.println("Vous aurrez au max " + maxVictoire + " victoires et vous commencerez avec " + pointCapital + " points\n");

        System.out.print("Entrez votre nombre de victoire : ");
        victoires = In.readInteger();

        if (victoires > maxVictoire) {
            System.out.println("Vous avez trop de victoires, seulement " + maxVictoire + " seront comptabilisés");
            victoires = maxVictoire;
        } else {
            System.out.println("Vous commencerez avec " + victoires + " victoires");
        }
        System.out.println();

        pts = pointCapital;
        for (int i = 0; i < victoires; i++) {
            int addPts = 0;
            System.out.println("Victoire n°" + (i + 1) + " :");
            System.out.println("[1] Victoire à 2 échelons au dessus et plus");
            System.out.println("[2] Victoire à 1 échelon au dessus");
            System.out.println("[3] Victoire à échelon égal");
            System.out.println("[4] Victoire à 1 échelon en dessous");
            System.out.println("[5] Victoire à 2 échelons en dessous");
            System.out.println("[6] Victoire à 3 échelons en dessous");
            System.out.println("[7] Victoire à 4 échelons en dessous et plus");

            System.out.print("\nEntrez votre type de victoire : ");
            switch (In.readInteger()) {
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
                min = 0;
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
        } else if (pts <= min) {
            System.out.println("Dommage ! Vous perdez de 1 dans le classement");
        } else {
            System.out.println("Vous ne bougez pas dans le classement");
        }
    }
}
