import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class NasaRequest {
    final private String copyright;
    final private String date;
    final private String explanation;
    final private String ndurl;
    final private String media_type;
    final private String service_version;
    final private String title;
    final private String url;

    public String getCopyright() {
        return copyright;
    }

    public String getDate() {
        return date;
    }

    public String getExplanation() {
        return explanation;
    }

    public String getNdurl() {
        return ndurl;
    }

    public String getMedia_type() {
        return media_type;
    }

    public String getService_version() {
        return service_version;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public NasaRequest(@JsonProperty("copyright") String copyright, @JsonProperty("date") String date, @JsonProperty("explanation") String explanation, @JsonProperty("ndurl") String ndurl, @JsonProperty("media_type") String media_type, @JsonProperty("service_version") String service_version, @JsonProperty("title") String title, @JsonProperty("url") String url) {
        this.copyright = copyright;
        this.date = date;
        this.explanation = explanation;
        this.ndurl = ndurl;
        this.media_type = media_type;
        this.service_version = service_version;
        this.title = title;
        this.url = url;
    }

    @Override
    public String toString() {
        return "NasaRequest{" +
                "copyright='" + copyright + '\'' +
                ", date='" + date + '\'' +
                ", explanation='" + explanation + '\'' +
                ", ndurl='" + ndurl + '\'' +
                ", media_type='" + media_type + '\'' +
                ", service_version='" + service_version + '\'' +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
