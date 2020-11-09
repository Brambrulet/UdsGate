package ru.brambrulet.request;

import ru.brambrulet.UdsGate;
import ru.brambrulet.json.ApplicationSettings;
import ru.brambrulet.request.sub.UdsRequest;

public class SettingsRequest extends UdsRequest<ApplicationSettings> {

    public static ApplicationSettings request(UdsGate gate) {
        return new SettingsRequest().request(gate, ApplicationSettings.class);
    }

    @Override
    protected String getUrl() {
        return UdsRequest.BASE_URL + "/settings";
    }
}
