/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ce326.hw1;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Trie {
    TrieNode root = new TrieNode(' ');
    List<String> allWords = new ArrayList<>();

    int insert(String word){
        char[] chars = word.toCharArray();
        int i = 0;
        TrieNode temp = root;

        if(find(word) == 1){ //The word exists in the Trie, return -1
            return -1;
        }
        else{
            //The word doesn't exists in the Trie, so we insert the word in Trie
            allWords.add(word);
            for(char c: chars){
                if(temp.getNode(c) == null){
                    if(temp.numChildren >= 1){
                        i = 0;
                        for(TrieNode ch: temp.children){
                            if(c < ch.NodeCharacter){
                                temp.children.add(i, new TrieNode(c));
                                break;
                            }
                            else if(c > ch.NodeCharacter){
                                if(i == temp.numChildren-1){
                                    temp.children.add(new TrieNode(c));
                                    break;
                                }              
                            }
                            i++;
                        }
                    }
                    else{
                        temp.children.add(new TrieNode(c));	
                    }
                    temp.numChildren++;		         			      
                }
                temp = temp.getNode(c);
                i = 0;
            }
            temp.isTheEndOfWord = true;
        }

        return 1;
    }

    int remove(String word){
        char[] chars = word.toCharArray();
        char r=' ';
        TrieNode temp = root;
        int i=0, l=0, twoChildrenNodes=0, endWordNodes = 0, j=0, goIn=0; // twoChildrenNodes count the nodes with numChildren > 1, from the root till the last node
                                                                         // endWordNodes count how many nodes is the finanode of a word

        if(find(word) == -1){ //The word doesn't exists in the Trie, return -1
            return -1;
        }
        else{
            allWords.remove(word);
            //The word exists in the Trie, now we have to remove it
            for(char c: chars){
                i++;
                if(goIn == 1){
                    r = c;
                    goIn = 0;
                }
                if(temp.getNode(c) == null){
                    return -1;
                }
                temp = temp.getNode(c);
                if(temp.numChildren > 1 && i != word.length()){
                    twoChildrenNodes++;
                    goIn=1;
                }
                if(temp.isTheEndOfWord == true && i != word.length()){
                    endWordNodes++;
                }
            }
            
            temp.isTheEndOfWord = false;
            // if the last node have children, then return 
            if(temp.numChildren >= 1){
                return 1;
            }
            
            else{
                // else if the last node have not children
                if(twoChildrenNodes >= 1){
                    temp = root;
                    for(char c: chars){
                        if(temp.getNode(c) == null){
                            return -1;
                        }
                        temp = temp.getNode(c);
                        
                        if(temp.isTheEndOfWord == true){
                            endWordNodes--;
                        }
                        
                        if(temp.numChildren > 1){
                            twoChildrenNodes--;
                        }
                        if(twoChildrenNodes == 0 && endWordNodes == 0){
                            if(temp.numChildren > 1){
                                 temp.numChildren--; 
                            }                          
                            j = 0;
                            for(TrieNode ch: temp.children){
                                if(ch.NodeCharacter == r){
                                    temp.children.remove(j);
                                    break;
                                }
                                j++;
                            }
                            break;
                        }
                    }
                }
            }
        }
        return 1;
    }

    int find(String word){
        char[] chars = word.toCharArray();
        TrieNode temp = root;
        int i = 0;

        for(char c: chars){
            if(temp.getNode(c) == null){
                // The char that i want, doesn't appear in the Trie, so the word isn't in the Trie
                return -1;
            }
            else{
                temp = temp.getNode(c);
            }

            if(word.length()-1 == i){
                if(temp.isTheEndOfWord == false){
                    return -1;
                }
            }
            i++;
        }

        //if the word exists return 1
        return 1;
    }

    void print(){
        //Pre-Order
        List<String> arr = new ArrayList<String>();
        System.out.print("PreOrder: ");
        rec_print(root, "", arr);

        for(String element: arr){
                System.out.print(element+" ");
        }

        System.out.print("\n");
    }

    void rec_print(TrieNode temp, String str, List<String> arr){
        String word;
        int i=0, oneTime=0;

        if(temp.isTheEndOfWord == true){
            word = str + temp.NodeCharacter + "#";
            arr.add(word);
            str = "";
            i = 1;
        }

        for(TrieNode ch: temp.children){

            if(temp.numChildren > 1 && i == 0 && oneTime == 0){
                word = str + temp.NodeCharacter;
                if(!" ".equals(word)){
                    arr.add(word);
                }
                str = "";
                rec_print(ch, str, arr);
                oneTime = 1;
            }
            else{                
                if(i == 0){
                    if(temp.NodeCharacter == ' ' || oneTime == 1){
                        rec_print(ch, str, arr);
                        oneTime = 1;
                    }
                    else{
                        rec_print(ch, str+temp.NodeCharacter, arr);
                        oneTime = 1;
                    }               
                }
                else{
                    i = 0;
                    str = "";
                    rec_print(ch, str, arr);
                    oneTime = 1;
                }
            }            
        }
    }
    

    void rec_word(TrieNode temp, String str, List<String> arr){
        String word;

        if(temp.isTheEndOfWord == true){
            word = str + temp.NodeCharacter;
            arr.add(word.substring(1));
        }

        for(TrieNode ch: temp.children){
            rec_word(ch, str+temp.NodeCharacter, arr);
        }
    }


    void dictionary(){
        Collections.sort(allWords); // sort allWords List by alphabetical order
        System.out.print("\n***** Dictionary *****\n");

        for(String element: allWords){
            System.out.println(element);
        }

        System.out.print("\n");
    }

    boolean diffStrings(String word1, String word2, int x){
        int i;

        for(i = 0; i < word1.length(); i++) { // go from first to last character index the words
            if(word1.charAt(i) != word2.charAt(i)) { // if this character from word 1 does not equal the character from word 2
                x--;
                if(x < 0) { // if the words have more differents character, than allowed 
                    return false; // return false
                }
            }
        }

        if(x > 0){
            return false; //i want x == 0 in the end, e.g.
        }

        return true;        
    }

    void Word(String word ,int x){
        TrieNode temp = root;
        List<String> arr = new ArrayList<String>();     // list with all the strings in Trie
        List<String> sameLength = new ArrayList<String>();  // list with all the strings with the same length with word
        List<String> end = new ArrayList<String>();

        System.out.print("\nDistant words of " + word + " (" + x + "):\n");
        rec_word(temp, "", arr);

        for(String element: arr){
            if(element.length() == word.length()){
                sameLength.add(element);
            }   
        }

        for(String element: sameLength){
            if(diffStrings(word, element, x) == true){
                end.add(element);
            }        
        }

        Collections.sort(end);
        for(String element: end){
            System.out.println(element);
        }
        System.out.print("\n");

    }

    void suffix(String word){
        int j = word.length()-1, same = 0;
        List<String> suffixArray = new ArrayList<String>();

        for(String element: allWords){
            for(int i = element.length()-1; j >= 0; i--){
                if(i < 0){
                    break;
                }
                if(element.charAt(i) == word.charAt(j)){
                    same++;
                }
                j--;
            }
            if(same == word.length()){
                suffixArray.add(element);
            }
            j = word.length()-1;
            same = 0;
        }
        System.out.print("\nWords with suffix " + word +":\n");
        Collections.sort(suffixArray);

        for(String element: suffixArray){
            System.out.println(element);
        }
        System.out.print("\n");
    }
}
