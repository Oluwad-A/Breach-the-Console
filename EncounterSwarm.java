public class EncounterSwarm extends EncounterBase {


  @Override
  public void runEncounter(int stam, String item, Player flee, String playerStatus, int difficulty,
                           int seerCount, int swarmCount, int cageCount) {
    // Pictionary. Game doesn't immediately end if the user loses.
    // Instead it gives them a status effect.
    boolean loseItem = false;
    System.out.println(img.Spires());
    int chances = subRandomizer;
    boolean correct = false;
    boolean running = true;
    String answer = "";
    int spireCount = subRandomizer * difficulty;
    newStam = stam;

    while (spireCount > 0) {
      int letterLabel = (int) (Math.random() * 26);
      if (letterLabel == 0) {
        answer = "A";
      } else if (letterLabel == 1) {
        answer = "B";
      } else if (letterLabel == 2) {
        answer = "C";
      } else if (letterLabel == 3) {
        answer = "D";
      } else if (letterLabel == 4) {
        answer = "E";
      } else if (letterLabel == 5) {
        answer = "F";
      } else if (letterLabel == 6) {
        answer = "G";
      } else if (letterLabel == 7) {
        answer = "H";
      } else if (letterLabel == 8) {
        answer = "I";
      } else if (letterLabel == 9) {
        answer = "J";
      } else if (letterLabel == 10) {
        answer = "K";
      } else if (letterLabel == 11) {
        answer = "L";
      } else if (letterLabel == 12) {
        answer = "M";
      } else if (letterLabel == 13) {
        answer = "N";
      } else if (letterLabel == 14) {
        answer = "O";
      } else if (letterLabel == 15) {
        answer = "P";
      } else if (letterLabel == 16) {
        answer = "Q";
      } else if (letterLabel == 17) {
        answer = "R";
      } else if (letterLabel == 18) {
        answer = "S";
      } else if (letterLabel == 19) {
        answer = "T";
      } else if (letterLabel == 20) {
        answer = "U";
      } else if (letterLabel == 21) {
        answer = "V";
      } else if (letterLabel == 22) {
        answer = "W";
      } else if (letterLabel == 23) {
        answer = "X";
      } else if (letterLabel == 24) {
        answer = "Y";
      } else if (letterLabel == 25) {
        answer = "Z";
      }
      System.out.print(img.imageLetter(letterLabel));
      if (item.equals("Broken Lens")) {
        loseItem = true;
        System.out.println(
                "Through one of your eyes, the archaic character appears to be '" + answer + "'...");
      }
      System.out
              .println("After looking at the arrangement of a spire, you inscribe the sigil... ");

      makeGuess(test.next());

      if (guess.toUpperCase().equals(answer)) {
        System.out.println(" ... and the spire crumbles and falls back to the ground leaving "
                + (spireCount - 1) + " sig" + "ils to inscribe... ");
        spireCount--;
        correct = true;
      } else {
        System.out.println(
                "... but it was a mistake. The spires rattle like snakes; Their vibrations crea"
                        + "ting a mocking laughter. You feel yourself growing weaker and your stamina lea"
                        + "ving your body...");
        correct = false;
        newStam--;
        chances--;
      }
    }

    if (newStam > 0) {
      won = true;
    } else {
      newStam = 0;
      won = false;
    }

    if (won == true) {
      System.out.println(img.SpiresWon());
      System.out.println(
              "The spires loosen their grasp on your body and recoil back into the ground. As you watch them crumble, you feel a peculiar tinge of regret for at not being able to play longer. Nevertheless, when the final spire has returned to its origin, you continue onwards.");
    } else {
      statusGained = getRandomStatus();
      System.out.println(img.SpiresLost());
      System.out.println(
              "Your head feels groggy and your body feels much weaker. You fall downwards only to be cushioned by the consoling grasp of the spires that previously teased you. They give you time to rest and recupurate -  but even when you rise to your feet and the spires have long since left, something feels different. You feel as though you've been '"
                      + statusGained + "' but continue onwards.");
    }
    if (loseItem) {
      flee.toggleUsed();
      flee.setItemInUse();
      System.out.println(
              "That which you had gained was used. The lens that allowed you to see the truth of that in front of you is now broken beyond repair...");
    }
  }
}
