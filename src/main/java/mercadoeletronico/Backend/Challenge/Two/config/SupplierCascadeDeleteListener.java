package mercadoeletronico.Backend.Challenge.Two.config;

import mercadoeletronico.Backend.Challenge.Two.domain.supplier.Supplier;
import mercadoeletronico.Backend.Challenge.Two.domain.supplier.SupplierMainContact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeDeleteEvent;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

@Component
public class SupplierCascadeDeleteListener extends AbstractMongoEventListener<Supplier> {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void onBeforeDelete(BeforeDeleteEvent<Supplier> event) {
        String id = event.getSource().get("_id").toString();

        Query query = new Query(Criteria.where("supplierId").is(id));

        mongoTemplate.remove(query, SupplierMainContact.class, "suppliers_contact");
    }
}
