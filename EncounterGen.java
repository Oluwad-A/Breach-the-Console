import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
// USed to determine general encounters

public class EncounterGen extends EncounterBase {

  boolean haveEncounter;

  public EncounterGen() { // Add difficulty based on stamina
    randomizer = (int) (Math.random() * 10);
    subRandomizer = (int) (Math.random() * 10) + 3; // May be edited
    if (randomizer <= 9) {
      encounterType = "Free"; // impossible to get if staying fails
    }
    if (randomizer <= 8) {
      encounterType = "Overseer/Hangman"; // Not really Wordle -> Hangman
    }
    if (randomizer <= 6) {
      encounterType = "Swarm/Pictionary"; // Not actually Hangman -> PICTIONARY.
    }
    if (randomizer <= 4) {
      encounterType = "Cage/Pacman"; // Pacman
    }
    // Old location for encounter intro text.
  }

  public String getRandomWord() {
    String[][] words = new String[11][4];
    List<String> someWords = new ArrayList<>();
    try {
      String wordListFilePath = "Word List.txt";
      BufferedReader fileReader = new BufferedReader(new FileReader(wordListFilePath));
      String currentLine;

      while (((currentLine = fileReader.readLine()) != null)) {
        someWords.add(currentLine);
      }
      System.out.println("num obtained");
      return someWords.get((int) (Math.random() * someWords.size()));

    } catch (IOException noFileFound) {

      /*
      words[0][0] = "cpu"; // 3 letter words
      words[0][1] = "net";
      words[0][2] = "rpg";
      words[0][3] = "int";

      words[1][0] = "byte"; // 4 letter words
      words[1][1] = "chip";
      words[1][2] = "data";
      words[1][3] = "code";

      words[2][0] = "virus"; // 5 letter words
      words[2][1] = "pixel";
      words[2][2] = "input";
      words[2][3] = "block";

      words[3][0] = "output"; // 6 letter words
      words[3][1] = "update";
      words[3][2] = "upload";
      words[3][3] = "server";

      words[4][0] = "malware"; // 7 letter words
      words[4][1] = "digital";
      words[4][2] = "browser";
      words[4][3] = "offline";

      words[5][0] = "internet"; // 8 letter words
      words[5][1] = "firewall";
      words[5][2] = "software";
      words[5][3] = "hardware";

      words[6][0] = "procedure"; // 9 letter words
      words[6][1] = "downgrade";
      words[6][2] = "algorithm";
      words[6][3] = "character";

      words[7][0] = "encryption"; // 10 letter words
      words[7][1] = "multimedia";
      words[7][2] = "simulation";
      words[7][3] = "diagnostic";

      words[8][0] = "compression"; // 11 letter words
      words[8][1] = "programming";
      words[8][2] = "instruction";
      words[8][3] = "abomination";

      words[9][0] = "annihilation"; // 12 letter words
      words[9][1] = "obliteration";
      words[9][2] = "degeneration";
      words[9][3] = "irredeemable";

      words[10][0] = "contamination"; // 13 letter words
      words[10][1] = "indescribable";
      words[10][2] = "insubordinate";
      words[10][3] = "dishonourable";

      return words[subRandomizer - 2][(int) ((Math.random() * 10) * .25)];
    */
      return "guh";
    }
  }

  public String getRandomStatus() {
    String[] statuses = new String[] { "Poisoned", "Disheartened", "Slowed" };

    return statuses[(int) (Math.random() * 3)];
  }

  public String getStatusGained() {
    return statusGained;
  }

