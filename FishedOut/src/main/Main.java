package main;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import factory.RodFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import model.Fish;
import model.Inventory;
import model.Player;
import model.Rod;

public class Main {
	public static void main(String[] args) throws IOException {
		HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

		registerRoute(server, "/index", Main::handleIndexRoute);
		registerRoute(server, "/about", Main::handleAboutRoute);
		registerRoute(server, "/catchFish", Main::catchFish);
		registerRoute(server, "/getMoney", Main::getMoney);
		registerRoute(server, "/rollChest", Main::rollChest);
		registerRoute(server, "/setPlayer", Main::handleSetPlayer);
		registerRoute(server, "/setRod", Main::setRod);
		registerRoute(server, "/addMoney", Main::addMoney);
		registerRoute(server, "/removeMoney", Main::removeMoney);
		registerRoute(server, "/getRod", Main::getRod);
			
		server.setExecutor(null);
		System.out.println("Server started at http://localhost:8080");
		server.start();
	}

	private static void registerRoute(HttpServer server, String context, HttpHandler handler) {
		server.createContext(context, handler);
	}
	
	private static void handleSetPlayer(HttpExchange exchange) throws IOException {
	    Player player = Player.getPlayer();
	    Inventory.getInventory();
	    if (player == null) {
	        String errorResponse = "{ \"error\": \"Player not found\" }";
	        exchange.sendResponseHeaders(500, errorResponse.getBytes().length);
	        OutputStream os = exchange.getResponseBody();
	        os.write(errorResponse.getBytes());
	        os.close();
	        return;
	    }
	    
	    String response = player.jsonify();
	    
	    exchange.sendResponseHeaders(200, response.getBytes().length);
	    OutputStream os = exchange.getResponseBody();
	    os.write(response.getBytes());
	    os.close();
	}

	private static void handleIndexRoute(HttpExchange exchange) throws IOException {
		String response = "Hello, welcome to the /index API!";
		exchange.sendResponseHeaders(200, response.getBytes().length);
		OutputStream os = exchange.getResponseBody();
		os.write(response.getBytes());
		os.close();
	}

	private static void handleAboutRoute(HttpExchange exchange) throws IOException {
		String response = "This is the About page!";
		exchange.sendResponseHeaders(200, response.getBytes().length);
		OutputStream os = exchange.getResponseBody();
		os.write(response.getBytes());
		os.close();
	}

	private static void catchFish(HttpExchange exchange) throws IOException {
		Fish randomFish = Player.getPlayer().catchFish();
		String response = randomFish.jsonify();

		exchange.getResponseHeaders().set("Content-Type", "application/json");
		exchange.sendResponseHeaders(200, response.getBytes().length);
		OutputStream os = exchange.getResponseBody();
		os.write(response.getBytes());
		os.close();
	}	
	
	public static void setRod(HttpExchange exchange) throws IOException {

	    if (!"GET".equals(exchange.getRequestMethod())) {
	        exchange.sendResponseHeaders(405, -1); 
	        return;
	    }

	    String query = exchange.getRequestURI().getQuery();
	    String rodName = null;
	    if (query != null && query.contains("rodName=")) {
	        rodName = query.split("=")[1];
	    }

	    if (rodName == null) {
	        String errorResponse = "{ \"error\": \"Missing or invalid 'rodName' query parameter\" }";
	        exchange.sendResponseHeaders(400, errorResponse.getBytes().length); // Bad Request
	        OutputStream os = exchange.getResponseBody();
	        os.write(errorResponse.getBytes());
	        os.close();
	        return;
	    }
	    RodFactory rf = new RodFactory();
	    Rod r = rf.createRod(rodName);
	    Player.getPlayer().setFishingRod(r);

	    String response = r.jsonify();
	    exchange.getResponseHeaders().set("Content-Type", "application/json");
	    exchange.sendResponseHeaders(200, response.getBytes().length); // OK
	    OutputStream os = exchange.getResponseBody();
	    os.write(response.getBytes());
	    os.close();
	}

	public static void getMoney(HttpExchange exchange) throws IOException {
	    String response = Double.toString(Player.getPlayer().getMoney());
	    response = "{ \"money\": \"" + response + "\" }";

	    exchange.getResponseHeaders().set("Content-Type", "application/json");
	    exchange.sendResponseHeaders(200, response.getBytes().length);

	    OutputStream os = exchange.getResponseBody();
	    os.write(response.getBytes());
	    os.close();
	}
	
