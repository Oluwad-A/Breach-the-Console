public class EncounterCage extends EncounterBase {

  private final StringImages img = new StringImages();

  @Override
  public void runEncounter(int stam, String item, Player flee, String playerStatus, int difficulty,
                           int seerCount, int swarmCount, int cageCount) {

    System.out.println(img.beast());

    if (cageCount == 1) {
      System.out.println(
              "Adrenaline trickles, streams, then gushes through you as you stand before a de"
                      + "mented beast. Before you are mesmermized by its gaping maw, bulging flesh, and"
                      + " putrid figure, you're released from your fear and returned to your senses by "
                      + "its boisterous roar. You are caged with it. You have to get out - NOW. It may "
                      + "be slower than you but one mistake is all it needs... \n(Enter 'up', 'down', 'left', or 'right' to move towards the end)");
    }
    else {
      System.out.println("You are once more trapped within the cage of a beast...");
    }

    boolean loseItem = false;
    String[][] field = new String[(int) (Math.random() * 10 + 10)][(int) (Math.random() * 10
            + 10)];
    int wallCount = field.length;
    int playerVal = 0;
    boolean reachEnd = false;
    boolean caught = false;
    int playerR = (int) (field.length / 2);
    int playerC = 1;
    int beastR = (int) (field.length / 2);
    int beastC = 0;
    int beastVal = -1;
    int endR = (int) (field.length / 2);
    int endC = field[0].length - 1;
    int itemR = -1;
    int itemC = -1;
    boolean gotItem = false;
    boolean spawnItem = false;
    boolean canLeap = false;
    boolean twoBeast = false;

    int diffiBeastR = 0;
    int diffiBeastC = 0;

    if (playerStatus.equals("Slowed")) {

      beastVal = -5;
    }

    for (int r = 0; r < field.length; r++) {
      for (int c = 0; c < field[0].length; c++) {
        field[r][c] = "|||||"; // Makes edges of field into walls ||||| = wall " " = space player
        // = you
      }
    }

    for (int r = 0; r < field.length; r++) {
      for (int c = 0; c < field[0].length; c++) {
        if ((r != 0 && r != (field.length - 1)) && (c != 0 && c != (field[0].length - 1))) {
          field[r][c] = "     "; // Makes all elements inside fields into spaces
        }
      }
    }

    for (int r = 0; r < field.length; r++) {
      for (int c = 0; c < field[0].length; c++) {
        if (field[r][c] == "     ") {
          if ((int) (Math.random() * 10) > 7) {
            if (c != field[0].length - 2) {
              if (((int) (Math.random() * 10) == 9) && spawnItem == false) {
                field[r][c] = "*✧.:✧"; // Item
                itemR = r;
                itemC = c;
                spawnItem = true;
              }
              else {
                field[r][c] = "|||||";
              }

            }
          } // Makes random walls in field
        }
      }
    }

    field[playerR][playerC] = "(._.)";
    field[beastR][beastC] = "(ÒʍÓ)";
    field[endR][endC] = " end ";
    field[playerR - 1][playerC] = "     "; // Prevents being trapped
    field[playerR - 1][playerC + 1] = "     ";
    field[playerR][playerC + 1] = "     ";
    field[playerR + 1][playerC + 1] = "     ";
    field[playerR + 1][playerC] = "     ";
    if (difficulty == 2) {
      boolean set = false;
      int num;
      for (int r = 0; r < field.length; r++) {
        for (int c = 0; c < field[0].length; c++) {
          num = (int) (Math.random() * 10);
          if (num == 9 && set == false && field[r][c].equals("|||||") && r != 0 && c != 0) {
            set = true;
            diffiBeastR = r;
            diffiBeastC = c;
          }
        }
      }
      if (diffiBeastR != 0 && diffiBeastC != 0) {
        field[diffiBeastR][diffiBeastC] = "(ÒʍÓ)";
        System.out.println("This time you feel the presence of two beasts...");
        twoBeast = true;
      }

    }

    if (item.equals("Living Mound")) {
      field[beastR][beastC] = "(>ʍ<)";
      field[diffiBeastR][diffiBeastC] = "(>ʍ<)";
      loseItem = true;
      beastVal -= 5;
      System.out.println(
              "To your surprise, the beast lunges at you with its fangs outrestched well " +
                      "beyond the capacity of its mouth. However, before it can penetrate " +
                      "through your flesh a clumb of meat fills the mouth of the beast, " +
                      "keeping it preoccupied. This is your opportunity to move towards the " +
                      "exit...");
    }
    if (item.equals("Toxic Herb")) {
      field[beastR][beastC] = "(XʍX)";
      field[diffiBeastR][diffiBeastC] = "(XʍX)";
      loseItem = true;
      beastVal = -50000000;
      System.out.println(
              "In the presence of an unbeatable foe, your body collapses. Just as the beast is " +
                      "about to swallow you whole, it coils into itself like a writhing infant " +
                      "and lets out an ardorous howl. All your energy leaves your body and " +
                      "although you're not sure what has happened you know that you must crawl " +
                      "onwards while the beast is incapacitated...");
    }

    while (reachEnd == false && caught == false) {

      for (int r = 0; r < field.length; r++) {
        for (int c = 0; c < field[0].length; c++) {
          System.out.print(field[r][c] + " ");
        }
        System.out.println("");

      }

      System.out.print("You try to run...");

      makeGuess(test.next()); // Used to move a direction

      beastVal++;

      if (playerStatus.equals("Slowed")) {
        playerVal++;
        int canMove = (int) (Math.random() * 10);
        if (playerVal % 2 == 0 && canMove > 6) {
          System.out.println("Your legs stagger and you are unable to move...");
        }
        else {
          if (guess.equals("right")) { // Move right
            if (!field[playerR][playerC + 1].equals("|||||")
                    && !field[playerR][playerC + 1].equals(null)) {
              field[playerR][playerC + 1] = "(._.)";
              field[playerR][playerC] = "     ";
              System.out.println("You move right...");
              playerC++;
            }
            else {
              System.out.println("There's a wall in the way");
            }
          }
          if (guess.equals("up")) { // Move up
            if (!field[playerR - 1][playerC].equals("|||||")
                    && !field[playerR - 1][playerC].equals(null)) {
              field[playerR - 1][playerC] = "(._.)";
              field[playerR][playerC] = "     ";
              System.out.println("You move up...");
              playerR--;
            }
            else {
              System.out.println("There's a wall in the way");
            }
          }
          if (guess.equals("left")) {
            if (!field[playerR][playerC - 1].equals("|||||")
                    && !field[playerR][playerC - 1].equals(null)) {
              field[playerR][playerC - 1] = "(._.)";
              field[playerR][playerC] = "     ";
              System.out.println("You move left...");
              playerC--;
            }
            else {
              System.out.println("There's a wall in the way");
            }
          }
          if (guess.equals("down")) {
            if (!field[playerR + 1][playerC].equals("|||||")
                    && !field[playerR + 1][playerC].equals(null)) {
              field[playerR + 1][playerC] = "(._.)";
              field[playerR][playerC] = "     ";
              playerR++;
              System.out.println("You move down...");
            }
            else {
              System.out.println("There's a wall in the way");
            }
          }

        }

      }
      else {
        if (guess.equals("right")) { // Move right
          if (!field[playerR][playerC + 1].equals("|||||")
                  && !field[playerR][playerC + 1].equals(null)) {
            field[playerR][playerC + 1] = "(._.)";
            field[playerR][playerC] = "     ";
            System.out.println("You move right...");
            playerC++;
          }
          else {
            System.out.println("There's a wall in the way");
          }
        }
        if (guess.equals("up")) { // Move up
          if (!field[playerR - 1][playerC].equals("|||||")
                  && !field[playerR - 1][playerC].equals(null)) {
            field[playerR - 1][playerC] = "(._.)";
            field[playerR][playerC] = "     ";
            System.out.println("You move up...");
            playerR--;
          }
          else {
            System.out.println("There's a wall in the way");
          }
        }
        if (guess.equals("left")) {
          if (!field[playerR][playerC - 1].equals("|||||")
                  && !field[playerR][playerC - 1].equals(null)) {
            field[playerR][playerC - 1] = "(._.)";
            field[playerR][playerC] = "     ";
            System.out.println("You move left...");
            playerC--;
          }
          else {
            System.out.println("There's a wall in the way");
          }
        }
        if (guess.equals("down")) {
          if (!field[playerR + 1][playerC].equals("|||||")
                  && !field[playerR + 1][playerC].equals(null)) {
            field[playerR + 1][playerC] = "(._.)";
            field[playerR][playerC] = "     ";
            playerR++;
            System.out.println("You move down...");
          }
          else {
            System.out.println("There's a wall in the way");
          }
        }
      }

      if (beastVal > 0) { // move beast
        if ((playerC - 3 > beastC || playerC + 3 < beastC) && canLeap == true) {

          canLeap = false;
          field[beastR][beastC] = "     ";
          beastR = (int) ((Math.random() * (field.length / 2)) + (field.length / 2));
          beastC = (int) (Math.random() * (field[0].length - 1));
          while (field[beastR][beastC].equals("|||||") || field[beastR][beastC].equals(" end ")) {
            beastR = (int) ((Math.random() * (field.length / 2)) + (field.length / 2));
            beastC = (int) (Math.random() * (field[0].length - 1));
          }
          System.out.println("Enraged, the beast leaps to another location...");
          field[beastR][beastC] = "(ÒʍÓ)";
        }
        if (playerC > beastC && !field[beastR][beastC + 1].equals("|||||")) { // Right
          if (field[beastR][beastC + 2].equals("|||||")
                  && field[beastR - 1][beastC + 1].equals("|||||")
                  && (playerR < beastR - 1 || playerR > beastR + 1)
                  && !field[beastR - 1][beastC].equals("|||||")) {
            field[beastR - 1][beastC] = "(ÒʍÓ)";
            field[beastR][beastC] = "     ";
            beastR--;
          }
          else {
            field[beastR][beastC + 1] = "(ÒʍÓ)";
            field[beastR][beastC] = "     ";
            beastC++;
          }
          canLeap = true;
        }
        else if (playerR > beastR && !field[beastR + 1][beastC].equals("|||||")) { // Down
          field[beastR + 1][beastC] = "(ÒʍÓ)";
          field[beastR][beastC] = "     ";
          beastR++;
          canLeap = true;
        }
        else if ((playerR < beastR && !field[beastR - 1][beastC].equals("|||||"))) { // Up
          field[beastR - 1][beastC] = "(ÒʍÓ)";
          field[beastR][beastC] = "     ";
          beastR--;
          canLeap = true;
        }
        else if (playerC < beastC && !field[beastR][beastC - 1].equals("|||||")) { // Left
          field[beastR][beastC - 1] = "(ÒʍÓ)";
          field[beastR][beastC] = "     ";
          beastC--;
          canLeap = true;
        }
        else if (playerC > beastC && field[beastR][beastC + 1].equals("|||||")
                && !field[beastR - 1][beastC].equals("|||||")) {
          field[beastR - 1][beastC] = "(ÒʍÓ)";
          field[beastR][beastC] = "     ";
          beastR--;
          canLeap = true;

        }
      }

      if (beastVal > 0 && twoBeast == true) { // move beast
        if ((playerC - 3 > diffiBeastC || playerC + 3 < diffiBeastC) && canLeap == true) {

          canLeap = false;
          field[diffiBeastR][diffiBeastC] = "     ";
          diffiBeastR = (int) ((Math.random() * (field.length / 2)) + (field.length / 2));
          diffiBeastC = (int) (Math.random() * (field[0].length - 1));
          while (field[diffiBeastR][diffiBeastC].equals("|||||")
                  || field[diffiBeastR][diffiBeastC].equals(" end ")) {
            diffiBeastR = (int) ((Math.random() * (field.length / 2)) + (field.length / 2));
            diffiBeastC = (int) (Math.random() * (field[0].length - 1));
          }
          System.out.println("Enraged, the beast leaps to another location...");
          field[diffiBeastR][diffiBeastC] = "(ÒʍÓ)";
        }
        if (playerC > diffiBeastC && !field[diffiBeastR][diffiBeastC + 1].equals("|||||")) { // Right
          if (field[beastR][diffiBeastC + 2].equals("|||||")
                  && field[diffiBeastR - 1][diffiBeastC + 1].equals("|||||")
                  && (playerR < diffiBeastR - 1 || playerR > diffiBeastR + 1)
                  && !field[diffiBeastR - 1][diffiBeastC].equals("|||||")) {
            field[diffiBeastR - 1][diffiBeastC] = "(ÒʍÓ)";
            field[diffiBeastR][diffiBeastC] = "     ";
            diffiBeastR--;
          }
          else {
            field[diffiBeastR][diffiBeastC + 1] = "(ÒʍÓ)";
            field[diffiBeastR][diffiBeastC] = "     ";
            diffiBeastC++;
          }
          canLeap = true;
        }
        else if (playerR > diffiBeastR && !field[diffiBeastR + 1][diffiBeastC].equals("|||||")) { // Down
          field[diffiBeastR + 1][diffiBeastC] = "(ÒʍÓ)";
          field[diffiBeastR][diffiBeastC] = "     ";
          diffiBeastR++;
          canLeap = true;
        }
        else if ((playerR < diffiBeastR
                && !field[diffiBeastR - 1][diffiBeastC].equals("|||||"))) { // Up
          field[diffiBeastR - 1][diffiBeastC] = "(ÒʍÓ)";
          field[diffiBeastR][diffiBeastC] = "     ";
          diffiBeastR--;
          canLeap = true;
        }
        else if (playerC < diffiBeastC && !field[diffiBeastR][diffiBeastC - 1].equals("|||||")) { // Left
          field[diffiBeastR][diffiBeastC - 1] = "(ÒʍÓ)";
          field[diffiBeastR][diffiBeastC] = "     ";
          diffiBeastC--;
          canLeap = true;
        }
        else if (playerC > beastC && field[diffiBeastR][diffiBeastC + 1].equals("|||||")
                && !field[diffiBeastR - 1][diffiBeastC].equals("|||||")) {
          field[diffiBeastR - 1][diffiBeastC] = "(ÒʍÓ)";
          field[diffiBeastR][diffiBeastC] = "     ";
          diffiBeastR--;
          canLeap = true;

        }
      }

      // If player runs into sparkles on the grid (item), they gain a random item.
      if (playerR == itemR && playerC == itemC && gotItem == false) {
        int itemRandomizer = (int) (Math.random() * 10);
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
        gotItem = true;
        flee.itemsAdd(gainedItem);
        System.out.println("While trying to escape, you find 1 '" + gainedItem + "'...");
      }
      if (field[playerR][playerC].equals(field[beastR][beastC])) {
        caught = true;
      }
      if (playerR == endR && playerC == endC) {
        reachEnd = true;
      }

    }
    if (caught == true) {
      won = false;
      for (int r = 0; r < field.length; r++) {
        for (int c = 0; c < field[0].length; c++) {
          if (r == playerR && c == playerC) {
            field[r][c] = "(ÒʍÓ)";
          }
          System.out.print(field[r][c] + " ");
        }
        System.out.println("");

      } // Prints games result
      System.out.println(img.beastLose());
      System.out.println("The beast catches up to you...");
    }
    if (reachEnd == true) {
      for (int r = 0; r < field.length; r++) {
        for (int c = 0; c < field[0].length; c++) {
          System.out.print(field[r][c] + " ");
        }
        System.out.println("");
      }
      System.out.println(img.beastWin());
      System.out
              .println("Although out of breath, you manage to escape the domain of the beast...");
      if (loseItem == true) {
        if (item.equals("Living Mound")) {
          flee.toggleUsed();
          flee.setItemInUse();
          System.out.println(
                  "That which you had gained was used. The excess flesh that weighed you " +
                          "down sacrificed itself for your prosperity... Do not let its " +
                          "efforts be in vain...");
        }
        else {
          flee.setStatus("");
          flee.toggleUsed();
          flee.setItemInUse();
          System.out.println(
                  "That which you had gained was used. The toxic residue that sapped at your " +
                          "stamina was expelled from your body through the sweat of " +
                          "your panic...");
        }
      }
      won = true;
    }
  }

}

