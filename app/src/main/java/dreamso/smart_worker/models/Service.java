package dreamso.smart_worker.models;

/**
 * Created by admin on 5/21/2018.
 */

public class Service {

    private String owner;
    private String category;
    private String jobtitle;
    private String description;
    private String mobile;
    private String address;
    private String lattitude;
    private String longtitude;

    public Service() {
    }



    public Service(String owner, String category, String jobtitle, String description, String mobile, String address, String lattitude, String longtitude) {
        this.owner = owner;
        this.category = category;
        this.jobtitle = jobtitle;
        this.description = description;
        this.mobile = mobile;
        this.address = address;
        this.lattitude = lattitude;
        this.longtitude = longtitude;
    }


    public String getOwner() {
        return owner;
    }

    public String getCategory() {
        return category;
    }

    public String getJobtitle() {
        return jobtitle;
    }

    public String getDescription() {
        return description;
    }

    public String getMobile() {
        return mobile;
    }

    public String getAddress() {
        return address;
    }

    public String getLattitude() {
        return lattitude;
    }

    public String getLongtitude() {
        return longtitude;
    }
}
