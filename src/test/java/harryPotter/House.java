package harryPotter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString

public class House {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("mascot")
    @Expose
    private String mascot;
    @SerializedName("headOfHouse")
    @Expose
    private String headOfHouse;
    @SerializedName("founder")
    @Expose
    private String founder;
    @SerializedName("__v")
    @Expose
    private Integer __v;
    @SerializedName("school")
    @Expose
    private String school;
    @SerializedName("members")
    @Expose
    private List<Members> members = null;
    @SerializedName("values")
    @Expose
    private List<Values> values = null;
    @SerializedName("colors")
    @Expose
    private List<Colors> colors = null;


}
