package CodeTest;

import java.util.*;

/**
 * @author masuo
 * @date: 2022/05/08/ 上午11:07
 * @description
 */
public class AlpheTest2 {

    //"给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，在字符串中增加空格来构建一个句子，使得句子中所有的单词都在词典中。返回所有这些可能的句子。
    //
    //说明：
    //
    //分隔时可以重复使用字典中的单词。
    //你可以假设字典中没有重复的单词。
    //
    //示例 1：
    //输入:
    //s = ""catsanddog""
    //wordDict = [""cat"", ""cats"", ""and"", ""sand"", ""dog""]
    //输出:
    //[
    //  ""cats and dog"",
    //  ""cat sand dog""
    //]
    //
    //示例 2：
    //输入:
    //s = ""pineapplepenapple""
    //wordDict = [""apple"", ""pen"", ""applepen"", ""pine"", ""pineapple""]
    //输出:
    //[
    //  ""pine apple pen apple"",
    //  ""pineapple pen apple"",
    //  ""pine applepen apple""
    //]
    //解释: 注意你可以重复使用字典中的单词。
    public static void main(String[] args) {
        //输入:
        //s = ""catsanddog""
        //wordDict = [""cat"", ""cats"", ""and"", ""sand"", ""dog""]
        //输出:
        //[
        //  ""cats and dog"",
        //  ""cat sand dog""
        //]

        // 动态规划
        String s = "catsanddog";
        String[] wordDict = {"cat", "cats", "and", "sand", "dog"};

        String[] sentence = buildSentence(s, wordDict);

        Arrays.stream(sentence).forEach(System.out::println);
    }

    private static String[] buildSentence(String s, String[] wordDict) {
        int length = s.length();
        String[] rt = new String[length];
        Set<String> words = new HashSet<>(Arrays.asList(wordDict));
        // 记录单词
        boolean[] word = new boolean[length];
        for (int i = 0; i < length; i++) {
            if (words.contains(s.substring(0, i + 1))) {
                word[i] = true;
                continue;
            }
            // 双指针
            for (int j = 0; j < i; j++) {
                if (word[j] && words.contains(s.substring(j + 1, i + 1))) {
                    word[i] = true;
                    break;
                }
            }
        }
        List<String> wordList = new ArrayList<>();

        // 深度搜索
        if (word[length - 1]) {
            LinkedList<String> rts = new LinkedList<>();
            dfs(s, length - 1, words, word, rts, wordList);
        }

        return rt;
    }

    private static void dfs(String s, int i, Set<String> words, boolean[] word, LinkedList<String> rts, List<String> wordList) {

        if (words.contains(s.substring(0, i + 1))) {
            wordList.add(s.substring(0, i + 1));

            StringBuilder sb = new StringBuilder();
            for (String rt : wordList) {
                sb.append(rt).append(" ");
            }
            sb.deleteCharAt(sb.length() - 1);
            wordList.add(sb.toString());
            rts.removeFirst();
        }

        for (int j = 0; j < i; j++) {
            if(word[j]){
                String pre = s.substring(j + 1,i + 1);

                if (words.contains(pre)){
                    rts.addFirst(pre);
                    dfs(s, i, words, word, rts, wordList);
                    rts.removeFirst();
                }
            }
        }

    }

}
