package dreamso.smart_worker.models;

/**
 * Created by admin on 5/20/2018.
 */

public class User {

    private String name;
    private String email;
    private String phoneNumber;
    private String nicid;
    private String password;

    public User(String name, String email, String phoneNumber, String nicid, String password) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.nicid = nicid;
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setNicid(String nicid) {
        this.nicid = nicid;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getNicid() {
        return nicid;
    }

    public String getPassword() {
        return password;
    }


}
