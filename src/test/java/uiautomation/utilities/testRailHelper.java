package uiautomation.utilities;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.Base64;

public class testRailHelper {

    public static String publishToTestRail(String testCaseId, String testResult) throws IOException, URISyntaxException {
        String runId = addTestRun();
        String resId = addTestResult(runId, testCaseId, testResult);

       Desktop.getDesktop().browse(new URI(String.format("https://testrail_host/index.php?/runs/view/%s", runId)));
        return resId;
    }

    private static String addTestResult(String runId, String testCase, String testResult) throws IOException{
        final String charset = "UTF-8";
        // Create the connection
        HttpURLConnection connection = (HttpURLConnection) new URL(String.format("https://testrail_host/index.php?/api/v2/add_result_for_case/%s/%s", runId, testCase)).openConnection();
        // setDoOutput(true) implicitly set's the request type to POST
        connection.setDoOutput(true);
        connection.setRequestProperty("Accept-Charset", charset);
        connection.setRequestProperty("Content-type", "application/json");

        String userpass = "user.name@yourcompany.com:d1HMa3D999BhK9Bq6Q7u-Xe1I6KLzTa/q2W11U/38";
        String basicAuth = "Basic " + new String(Base64.getEncoder().encode(userpass.getBytes()));
        connection.setRequestProperty ("Authorization", basicAuth);

        // Write to the connection
        OutputStream output = connection.getOutputStream();
        output.write(String.format("{ \"status_id\": %s, \"comment\" : \"test comment for run %s\" }", testResult, runId).getBytes(charset));
        output.close();

        // Check the error stream first, if this is null then there have been no issues with the request
        InputStream inputStream = connection.getErrorStream();
        if (inputStream == null)
            inputStream = connection.getInputStream();

        // Read everything from our stream
        BufferedReader responseReader = new BufferedReader(new InputStreamReader(inputStream, charset));

        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = responseReader.readLine()) != null) {
            response.append(inputLine);
        }
        responseReader.close();

        Msj(response.toString());

        JSONObject jsonObject = (JSONObject) JSONValue.parse(response.toString());
        String resId = jsonObject.get("id").toString();
        return resId;
    }

    private static String addTestRun() throws IOException{
        final String charset = "UTF-8";
        // Create the connection
        HttpURLConnection connection = (HttpURLConnection) new URL("https://testrail_host/index.php?api/v2/add_run/2").openConnection();
        // setDoOutput(true) implicitly set's the request type to POST
        connection.setDoOutput(true);
        connection.setRequestProperty("Accept-Charset", charset);
        connection.setRequestProperty("Content-type", "application/json");

        String userpass = "user.name@yourcompany.com:d1HMa3DBGCBhK9Bq6Q7u-Xe1I6KLzTa/q2W11U/38";
        String basicAuth = "Basic " + new String(Base64.getEncoder().encode(userpass.getBytes()));
        connection.setRequestProperty ("Authorization", basicAuth);

        // Write to the connection
        OutputStream output = connection.getOutputStream();
        output.write("{ \"name\": \"Demo test run\", \"include_all\": false, \"case_ids\": [258] }".getBytes(charset));
        output.close();

        // Check the error stream first, if this is null then there have been no issues with the request
        InputStream inputStream = connection.getErrorStream();
        if (inputStream == null)
            inputStream = connection.getInputStream();

        // Read everything from our stream
        BufferedReader responseReader = new BufferedReader(new InputStreamReader(inputStream, charset));

        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = responseReader.readLine()) != null) {
            response.append(inputLine);
        }
        responseReader.close();

        Msj(response.toString());

        JSONObject jsonObject = (JSONObject) JSONValue.parse(response.toString());
        String id = jsonObject.get("id").toString();
        return id;
    }

    private static void Msj(String texto){
        System.out.println(texto);
    }
}
