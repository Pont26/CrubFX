package util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import annotation.Column;
import annotation.Id;
import annotation.ManyToOne;

public class DaoUtail {
    public static List<Field> getFieldsFromObj(Object obj,Boolean need,String... fields){
        List<Field> filteredFields = new ArrayList<Field>();
        for (Field field: obj.getClass().getDeclaredFields()){
            List<String> listFields= List.of(fields);

            Id idAnnotation = field.getAnnotation(Id.class);
            Column columnAnnotation = field.getAnnotation(Column.class);

            if (field.isAnnotationPresent(ManyToOne.class)) {
            	
            }
            String fieldName = null;
            if (idAnnotation != null) {
                fieldName = idAnnotation.name();
            } else if (columnAnnotation != null) {
                fieldName = columnAnnotation.name();
            }
            if(need){
                if(listFields.contains(fieldName)){

                    filteredFields.add(field);
                }
            }else{
                if(!listFields.contains(fieldName)){
                    filteredFields.add(field);
                }
            }
        }
        return filteredFields;
    }
}
