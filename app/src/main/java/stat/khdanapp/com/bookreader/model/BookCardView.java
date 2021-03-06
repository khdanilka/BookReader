package stat.khdanapp.com.bookreader.model;


public class BookCardView {

    private int imageId;
    private String bookTitle;
    private String author;
    private int favoriteId;

    public int getFavoriteId() {
        return favoriteId;
    }

    public void setFavoriteId(int favoriteId) {
        this.favoriteId = favoriteId;
    }

    public BookCardView(int imageId, String bookTitle, String author, int favoriteId) {
        this.imageId = imageId;
        this.bookTitle = bookTitle;
        this.author = author;
        this.favoriteId = favoriteId;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
