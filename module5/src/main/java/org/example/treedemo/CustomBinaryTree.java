package org.example.treedemo;

public class CustomBinaryTree<T extends Comparable<? super T>> {
    private Node<T> root;

    public CustomBinaryTree() {
    }

    public CustomBinaryTree(T... elements) {
        for (T element : elements) {
            insert(element);
        }
    }

    public boolean contains(T element) {
        return contains(root, element);
    }

    private boolean contains(Node<T> root, T element) {
        if (root == null) {
            return false;
        }
        if (element.compareTo(root.element) < 0) {
            return contains(root.left, element);
        } else if (element.compareTo(root.element) > 0) {
            return contains(root.right, element);
        } else {
            return true;
        }
    }

    public void insert(T element) {
        if (root == null) {
            Node<T> newNode = new Node<>(element);
            root = newNode;
            return;
        }
        insert(root, element);
    }

    private void insert(Node<T> root, T element) {
        Node<T> newNode = new Node<>(element);
        if (element.compareTo(root.element) < 0) { //go to left
            if (root.left == null) {
                root.left = newNode;
            } else {
                insert(root.left, element);
            }
        } else if (element.compareTo(root.element) > 0) { // go to right
            if (root.right == null) {
                root.right = newNode;
            } else {
                insert(root.right, element);
            }
        }
    }


    static class Node<T extends Comparable<? super T>> {
        T element;
        Node<T> left;
        Node<T> right;

        public Node(T element) {
            this.element = element;
        }
    }
}
