package services;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.Iterator;

import static java.User.userNameStudent;

public class Student {
    public static String nameCourse;
    public static String price;
    public static String description;
    public static String content;
    public static String courseListStudent = "";
    public static String courseNameStudent;

    public static boolean searchCourseStudent(String name){
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
            if (obj.get("Name:").equals(name))
            {
                nameCourse = (String) obj.get("Name:");
                price = (String) obj.get("Price:");
                description = (String) obj.get("Description:");
                content = (String) obj.get("Content:");
                ok = true;
            }

        }
        return ok;
    }

    public static boolean findCourse(String name){
        boolean ok = false;
        JSONParser parser = new JSONParser();
        Object p;
        JSONArray arrayClient = new JSONArray();
        try {
            FileReader readFile = new FileReader("src/main/buyCourse.json");
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
            if (obj.get("Name of the course:").equals(name) && (obj.get("Username:").equals(userNameStudent)) )
            {
                searchCourseStudent(name);
                ok = true;
            }
        }
        return ok;
    }

    public static void viewCourseList(String name)
    {
        JSONParser parser = new JSONParser();
        Object p;
        JSONArray arrayClient = new JSONArray();
        try {
            FileReader readFile = new FileReader("src/main/buyCourse.json");
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
            if (obj.get("Username:").equals(name))
            {
                String actual = courseListStudent;
                if(courseListStudent.equals(""))
                {
                    courseListStudent = (String) obj.get("Name of the course:");
                }
                else
                {
                    courseListStudent = actual + "\n" + (String) obj.get("Name of the course:");
                }
            }

        }
    }

    public static void buyCourse(String userName, String courseName)
    {
        JSONObject obj = new JSONObject();
        JSONArray arrayClient = new JSONArray();
        JSONParser jp = new JSONParser();
        Object p;
        try {
            FileReader readFile = new FileReader("src/main/buyCourse.json");
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
        JSONArray array= new JSONArray();
        obj.put("Username:", userName);
        obj.put("Name of the course:", courseName);
        arrayClient.add(obj);
        try {
            File file = new File("src/main/buyCourse.json");
            FileWriter fisier = new FileWriter(file.getAbsoluteFile());
            fisier.write(arrayClient.toJSONString());
            fisier.flush();
            fisier.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
