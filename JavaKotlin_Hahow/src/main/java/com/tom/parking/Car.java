package com.tom.parking;

import java.time.Duration;
import java.time.LocalDateTime;

public class Car {
    private String id;

//    private long enter;
//    private long leave;

    // 改用 LocalDateTime
    private LocalDateTime enter;
    private LocalDateTime leave;

    public Car(String id, LocalDateTime enter) {
        this.id = id;
        this.enter = enter;
    }

    public void leave() {
        leave = LocalDateTime.now(); //System.currentTimeMillis();
    }

    // 停留時間
    public long getDuration() {
        Duration duration = Duration.between(enter, leave);
        return duration.toMinutes();
    }

    // 封裝的目的可以讓設置值的時候可以檢查
    public void setLeave(LocalDateTime leave) {
//        if (leave > enter) {
//            this.leave = leave;
//        }
        if (leave.isAfter(enter)) { // 改用 LocalDateTime
            this.leave = leave;
        }
    }
}
