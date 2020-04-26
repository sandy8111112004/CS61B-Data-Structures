public class Palindrome{
    public Deque<Character> wordToDeque(String word){
        LinkedListDeque<Character> chrList = new LinkedListDeque<Character>();
        for (int i =0;i < word.length();i++){
            chrList.addLast(word.charAt(i));
        }
        return chrList;
    }

    public boolean isPalindrome(String word){
        Deque d = wordToDeque(word);
        return isPalindromeHelper(d);
    }

    private boolean isPalindromeHelper(Deque dWord){
        if(dWord.size() <= 1){
            return true;
        }
        boolean temp = dWord.removeFirst().equals(dWord.removeLast());
        return temp && isPalindromeHelper(dWord);
    }

    public boolean isPalindrome(String word, CharacterComparator cc){
        boolean temp = true;
        int length = word.length();
        for(int i=0; i<length/2; i++){
            temp = temp && cc.equalChars(word.charAt(i), word.charAt(length-i-1));
        }
        return temp;
    }


}