package services;

import Java.exceptions.CourseNameExistsException;
import Java.exceptions.EmptyFieldException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.Iterator;

import static java.User.userNameProvider;

public class Provider {
    public static String nameCourseProvider;
    public static String priceProvider;
    public static String descriptionProvider;
    public static String contentProvider;

    public static void createCourse(String nameField, String priceField, String descriptionField, String contentField) throws EmptyFieldException, CourseNameExistsException {
        checkIfFieldAreEmpty(nameField, priceField, descriptionField, contentField);
        JSONObject obj = new JSONObject();
        JSONArray arrayClient = new JSONArray();
        JSONParser jp = new JSONParser();
        Object p;
        try {
            FileReader readFile = new FileReader("src/main/createCourse.json");
            BufferedReader read = new BufferedReader(readFile);
            p = jp.parse(read);
            if (p instanceof JSONArray) {
                arrayClient = (JSONArray) p;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Iterator<JSONObject> iterator = arrayClient.iterator();
        while (iterator.hasNext())
        {
            JSONObject obj2 = iterator.next();
            if (obj2.get("Name:").equals(nameField))
            {
                throw new CourseNameExistsException();
            }

        }
        JSONArray array= new JSONArray();
        obj.put("Name:", nameField);
        obj.put("Price:", priceField);
        obj.put("Description:", descriptionField);
        obj.put("Content:", contentField);
        obj.put("Username:", userNameProvider);
        arrayClient.add(obj);
        try {
            File file = new File("src/main/createCourse.json");
            FileWriter fisier = new FileWriter(file.getAbsoluteFile());
            fisier.write(arrayClient.toJSONString());
            fisier.flush();
            fisier.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteCourse(String name){
        JSONParser parser = new JSONParser();
        Object p;
        JSONObject obj = new JSONObject();
        JSONObject obj3 = null;
        JSONArray arrayClient = new JSONArray();
        try {
            FileReader readFile = new FileReader("src/main/createCourse.json");
            BufferedReader read = new BufferedReader(readFile);
            p = parser.parse(read);
            if (p instanceof JSONArray) {
                arrayClient = (JSONArray) p;
            }
        } catch (ParseException parseException) {
            parseException.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        Iterator<JSONObject> iterator = arrayClient.iterator();
        while (iterator.hasNext())
        {
            JSONObject obj2 = iterator.next();
            if (obj2.get("Name:").equals(name) && (obj2.get("Username:").equals(userNameProvider)) )
            {
                obj3 = obj2;
            }
        }
        arrayClient.remove(obj3);
        try {
            File file = new File("src/main/createCourse.json");
            FileWriter fisier = new FileWriter(file.getAbsoluteFile());
            fisier.write(arrayClient.toJSONString());
            fisier.flush();
            fisier.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void editCourse(String oldName, String name, String price, String description, String content) throws EmptyFieldException, CourseNameExistsException {
        checkIfFieldAreEmpty(name, price, description, content);
        JSONParser parser = new JSONParser();
        Object p;
        JSONObject obj = new JSONObject();
        JSONObject obj3 = null;
        JSONArray arrayClient = new JSONArray();
        try {
            FileReader readFile = new FileReader("src/main/createCourse.json");
            BufferedReader read = new BufferedReader(readFile);
            p = parser.parse(read);
            if (p instanceof JSONArray) {
                arrayClient = (JSONArray) p;
            }
        } catch (ParseException parseException) {
            parseException.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        Iterator<JSONObject> iterator = arrayClient.iterator();
        while (iterator.hasNext())
        {
            JSONObject obj2 = iterator.next();
            if (obj2.get("Name:").equals(oldName) && (obj2.get("Username:").equals(userNameProvider)) )
            {
                obj3 = obj2;
            }
        }
        arrayClient.remove(obj3);
        Iterator<JSONObject> iterator2 = arrayClient.iterator();
        while (iterator2.hasNext())
        {
            JSONObject obj2 = iterator2.next();
            if (obj2.get("Name:").equals(name) && (obj2.get("Username:").equals(userNameProvider)) )
            {
                throw new CourseNameExistsException();
            }
        }
        obj.put("Name:", name);
        obj.put("Price:", price);
        obj.put("Description:", description);
        obj.put("Content:", content);
        obj.put("Username:", userNameProvider);
        arrayClient.add(obj);
        try {
            File file = new File("src/main/createCourse.json");
            FileWriter fisier = new FileWriter(file.getAbsoluteFile());
            fisier.write(arrayClient.toJSONString());
            fisier.flush();
            fisier.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean searchCourseProvider(String name){
        boolean ok = false;
        JSONParser parser = new JSONParser();
        Object p;
        JSONArray arrayClient = new JSONArray();
        try {
            FileReader readFile = new FileReader("src/main/createCourse.json");
            BufferedReader read = new BufferedReader(readFile);
            p = parser.parse(read);
            if (p instanceof JSONArray) {
                arrayClient = (JSONArray) p;
            }
        } catch (ParseException parseException) {
            parseException.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        Iterator<JSONObject> iterator = arrayClient.iterator();
        while (iterator.hasNext())
        {
            JSONObject obj = iterator.next();
            if (obj.get("Name:").equals(name) && (obj.get("Username:").equals(userNameProvider)) )
            {
                nameCourseProvider = (String) obj.get("Name:");
                priceProvider = (String) obj.get("Price:");
                descriptionProvider = (String) obj.get("Description:");
                contentProvider = (String) obj.get("Content:");
                ok = true;
            }
        }
        return ok;
    }

    private static void checkIfFieldAreEmpty(String nameField, String priceField, String descriptionField, String contentField) throws EmptyFieldException {
        if(nameField.isEmpty() | priceField.isEmpty() | descriptionField.isEmpty() | contentField.isEmpty())
        {
            throw new EmptyFieldException();
        }
    }
}
