package breakout;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * HighScoreManager loads and saves the file that keeps track of high scores.
 * The high score file is located at HIGH_SCORE_FILE_PATH. The top MAX_NUM_HIGH_SCORES_TO_SAVE
 * scores are saved into the file in descending order. 
 */
public class HighScoreManager {

  public static final String HIGH_SCORE_FILE_PATH = "highscore.txt";
  public static final int MAX_NUM_HIGH_SCORES_TO_SAVE = 10;
  private ArrayList<Integer> highScoreList = new ArrayList<>();

  /**
   * Add the input to the high score file.
   * Load the high score file if possible. Add the score to the list of high scores. Save
   * the top MAX_NUM_HIGH_SCORES_TO_SAVE to the file saved at HIGH_SCORE_FILE_PATH.
   *
   * @param score int to add to the high score file.
   */
  public void saveNewHighScores(int score) {
    tryLoadHighScores();
    highScoreList.add(score);
    trySaveTopHighScores();
  }

  /**
   * Try to load the high scores from HIGH_SCORE_FILE_PATH. If loadHighScores() throws
   * exception, print message to user.
   */
  private void tryLoadHighScores() {
    try {
      loadHighScores();
    }
    catch (IOException e) {
      System.out.println("Unable to find existing high score file at path: " +
          HIGH_SCORE_FILE_PATH + ". Will create new high score file.");
    }
  }

  /**
   * Load the high score file and load the integer values into the highScoreList.
   *
   * @throws IOException Throws if file reading issue.
   */
  private void loadHighScores() throws IOException {
    highScoreList.clear();

    // InputStream highScoreFileInputStream = getClassLoader().getResourceAsStream(
    //     HIGH_SCORE_FILE_PATH);
    BufferedReader bufferedReader = new BufferedReader(new FileReader(HIGH_SCORE_FILE_PATH));
    Scanner highScoreFileScanner = new Scanner(bufferedReader);

    while (highScoreFileScanner.hasNext()) {
      highScoreList.add(highScoreFileScanner.nextInt());
    }

    bufferedReader.close();
    highScoreFileScanner.close();
  }

  /**
   * Try to save top high scores. If saveTopHighScores throws exception, print message to user.
   */
  private void trySaveTopHighScores() {
    try {
      saveTopHighScores();
    }
    catch (IOException e) {
      System.out.println("Unable to save high score file.");
    }
  }

  /**
   * Save the top high scores into the file path given by HIGH_SCORE_FILE_PATH. Will save at most
   * MAX_NUM_HIGH_SCORES_TO_SAVE number of high score values into the file.
   *
   * @throws IOException Throws if file writing issue.
   */
  private void saveTopHighScores() throws IOException {
    Collections.sort(highScoreList, Collections.reverseOrder());

    int num_scores_to_save = Math.min(MAX_NUM_HIGH_SCORES_TO_SAVE, highScoreList.size());

    FileWriter fWriter = new FileWriter(HIGH_SCORE_FILE_PATH);
    for (int i = 0; i < num_scores_to_save; i++) {
      if (highScoreList.isEmpty()) {
        break;
      }
      fWriter.write(String.valueOf(highScoreList.get(i)) + "\n");
    }
    fWriter.close();
  }

  /**
   * Get ClassLoader for this class.
   *
   * @return ClassLoader for HighScoreManager.
   */
  public ClassLoader getClassLoader() {
    return HighScoreManager.class.getClassLoader();
  }
}
