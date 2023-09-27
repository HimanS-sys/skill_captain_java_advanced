import java.util.HashMap;

// Creates a hashmap containing user information for each thread.
class UserLoginSession {
    private int userID;
    private String username;
    private String email;
    private boolean isAuthenticated;
    public UserLoginSession(int userID, String username, String email) {
        this.userID = userID;
        this.username = username;
        this.email = email;
        this.isAuthenticated = false;
    }

    public int getUserID() {
        return userID;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public boolean getIsAuthenticated() {
        return isAuthenticated;
    }

    public void authenticate() {
        this.isAuthenticated = true;
        System.out.println(this.username + " authenticated");
    }

    //    @Override
//    public void run() {
//        this.threadLocal.withInitial(() -> {
//            HashMap<String, String> loginInfoMap = new HashMap();
//            loginInfoMap.put("name", name);
//            loginInfoMap.put("userID", userID);
//            loginInfoMap.put("password", password);
//            return loginInfoMap;
//    });
}

public class Main {
    public static ThreadLocal<HashMap<String, UserLoginSession>> threadLocalSession = ThreadLocal.withInitial(() -> new HashMap<>());
    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            UserLoginSession user = new UserLoginSession(1000, "himanshu", "himanshuk@gmail.com");
            user.authenticate();
            threadLocalSession.get().put("Thread1", user);
            displaySessionInfo("Thread1");

        });

        Thread thread2 = new Thread(() -> {
            UserLoginSession user = new UserLoginSession(1002, "shivani", "shivanixyz@gmail.com");
            user.authenticate();
            threadLocalSession.get().put("Thread2", user);
            displaySessionInfo("Thread2");

        });

        Thread thread3 = new Thread(() -> {
            UserLoginSession user = new UserLoginSession(1003, "siddesh", "sid0919@gmail.com");
            user.authenticate();
            threadLocalSession.get().put("Thread3", user);
            displaySessionInfo("Thread3");

        });

        thread1.start();
        thread2.start();
        thread3.start();

        try{
            thread1.join();
            thread2.join();
            thread3.join();
        } catch(InterruptedException e) {
            System.err.println("Thread execution interrupted: " + e.getMessage());
        }
        System.out.println("all threads finished execution");
    }

    public static void displaySessionInfo(String thread) {
        UserLoginSession user = threadLocalSession.get().get(thread);
        System.out.println("--------------------------------\n" + thread + "\nuser id: " + user.getUserID() + "\nusername: "
                + user.getUsername() + "\nemail: " + user.getEmail() + "\nAuthenticated: " + user.getIsAuthenticated()
                + "\n--------------------------------");
    }
}