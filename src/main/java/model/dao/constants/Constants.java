package model.dao.constants;

import model.domain.Status;

public interface Constants {

    String STATUS_FREE = Status.FREE.toString().toLowerCase();
    String STATUS_WORK = Status.WORK.toString().toLowerCase();
    String STATUS_EMPTY = Status.EMPTY.toString().toLowerCase();
}
