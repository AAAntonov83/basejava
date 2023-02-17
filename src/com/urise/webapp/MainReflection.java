package com.urise.webapp;

import com.urise.webapp.model.Resume;

import java.lang.reflect.Field;

public class MainReflection {

    public static void main(String[] args) throws ReflectiveOperationException {
        Resume r = new Resume();
        Field field = r.getClass().getDeclaredFields()[0];
        field.setAccessible(true);
        System.out.println(field.getName());
        System.out.println(field.get(r));
        field.set(r, "new_uuid");
        //r.getClass().getMethod("toString").invoke(r);
        Class.forName("Resume").getMethod("toString").invoke(r);
        System.out.println(r);
    }
}
