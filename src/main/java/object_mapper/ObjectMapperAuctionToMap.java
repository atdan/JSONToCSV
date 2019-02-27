package object_mapper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Auction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import org.supercsv.io.CsvMapWriter;
import org.supercsv.prefs.CsvPreference;


import java.io.*;
import java.util.*;

public class ObjectMapperAuctionToMap {
    private  final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private Resource outputResource = new FileSystemResource("output/outputData.csv");

    String[] headers = new String[]{"_index", "_type", "_score", "sort", "actual", "ad", "adType",
            "audience", "bid", "browser", "budgetConsumedActual",

            "budgetConsumedActualNaira", "budgetConsumedAdvertiser", "budgetConsumedAdvertiserNaira",

            "budgetConsumedAgency", "budgetConsumedAgencyNaira", "category", "device", "discount", "effective", "effectivePricing",

            "ip", "ipv6", "language", "location", "minBid", "network",

            "operatingSystem", "organization", "payout", "start", "timestamp", "type",

            "ua", "zone"};

    public void readJsonWithObjectMapper() throws Exception {

//        ObjectMapper objectMapper = new ObjectMapper();
//
//        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//
//
//

//
//        Map<?,?> auctionMap = objectMapper.readValue(new FileInputStream("data-sample.json"),Map.class);
//        for (Map.Entry<?,?> entry: auctionMap.entrySet()){
//            LOGGER.info("\n--------------------\n" + entry.getKey() + " = " + entry.getValue() + "\n");
//        }

        Map<String, Auction> auctionsMap = new HashMap<String, Auction>();

        List<Auction> auctionsList = readJSONAuctions("data-sample.json");





        //convert from list to hashmap with key=getId and value=Song.class object
        for (Auction i : auctionsList) {
            auctionsMap.put(i.get_id(), i);

        }



        int i = 1;
        for (Map.Entry<?, ?> entry : auctionsMap.entrySet()) {
            LOGGER.debug(i + "\n" );
            LOGGER.info("\n--------------------\n" + entry.getKey() + " = " + entry.getValue() + "\n");


            i++;

        }

        StringWriter sw = new StringWriter();
        CsvMapWriter writer = new CsvMapWriter(sw,CsvPreference.STANDARD_PREFERENCE);

        writer.writeHeader(headers);
        writer.write(auctionsMap,headers);
        writer.close();

        System.out.println("Generated CSV: \n");
        System.out.println(sw.toString());
    }

    static List<Auction> readJSONAuctions(String filename) throws IOException, FileNotFoundException {
        ObjectMapper objectMapper = new ObjectMapper();

        try (InputStream is = new FileInputStream(filename)) {

            return (List<Auction>) objectMapper.readValue(is, new TypeReference<List<Auction>>() {
            });

        }
    }






}