  public void runEncounter(int stam, String item, Player flee, String playerStatus, int difficulty,
                           int seerCount, int swarmCount, int cageCount) { // In free encounters, the user finds a random
    // item. Parameters used for certain
    // encounters
    boolean canFlee = true;
    if (flee.getSurvivalScore() >= 100 && item.equals("Crystalline Flower")) {
      encounterType = "Final"; // Used to end game
      won = true;
    }
    if (difficulty >= 3) {
      difficulty = 2;
    }
    if (playerStatus.equals("Poisoned")) {
      flee.setStamina(0);
      if (item.equals("Rejuvinating Pill")) {
        flee.setStatus("");
        System.out.println(
                "That which you had gained has been used. You feel rejuvinated and you are no longer "
                        + playerStatus + "...");
        playerStatus = flee.getStatus();
      }
    }
    if (playerStatus.equals("Disheartened")) {
      flee.setSurvivalScoreBonus(0);
      if (item.equals("Rejuvinating Pill")) {
        flee.setStatus("");
        System.out.println(
                "That which you had gained has been used. You feel rejuvinated and you are no longer "
                        + playerStatus + "...");
        playerStatus = flee.getStatus();
      }
    }
    if (encounterType.equals("Free")) {
      System.out
              .println("You find a small crack in the rubble to hide in. You quickly delve in and to r"
                      + "est. You feel your stamina steadily returning and your will to continue increa"
                      + "ses..."); // Stamina increase + survival score increase
    }
    if (encounterType.equals("Overseer/Hangman")) { // Slightly changes text dependening on whether
      // or not this is the first time player has
      // encounter.
      if (seerCount == 1) {
        System.out.println(
                "You instinctively come to a stop. The air around you grows colder and through "
                        + "your peripheral vision you can see a mound of weaponry encased in closed, frac"
                        + "tured eyes. The 'overseers' are in shambles but haven’t ceased operation. You "
                        + "mutter a curse under your breath and immediately one eye out of " + subRandomizer
                        + " opens up. The overseer has yet to relent its gaze. You must g"
                        + "uess the phrase to put it to sleep'. " + (subRandomizer - 1)
                        + " eyes remain closed... \n(You must enter one character at a time to determine the word. Once you believe you know the word, type out the entire word.");
        subRandomizer--;
      }
      else {
        System.out.println("Once more you find yourself in the presence of an overseer. "
                + subRandomizer + " eyes remain closed...");
      }
    }
    if (encounterType.equals("Swarm/Pictionary")) {
      if (swarmCount == 1) {
        System.out.println("Without warning, " + subRandomizer
                + " spires erect around you and impede your"
                + " movement. Each spire bears an empty space where a sigil may be drawn. You ill"
                + "ustrate the sigil 'z' and feel your stamina leaving you. You don't wish to kno"
                + "w what would happen if your mistakes persist... \n(Enter what character you believe the image is to inscribe the sigil)");
      }
      else {
        System.out.println("You're surrounded by spires once again. This time there are "
                + subRandomizer + " of them...");
      }
    }
    // ------------------------------------------------
    boolean inProgress = true;
    String trueWord = getRandomWord();

    if (encounterType.equals("Free")) { // Free Item
      itemRandomizer = (int) (Math.random() * 10);
      if (itemRandomizer <= 9) {
        gainedItem = "Odd Paper"; // Reveals word for hangman
      }
      if (itemRandomizer <= 7) {
        gainedItem = "Broken Lens"; // Gives the answer to Pictionary
      }
      if (itemRandomizer <= 6) {
        gainedItem = "Living Mound"; // Can be used as a substitute (slows down beast)
      }
      if (itemRandomizer <= 5) {
        gainedItem = "Toxic Herb"; // Sets stamina to 0 during beast encounter but automatically
        // wins.
      }
      if (itemRandomizer <= 3) {
        gainedItem = "Rejuvinating Pill"; // Heals status effect
      }
      if (itemRandomizer <= 2) {
        gainedItem = "Ethereal Tongue"; // Delays Overseer
      }
      if (itemRandomizer == 0) {
        gainedItem = "Crystalline Flower"; // Used to win
      }
      won = true;
      System.out.println(img.imageFree());
      System.out.println("While catching your breath, you also happened to find 1 '" + gainedItem
              + "' lod" + "ged within the rubbel around you. Perhaps it'll be of use to you.");

    }
    // ------------------------------------------------

    if (encounterType.equals("Overseer/Hangman")) { // Guessing a word
      System.out.println(img.Overseer());
      boolean loseItem = false;
      boolean slumber = false;
      while (inProgress == true) {
        ArrayList<String> theWord = new ArrayList<String>();
        ArrayList<String> censor = new ArrayList<String>();
        for (int i = 0; i < trueWord.length(); i++) {
          theWord.add(trueWord.substring(i, i + 1));
          censor.add("_ ");
          System.out.print(censor.get(i));
        }

        String wordCopy = trueWord;
        int chances = subRandomizer;
        System.out.println("Sleep Phrase Progress: \n"); // Introduces Word
        if (item.equals("Odd Paper")) {
          loseItem = true;
          System.out.println("You glance back at the odd paper, which reads '" + trueWord + "'...");
        }
        if (item.equals("Ethereal Tongue")) {
          loseItem = true;
          slumber = true;
        }
        for (int i = 0; i < trueWord.length(); i++) {
          System.out.print(censor.get(i));
        }
        int ch = subRandomizer;
        while (ch != 0) {
          // for(int i = 0; i < trueWord.length(); i++){
          System.out.println("");
          makeGuess(test.next());
          if (guess.toUpperCase().equals("FLEE")) {
            if (canFlee == true) {
              System.out.println(flee.Flee());
              if (flee.getActionSu() == true) {
                won = true;
                inProgress = false;
                ch = 0;
              }
              else {
                System.out.println(" An eye of the Overseer opens and fixates itself upon you... "
                        + (chances - 1) + " eyes remain closed...");
                chances--;
                if (chances <= 0) {
                  System.out.println(img.OverseerLose());
                  System.out.println(
                          "All eyes of the Overseer have now been opened. You fall to your knees; Knowing"
                                  + " that any attempt to continue onwards is futile. As the eyes of the Overseer r"
                                  + "emain planted upon you, you feel your own eyes grow heavy. Your vision grows d"
                                  + "arker... Your sight is filled with constellations and stars... You fall asleep"
                                  + "... ");
                  won = false;
                  ch = 0;
                  inProgress = false;
                }
              }
            }
          }
          else if (trueWord.indexOf(guess) != -1) {
            if (trueWord.equals(guess)) {
              System.out.println(img.OverseerClosed());
              System.out.println(
                      "The eyes of the overseer revert back to their dormant state. The decaying guar"
                              + "dian sleeps once more and you're able to pass by...");
              won = true;
              inProgress = false;
              if (loseItem == true) {
                flee.toggleUsed();
                flee.setItemInUse();
                System.out.println(
                        "That which you had gained was used. The newfound sparkle of your eyes dissipates and your eyes lose their attunement with the chaos around you...");
              }
              ch = 0;
            }
            else {
              System.out.println("The mound shudders, but no eyes open...");
              String chara;
              for (int r = 0; r < trueWord.length(); r++) {
                if (theWord.get(r).equals(guess)) {
                  censor.set(r, ("" + guess + " "));
                }
              }
              for (int l = 0; l < trueWord.length(); l++) {
                System.out.print(censor.get(l));
              }
            }
          }
          else {
            System.out.println(" An eye of the Overseer opens and fixates itself upon you... "
                    + (chances - 1) + " eyes remain closed...");
            chances--;
            if (chances == 2) {
              System.out.println("You entertain the idea of trying to 'flee'...");
            }
            else if (chances == 1) {
              System.out.println(
                      "As you struggle to open your mouth, you frantically try to decide whether you should take one last guess or risk 'flee'ing...");
            }
            if (chances <= 0) {
              if (slumber == true) {
                slumber = false;
                chances = subRandomizer;
                System.out.println(
                        "That which you had gained was used. Your jaw opens and from it comes an uncontrollable onslaught of noise. By the time you regain control of your mouth, you feel 'Disheartened' and all but 1 of the "
                                + chances + " eyes of the Overseer have closed...");
                flee.setStatus("Disheartened");
                chances--;
              }
              else {
                System.out.println(img.OverseerLose());
                System.out.println(
                        "All eyes of the Overseer have now been opened. You fall to your knees; Knowing"
                                + " that any attempt to continue onwards is futile. As the eyes of the Overseer r"
                                + "emain planted upon you, you feel your own eyes grow heavy. Your vision grows d"
                                + "arker... Your sight is filled with constellations and stars... You fall asleep"
                                + "... ");
                won = false;
                ch = 0;
                inProgress = false;
              }
            }
          }
        }
      }
      inProgress = false;
    }

    // --------------------------------------------------------------------------------------

    if (encounterType.equals("Swarm/Pictionary")) {
      Encounter swarmEnc = new EncounterSwarm();
      swarmEnc.runEncounter(stam, item, flee, playerStatus, difficulty,
              seerCount, swarmCount, cageCount);
      won = swarmEnc.getWon();
    }

    // --------------------------------------------------------------------------------------
    if (encounterType.equals("Cage/Pacman")) {
      Encounter beastEnc = new EncounterCage();
      beastEnc.runEncounter(stam, item, flee, playerStatus, difficulty,
              seerCount, swarmCount, cageCount);
      won = beastEnc.getWon();
    }
  }
}
