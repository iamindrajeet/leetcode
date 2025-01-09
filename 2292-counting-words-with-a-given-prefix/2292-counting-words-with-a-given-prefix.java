class Solution {
    public int prefixCount(String[] words, String pref) {
        int count = 0;
        for(String word : words){
            boolean hasPref = hasPrefix(word, pref);
            if(hasPref)
                count++;
        }
        return count;
    }

    private boolean hasPrefix(String word, String pref){
        int i = 0;
        for(i = 0; i < word.length() && i < pref.length(); i++){
            if(word.charAt(i) != pref.charAt(i))
                return false;
        }

        if(i != pref.length())
            return false;
        
        return true;
    }
}