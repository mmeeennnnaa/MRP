package at.fhtw.mrp.model;

public class Rating {
    private int entryId;
    private String username;
    private int stars; // 1 - 5

    public Rating(int entryId, String username, int stars) {
        this.entryId = entryId;
        this.username = username;
        this.stars = stars;
    }

    public int getEntryId() { return entryId; }
    public String getUsername() { return username; }
    public int getStars() { return stars; }
}
