package learning.datetime;

import java.util.Date;

public class Person {
    Date creationDate = new Date();

    public Date getCreationDate() {
        // return creationDate;
        return new Date(this.creationDate.getTime());
    }
}
