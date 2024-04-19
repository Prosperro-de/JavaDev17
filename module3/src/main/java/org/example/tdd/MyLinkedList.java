package org.example.tdd;

public class MyLinkedList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;


    public void add(T element) {
        if (head == null) {
            head = tail = new Node<>(element);
            size++;
            return;
        }
        Node<T> newNode = new Node<>(element);
        tail.next = newNode;
        tail = newNode;
        size++;
    }

    public int size() {
        return size;
    }

    public T get(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            return head.element;
        } else if (index == size - 1) {
            return tail.element;
        }
        Node<T> current = head;
        int count = 0;
        while (count != index) {
            current = current.next;
            count++;
        }
        return current.element;
    }


    static class Node<T> {
        T element;
        Node<T> next;

        public Node(T element) {
            this.element = element;
        }
    }
}
