package day6;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Item {

    @SerializedName("region_id")
    @Expose
    private Integer region_id;
    @SerializedName("region_name")
    @Expose
    private String region_name;
    @SerializedName("links")
    @Expose
    private List<Link> links = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public Item() {
    }



    /**
     *
     * @param region_id
     * @param region_name
     * @param links
     */
    public Item(Integer region_id, String region_name, List<Link> links) {
        super();
        this.region_id = region_id;
        this.region_name = region_name;
        this.links = links;
    }

    public Integer getRegion_id() {
        return region_id;
    }

    public void setRegion_id(Integer region_id) {
        this.region_id = region_id;
    }

    public String getRegion_name() {
        return region_name;
    }

    public void setRegion_name(String region_name) {
        this.region_name = region_name;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }
}