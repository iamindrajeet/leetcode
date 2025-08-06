class Solution {
    public boolean isIsomorphic(String s, String t) {
        int lenOfS = s.length();
        int lenOfT = t.length();

        // If the lengths of the two strings are not equal, they can't be isomorphic
        if (lenOfS != lenOfT)
            return false;

        // Map to store character mapping from s → t
        Map<Character, Character> map = new HashMap<>();

        // Iterate through each character in the strings
        for (int i = 0; i < s.length(); i++) {
            char charS = s.charAt(i); // character from string s
            char charT = t.charAt(i); // corresponding character from string t

            // If charS is not yet mapped, attempt to map it to charT
            if (!map.containsKey(charS)) {
                // Before mapping, check if charT is already mapped from another character
                if (map.containsValue(charT)) {
                    return false; // Two characters in s cannot map to the same character in t
                }
                map.put(charS, charT); // create the mapping
            } else {
                // If already mapped, check if the mapping is consistent
                char mappedChar = map.get(charS);
                if (mappedChar != charT)
                    return false; // Mismatch in mapping
            }
        }

        // All characters matched consistently
        return true;
    }
}
