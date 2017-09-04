package api;


import io.dropwizard.jersey.validation.Validators;
import org.junit.Test;
import static org.junit.Assert.assertEquals;


import javax.validation.Validator;
import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.collection.IsEmptyCollection.empty;

public class NetIDTest {
    @Test
    public void testNetID() {
        NetID netid = new NetID();
        assertEquals("Incorrect NetID", netid.netid, "dh649");
    }
}
