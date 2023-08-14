package online.syncio.model;

/**
 * A utility class that manages the logged-in user and provides methods for authentication and role checks.
 */
public class LoggedInUser {
    private static User currentUser;

    /**
     * Gets the currently logged-in user.
     *
     * @return the currently logged-in user, or null if no user is logged in
     */
    public static User getCurrentUser() {
        return currentUser;
    }

    /**
     * Sets the currently logged-in user.
     *
     * @param user the user to set as the currently logged-in user
     */
    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    /**
     * Checks if a user is currently logged in.
     *
     * @return true if a user is logged in, false otherwise
     */
    public static boolean isLoggedIn() {
        return currentUser != null;
    }

    /**
     * Checks if the currently logged-in user has the role of a regular user.
     *
     * @return true if the current user is a regular user, false otherwise
     */
    public static boolean isUser() {
        return isLoggedIn() && currentUser.getRole() == 0;
    }
    
    /**
     * Checks if the currently logged-in user has the role of an administrator.
     *
     * @return true if the current user is an administrator, false otherwise
     */
    public static boolean isAdmin() {
        return isLoggedIn() && currentUser.getRole() == 1;
    }
    
    /**
     * Logs out the currently logged-in user by setting it to null.
     */
    public static void logOut() {
        currentUser = null;
    }
}
