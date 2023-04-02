package sg.edu.nus.iss.app.day29;

import java.io.Reader;
import java.io.StringReader;
import java.util.Date;
import java.util.List;

import org.bson.Document;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import sg.edu.nus.iss.app.day29.models.LineItem;
import sg.edu.nus.iss.app.day29.models.Order;

public class Utils {
    
	// This method converts a JsonObject to a LineItem object
    public static LineItem toLineItem(JsonObject json) {
		LineItem lineItem = new LineItem();
		lineItem.setItem(json.getString("item"));
		lineItem.setQuantity(json.getInt("quantity"));
		return lineItem;
	}

	// This method converts a JsonObject to an Order object
	public static Order toOrder(JsonObject json) {
		Order order = new Order();
		order.setName(json.getString("name"));
		order.setEmail(json.getString("email"));
		// yyyy-mm-dd
		// Date date = convert the date
		// create a Date object for the deliveryDate field of the Order object (currently hardcoded as the current date)
		order.setDeliveryDate(new Date());

		// create a List of LineItem objects by mapping the JsonArray of line items to LineItem objects using the toLineItem method
		List<LineItem> lineItems = json.getJsonArray("lineItems")
			.stream()
			.map(v -> (JsonObject)v)
			.map(v -> toLineItem(v))
			.toList();
		order.setLineItems(lineItems);

		return order;
	}

	// This method converts a LineItem object to a JsonObject
    public static JsonObject toJson(LineItem lineItem) {
		return Json.createObjectBuilder() // create a JsonObjectBuilder
			.add("item", lineItem.getItem())
			.add("quantity", lineItem.getQuantity())
			.build();
	}
	
	// This method converts an Order object to a JsonObject
    public static JsonObject toJson(Order order) {
		// create a JsonArrayBuilder by mapping the List of LineItem objects to JsonObjects using the toJson method and adding them to the array
		JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
		order.getLineItems()
			.stream()
			.map(v -> toJson(v)) // maps LineItem objects to JsonObjects using the toJson method
			.forEach(v -> {
				arrBuilder.add(v); // adds the JsonObjects to the array
			});
		
		// build a JsonObject by adding the Order properties to the builder
		return Json.createObjectBuilder()
			.add("orderId", order.getOrderId())
			.add("name", order.getName())
			.add("email", order.getEmail())
			.add("deliveryDate", order.getDeliveryDate().toString())
			.add("lineItems", arrBuilder.build()) // adds the array of LineItems to the builder
			.build();
	}

	// This method converts a LineItem object to a MongoDB document
    public static Document toDocument(LineItem lineItem) {
		Document doc = new Document();
		doc.put("item", lineItem.getItem());
		doc.put("quantity", lineItem.getQuantity());
		return doc;
	}

	// This method converts an Order object to a MongoDB document
	public static Document toDocument(Order order) {
		Document doc = new Document();
		doc.put("orderId", order.getOrderId());
		doc.put("name", order.getName());
		doc.put("email", order.getEmail());
		doc.put("deliveryDate", order.getDeliveryDate());

		// convert each LineItem to a MongoDB document and add to docs list
		List<Document> docs = order.getLineItems()
			.stream()
			.map(v -> toDocument(v)) // convert each LineItem to a MongoDB document
			.toList(); // store the documents in a list
		doc.put("lineItems", docs); // add the lineItems field to the document
		return doc;
	}

	//Converts a JSON string to a JsonObject
    public static JsonObject toJson(String str) {
		Reader reader = new StringReader(str); // create a new string reader
		JsonReader jsonReader = Json.createReader(reader); // create a new JSON reader
		return jsonReader.readObject(); // return the JSON object
	}
}
