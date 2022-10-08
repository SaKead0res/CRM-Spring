package com.example.CRM.enums;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
public enum Status {
    OPEN, CLOSED_WON, CLOSED_LOST

}
