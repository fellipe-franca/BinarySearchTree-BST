package main.java.dataStructure;

import java.util.Scanner;

public class BST {

    public Node root;


    public class Node {
        public int value;
        public Node left;
        public Node right;



        public Node(int value) {
            this.value = value;
        }
    }

    public void insert(int value) {
        if (root == null) {
            root = new Node(value);
        } else {
            insert(root,value);
        }
    }

    private void insert(final Node root, final int value) {
        if (root == null) return;
        if (value == root.value) return;
        if (value > root.value) {
            if (root.right == null) root.right = new Node(value);
            else insert(root.right, value);
        } else {
            if (root.left == null) root.left = new Node(value);
            else insert(root.left, value);
        }
    }

    //Metodo em ondem
    public void inOrder() {
        inOrder(root);
    }
    private void inOrder(final Node node) {
        // E R D
        if (node == null) return;
        inOrder(node.left);
        System.out.println(node.value);
        inOrder(node.right);
    }

    public boolean contains(int value) {
        return contains(root, value);
    }

    private boolean contains(final Node root, final int value) {
        if (root == null) return false;
        if (root.value == value) return true;
        if (value > root.value) return contains(root.right, value);
        else return contains(root.left, value);
    }


    public int minValue(Node currentNode) {
        while (currentNode.left != null) {
            currentNode = currentNode.left;
        }
        return currentNode.value;
    }

    public void deleteNode(int value) {
        root = deleteNode(root, value);
    }

    private Node deleteNode(final Node root, final int value) {
        if (root == null) return null;

        if (value < root.value) {
            root.left = deleteNode(root.left, value);
        } else if (value > root.value) {
            root.right = deleteNode(root.right, value);
        } else {
            if ((root.left == null) && (root.right == null)) {
                return null;
            } else if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            } else {
                int minValue = minValue(root.right);
                root.value = minValue;
                root.right = deleteNode(root.right, minValue);
            }
        }
        return root;

    }

    public static void main(String[] args) {
        BST tree = new BST();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("Escolha uma operação:");
            System.out.println("insert (i) - Inserir um valor");
            System.out.println("contains (c) - Verificar se a árvore contém um valor");
            System.out.println("inOrder (o) - Imprimir valores em ordem");
            System.out.println("delete (d) - Remover um valor");
            System.out.println("sair (s) - Sair do programa");
            String command = scanner.nextLine();

            switch (command) {
                case "i":
                    System.out.print("Digite um valor para inserir: ");
                    int valueToInsert = Integer.parseInt(scanner.nextLine());
                    tree.insert(valueToInsert);
                    System.out.println("Valor inserido: " + valueToInsert);
                    break;

                case "c":
                    System.out.print("Digite um valor para verificar: ");
                    int valueToCheck = Integer.parseInt(scanner.nextLine());
                    boolean contains = tree.contains(valueToCheck);
                    System.out.println("A árvore " + (contains ? "contém" : "não contém") + " o valor: " + valueToCheck);
                    break;

                case "o":
                    System.out.println("Valores em ordem:");
                    tree.inOrder();
                    break;

                case "d":
                    System.out.print("Digite um valor para remover: ");
                    int valueToDelete = Integer.parseInt(scanner.nextLine());
                    tree.deleteNode(valueToDelete);
                    System.out.println("Valor removido: " + valueToDelete);
                    break;

                case "s":
                    running = false;
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Comando inválido. Tente novamente.");
                    break;
            }
        }

        scanner.close();
    }

}
