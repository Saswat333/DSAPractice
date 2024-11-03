package rest_api;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class TransactionProcessor {

    // Base URL for the API
    private static final String BASE_URL = "https://jsonmock.hackerrank.com/api/transactions/search?userId=";

    public static void main(String[] args) throws Exception {
        int uid = 2; // Sample user ID
        int locationId = 8; // Sample location ID
        int netStart = 5; // Sample network start range
        int netEnd = 50; // Sample network end range

        // Get the total amount of valid transactions
        int totalAmount = getTransactions(uid, locationId, netStart, netEnd);
        System.out.println("Total Transaction Amount: " + totalAmount);
    }

    /**
     * This method fetches all transactions of the user with the provided filters and returns
     * the total sum of the amount for transactions matching the criteria.
     */
    public static int getTransactions(int uid, int locationId, int netStart, int netEnd) throws Exception {
        int page = 1;
        int totalAmount = 0;
        boolean morePages = true;

        while (morePages) {
            // Fetch transactions for the given page
            JSONObject response = fetchTransactions(uid, page);
            JSONArray data = response.getJSONArray("data");
            int totalPages = response.getInt("total_pages");

            // Iterate through each transaction in the current page's data
            for (int i = 0; i < data.length(); i++) {
                JSONObject transaction = data.getJSONObject(i);

                // Check if the location ID matches
                JSONObject location = transaction.getJSONObject("location");
                if (location.getInt("id") == locationId) {

                    // Check if the IP address is in the given range
                    String ip = transaction.getString("ip");
                    if (isIpInRange(ip, netStart, netEnd)) {
                        String amountStr = transaction.getString("amount").replace("$", "").replace(",", "");
                        double amount = Double.parseDouble(amountStr);
                        totalAmount += Math.round(amount); // Round to the nearest int
                    }
                }
            }

            page++;
            if (page > totalPages) {
                morePages = false;
            }
        }

        return totalAmount;
    }

    /**
     * Helper method to fetch transactions from the API for a specific user and page.
     */
    private static JSONObject fetchTransactions(int uid, int page) throws Exception {
        String urlString = BASE_URL + uid + "&page=" + page;
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }

        in.close();
        conn.disconnect();

        return new JSONObject(content.toString());
    }

    /**
     * Helper method to check if the given IP address falls within the given range.
     * The check is done on the first byte of the IP address.
     */
    private static boolean isIpInRange(String ip, int netStart, int netEnd) {
        String[] ipParts = ip.split("\\.");
        int firstByte = Integer.parseInt(ipParts[0]);

        return firstByte >= netStart && firstByte <= netEnd;
    }
}

