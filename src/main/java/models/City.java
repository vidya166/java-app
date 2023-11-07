package models;

import lombok.Data;

@Data
public class City {

    private long cityId;
    public void setCityId(long cityId) {
        this.cityId = cityId;
    }

    public long getCityId() {
        return cityId;
    }
}
