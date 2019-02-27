package object_mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Auction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.IOException;
public class ObjectMapperAuction {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public Auction readJsonWithObjectMapper() throws IOException{
        ObjectMapper objectMapper = new ObjectMapper();

        Auction auction = objectMapper.readValue(new File("data-sample.json"),Auction.class);

        logger.info(auction.toString());
        return auction;
    }
}
