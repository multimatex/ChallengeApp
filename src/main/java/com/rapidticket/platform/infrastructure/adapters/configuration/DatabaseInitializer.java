package com.rapidticket.platform.infrastructure.adapters.configuration;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.r2dbc.core.DatabaseClient;
import reactor.core.publisher.Mono;

@Configuration
public class DatabaseInitializer {

    @Bean
    public ApplicationRunner initDB(ConnectionFactory connectionFactory) {
        DatabaseClient client = DatabaseClient.create(connectionFactory);

        return args -> client.sql("""            
            CREATE TABLE IF NOT EXISTS location (
                id BIGSERIAL PRIMARY KEY,
                name VARCHAR(100) NOT NULL,
                address VARCHAR(200),
                country VARCHAR(50),
                city VARCHAR(50)
            );

            CREATE TABLE IF NOT EXISTS auditorium (
                id BIGSERIAL PRIMARY KEY,
                location_id BIGINT,
                name VARCHAR(100),
                is_numbered_seats BOOLEAN,
                FOREIGN KEY (location_id) REFERENCES location(id)
            );

            CREATE TABLE IF NOT EXISTS sections (
                id BIGSERIAL PRIMARY KEY,
                name VARCHAR(100) NOT NULL
            );

            CREATE TABLE IF NOT EXISTS show_event (
                id BIGSERIAL PRIMARY KEY,
                title VARCHAR(200),
                description TEXT
            );

            CREATE TABLE IF NOT EXISTS performance (
                id BIGSERIAL PRIMARY KEY,
                show_id BIGINT,
                auditorium_id BIGINT,
                date_time TIMESTAMP,
                FOREIGN KEY (show_id) REFERENCES show_event(id),
                FOREIGN KEY (auditorium_id) REFERENCES auditorium(id)
            );

            CREATE TABLE IF NOT EXISTS spectator (
                id BIGSERIAL PRIMARY KEY,
                dni VARCHAR(50),
                name VARCHAR(100)
            );

            CREATE TABLE IF NOT EXISTS reservation (
                id BIGSERIAL PRIMARY KEY,
                spectator_id BIGINT,
                reservation_date TIMESTAMP,
                total_price DOUBLE PRECISION,
                FOREIGN KEY (spectator_id) REFERENCES spectator(id)
            );

            CREATE TABLE IF NOT EXISTS seat (
                id BIGSERIAL PRIMARY KEY,
                auditorium_id BIGINT,
                number INT,
                FOREIGN KEY (auditorium_id) REFERENCES auditorium(id)
            );

            CREATE TABLE IF NOT EXISTS seat_price (
                id BIGSERIAL PRIMARY KEY,
                show_id BIGINT,
                section_id BIGINT,
                price DOUBLE PRECISION,
                FOREIGN KEY (show_id) REFERENCES show_event(id),
                FOREIGN KEY (section_id) REFERENCES sections(id)
            );

            CREATE TABLE IF NOT EXISTS show_section_seat (
                id BIGSERIAL PRIMARY KEY,
                show_id BIGINT,
                section_id BIGINT,
                seat_id BIGINT,
                seat_price_id BIGINT,
                FOREIGN KEY (show_id) REFERENCES show_event(id),
                FOREIGN KEY (section_id) REFERENCES sections(id),
                FOREIGN KEY (seat_id) REFERENCES seat(id),
                FOREIGN KEY (seat_price_id) REFERENCES seat_price(id)
            );

            CREATE TABLE IF NOT EXISTS ticket (
                id BIGSERIAL PRIMARY KEY,
                reservation_id BIGINT,
                performance_id BIGINT,
                show_section_seat_id BIGINT,
                FOREIGN KEY (reservation_id) REFERENCES reservation(id),
                FOREIGN KEY (performance_id) REFERENCES performance(id),
                FOREIGN KEY (show_section_seat_id) REFERENCES show_section_seat(id)
            );
        """).then()

                .then(insertIfEmpty(client, "location", """
            INSERT INTO public."location" (name,address,country,city) VALUES
            ('Madison Square Garden','4 Pennsylvania Plaza','USA','New York'),
            ('teatro brooklyn','Bennelong Point','Usa','NY'),
            ('Auditorios UdeA','transversal 2','Colombia','Medellin'),
            ('Teatro moderno','Calle 5 ','Colombia','Bogota');
        """))

                .then(insertIfEmpty(client, "auditorium", """
            INSERT INTO public.auditorium (location_id,name,is_numbered_seats) VALUES
            (1,'Sala 1',true),
            (1,'Sala 2',true),
            (1,'Sala 4',true),
            (1,'Sala 5',true),
            (4,'Sala 1',true),
            (5,'Sala 1',true),
            (5,'Sala 2',false),
            (1,'Sala 3',false),
            (4,'Sala 2',false);
        """))

                .then(insertIfEmpty(client, "sections", """
            INSERT INTO sections (name) VALUES
            ('VIP'),
            ('General');
        """))

                .then(insertIfEmpty(client, "show_event", """
            INSERT INTO show_event (title, description) VALUES
            ('Rock Concert', 'An amazing rock show!'),
            ('Classical Orchestra', 'A wonderful classical performance.');
        """))

                .then(insertIfEmpty(client, "performance", """
            INSERT INTO public.performance (show_id,auditorium_id,date_time) VALUES
            (1,1,'2025-02-02 10:30:00'),
            (1,2,'2025-02-14 15:00:00'),
            (1,3,'2025-03-24 15:25:00'),
            (1,4,'2025-06-13 17:45:00'),
            (1,5,'2025-06-13 17:45:00'),
            (2,1,'2026-01-18 16:30:00'),
            (2,3,'2026-01-18 16:30:00'),
            (3,12,'2025-02-02 10:30:00'),
            (3,13,'2025-02-02 07:30:00'),
            (3,12,'2025-02-03 07:30:00'),
            (4,14,'2025-05-12 10:30:00'),
            (4,15,'2025-02-02 09:30:00');
        """))

                .then(insertIfEmpty(client, "spectator", """
            INSERT INTO spectator (dni, name) VALUES
            ('12345678', 'Alice'),
            ('87654321', 'Bob');
        """))

                .then(insertIfEmpty(client, "reservation", """
            INSERT INTO public.reservation (spectator_id,reservation_date,total_price,status) VALUES
            (1,'2025-04-01 10:00:00',100.0,'Confirmed'),
            (2,'2025-04-02 11:00:00',200.0,'Pending'),
            (2,'2025-04-01 10:00:00',8000.0,NULL),
            (2,'2025-02-07 14:17:23',2000.0,NULL),
            (5,'2025-02-08 12:19:00',1000.0,NULL),
            (4,'2025-02-01 11:12:43',2500.0,NULL),
            (1,'2025-02-09 20:51:36.597719',5000.0,NULL);
        """))

                .then(insertIfEmpty(client, "seat", """
            INSERT INTO seat (auditorium_id, number) VALUES
            (1, 1),
            (1, 2),
            (3, 10);
        """))

                .then(insertIfEmpty(client, "seat_price", """
            INSERT INTO seat_price (show_id, section_id, price) VALUES
            (1, 1, 150.0),
            (1, 2, 80.0),
            (2, 1, 120.0);
        """))

                .then(insertIfEmpty(client, "show_section_seat", """
            INSERT INTO show_section_seat (show_id, section_id, seat_id, seat_price_id) VALUES
            (1, 1, 1, 1),
            (1, 2, 2, 2),
            (2, 1, 3, 3);
        """))

                .then(insertIfEmpty(client, "ticket", """
            INSERT INTO ticket (reservation_id, performance_id, show_section_seat_id) VALUES
            (1, 1, 1),
            (2, 2, 3);
        """))

                .subscribe();
    }

    private Mono<Void> insertIfEmpty(DatabaseClient client, String tableName, String insertQuery) {
        return client.sql("SELECT COUNT(*) FROM " + tableName)
                .map(row -> row.get(0, Integer.class))
                .first()
                .filter(count -> count == null || count == 0)
                .flatMap(ignore -> client.sql(insertQuery).then());
    }
}
