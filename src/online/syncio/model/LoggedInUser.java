package online.syncio.model;

public class LoggedInUser {
    private static User currentUser;

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    public static boolean isLoggedIn() {
        return currentUser != null;
    }

    public static boolean isUser() {
        return isLoggedIn() && currentUser.getRole() == 0;
    }
    
    public static boolean isAdmin() {
        return isLoggedIn() && currentUser.getRole() == 1;
    }
    
    public static void logOut() {
        currentUser = null;
    }
}
