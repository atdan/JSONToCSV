package object_mapper;

import org.junit.Test;

public class ObjectMapperAuctionTest {

    @Test
    public void testReadJsonWithObjectMapper() throws Exception{

        ObjectMapperAuctionToMap objectMapper = new ObjectMapperAuctionToMap();

        objectMapper.readJsonWithObjectMapper();
    }
}
