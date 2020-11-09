package ru.brambrulet.request;

import com.google.gson.Gson;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import ru.brambrulet.UdsGate;
import ru.brambrulet.json.Accepted;
import ru.brambrulet.request.json.Reward;
import ru.brambrulet.request.sub.UdsRequest;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RewardingRequest extends UdsRequest<Accepted> {

    private String json;

    public static Accepted request(UdsGate gate, BigDecimal points, String comment, String participant, String...participants) {
        Reward reward = new Reward(points, comment, participant, participants);

        return new RewardingRequest(new Gson().toJson(reward)).request(gate, Accepted.class);
    }

    public static BuilderInterface reward(BigDecimal points, String comment) {
        return new Builder(points, comment);
    }

    private static Accepted request(UdsGate gate, BigDecimal points, String comment, List<String> participants) {
        Reward reward = new Reward(points, comment, participants);

        return new RewardingRequest(new Gson().toJson(reward)).request(gate, Accepted.class);
    }

    @Override
    protected String getUrl() {
        return urlBuilder("/operations/reward").build();
    }

    @Override
    protected RequestBuilder requestBuilder() {
        return RequestBuilder.post()
                .setEntity(new StringEntity(json, ContentType.APPLICATION_JSON));
    }

    @RequiredArgsConstructor
    private static class Builder implements BuilderInterface {
        private List<String> participants = new ArrayList<>();
        private final BigDecimal points;
        private final String comment;

        @Override
        public Builder participant(String participant) {
            participants.add(participant);
            return this;
        }

        @Override
        public Accepted request(UdsGate gate) {
            return RewardingRequest.request(gate, points, comment, participants);
        }
    }

    public interface BuilderInterface {
        BuilderInterface participant(String participant);
        Accepted request(UdsGate gate);
    }
}
