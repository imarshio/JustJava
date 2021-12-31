package leetcode.hard;

import java.util.List;

class SolutionPuzzles {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Auto-generated method stub
		SolutionPuzzles puzzle = new SolutionPuzzles();
		String[] words = { "aaaa", "asas", "able", "ability", "actt", "actor", "access" };
		String[] puzzles = { "aboveyz", "abrodyz", "abslute", "absoryz", "actresz", "gaswxyz" };
		puzzle.findNumOfValidWords(words, puzzles);
	}

	public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
		for (String puzzle : puzzles) {
			char p0 = puzzle.charAt(0);
			for (String word : words) {
				// 在words中找出可以当word的谜底的，aboveyz，必须要包含a，所包含的所有字母必须在word中
				System.out.println(p0);
				for(int i=0;i<word.length();i++) {
					
				}

			}
		}
		return null;

	}

}
