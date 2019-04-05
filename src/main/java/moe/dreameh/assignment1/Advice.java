package moe.dreameh.assignment1;

public class Advice {
    private final String author;
    private final String category;
    private final String content;


    public Advice(String author, String category, String content) {
        this.author = author;
        this.category = category;
        this.content = content;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getCategory() {
        return this.category;
    }

    public String getContent() {
        return this.content;
    }

}
