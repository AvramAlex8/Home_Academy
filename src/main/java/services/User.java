package services;

import exceptions.EmptyFieldException;
import exceptions.ShortPasswordException;
import exceptions.UsernameAlreadyExistsException;
import exceptions.UsernameOrPasswordIncorrectException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.util.Base64;
import java.util.Iterator;

public class User {
    public static String userNameProvider;
    public static String userNameStudent;
    public static String key = "Jar12345Jar12345";
    public static String initVector = "RandomInitVector";

    public static void addUserStudent(String firstName, String lastName, String userName, String password) throws EmptyFieldException, ShortPasswordException, UsernameAlreadyExistsException {
        checkIfFieldAreEmptyStudent(firstName, lastName, userName, password);
        checkIfPasswordIsLong(password);
        JSONObject obj = new JSONObject();
        JSONArray arrayClient = new JSONArray();
        JSONParser jp = new JSONParser();
        Object p;
        try {
            FileReader readFile = new FileReader("src/main/registerStudent.json");
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
            if (obj2.get("Username:").equals(userName))
            {
                throw new UsernameAlreadyExistsException();
            }

        }
        JSONArray array= new JSONArray();
        obj.put("First Name:", firstName);
        obj.put("Last Name:", lastName);
        obj.put("Username:", userName);
        obj.put("Password:", encodePassword(key, initVector, password));
        arrayClient.add(obj);
        try {
            File file = new File("src/main/registerStudent.json");
            FileWriter fisier = new FileWriter(file.getAbsoluteFile());
            fisier.write(arrayClient.toJSONString());
            fisier.flush();
            fisier.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void checkIfFieldAreEmptyStudent(String firstName, String lastName, String userName, String password) throws EmptyFieldException {
        if(firstName.isEmpty() | lastName.isEmpty() | userName.isEmpty() | password.isEmpty())
        {
            throw new EmptyFieldException();
        }
    }

    public static void addUserProvider(String businessName, String userName, String password) throws EmptyFieldException, ShortPasswordException, UsernameAlreadyExistsException {
        checkIfFieldAreEmptyProvider(businessName, userName, password);
        checkIfPasswordIsLong(password);
        JSONObject obj = new JSONObject();
        JSONArray arrayClient = new JSONArray();
        JSONParser jp = new JSONParser();
        Object p;
        try {
            FileReader readFile = new FileReader("src/main/registerProvider.json");
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
            if (obj2.get("Username:").equals(userName))
            {
                throw new UsernameAlreadyExistsException();
            }

        }
        JSONArray array= new JSONArray();
        obj.put("Name of business:", businessName);
        obj.put("Username:", userName);
        obj.put("Password:", encodePassword(key, initVector, password));
        arrayClient.add(obj);
        try {
            File file = new File("src/main/registerProvider.json");
            FileWriter fisier = new FileWriter(file.getAbsoluteFile());
            fisier.write(arrayClient.toJSONString());
            fisier.flush();
            fisier.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void checkIfFieldAreEmptyProvider(String businessname, String userName, String password) throws EmptyFieldException {
        if(businessname.isEmpty() | userName.isEmpty() | password.isEmpty())
        {
            throw new EmptyFieldException();
        }
    }

    private static void checkIfPasswordIsLong(String password) throws ShortPasswordException {
        if(password.length() < 8)
        {
            throw new ShortPasswordException();
        }
    }

    static boolean correctAccount = false;
    public static String loginCheckStudent(String userName, String password)throws UsernameOrPasswordIncorrectException {
        JSONParser parser = new JSONParser();
        Object p;
        JSONArray arrayClient = new JSONArray();
        try {
            FileReader readFile = new FileReader("src/main/registerStudent.json");
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
            if (obj.get("Username:").equals(userName) && obj.get("Password:").equals(encodePassword(key, initVector, password)))
            {
                correctAccount = true;
                userNameStudent = userName;
                return "Student";
            }
        }
        return"";
    }

    public static String loginCheckProvider(String userName, String password)throws UsernameOrPasswordIncorrectException {
        JSONParser parser = new JSONParser();
        Object p;
        JSONArray arrayClient = new JSONArray();
        try {
            FileReader readFile = new FileReader("src/main/registerProvider.json");
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
            if (obj.get("Username:").equals(userName) && obj.get("Password:").equals(encodePassword(key, initVector, password)))
            {
                correctAccount = true;
                userNameProvider = userName;
                return "Provider";
            }
        }
        return"";
    }

    public static void checkIncorrect()throws UsernameOrPasswordIncorrectException
    {
        if(!correctAccount)
        {
            throw new UsernameOrPasswordIncorrectException();
        }
    }

    public static String encodePassword(String key, String initVector, String value)
    {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(1, skeySpec, iv);
            byte[] encrypted = cipher.doFinal(value.getBytes());
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
