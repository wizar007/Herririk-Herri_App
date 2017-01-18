package herririk_herri.tta.intel.ehu.eus.herririk_herri.model;

/**
 * Created by mikel on 18/01/17.
 */
public class User {
    private int ark2Unblocked;

    private int ark3Unblocked;

    private String lastName;
    private String login;

    private String name;


    private String password;

    private int testVis;

    public User()
    {
    }

    public User(String login, String password, String name, String lastName, int ark2Unblocked,int ark3Unblocked, int testVis)
    {
        this.setLogin(login);
        this.setPassword(password);
        this.setName(name);
        this.setLastName(lastName);
        this.setArk2Unblocked(ark2Unblocked);
        this.setArk3Unblocked(ark3Unblocked);
        this.setTestVis(testVis);
    }

    public int getArk2Unblocked() {
        return this.ark2Unblocked;
    }

    public void setArk2Unblocked(int ark2Unblocked) {
        this.ark2Unblocked = ark2Unblocked;
    }

    public int getArk3Unblocked() {
        return this.ark3Unblocked;
    }

    public void setArk3Unblocked(int ark3Unblocked) {
        this.ark3Unblocked = ark3Unblocked;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getTestVis() {
        return this.testVis;
    }

    public void setTestVis(int testVis) {
        this.testVis = testVis;
    }
}
