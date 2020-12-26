package harryPotter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class Character {

    @SerializedName("_id")
    @Expose
    private String _id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("role")
    @Expose
    private String role;
    @SerializedName("house")
    @Expose
    private String house;
    @SerializedName("school")
    @Expose
    private String school;
    @SerializedName("__v")
    @Expose
    private Integer __v;
    @SerializedName("ministryOfMagic")
    @Expose
    private boolean ministryOfMagic;
    @SerializedName("orderOfThePhoenix")
    @Expose
    private boolean orderOfThePhoenix;
    @SerializedName("dumbledoresArmy")
    @Expose
    private boolean dumbledoresArmy;
    @SerializedName("deathEater")
    @Expose
    private boolean deathEater;
    @SerializedName("bloodStatus")
    @Expose
    private String bloodStatus;
    @SerializedName("species")
    @Expose
    private String species;

    public Character(String house){
        this.house = house;
    }
}
