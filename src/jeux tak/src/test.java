
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class test {

    private static String a;
    static ArrayList tab = new ArrayList();
    static ArrayList tab2 = new ArrayList();
    static long score = 0;

    public static void main(String[] args) {
        Tak t1;
        Tak t;
        Tak essai = new Tak();

        int choix = -1;
        while (choix != 0) {

            System.out.println("0 -> quitter");
            System.out.println("1 -> regle du jeux");
            System.out.println("2 -> jouer");

            System.out.println("Votre Choix :");
            Scanner sc1 = new Scanner(System.in);
            int choixi = sc1.nextInt();

            if (choixi == 1) {
                System.out.println("c'est un jeux semblable a sudoku la grille est compose de 0 et 1  ");
                System.out.println("==========");
                System.out.println("les lignes doivent etre differents et les colonnes");
                System.out.println("==========");
                System.out.println("le nombre de 1 doit etre egale au nombre de 0 dans chaque ligne et colonne");

                System.out
                        .println("Les grilles doivent etre carre, et les lignes doivent avoir un nombre pair de cases.");

                System.out.println("choisir le 2eme choix du menu pour jouer");
                System.out.println("==========");

                System.out.println("Lancer le jeux Takuzu de niveau entr�.");
                System.out.println("");
                System.out
                        .println(" la grille est preremplis aleatoirement");
                System.out.println("les cases vides sont designes par des etoiles ");
                System.out.println("si tu trouves que plus que la moitie des cases portant le meme nombre  tu peux changer");

                System.exit(0);
            } else if (choixi == 2) {

                System.out
                        .println("             ********************************");
                System.out
                        .println("             *          1 Nouvelle partie     *");
                System.out.println("             *          Be ready   *");
                System.out
                        .println("             ********************************");
                int taille = 0;
                do {
                    System.out.println("donner le niveau du jeux 4*4 /6*6/8*8");
                    Scanner sc2 = new Scanner(System.in);
                    taille = sc2.nextInt();
                } while ((taille != 6) && (taille != 4) && (taille != 8));

                int l = 1;

                while (l < 3) {
                    float f = 10;

                    t = new Tak(taille);
                    System.out.println(t.toString());

                    System.out.println("donner ta" + " " + l + " " + "solution:");
                    long startTime1 = System.currentTimeMillis();
                    String a;
                    for (int j = 0; j < taille; j++) {
                        Scanner sc6 = new Scanner(System.in);
                        a = sc6.next();
                        for (int i = 0; i < taille; i++) {
                            for (int k = 0; k < taille; k++) {

                                essai.grille[i][k] = (int) a.charAt(k);

                            }
                        }
                    }

                    long endTime = System.currentTimeMillis();
                    score = (endTime - startTime1) / 1000;
                    System.out.println("****");
                    System.out.println("ton score est:");
                    System.out.println(score + " " + "secondes");

                    Tak slt = essai.resolution();
                    System.out.println(tab);
                    if (essai.estValide()) {
                        System.out.println("vous avez gagnez !voulez vous rejouer 1/0");

                        Scanner sc7 = new Scanner(System.in);
                        int a1 = sc7.nextInt();
                        if (a1 == 0) {

                        }
                    } else {
                        System.out.println("vous avez perdu car:");

                        for (int j = 0; j < essai.grille.length; j++) {

                            if (essai.ligneValide(j)) {
                                System.out.println("la ligne" + " " + j + " " + "nest pas valide");
                                System.out.println("******");
                            } else if (essai.ligneEstUnique(j)) {
                                System.out.println("la ligne " + j + "est dupliqu�");
                                System.out.println("******");
                            }

                            if (essai.coloneValide(j)) {
                                System.out.println("la colone" + " " + j + "" + "nest pas valide");
                                System.out.println("*+*+*+*+*+*");
                            } else if (essai.coloneEstUnique(j)) {
                                System.out.println("la colone " + " " + j + " " + "est dupliqu�");
                                System.out.println("******");
                            }
                        }
                        System.out.println("***********");

                    }

                    l++;
                }

                tab.add(score);
                Collections.sort(tab);
                for (int i = 0; i < 10; i++) {

                    tab2.add(tab.get(i));
                }
                try {
                    PrintStream s = new PrintStream(new File("C:\\Users\\user\\eclipse-workspace\\jeux Tak\\src\\scores.txt"));
                    //System.setOut(s);
                    if (tab.size() > 10) {

                        for (int i = 0; i < 10; i++) {
                            s.print(tab2.get(i));

                        }

                    } else {
                        for (int i = 0; i < tab.size(); i++) {
                            s.print(tab2.get(i));

                        }
                    }

                } catch (FileNotFoundException o) {
                    System.out.println(o);

                }

            }
        }
    }
}
