package application;

public class DiscordToken {

    private String user = null;
    private String token = null;
    private String discorduserid = null;
    private String time = null;
    private String verified = null;

    public DiscordToken() {
    }

    public DiscordToken(String user, String token, String discorduserid, String time, String verified) {
        this.user = user;
        this.token = token;
        this.discorduserid = discorduserid;
        this.time = time;
        this.verified = verified;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
    
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    
    public String getDiscorduserid() {
        return discorduserid;
    }

    public void setDiscorduserid(String discorduserid) {
        this.discorduserid = discorduserid;
    }
    
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
    
    public String getVerified() {
        return verified;
    }

    public void setVerified(String verified) {
        this.verified = verified;
    }
    
}