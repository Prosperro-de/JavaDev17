package org.example.treedemo;

public class Demo {

    public static void main(String[] args) {
        CustomBinaryTree<Integer> intTree = new CustomBinaryTree<>(5, 7, 3, 4);
//        intTree.insert(5);
//        intTree.insert(7);
//        intTree.insert(3);
//        intTree.insert(4);
//
        System.out.println(intTree.contains(4));
        System.out.println(intTree.contains(10));
        System.out.println(intTree.contains(7));
    }
}
