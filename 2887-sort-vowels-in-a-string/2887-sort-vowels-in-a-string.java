class Solution {
    public String sortVowels(String s) {
        // Step 1: Collect all vowels from the input string
        List<Character> listOfVowels = new ArrayList<>();
        for (char ch : s.toCharArray()) {
            if (isVowel(ch)) {
                listOfVowels.add(ch);
            }
        }

        // Step 2: Sort the collected vowels based on ASCII value
        // (Capital letters will come before small letters since ASCII of 'A' < 'a')
        Collections.sort(listOfVowels);

        // Step 3: Rebuild the final string
        // We'll replace only the vowels in the original string with sorted vowels
        StringBuilder ans = new StringBuilder();
        int j = 0; // pointer to track position in sorted vowels list

        for (char ch : s.toCharArray()) {
            if (isVowel(ch)) {
                // Replace vowel with next sorted vowel
                ans.append(listOfVowels.get(j++));
            } else {
                // Non-vowel characters remain the same
                ans.append(ch);
            }
        }

        // Step 4: Return the string with vowels sorted but consonants in original position
        return ans.toString();
    }

    private boolean isVowel(char ch) {
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u' ||
               ch == 'A' || ch == 'E' || ch == 'I' || ch == 'O' || ch == 'U';
    }
}
