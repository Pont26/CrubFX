package util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Util {

	
	public static List<String> getField(Object object, boolean includeAllFields, Object... contation) {
	    List<String> columns = new ArrayList<>();
	    Field[] fields = object.getClass().getDeclaredFields();
	    List<Object> FieldList = List.of(contation);

	    for (Field field : fields) {
	        field.setAccessible(true);
	        if (includeAllFields) {
	            // If condition is true, add only the unnecessary fields
	            if (FieldList.contains(field.getName())) {
	                columns.add(field.getName());
	            }
	        } else {
	            // If condition is false, add all fields except the unnecessary ones
	            if (!FieldList.contains(field.getName())) {  
	                columns.add(field.getName());
	            }
	        }
	    }
	    return columns;
	}
}

	
    
