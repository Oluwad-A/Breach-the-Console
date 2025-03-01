import java.util.Scanner;

public class MainGame {

  public static void main(String[] args) {
    Scanner choices = new Scanner(System.in);
    Formatting format = new Formatting();
    StringImages img = new StringImages();
    EncounterGen enc = new EncounterGen();
    Player you = new Player();
    String playerAction = "";
    String name;
    int difficulty = 0;
    boolean inEncounter = false;
    boolean inStay = false;
    boolean gameEnd = false;
    boolean inGame = true; // Determines whether or not user is in a game.
    int seerCount = 0;
    int swarmCount = 0;
    int cageCount = 0;
    boolean newGame = true;
    String placeholder = "";

    while (newGame == true) {
      gameEnd = false;
      System.out.println(img.imageStart());

      System.out
              .println("Your eyes tremble. It is only natural, given how long you've been asleep. You "
                      + "attempt to recall your name... It was...");

      format.singleSpace();

      name = choices.next();

      format.singleSpace();

      System.out
              .println(name + "...Yes. " + name + " was your name. You stand upright and grasp your su"
                      + "rroundings. At your feet is broken glass and odd green fluid. There are sirens"
                      + " blaring all around you. You're unsure of what's happening but you feel compel"
                      + "led to do something. Given the choice to 'move' or 'stay', you...");

      while (gameEnd == false) {
        while (inEncounter == false) {

          playerAction = choices.next();

          while (!playerAction.equalsIgnoreCase("move") && !playerAction.equalsIgnoreCase("stay")) {
            System.out
                    .println("Once more, you have the options to 'move' or 'stay' and decide to...");
            playerAction = choices.next();
          }
          if (playerAction.equals("move")) {
            System.out.println(you.Move());
            format.singleSpace();
          }
          else {

            System.out.println(you.Stay());

            format.singleSpace();

            inStay = you.getActionSu();

            while (inStay == true) {

              if (playerAction == "") {
              }
              else {
                System.out.println(
                        "Now that you have time to think, given the options of 'view'ing, 'stay'ing, 'u"
                                + "s(e)' or trying to 'move', you try to... ");
              }
              playerAction = choices.next();
              if (playerAction.equals("move")) {
                System.out.println(you.Move());
                inStay = false;
                format.singleSpace();
              }
              if (playerAction.equals("stay")) {
                System.out.println(you.Stay());
                if (you.getActionSu() != true) {
                  inStay = false;
                }
                format.singleSpace();
              }
              if (playerAction.equals("view")) {
                System.out.print(you.View());
              }
              if (playerAction.equals("use")) {
                String item = choices.next().toUpperCase();
                if (item.equals("ODD")) {
                  item = "Odd Paper";
                }
                if (item.equals("BROKEN")) {
                  item = "Broken Lens";
                }
                if (item.equals("LIVING")) {
                  item = "Living Mound";
                }
                if (item.equals("TOXIC")) {
                  item = "Toxic Herb";
                  you.setStatus("Poisoned");
                }
                if (item.equals("REJUVINATING")) {
                  item = "Rejuvinating Pill";
                }
                if (item.equals("ETHEREAL")) {
                  item = "Ethereal Tongue";
                }
                if (item.equals("CRYSTALLINE")) {
                  item = "Crystalline Flower";
                }
                System.out.println(you.Use(item));
                playerAction = ""; // Used to stop replication of "Now that..." String
              }

            }

          }
          // While staying, the player has the options to check their stats, move, or try
          // staying.
          enc = new EncounterGen();
          inEncounter = true;
          if (enc.getEncounterType().equals("Overseer/Wordle")) {
            seerCount++;
          }
          if (enc.getEncounterType().equals("Swarm/Hangman")) {
            swarmCount++;
          }
          if (enc.getEncounterType().equals("Cage/Pacman")) {
            cageCount++;
          }
        }

        while (inEncounter == true) {
          Formatting.Line();
          enc.runEncounter(you.getStamina(), you.getItemInUse(), you, you.getStatus(),
                  you.getDifficulty(), seerCount, swarmCount, cageCount);
          Formatting.Line();
          you.setSurvivalScoreBonus(you.getSurvivalScoreBonus() + 1);
          if (!enc.getWon()) {
            // inEncounter = false;
            if (enc.getEncounterType().equals("Swarm/Hangman")) {
              you.setStatus(enc.getStatusGained());
            }
            else {
              gameEnd = true;
            }
          }
          if (enc.getEncounterType().equals("Free")) {
            you.itemsAdd(enc.getGainedItem());
            you.gainStamina(2);
          }
          if (enc.getEncounterType().equals("Overseer/Wordle")) {
            you.gainSurvivalScore(5 + you.getSurvivalScoreBonus());
          }
          if (enc.getEncounterType().equals("Swarm/Hangman")) {
            you.gainSurvivalScore(5 + you.getSurvivalScoreBonus());
            you.setStamina(enc.getNewStamina());
          }
          if (enc.getEncounterType().equals("Cage/Pacman")) {
            you.gainSurvivalScore(5 + you.getSurvivalScoreBonus());
          }
          if (enc.getEncounterType().equals("Final")) {
            gameEnd = true;
          }

          inEncounter = false;
          playerAction = "";
        }

        if (!enc.getWon() && gameEnd) {
          System.out.println(img.Lost());
          System.out.println("You, " + name
                  + ", tried to gain freedom but inevitably failed. You escaped " + seerCount
                  + " Overseers, endured " + swarmCount
                  + " spire swarms, and survived the domain of a beast " + cageCount + " times with "
                  + "a score of " + you.getSurvivalScore() + ". \n Play Again? ('Yes' or 'No')");
          if (choices.next().equalsIgnoreCase("YES")) {
            seerCount = 0;
            cageCount = 0;
            swarmCount = 0;
            you = new Player();
            placeholder = choices.next();
          }
          else {
            newGame = false;
          }

        }

        if (you.getSurvivalScore() >= 100 && you.getHasFlower() == true) {
          System.out.println(img.Won());
          System.out.println("☆*.∘˚˳° ★ ⊂(╹ヮ╹)⊃ ★ ∘˚˳°☆*. CONGRATS!");
          System.out.println("You, " + name
                  + ", fled endlessly towards your freedom and freedom is what you obtained! You "
                  + "are liberated; Never to return to that which you've forgotten... You escaped "
                  + seerCount + " Overseers, endured " + swarmCount
                  + " spire swarms, and survived the domain of a beast " + cageCount + " times on with "
                  + "a score of " + you.getSurvivalScore() + ". \n Play Again? ('Yes' or 'No')");
          if (choices.next().toUpperCase().equals("YES")) {
            gameEnd = false;
            seerCount = 0;
            cageCount = 0;
            swarmCount = 0;
            you = new Player();
          }
          else {
            newGame = false;
          }
        }

      }
    }
  }
}
