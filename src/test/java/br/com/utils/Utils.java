package br.com.utils;

import com.aventstack.extentreports.ExtentReports;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.regex.Matcher;

public class Utils {


    private static String environmentPropertiesPath;

    public static Properties loadTestProperties() throws FileNotFoundException, IOException {
        Properties prop = new Properties();
        FileInputStream input = new FileInputStream(environmentPropertiesPath);
        prop.load(input);
        input.close();
        return prop;
    }

    public static void setEnvironment() {
        String environment = "";
        if (System.getProperty("test.environment") != null) {
            environment = System.getProperty("test.environment");
        } else {
            environment = getTestEnvironmentFromPropertiesFile();
        }

        File environmentPropertie = new File("src/test/resources/" + environment + ".properties");
        if (environmentPropertie.exists()) {
            environmentPropertiesPath = "src/test/resources/" + environment + ".properties";
        } else {
            System.err.println("No known environments were selected. Create the file with the necessary properties: " + "[src/test/resources/" + environment + ".properties]");
        }
    }

    public static String getTestEnvironmentFromPropertiesFile() {
        Properties setEnvironment = loadProperties("src/test/resources/select-environment.properties");
        String environment = getProperty(setEnvironment, "test.environment");
        return environment;
    }

    public static String getProperty(Properties prop, String propertyKey) {
        try {
            if (System.getProperty(propertyKey) != null) {
                return System.getProperty(propertyKey);
            }
            return prop.getProperty(propertyKey).trim();
        } catch (Exception e) {
            String error_msg = String.format("Cannot load propertie %s. Error %s", propertyKey,
                    e.getMessage());
            throw new IllegalStateException(error_msg);
        }
    }

    public static Map<String, String> mapResultDBGenerator(ResultSet results, String[] collunms) throws SQLException {
        Map<String, String> resultado = new HashMap<String, String>();
        if(!results.next()){
            return resultado;
        }
        String result;
        for (String collunm : collunms) {
            if(results.isAfterLast())
                results.next();
            result = results.getString(collunm);
            resultado.put(collunm,result);
        }
        mapResultDBGenerator(results, collunms);
        return resultado;
    }

    public static Matcher substringRegex(String regex, String data) {
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(regex);
        Matcher matcher = pattern.matcher(data);
        matcher.find();
        return matcher;
    }


    public static String substringRegexGroup1(String regex, String data) {
        return substringRegex(regex, data).group(1);
    }

    public static void deleteAllFilesInFolder(String folderPath) {
        File folder = new File(folderPath);
        for (File f : folder.listFiles()) {
            if (!f.isDirectory())
                f.delete();
        }
    }

    public static void addAllTestPropertiesToExtentReport(ExtentReports extentReport) {
        Set<Object> keys = Utils.getAllTestProperties();
        for (Object k : keys) {
            String key = (String) k;
            extentReport.setSystemInfo(key, Utils.getTestProperty(key));
        }
    }

    public static String getTestProperty(String propertyKey) {
        try {
            if (System.getProperty(propertyKey) != null) {
                return System.getProperty(propertyKey);
            }
            Properties prop = loadTestProperties();
            return prop.getProperty(propertyKey).trim();
        } catch (Exception e) {
            String error_msg = String.format("Cannot load propertie %s. Error %s", propertyKey,
                    e.getMessage());
            throw new IllegalStateException(error_msg);
        }
    }


    public static String[] getSubDirectoriesNames(String path) {
        File file = new File(path);
        String[] directories = file.list(new FilenameFilter() {
            public boolean accept(File current, String name) {
                return new File(current, name).isDirectory();
            }
        });
        return directories;
    }

    public static Set<Object> getAllTestProperties() {
        Properties prop = loadProperties(environmentPropertiesPath);
        Set<Object> keys = prop.keySet();
        return keys;
    }

    public static Properties loadProperties(String propertiesPath) {
        Properties prop = new Properties();
        try {
            FileInputStream input = new FileInputStream(propertiesPath);
            prop.load(input);
            input.close();
        } catch (Exception e) {
            throw new RuntimeException("Cannot load propertie file: " + propertiesPath);
        }
        return prop;
    }

    public static String getBodyJSON(br.com.utils.Paths path) {
        String body = null;
        try {
            body = new String(Files.readAllBytes(Paths.get(path.getPath())));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return body;
    }


}
