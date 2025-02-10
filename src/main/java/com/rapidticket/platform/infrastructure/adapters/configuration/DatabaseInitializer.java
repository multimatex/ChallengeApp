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
            INSERT INTO public."location" (id,name,address,country,city) VALUES
            (2,'Madison Square Garden','4 Pennsylvania Plaza','USA','New York'),
            (1,'teatro brooklyn','Bennelong Point','Usa','NY'),
            (5,'Auditorios UdeA','transversal 2','Colombia','Medellin'),
            (4,'Teatro moderno','Calle 5 ','Colombia','Bogota');
        """))

                .then(insertIfEmpty(client, "auditorium", """
            INSERT INTO public.auditorium (id,location_id,name,is_numbered_seats) VALUES
            (1,1,'Sala 1',true),
            (2,1,'Sala 2',true),
            (4,1,'Sala 4',true),
            (5,1,'Sala 5',true),
            (12,4,'Sala 1',true),
            (14,5,'Sala 1',true),
            (15,5,'Sala 2',false),
            (3,1,'Sala 3',false),
            (13,4,'Sala 2',false);
        """))

                .then(insertIfEmpty(client, "sections", """
            INSERT INTO public.sections (id,name) VALUES
            (1,'Normal'),
            (2,'VIP'),
            (3,'Normal'),
            (4,'VIP'),
            (5,'DIAMOND'),
            (8,'General'),
            (9,'Preferencial'),
            (10,'General');
        """))

                .then(insertIfEmpty(client, "show_event", """
            INSERT INTO public.show_event (id,title,description) VALUES
            (1,'Hamlet','Obra de William Shakespeare'),
            (2,'Frankenstein','Obra de teatro'),
            (3,'Cascanueces','Ballet navideño'),
            (4,'Concierto tributo Queens','Concierto rock');
        """))

                .then(insertIfEmpty(client, "performance", """
            INSERT INTO public.performance (id,show_id,auditorium_id,date_time) VALUES
            (1,1,1,'2025-02-02 10:30:00'),
            (2,1,2,'2025-02-14 15:00:00'),
            (3,1,3,'2025-03-24 15:25:00'),
            (4,1,4,'2025-06-13 17:45:00'),
            (5,1,5,'2025-06-13 17:45:00'),
            (6,2,1,'2026-01-18 16:30:00'),
            (7,2,3,'2026-01-18 16:30:00'),
            (8,3,12,'2025-02-02 10:30:00'),
            (9,3,13,'2025-02-02 07:30:00'),
            (10,3,12,'2025-02-03 07:30:00'),
            (11,4,14,'2025-05-12 10:30:00'),
            (12,4,15,'2025-02-02 09:30:00');
        """))

                .then(insertIfEmpty(client, "spectator", """
            INSERT INTO public.spectator (id,dni,name) VALUES
            (2,'87654321','Bob'),
            (1,'12345678','Alex'),
            (3,'1122334455','Alice'),
            (4,'2233445566','Rick'),
            (5,'778899001','Martin');
        """))

                .then(insertIfEmpty(client, "reservation", """
            INSERT INTO public.reservation (id,spectator_id,reservation_date,total_price,status) VALUES
            (1,1,'2025-04-01 10:00:00',100.0,'Confirmed'),
            (2,2,'2025-04-02 11:00:00',200.0,'Pending'),
            (4,2,'2025-04-01 10:00:00',8000.0,NULL),
            (3,2,'2025-02-07 14:17:23',2000.0,NULL),
            (6,5,'2025-02-08 12:19:00',1000.0,NULL),
            (7,4,'2025-02-01 11:12:43',2500.0,NULL),
            (8,1,'2025-02-09 20:51:36.597719',5000.0,NULL);
        """))

                .then(insertIfEmpty(client, "seat", """
            INSERT INTO public.seat (id,auditorium_id,"number") VALUES
            (1,1,1),
            (2,1,2),
            (3,1,3),
            (4,1,4),
            (5,1,5),
            (6,1,6),
            (7,2,1),
            (8,2,2),
            (9,2,3),
            (10,2,4),
            (11,2,5),
            (12,2,6),
            (13,3,NULL),
            (14,3,NULL),
            (15,3,NULL),
            (16,3,NULL),
            (17,3,NULL),
            (18,3,NULL),
            (19,4,1),
            (20,4,2),
            (21,4,3),
            (22,4,4),
            (23,5,1),
            (24,5,2),
            (25,5,3),
            (26,5,4),
            (27,5,5),
            (28,5,6),
            (50,12,1),
            (51,12,2),
            (52,12,3),
            (53,12,4),
            (54,12,5),
            (55,12,6),
            (56,13,NULL),
            (57,13,NULL),
            (58,13,NULL),
            (59,13,NULL),
            (60,13,NULL),
            (61,13,NULL),
            (62,14,1),
            (63,14,2),
            (64,14,3),
            (65,15,NULL),
            (66,15,NULL),
            (67,15,NULL);
        """))

                .then(insertIfEmpty(client, "seat_price", """
            INSERT INTO public.seat_price (id,show_id,section_id,price) VALUES
            (1,1,1,1000.0),
            (2,1,2,2500.0),
            (4,2,4,10000.0),
            (5,2,5,50000.0),
            (3,2,3,4000.0),
            (6,3,8,10000.0),
            (7,3,9,30000.0),
            (8,4,10,60000.0);
        """))

                .then(insertIfEmpty(client, "show_section_seat", """
             INSERT INTO public.show_section_seat (id,show_id,section_id,seat_id,seat_price_id) VALUES
             (1,1,1,1,1),
             (2,1,1,2,1),
             (3,1,1,3,1),
             (4,1,2,4,2),
             (5,1,2,5,2),
             (6,1,2,6,2),
             (7,1,1,7,1),
             (8,1,1,8,1),
             (9,1,1,9,1),
             (10,1,2,10,2),
             (11,1,2,11,2),
             (12,1,2,12,2),
             (13,1,1,13,1),
             (14,1,1,14,1),
             (15,1,1,15,1),
             (16,1,2,16,2),
             (17,1,2,17,2),
             (18,1,2,18,2),
             (19,1,1,19,1),
             (20,1,1,20,1),
             (21,1,2,21,2),
             (22,1,2,22,2),
             (23,1,1,23,1),
             (24,1,1,24,1),
             (25,1,1,25,1),
             (26,1,1,26,1),
             (27,1,2,27,2),
             (28,1,2,28,2),
             (29,3,8,50,6),
             (30,2,3,1,3),
             (31,2,3,2,3),
             (32,2,4,3,4),
             (33,2,4,4,4),
             (34,2,5,5,5),
             (35,2,5,6,5),
             (36,2,3,13,3),
             (37,2,3,14,3),
             (38,2,4,15,4),
             (39,2,4,16,4),
             (40,2,5,17,5),
             (41,2,5,18,5),
             (42,3,8,51,6),
             (43,3,8,52,6),
             (45,3,9,54,7),
             (46,3,9,55,7),
             (44,3,9,53,7),
             (47,3,8,56,6),
             (48,3,8,57,6),
             (49,3,8,58,6),
             (50,3,9,59,7),
             (51,3,9,60,7),
             (52,3,9,61,7),
             (53,3,8,50,6),
             (54,3,8,51,6),
             (55,3,8,52,6),
             (56,3,9,53,7),
             (57,3,9,54,7),
             (58,3,9,55,7),
             (59,4,10,62,8),
             (60,4,10,63,8),
             (61,4,10,64,8),
             (62,4,10,65,8),
             (63,4,10,66,8),
             (64,4,10,67,8);
        """))

                .then(insertIfEmpty(client, "ticket", """
            INSERT INTO public.ticket (id,reservation_id,performance_id,show_section_seat_id) VALUES
            (1,1,1,1),
            (2,1,1,5),
            (3,3,2,7),
            (4,3,2,8),
            (5,6,2,9),
            (6,7,2,12),
            (7,8,1,5),
            (8,8,1,6);
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