	public static void getRod(HttpExchange exchange) throws IOException{
	    String query = exchange.getRequestURI().getQuery();
	    String rodName = null;
	    if (query != null && query.contains("rodName=")) {
	        rodName = query.split("=")[1];
	    }
	    
	    RodFactory rf = new RodFactory();
	    Rod rod = rf.createRod(rodName);
	    
	    String response = rod.jsonify();
	    exchange.getResponseHeaders().set("Content-Type", "application/json");
	    exchange.sendResponseHeaders(200, response.getBytes().length);

	    OutputStream os = exchange.getResponseBody();
	    os.write(response.getBytes());
	    os.close();
	}
	
	public static void addMoney(HttpExchange exchange) throws IOException {
	    if (!"GET".equals(exchange.getRequestMethod())) {
	        exchange.sendResponseHeaders(405, -1); // Method Not Allowed
	        return;
	    }

	    String query = exchange.getRequestURI().getQuery();
	    String amountStr = null;
	    if (query != null && query.contains("amount=")) {
	        amountStr = query.split("=")[1];
	    }

	    if (amountStr == null) {
	        String errorResponse = "{ \"error\": \"Missing or invalid 'amount' query parameter\" }";
	        exchange.sendResponseHeaders(400, errorResponse.getBytes().length); // Bad Request
	        OutputStream os = exchange.getResponseBody();
	        os.write(errorResponse.getBytes());
	        os.close();
	        return;
	    }

	    try {
	        double amount = Double.parseDouble(amountStr);
	        Player.getPlayer().incrementMoney(amount); // Update the player's money
	        String response = "{ \"success\": true, \"newMoney\": " + Player.getPlayer().getMoney() + " }";
	        exchange.getResponseHeaders().set("Content-Type", "application/json");
	        exchange.sendResponseHeaders(200, response.getBytes().length); // OK
	        OutputStream os = exchange.getResponseBody();
	        os.write(response.getBytes());
	        os.close();
	    } catch (NumberFormatException e) {
	        String errorResponse = "{ \"error\": \"Invalid amount format\" }";
	        exchange.sendResponseHeaders(400, errorResponse.getBytes().length); // Bad Request
	        OutputStream os = exchange.getResponseBody();
	        os.write(errorResponse.getBytes());
	        os.close();
	    }
	}
	
	public static void removeMoney(HttpExchange exchange) throws IOException {
	    if (!"GET".equals(exchange.getRequestMethod())) {
	        exchange.sendResponseHeaders(405, -1); // Method Not Allowed
	        return;
	    }

	    String query = exchange.getRequestURI().getQuery();
	    String amountStr = null;
	    if (query != null && query.contains("amount=")) {
	        amountStr = query.split("=")[1];
	    }

	    if (amountStr == null) {
	        String errorResponse = "{ \"error\": \"Missing or invalid 'amount' query parameter\" }";
	        exchange.sendResponseHeaders(400, errorResponse.getBytes().length); // Bad Request
	        OutputStream os = exchange.getResponseBody();
	        os.write(errorResponse.getBytes());
	        os.close();
	        return;
	    }

	    try {
	        double amount = Double.parseDouble(amountStr);
	        if (Player.getPlayer().getMoney() >= amount) {
	        	Player.getPlayer().decrementMoney(amount); 
	        }
	        
	        String response = "{ \"success\": true, \"newMoney\": " + Player.getPlayer().getMoney() + " }";
	        exchange.getResponseHeaders().set("Content-Type", "application/json");
	        exchange.sendResponseHeaders(200, response.getBytes().length); // OK
	        OutputStream os = exchange.getResponseBody();
	        os.write(response.getBytes());
	        os.close();
	    } catch (NumberFormatException e) {
	        String errorResponse = "{ \"error\": \"Invalid amount format\" }";
	        exchange.sendResponseHeaders(400, errorResponse.getBytes().length); // Bad Request
	        OutputStream os = exchange.getResponseBody();
	        os.write(errorResponse.getBytes());
	        os.close();
	    }
	}

	public static void rollChest(HttpExchange exchange) throws IOException {
	    String requestBody = readRequestBody(exchange);

	    String itemName = parseFromJson(requestBody);

	    System.out.println("Received item: " + itemName);

	    String response = "Rolled chest with item: " + itemName;
	    exchange.sendResponseHeaders(200, response.getBytes().length);
	    exchange.getResponseBody().write(response.getBytes());
	    exchange.getResponseBody().close();
	}

	private static String parseFromJson(String json) {
	    int startIndex = json.indexOf(":") + 2;
	    int endIndex = json.indexOf("\"", startIndex);
	    return json.substring(startIndex, endIndex);
	}

	private static String readRequestBody(HttpExchange exchange) throws IOException {
	    InputStreamReader isr = new InputStreamReader(exchange.getRequestBody());
	    BufferedReader reader = new BufferedReader(isr);

	    StringBuilder body = new StringBuilder();
	    String line;
	    while ((line = reader.readLine()) != null) {
	        body.append(line);
	    }

	    reader.close();
	    return body.toString();
	}
}
