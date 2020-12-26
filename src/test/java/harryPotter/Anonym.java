package harryPotter;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Anonym {


    @SerializedName("error")
    @Expose
    private String error;

    public Anonym() {
    }

    public Anonym(String error) {

        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
    /*

    @SerializedName("error1")
    @Expose
    private String error1;
    @SerializedName("error2")
    @Expose
    private String error2;

    public Anonym(){

    }

    public Anonym(String error1,String error2){
        this.error1 = error1;
        this.error2 = error2;

    }

    public String getError1() {
        return error1;
    }

    public String getError2() {
        return error2;
    }
*/
}
