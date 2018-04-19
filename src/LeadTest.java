import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class LeadTest {

	static PrintStream o;
	public static void main(String args[]) throws Exception {

        URL url = new URL(
                "http://10.10.114.113:9094/resbilling-services/v1/allocateLead");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        String postJsonData = "{\"username\":\"testnew@mail.com\",\"name\":\"test user\","
                + "\"email\":\"testnew@mail.com\",\"ctc\":\"10\",\"exp\":\"3\","
                + "\"modtime\":\"2018-04-19 00:00:00\",\"entrytime\":\"2018-04-19 00:00:00\","
                + "\"fftid\":\"app_optin_dnc\",\"sitetype\":\"IND\",\"apptype\":\"DESK\","
                + "\"appid\":\"123\",\"promo\":\"N\",\"sourcepage\":\"home\","
                + "\"sourcepageid\":\"1\",\"leadtype\":\"SAMPLE\","
                + "\"callallowed\":\"YES\",\"followup\":\"2018-01-16 00:00:00\","
                + "\"comment\":\"CMT\",\"agentid\":\"0\",\"agentname\":\"NA\","
                + "\"query\":\"NA\",\"processname\":\"NA\",\"blockstatus\":\"NA\","
                + "\"leadscore\":\"80\",\"disposition\":\"Email Sent\","
                + "\"processid\":\"0\",\"mobile\":\"6789543456\",\"assigndatetime\":\"2018-04-19 00:00:00\"}";
        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(postJsonData);
        wr.flush();
        wr.close();

        o = new PrintStream(new FileOutputStream("output.txt", true));
        System.setOut(o);
        int responseCode = con.getResponseCode();
        System.out.println("nSending 'POST' request to URL : " + url);
        System.out.println("Post Data : " + postJsonData);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(
                con.getInputStream()));
        String output;
        StringBuffer response = new StringBuffer();

        while ((output = in.readLine()) != null) {
            response.append(output);
        }
        in.close();
        
        // printing result from response
        System.out.println(response.toString());

    }

}
