package ir.mo.amin.hosseini.calendarview.Model.CalendarModels;

import com.activeandroid.Model;
import com.activeandroid.query.Select;


public class CustomORMModel extends Model {
    public CustomORMModel() {
        super();
    }

    public static <T extends Model> T getFirst(Class<T> type) {
        return new Select().from(type).executeSingle();
    }
}
