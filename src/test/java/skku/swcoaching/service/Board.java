package skku.swcoaching.service;

public class Board {
    private long id;
    private char title;
    private String author;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public char getTitle() {
        return title;
    }

    public void setTitle(char title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
