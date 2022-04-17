/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ce326.hw1;

import java.util.LinkedList;

public class TrieNode {
    char NodeCharacter;
    int numChildren;
    boolean isTheEndOfWord = false;
    LinkedList<TrieNode> children = new LinkedList<>();

    TrieNode(char ch){
        this.NodeCharacter = ch;
    }

    TrieNode getNode(char NodeCharacter){
        for(TrieNode node: children){
            if(node != null){
                if(node.NodeCharacter == NodeCharacter){
                    return node;
                }
            }
        }
        return null;
    }
}
