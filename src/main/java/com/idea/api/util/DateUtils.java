package com.idea.api.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DateUtils {

    public Date now() {
        ZonedDateTime zonedDateTime = LocalDateTime.now().atZone(ZoneId.systemDefault());
        return Date.from(zonedDateTime.toInstant());
    }

    public Date nowPlusSeconds(int seconds) {
        ZonedDateTime zonedDateTime = LocalDateTime.now()
                .plusSeconds(seconds)
                .atZone(ZoneId.systemDefault());
        return Date.from(zonedDateTime.toInstant());
    }
}
