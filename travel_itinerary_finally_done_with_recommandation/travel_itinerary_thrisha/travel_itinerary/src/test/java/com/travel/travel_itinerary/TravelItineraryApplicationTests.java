package com.travel.travel_itinerary;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = {
        "spring.datasource.url=jdbc:mysql://localhost:3306/travel_itinerary",
        "spring.datasource.username=root",
        "spring.datasource.password=Push",
        "spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver",
        "spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect"
})
class TravelItineraryApplicationTests {

    @Test
    void contextLoads() {
    }

}
