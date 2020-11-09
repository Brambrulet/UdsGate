package ru.brambrulet.request.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Reward {

    @SerializedName("points")
    @Expose
    public BigDecimal points;

    @SerializedName("comment")
    @Expose
    public String comment;

    @SerializedName("participants")
    @Expose
    public List<String> participants = new ArrayList<>();

    public Reward(BigDecimal points, String comment, String participant, String...participants) {
        this.points = points;
        this.comment = comment;
        this.participants = new ArrayList<>(Arrays.asList(participants));
        this.participants.add(participant);
    }

    public Reward(BigDecimal points, String comment, List<String> participants) {
        this.points = points;
        this.comment = comment;
        this.participants = participants;
    }
}
