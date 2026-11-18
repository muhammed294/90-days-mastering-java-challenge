package edu.haramaya.cosc3053.day11;

public class Box<T> {
    private T content;

    // TODO 11.1: Implement set(T content) and get() methods
    public void set(T content) { this.content = content; }
    public T get() { return content; }
    public boolean isEmpty() { return content == null; }

    public static void main(String[] args) {
        Box<String> strBox = new Box<>();
        strBox.set("Haramaya University");
        System.out.println("Box Value: " + strBox.get());
    }
}