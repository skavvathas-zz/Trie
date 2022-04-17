/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ce326.hw1;
import java.util.Scanner;

public class HW1 {
    public static void main(String []args){
        Scanner sc = new Scanner(System.in);
        String word, wordLower;
        int ret;
	String x;
        Trie trie = new Trie();

        while(sc.hasNext()){
            System.out.println("?: ");
            word = sc.next();

            switch (word) {
                case "-i":
                    word = sc.next();
                    // insert word in Trie
                    wordLower = word.toLowerCase();
                    ret = trie.insert(wordLower);

                    //Success
                    if(ret == 1){
                        System.out.println("ADD " + wordLower + " OK");
                    }
                    else{ // return -1, 
                        System.out.println("ADD " + wordLower + " NOK");
                    }                    
                    
                    break;
                
                case "-r":
                    word = sc.next();

                    // remove word from Trie
                    wordLower = word.toLowerCase();
                    ret = trie.remove(wordLower);

                    //Success
                    if(ret == 1){
                        System.out.println("RMV " + wordLower + " OK");
                    }
                    else{
                        System.out.println("RMV " + wordLower + " NOK");
                    }
                    
                    break;

                case "-f":
                    word = sc.next();

                    // search word in Trie
                    wordLower = word.toLowerCase();
                    ret = trie.find(wordLower);

                    //Search
                    if(ret == 1){
                        System.out.println("FND " + wordLower + " OK");
                    }
                    else{
                        System.out.println("FND " + wordLower + " NOK");
                    }
                    
                    break;

                case "-p":
                    trie.print();
                
                    break;
                
                case "-d":
                    trie.dictionary();

                    break;
            
                case "-w":
                    word = sc.next();
                    x = sc.next();
                    wordLower = word.toLowerCase();
                    trie.Word(wordLower, Integer.parseInt(x));
                    break;
                
                case "-s":
                    word = sc.next();
                    wordLower = word.toLowerCase();
                    trie.suffix(wordLower);
                    break;
                
                case "-q":
                    System.out.println("Bye bye!");
                    System.exit(0);
                    break;
                    
                default:
                    break;
            }
        }
    }
}
