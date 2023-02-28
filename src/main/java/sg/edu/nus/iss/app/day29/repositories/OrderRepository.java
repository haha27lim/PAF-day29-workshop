package sg.edu.nus.iss.app.day29.repositories;

import org.bson.Document;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.app.day29.Constants;
import sg.edu.nus.iss.app.day29.Utils;
import sg.edu.nus.iss.app.day29.models.Order;


@Repository
public class OrderRepository {

    @Autowired
    private MongoTemplate template;

    public void insertOrder(Order order) {
      Document doc = Utils.toDocument(order);
      template.insert(doc, Constants.COLLECTION_PURCHASE_ORDER);
    }
}
